package solutions.laxmi.lsnoti.internal

import androidx.compose.animation.core.*
import androidx.compose.animation.*

internal fun bounceIn(): EnterTransition {
    return scaleIn(
        initialScale = 0.5f,
        animationSpec = keyframes {
            durationMillis = 500
            1.1f at 200
            0.95f at 350
            1.0f at 500
        }
    ) + fadeIn(animationSpec = tween(500))
}
