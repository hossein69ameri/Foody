
# Foody
The application will work with `local` and `remote` data sources. And for the remote data source, a third-party API for food recipes, called [Spoonacular API](https://spoonacular.com/food-api) , was used. I am going to teach you how to establish a connection between your application and the server. So basically we will send a `GET request` to our `API`  and receive a list of food recipes as a response. Also, the application will not be able to handle any internet connection and store or cache the data locally in the database when needed.

Also from the application we can search recipes directly from our API. So if you have your favorite flavor, you can search for it here and you will probably find a lot of amazing recipes. It will also have Favorites Fragment where we can save our favorite recipes to use later when we need them. And finally the app will have its fun side, and that's a food joke snippet.
# Screen Shot
<img src="https://user-images.githubusercontent.com/103646893/212546482-971c2f83-d528-4928-8923-08b10b10e191.png" data-canonical-src="https://gyazo.com/eb5c5741b6a9a16c692170a41a49c858.png" width="200" height="400" /> | <img src="https://user-images.githubusercontent.com/103646893/212546497-ce04e86d-6194-4747-8a41-ef0cf9d4d3be.png" data-canonical-src="https://gyazo.com/eb5c5741b6a9a16c692170a41a49c858.png" width="200" height="400" /> | <img src="https://user-images.githubusercontent.com/103646893/212546516-a930550a-0c6a-4fde-bc4a-649763690086.png" data-canonical-src="https://gyazo.com/eb5c5741b6a9a16c692170a41a49c858.png" width="200" height="400" />
<img src="https://user-images.githubusercontent.com/103646893/212546506-4b910470-e281-49f7-a7d4-18d39fceb917.png" data-canonical-src="https://gyazo.com/eb5c5741b6a9a16c692170a41a49c858.png" width="200" height="400" /> | <img src="https://user-images.githubusercontent.com/103646893/212546489-b038ba32-6479-4c8f-8afd-1eb699388332.png" data-canonical-src="https://gyazo.com/eb5c5741b6a9a16c692170a41a49c858.png" width="200" height="400" />

# Features
100% Kotlin

MVVM architecture

Android Architecture Components

Kotlin Coroutines + Flow

LiveData

Single activity pattern

Offline Cache

REST API

Motion Layout

ViewPager2



Dependency injection

# Built with
[Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.

[LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.

[DataStore](https://developer.android.com/topic/libraries/architecture/datastore) - Jetpack DataStore is a data storage solution that allows you to store key-value pairs or typed objects with protocol buffers

[Retrofit](https://square.github.io/retrofit/) + [OkHttp](https://square.github.io/okhttp/) - RESTful API and networking client.

[Hilt](https://dagger.dev/hilt/)  - Dependency injection.

[ViewBinding](https://developer.android.com/topic/libraries/view-binding)  - Dependency injection.

[Android Architecture Components](https://developer.android.com/topic/libraries/architecture)  - A collections of libraries that help you design rebust, testable and maintainable apps.

[ViewModel](https://developer.android.com/reference/androidx/lifecycle/ViewModel)  - UI related data holder, lifecycle aware.

[Navigation component](https://developer.android.com/guide/navigation)  -  Fragment routing handler

[Coroutines](https://developer.android.com/kotlin/coroutines) - Concurrency design pattern for asynchronous programming.

[Flow](https://developer.android.com/kotlin/flow) - Stream of value that returns from suspend function.

[Coil](https://github.com/coil-kt/coil) - Image loading

# Architectures
![alt - Github](https://raw.githubusercontent.com/amitshekhariitbhu/MVVM-Architecture-Android/master/assets/mvvm-arch.png)

**View** : Activity/Fragment with UI-specific logics only.

**ViewModel** : It keeps the logic away from View layer, provides data streams for UI and handle user interactions

**Model** :  Repository pattern, data layers that provide interface to manipulate data from both the local and remote data sources. The local data sources will serve as single source of truth

# Contact
Have an project? DM me at

hossein.arabameri69@gmail.com
