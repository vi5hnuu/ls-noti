package solutions.laxmi.lsnoti.internal

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import solutions.laxmi.lsnoti.LsSnackbarConfig

import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import solutions.laxmi.lsnoti.LsSnackbarHostState
import solutions.laxmi.lsnoti.model.CancellationType
import solutions.laxmi.lsnoti.model.SnackbarCancellation

@Composable
internal fun SwipeDismissSnackbar(
    config: LsSnackbarConfig,
    onDismiss: (SnackbarCancellation) -> Unit,
    modifier: Modifier = Modifier,
) {
    val scope= rememberCoroutineScope();
    val dismissState = rememberSwipeToDismissBoxState(
        positionalThreshold = { it * 0.5f }, // 50% of its width
        confirmValueChange = {value->
            if(value=== SwipeToDismissBoxValue.Settled) false;
            else {
                onDismiss(SnackbarCancellation(id=config.id, type = CancellationType.SWIPE))
                true
            }
        }
    )

    LaunchedEffect(config.id) {
        launch {
            LsSnackbarHostState.dismissEmitter.collect { reason ->
                if (reason.id == config.id && reason.type!== CancellationType.SWIPE) {
                    dismissState.dismiss(SwipeToDismissBoxValue.StartToEnd)
                }
            }
        }

        launch {
            delay(config.durationMillis)
            onDismiss(SnackbarCancellation(id=config.id, type = CancellationType.TIMEOUT))
        }
    }

    SwipeToDismissBox(
        state = dismissState,
        backgroundContent = {}, // No background layer behind snackbar
        modifier = Modifier
            .then(modifier),
    ) {
        LsSnackbar(config = config){
            scope.launch {
                onDismiss(SnackbarCancellation(id=config.id, type = CancellationType.ACTION))
            }
        }
    }
}
