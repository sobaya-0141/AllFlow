package sobaya.example.allflow.util

import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.asFlow

class Dispatcher {
    private val testProcessor = BroadcastChannel<String>(Channel.BUFFERED)
    val testFlow = testProcessor.asFlow()
    suspend fun testSend(args: String) = testProcessor.send(args)
}
