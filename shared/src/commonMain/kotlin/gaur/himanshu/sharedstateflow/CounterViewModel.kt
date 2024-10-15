package gaur.himanshu.sharedstateflow

import androidx.lifecycle.ViewModel
import gaur.himanshu.sharedstateflow.flow.common
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CounterViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState  = _uiState.asStateFlow().common()

    fun updateCounter() = _uiState.update { it.copy(it.counter.plus(1)) }

}

data class UiState(
    val counter: Int = 0
)