package solutions.laxmi.lsnoti

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun LsSnackbarHost(
    state: LsSnackbarHostState,
    modifier: Modifier = Modifier,
    snackbar: @Composable (SnackbarData, LsSnackbarConfig) -> Unit = { data, config ->
        LsSnackbar(data, config)
    }
) {
    Box(
        modifier =  Modifier
            .fillMaxSize()
            .then(modifier),
        contentAlignment = when (state._lastShownConfig.position) {
            LsSnackbarPosition.TopStart -> Alignment.TopStart
            LsSnackbarPosition.TopCenter -> Alignment.TopCenter
            LsSnackbarPosition.TopEnd -> Alignment.TopEnd
            LsSnackbarPosition.Center -> Alignment.Center
            LsSnackbarPosition.BottomStart -> Alignment.BottomStart
            LsSnackbarPosition.BottomCenter -> Alignment.BottomCenter
            LsSnackbarPosition.BottomEnd -> Alignment.BottomEnd
        }
    ) {
        SnackbarHost(
            hostState = state.internalState,
            modifier = modifier,
            snackbar = { data -> snackbar(data, state._lastShownConfig) }
        )
    }
}

