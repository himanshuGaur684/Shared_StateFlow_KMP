package gaur.himanshu.sharedstateflow.flows

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

internal fun <T:Any> StateFlow<T>.common():CommonStateFlow<T> = CommonStateFlow<T>(this)

class CommonCancellable<T>(onCancel: () -> Unit)

class CommonStateFlow<T>(
    private val flow: StateFlow<T>
) : StateFlow<T> {
    override val replayCache: List<T>
        get() = flow.replayCache
    override val value: T
        get() = flow.value

    override suspend fun collect(collector: FlowCollector<T>): Nothing {
       flow.collect(collector)
    }

    fun startCollection(
        onEach: (T) -> Unit,
        onCancel: () -> Unit
    ): CommonCancellable<T> {
        val collectionScope = CoroutineScope(Dispatchers.Main)
        val collectionJob = collectionScope.launch {
            try {
                collect(collector = { onEach(it) })
            } catch (e: Exception) {
                onCancel.invoke()
                throw Exception()
            }
        }
        return CommonCancellable { collectionJob.cancel() }
    }
}