package solutions.laxmi.lsnoti

import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Stable
class LsSnackbarHostState internal constructor() {
    private val _messages = mutableStateListOf<Pair<String, LsSnackbarConfig>>()
    val messages: List<Pair<String, LsSnackbarConfig>> get() = _messages

    fun show(
        message: String,
        config: LsSnackbarConfig = LsSnackbarConfig()
    ) {
        _messages.add(message to config)
        // Auto-remove after duration
        val duration = config.durationMillis
        CoroutineScope(Dispatchers.Main).launch {
            delay(duration.toLong())
//            _messages.remove(message to config)
        }
    }

    fun dismiss(index: Int) {
        if (index in messages.indices) {
            _messages.removeAt(index)
        }
    }
}

