package solutions.laxmi.lsnoti

import androidx.compose.animation.core.*
import androidx.compose.animation.*
import androidx.compose.runtime.*

fun bounceIn(): EnterTransition {
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
