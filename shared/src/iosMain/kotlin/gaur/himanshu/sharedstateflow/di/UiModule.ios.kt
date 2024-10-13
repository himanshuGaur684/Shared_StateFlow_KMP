package gaur.himanshu.sharedstateflow.di

import gaur.himanshu.sharedstateflow.CounterViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.module.Module
import org.koin.dsl.module

private val viewModelModules = module {
    single { CounterViewModel() }
}

actual fun sharedViewModelsModule(): Module = viewModelModules

object ProvideViewModel : KoinComponent {

    fun getCounterViewModel() = get<CounterViewModel>()

}