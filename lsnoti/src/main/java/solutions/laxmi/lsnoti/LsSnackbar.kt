package solutions.laxmi.lsnoti

import android.R.interpolator.bounce
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LsSnackbar(
    data: SnackbarData,
    config: LsSnackbarConfig
) {
    val bgColor = when (config.type) {
        LsSnackbarType.Success -> Color(0xFF388E3C)
        LsSnackbarType.Error -> Color(0xFFD32F2F)
        LsSnackbarType.Warning -> Color(0xFFFFA000)
        LsSnackbarType.Info -> Color(0xFF1976D2)
    }

    val modifier = Modifier
        .padding(12.dp)
        .fillMaxWidth(0.7f) // ✅ 70% width by default
        .wrapContentHeight()

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Text(
            text = data.visuals.message,
            modifier = Modifier.padding(16.dp),
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

