# CurrencyConverter
This app helps to convert one form of currency into another form of currency based on the current exchange rate.
This app faciliates easy conversion of currencies and will be accurate as it updates the exchange rate every 10 seconds.

#### Breif Description about app
- when the user types number in the `from` field or `changing either of the dropdown` will resulted in updating the currency calculation and Unit price info on top of the section. plese refer the #fig1.
- The Api(https://api.exchangeratesapi.io/latest) which we used to fetch the currency value is returning the currency value based on `EUR` by default. But to give a better experience I've made some calculation and giving the proper conversion between the other currencies.
- The Api(https://api.exchangeratesapi.io/latest) is not returning the last fetched time.So I'm calculating this time locally and updating to the user.
- Currency data refreshing every 15s. (RXjava interval method and API calling peforming in the background and upon the success call posting the value back to the main thread.)
- Screen Orientation supportted.


![Currency App](https://user-images.githubusercontent.com/31012185/87347925-896af880-c586-11ea-81a1-feb7823e192d.gif)
*Fig. 1: Demonstrating App*

![Error Cases](https://user-images.githubusercontent.com/31012185/87348303-157d2000-c587-11ea-920e-0962f1eb0f3b.gif)
*Fig. 2: Demonstrating Error Cases*


# Built With
  Kotlin

# Architectural Pattern
- Used MVVM as the code can be easily reused and binding makes UI updates easier to handle. 
- This architecture makes the code more modular so maintainability of code in future will be more easy
- `LiveData` - **LiveData** to update the UI automatically when the data updates. We Used LiveData extensively to communicate between view and viewmodel. Whenever the API call is success or currecny conversion is success it will update the UI automatically according to our design.



#  Data Binding & 2 Way data Binding
- I've followed the data binding approach to bind the views with classes and view models,so that I can perform the UI actions with less code and directly from viewmodel.We have extensively used data binding in this application wherever it's possible. 
- I also used the 2 way binding for the Edittexts and Spinners to improve app's efficiency. 
- My Activity class and Spinner Adapter looks very clean.
- `Binding Adapters` and `Inverse Binding Adapters` used wherever it's possible, especially in setting the `double' type value to edittext. I'm happy that I got very good understanding about this after the completion of this project. 
    
# Other Important Libraries Used:
- `Retrofit` - For networking calls
- `RxJava` - Used to manage Asynchronous operations also to communicate between repository and view model.
- `Glide` - To Render the Image.
 # Packages in the App:
    - Adapters- Binding adapters and inverse binding adapters, Spinner Adapter.
    - Data - Retrofit API related class and repository classes under sub repositroy project.
    - Model - Model classes and kotlin data classes
    - UI - Views and Viewmodels
    - Utils - Date utils, Constants and Alert Classes.
    
# Supported version
Android 19(4.4)

# Further Enhancements(Improvements that could be done to this App)
- Since this app has single screen I din't add the base classes. In future if this app is developed with multiple features we can implement this.
- Currently there is a fluctuation in the UI when the keyboard is disappear/dismissing after pushing the views up. `android:windowSoftInputMode=adjustResize|stateAlwaysHidden` causing the issue. I didn't pay much attention to resolve this at the moment.
- We can use `Coroutines` and remove the RxJava. 


