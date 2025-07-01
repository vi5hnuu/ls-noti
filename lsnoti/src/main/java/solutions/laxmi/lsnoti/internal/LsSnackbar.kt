package solutions.laxmi.lsnoti.internal

import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import solutions.laxmi.lsnoti.LsSnackbarConfig
import solutions.laxmi.lsnoti.LsSnackbarType
import solutions.laxmi.lsnoti.R

@Composable
internal fun LsSnackbar(
    config: LsSnackbarConfig,
    backgroundColor: Color?=null,
    iconColor: Color?=null,
    textColor: Color?=null,
    @DrawableRes() iconRes: Int?=null,
    modifier: Modifier = Modifier,
    onClose:()-> Unit={}
) {
    val bgColor = backgroundColor ?: when (config.type) {
        LsSnackbarType.Success -> Color(0xFF388E3C)
        LsSnackbarType.Error -> Color(0xFFD32F2F)
        LsSnackbarType.Warning -> Color(0xFFFFA000)
        LsSnackbarType.Info -> Color(0xFF1976D2)
    }

    val iconColor = iconColor ?: when (config.type) {
        LsSnackbarType.Success -> Color.White
        LsSnackbarType.Error -> Color.White
        LsSnackbarType.Warning -> Color.White
        LsSnackbarType.Info -> Color.White
    }

    val textColor = textColor ?: when (config.type) {
        LsSnackbarType.Success -> Color.White
        LsSnackbarType.Error -> Color.White
        LsSnackbarType.Warning -> Color.White
        LsSnackbarType.Info -> Color.White
    }

    val icon=iconRes ?: when (config.type) {
        LsSnackbarType.Success -> R.drawable.ls_success
        LsSnackbarType.Error -> R.drawable.ls_dangerous
        LsSnackbarType.Warning -> R.drawable.ls_warning
        LsSnackbarType.Info -> R.drawable.ls_info
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight().then(modifier),
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 6.dp,vertical = 12.dp),verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            IconDrawable(icon = icon, modifier = Modifier.size(28.dp), tint = iconColor)
            Text(
                text = config.message,
                color = textColor,
                style = MaterialTheme.typography.bodySmall,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                softWrap = true,
                textAlign = TextAlign.Justify,
                modifier= Modifier.weight(1f)
            )
            IconDrawable(icon = R.drawable.ls_close,
                modifier = Modifier.size(28.dp)
                    .clip(CircleShape)
                    .clickable(true){
                    onClose()
                }, tint = iconColor)
        }
    }
}

