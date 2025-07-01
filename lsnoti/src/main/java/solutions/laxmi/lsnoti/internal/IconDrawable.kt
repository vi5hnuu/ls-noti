package solutions.laxmi.lsnoti.internal

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
internal fun IconDrawable(modifier: Modifier= Modifier, tint: Color=Color.Gray, @DrawableRes icon:Int, description: String?=null) {
    Icon(
        painter = painterResource(id = icon),
        contentDescription = description,
        modifier = modifier.size(18.dp),
        tint = tint
    )
}