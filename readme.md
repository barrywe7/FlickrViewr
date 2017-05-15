# Flickr Recruitment Exercise App
The Flickr Viewr app was undertaken as part of a recruitment exercise
## Running, Building & Testing Flickr Viewr
 - Clone the GitHub repository
 - Import the Flickr Viewr project into Android Studio (it was developed using version 2.3.2)
 - Run using a device or emulator at API level 21 or above
 - Unit tests can be run from within Android Studio
 - There are no instrumentation tests at present
## Code Architecture
 - The app is a standard single Activity Android project containing a Fragment to display the content (using a Fragment allows the possibility to have a master-detail view for tablets).
 - The content and position of the recyclerview is saved to the instance state when the fragment is recreated to save unnecessary calls to the network when performing rotations or restoring the activity.
 - Differing content is shown in portrait and landscape mode.
 - An (unthemed) swipe to refresh layout allows new data to be returned from the network.
 - The Fragment contains a RecyclerView to display the photos. 
 - When the fragment is created the MainPresenter makes use of an Interactor to instigate a call to the Flickr API on a background thread using RxJava and Retrofit 2. 
 - The data returned from the service is then mapped from the Gson annotated POJOs to a list of parcelable objects for local use by the UI elements.
 - This data is then mapped in the adapter using data binding.
 - Picasso is used to load the images supplied by FlickrAPI. This also uses an LRU cache to store the images. 
 - The Android data binding library is used to bind the views. Dagger 2 is used for Dependency Injection.
### Design patterns used
 - MVP (but also confusingly MVVM). Most companies use MVP because it is more established but I prefer MVVM. Normally only one of the two would be used.
 - Dependency Injection
 - Builder pattern
### Libraries used
 - RxJava
 - Dagger 2
 - Retrofit 2
 - Picasso
 - OkHttp 3
 - Android support library (design and cardview)

## Future Enhancements:
 - Allow ability to search for images by tag
 - Allow ability to order by date taken or date published
 - Allow ability to save image locally
 - Allow ability to open image in a browser
 - Allow ability to share image (via email or other)
 - Add more comprehensive unit tests
 - Implement caching of network responses
 - Implement better tablet support (master detail view)
 - Implement analytics
 - Add Night/Day themes
 - Introduce pagination of responses
 - Add translations