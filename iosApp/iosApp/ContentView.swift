import SwiftUI
import shared

struct ContentView: View {
	let greet = Greeting().greet()

    @ObservedObject var viewModelWrapper  = CounterViewModelWrapper()
    
 
	var body: some View {
        VStack{
            
            Text(String(viewModelWrapper.uiState.counter))
 
            Button(action: {
                viewModelWrapper.viewModel.updateCounter()
            }
                   , label: {
                Text("Update Counter")
            })
            
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
