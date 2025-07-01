package solutions.laxmi.lsnoti.internal

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import solutions.laxmi.lsnoti.LsSnackbarConfig
import kotlin.math.abs

@Composable
internal fun SwipeDismissSnackbar(
    config: LsSnackbarConfig,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    val swipeOffsetX = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val threshold = with(LocalDensity.current) { (screenWidth * 0.3f).toPx() }


    Box(
        modifier = Modifier
            .offset { IntOffset(swipeOffsetX.value.toInt(), 0) }
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {
                        scope.launch {
                            if (abs(swipeOffsetX.value) > threshold) {
                                // Animate out of screen
                                val targetX = if (swipeOffsetX.value > 0) 1000f else -1000f
                                swipeOffsetX.animateTo(targetX, animationSpec = tween(durationMillis = 300))
                                onDismiss()
                            } else {
                                swipeOffsetX.animateTo(0f, animationSpec = tween(durationMillis = 300))
                            }
                        }
                    },
                    onDrag = { _, dragAmount ->
                        scope.launch {
                            swipeOffsetX.snapTo(swipeOffsetX.value + dragAmount.x)
                        }
                    }
                )
            }
    ) {
        LsSnackbar(config = config, modifier = modifier)
    }
}
