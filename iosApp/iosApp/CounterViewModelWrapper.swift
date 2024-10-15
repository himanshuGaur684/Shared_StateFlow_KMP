//
//  CounterViewModelWrapper.swift
//  iosApp
//
//  Created by Himanshu Gaur on 14/10/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

class CounterViewModelWrapper : ObservableObject{
    
    
    
}

func collect<T>(stateFlow: CommonStateFlow<T>,onEach: @escaping (T)->Void){
    var continuation: CheckedContinuation<Void,Never>?
    
    let cancellable = stateFlow.startCollection(onEach:{value in onEach(value!)},onCancel:{continuation?.resume()})
}
