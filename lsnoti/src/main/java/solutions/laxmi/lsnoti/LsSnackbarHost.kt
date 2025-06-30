package solutions.laxmi.lsnoti

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex


import androidx.compose.foundation.layout.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@Composable
fun LsSnackbarHost(
    state: LsSnackbarHostState,
    modifier: Modifier = Modifier,
    maxStackHeightFraction: Float = 0.7f,
    overlapPx: Int = 12
) {
    val stack = state.messages
    val config = stack.lastOrNull()?.second ?: LsSnackbarConfig()
    val scrollState = rememberScrollState()

    val alignment = when (config.position) {
        LsSnackbarPosition.TopStart -> Alignment.TopStart
        LsSnackbarPosition.TopCenter -> Alignment.TopCenter
        LsSnackbarPosition.TopEnd -> Alignment.TopEnd
        LsSnackbarPosition.Center -> Alignment.Center
        LsSnackbarPosition.BottomStart -> Alignment.BottomStart
        LsSnackbarPosition.BottomCenter -> Alignment.BottomCenter
        LsSnackbarPosition.BottomEnd -> Alignment.BottomEnd
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
        contentAlignment = alignment
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .fillMaxHeight(maxStackHeightFraction)
                .padding(8.dp)
                .border(width = 1.dp, color = Color.Transparent,shape = RoundedCornerShape(8.dp))
                .clip(RoundedCornerShape(6.dp))
                .verticalScroll(state=scrollState, reverseScrolling = true)
        ) {
            Layout(
                content = {
                    stack.forEachIndexed { index, (message, cfg) ->
                        LsSnackbar(
                            message = message,
                            config = cfg,
                            modifier = Modifier
                                .shadow(elevation = 4.dp)
                                .zIndex((index).toFloat())
                        )
                    }
                },
                modifier = Modifier,
            ) { measurables, constraints ->
                val placeables = measurables.map { it.measure(constraints) }
                val totalHeight = placeables.sumOf { it.height } - (placeables.size-1)*overlapPx

                layout(constraints.maxWidth, totalHeight) {
                    var y = 0
                    placeables.forEachIndexed { _, placeable ->
                        placeable.placeRelative(0, y)
                        y += (placeable.height-overlapPx)
                    }
                }
            }
        }
    }
}
