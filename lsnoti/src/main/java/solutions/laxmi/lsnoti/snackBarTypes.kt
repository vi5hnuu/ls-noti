package solutions.laxmi.lsnoti

enum class LsSnackbarType { Success, Error, Warning, Info }

enum class LsSnackbarPosition {
    TopStart, TopCenter, TopEnd,
    Center,
    BottomStart, BottomCenter, BottomEnd
}

enum class LsSnackbarAnimation {
    Slide, Bounce // Add more later
}

data class LsSnackbarConfig(
    val type: LsSnackbarType = LsSnackbarType.Info,
    val position: LsSnackbarPosition = LsSnackbarPosition.Center,
    val animation: LsSnackbarAnimation = LsSnackbarAnimation.Bounce,
    val durationMillis: Int = 3000
)