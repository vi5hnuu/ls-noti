package solutions.laxmi.lsnoti.model

data class SnackbarCancellation(
    val id: String,
    val type:CancellationType
)

enum class CancellationType{
    SWIPE,
    ACTION,
    TIMEOUT,
}
