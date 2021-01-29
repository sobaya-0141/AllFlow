package sobaya.example.allflow.util

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class Dispatcher {
private val _testFlow = MutableSharedFlow<String>(1)
    val testFlow: SharedFlow<String> = _testFlow
    suspend fun testSend(args: String) = _testFlow.emit(args)
}
