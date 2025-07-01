package solutions.laxmi.lsnoti

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Stable
class LsSnackbarHostState internal constructor() {
    private var _messages = mutableStateListOf<LsSnackbarConfig>()
    internal val messages: List<LsSnackbarConfig> get() = _messages

    fun show(config: LsSnackbarData) {
        val config= LsSnackbarConfig.Companion.fromSnackbarData(config)
        _messages.add(config)
        // Auto-remove after duration
        val duration = config.durationMillis
        CoroutineScope(Dispatchers.Main).launch {
            delay(duration.toLong())
            dismiss(config.id)
        }
    }

    fun dismiss(id: String) {
        _messages.remove(_messages.find { it.id==id });
    }
}