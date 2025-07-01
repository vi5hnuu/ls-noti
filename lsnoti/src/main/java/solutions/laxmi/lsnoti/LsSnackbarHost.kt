package solutions.laxmi.lsnoti

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex


import androidx.compose.foundation.layout.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalConfiguration
import solutions.laxmi.lsnoti.LsSnackbarHostState
import solutions.laxmi.lsnoti.internal.SwipeDismissSnackbar

@Composable
fun LsSnackbarHost(
    state: LsSnackbarHostState,
    modifier: Modifier = Modifier,
    maxStackHeightFraction: Float = 0.7f,
    position: LsSnacksPosition = LsSnacksPosition.TopEnd,
    overlapPx: Int = 12
) {
    val stack = state.messages
    val scrollState = rememberScrollState()

    val alignment = when (position) {
        LsSnacksPosition.TopStart -> Alignment.TopStart
        LsSnacksPosition.TopCenter -> Alignment.TopCenter
        LsSnacksPosition.TopEnd -> Alignment.TopEnd
        LsSnacksPosition.Center -> Alignment.Center
        LsSnacksPosition.BottomStart -> Alignment.BottomStart
        LsSnacksPosition.BottomCenter -> Alignment.BottomCenter
        LsSnacksPosition.BottomEnd -> Alignment.BottomEnd
    }

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
        contentAlignment = alignment
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .sizeIn(minHeight=0.dp, maxHeight = screenHeight*maxStackHeightFraction)
                .padding(8.dp)
                .border(width = 1.dp, color = Color.Transparent,shape = RoundedCornerShape(8.dp))
                .clip(RoundedCornerShape(6.dp))
                .verticalScroll(state=scrollState, reverseScrolling = true)
        ) {
            Layout(
                content = {
                    stack.forEachIndexed { index, cfg ->
                        SwipeDismissSnackbar(
                            config = cfg,
                            modifier = Modifier
                                .shadow(elevation = 4.dp)
                                .zIndex((index).toFloat()),
                            onDismiss = { state.dismiss(cfg.id) },
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
