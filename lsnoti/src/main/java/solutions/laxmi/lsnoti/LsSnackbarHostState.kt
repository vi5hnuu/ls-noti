package solutions.laxmi.lsnoti

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import solutions.laxmi.lsnoti.model.SnackbarCancellation

@Stable
class LsSnackbarHostState internal constructor() {
    private var _messages = mutableStateListOf<LsSnackbarConfig>()
    internal val messages: List<LsSnackbarConfig> get() = _messages

    internal companion object{
        private val _dismissEmitter = MutableSharedFlow<SnackbarCancellation>(
            replay = 0,             // 🚫 no past values
            extraBufferCapacity = Int.MAX_VALUE,
            onBufferOverflow =BufferOverflow.SUSPEND
        )
        val dismissEmitter = _dismissEmitter.asSharedFlow()
    }

    fun show(config: LsSnackbarData) {
        val config= LsSnackbarConfig.Companion.fromSnackbarData(config)
        _messages.add(config)
    }

    fun dismiss(reason: SnackbarCancellation) {
       if(!_dismissEmitter.tryEmit(reason)){
           val x=10;
       }
    }

    fun removeMessage(id: String){
        _messages.remove(_messages.find { it.id==id });
    }
}