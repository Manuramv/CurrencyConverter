# CurrencyConverter
This app helps to convert one form of currency into another forn of currency based on the current exchange rate.
This app faciliates easy conversion of currencies and will be accurate as it updates the exchange rate every 10 seconds.

```sh
During the development of this application I want to ensure that I'm using the latest techniques.
```
# Built With
  Kotlin

# Architectural Pattern
I took a deap breath before start developing this applicastion because I have to complete this app within one or two days. So if I'm not following any architecture strictly I can finish this one very fast. But I don't ant to do a bad approach so I chose the MVVM pattern.

  ## MVVM Pattern 
    - Our currency converter app follows the MVVM pattern
#  Data Binding & 2 Way data Binding
- We've followed the data bindign approach to bind the views with classes and view models. So that I can perform the UI actions with less code and directly from viewmodel.We have extensively used data binding in this appliation wherever it's possible. 
- I also used the 2 way binding for the Edittexts and Spinners even though it was quite difficult. 
    
  

# Important Libraries Used:
    * Retrofit - For networking calls
    * RxJava - To 
 # Packages in the App:
    *Adapters- Binding adapters and inverse binding adapters, Spinner Adapter.
    *Data - Retrofit API related class and repository classes under sub repositroy project.
    *Model - Model classes and kotlin data classes
    *UI - Views and Viewmodels
    *Utils - Date utils, Constants and Alert Classes.
    
#Supported version
Android 19


