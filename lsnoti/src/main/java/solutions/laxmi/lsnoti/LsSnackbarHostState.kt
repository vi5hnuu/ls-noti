package solutions.laxmi.lsnoti

import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Stable

@Stable
class LsSnackbarHostState internal constructor(
    internal val internalState: SnackbarHostState
) {
    suspend fun show(
        message: String,
        config: LsSnackbarConfig = LsSnackbarConfig()
    ): SnackbarResult {
        _lastShownConfig = config
        return internalState.showSnackbar(message)
    }

    internal var _lastShownConfig: LsSnackbarConfig = LsSnackbarConfig()
}

