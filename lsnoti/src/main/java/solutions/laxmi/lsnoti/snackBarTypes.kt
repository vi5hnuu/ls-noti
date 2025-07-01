package solutions.laxmi.lsnoti

import java.util.UUID

enum class LsSnackbarType { Success, Error, Warning, Info }

enum class LsSnacksPosition {
    TopStart, TopCenter, TopEnd,
    Center, CenterEnd, CenterStart,
    BottomStart, BottomCenter, BottomEnd
}

enum class LsSnackbarAnimation {
    Slide, Bounce // Add more later
}

open class LsSnackbarData(
    open val message: String,
    open val type: LsSnackbarType = LsSnackbarType.Info,
    open val animation: LsSnackbarAnimation = LsSnackbarAnimation.Bounce,
    open val durationMillis: Long = 3000
)

internal class LsSnackbarConfig : LsSnackbarData {
    val id: String

    constructor(
        id: String,
        message: String,
        type: LsSnackbarType = LsSnackbarType.Info,
        animation: LsSnackbarAnimation = LsSnackbarAnimation.Bounce,
        durationMillis: Long = 3000
    ) : super(message, type, animation, durationMillis) {
        this.id = id
    }

    companion object{
        fun fromSnackbarData(data: LsSnackbarData):LsSnackbarConfig{
            return LsSnackbarConfig(id= UUID.randomUUID().toString(), message = data.message,type=data.type, animation = data.animation, durationMillis =data.durationMillis);
        }
    }
}
