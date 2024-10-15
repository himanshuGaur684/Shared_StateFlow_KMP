//
//  CounterViewModelWrapper.swift
//  iosApp
//
//  Created by Himanshu Gaur on 15/10/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

class CounterViewModelWrapper : ObservableObject{
    
    var viewModel : CounterViewModel
    
    
    var task : Task<Void,Never>?
    
    @Published var uiState : UiState = UiState.init(counter: 0)
    
    init(){
        viewModel = ProvideViewModel.shared.getCounterViewModel()
       task =  Task{ @MainActor [weak self] in
            
            await collect(flow: self!.viewModel.uiState, onEach:{value in
                self!.uiState = value
            })
            
        }
        
    }
    
    deinit{
        task?.cancel()
    }

}

func collect<T>( flow:CommonStateFlow<T> , onEach : @escaping (T)-> Void) async {
    
    var countinuation : CheckedContinuation<Void,Never>?
    
    let cancellable  =  flow.startCollection(onEach: {value in onEach(value!)}) {
        countinuation?.resume()
    }
    
    await withTaskCancellationHandler(operation: {
        await withCheckedContinuation{value in countinuation = value}
    }, onCancel: {
        cancellable.onCancel()
    })
    
    
}


