package solutions.laxmi.lsnoti

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

/**
 * Remembers an instance of [LsSnackbarHostState] which wraps an internal [SnackbarHostState].
 * This is your entry point to trigger custom snackbars from anywhere in your app.
 */
@Composable
fun rememberLsSnackbarHostState(): LsSnackbarHostState {
    return remember { LsSnackbarHostState() }
}