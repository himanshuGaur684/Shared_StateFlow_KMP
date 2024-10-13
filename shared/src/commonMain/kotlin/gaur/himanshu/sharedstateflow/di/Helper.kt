package gaur.himanshu.sharedstateflow.di

import org.koin.core.context.startKoin

fun initKoin(){
    startKoin {
        modules(sharedViewModelsModule())
    }
}