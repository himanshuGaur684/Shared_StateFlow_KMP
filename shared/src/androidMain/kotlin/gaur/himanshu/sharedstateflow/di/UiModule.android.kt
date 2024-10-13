package gaur.himanshu.sharedstateflow.di

import gaur.himanshu.sharedstateflow.CounterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

private val viewModelsModule = module {
    viewModel{CounterViewModel()}
}

actual fun sharedViewModelsModule(): Module = viewModelsModule