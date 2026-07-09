# Movie Android App

## Overview

Movie Android App is a native Android application written in Kotlin. It allows users to register, browse movie content, view movie details, search for movies, and save favorite movies locally on the device.

The project is a mobile software engineering project. It is not a machine learning, deep learning, or data analysis project.

The app uses a remote REST API as its data source and renders the returned movie data through XML-based Android screens. It also includes local drawable assets used for the splash screen, navigation icons, and the home-page slideshow.

## Key Features

- Splash screen that routes users based on a locally stored registration state.
- Registration screen that sends user details to a remote API.
- Home screen with a local poster slideshow, genre list, and movie list.
- Movie detail screen with poster, rating, runtime, release date, summary, actors, and gallery images.
- Search screen that calls the movie search endpoint as the user types.
- Favorites flow backed by `SharedPreferences`.
- Retrofit and OkHttp networking layer with Gson JSON parsing.
- ViewModel, LiveData, repository classes, Kotlin coroutines, and ViewBinding.

## Project Highlights

- Built a Kotlin Android movie-browsing app with multiple screens and local state handling.
- Implemented REST API integration for registration, genres, movie lists, search, and movie details.
- Organized the app around feature packages for registration, home, detail, search, favorites, shared networking, and utilities.
- Used RecyclerView adapters, Glide image loading, and a slideshow component to display movie content.
- Added GitHub-ready documentation and Android-focused ignore rules for cleaner publishing.

## Data Source

This project does not include a local dataset, notebook, model, or training data.

The app uses a remote REST API configured in [`API.kt`](app/src/main/java/com/mortazavian/movie_android_app/shared_component/API.kt):

```text
https://api.cseshirazu307.ir/
```

No API documentation, dataset license, or official dataset page is included in the current project files.

### API Endpoints Used

| Purpose | Method | Endpoint | Used By |
|---|---:|---|---|
| Register user | `POST` | `/register` | `RegisterRepository` |
| Load movies | `GET` | `/genres/1/movies` | `HomeRepository` |
| Load genres | `GET` | `/genres` | `GenreRepository` |
| Load movie details | `GET` | `/movie/{movieID}` | `DetailRepository` |
| Search movies | `GET` | `/movies/1/{searchText}` | `SearchRepository` |

### Data Structure

The API responses are modeled with Kotlin data classes under `app/src/main/java/com/mortazavian/movie_android_app/**/domain/data/model/`.

| Model | Important Fields |
|---|---|
| `UserInformation` | `email`, `name`, `studentNumber`, `password` |
| `RegisterMessage` | `status`, `description`, registered user `data` |
| `Genre` | genre `id`, genre `name`, response `description`, `status` |
| `MovieItemResponse` | movie `id`, `title`, `poster`, `images`, `country`, `genres`, `imdb_rating`, `year`, pagination metadata |
| `MovieSearchResponse` | movie `id`, `title`, `poster`, `images`, `genres`, pagination metadata |
| `MovieInformationResponse` | movie title, poster, gallery images, actors, awards, country, director, genres, IMDb fields, plot, rating, release date, runtime, writer, year |
| `MovieDetails` | local favorite item with `id`, `title`, `imdbRating`, `runtime`, `released`, `plot`, `actors`, `poster`, `images` |

### Dataset Notes

- Dataset name: Not specified in the current project files.
- Dataset source: Remote API at `https://api.cseshirazu307.ir/`; no official dataset page is included.
- Dataset type: JSON responses consumed by an Android app.
- Input format: User-entered registration fields, movie search text, and selected movie IDs.
- Output format: Android UI screens populated with movie, genre, detail, search, and favorite data.
- Train/test split: Not applicable.
- Labels/targets: Not applicable.
- Preprocessing: Gson deserializes JSON responses into Kotlin data classes.
- Feature engineering: Not applicable.
- Data cleaning: Not specified in the current project files.
- Missing value handling: Not specified in the current project files.
- Normalization/scaling/encoding/augmentation: Not applicable.
- Registration transformation: The password text is hashed with MD5 before it is sent to the registration endpoint.

## Project Structure

```text
Movie-Android-App/
|-- app/
|   |-- build.gradle
|   |-- proguard-rules.pro
|   |-- src/
|       |-- androidTest/
|       |   |-- java/com/mortazavian/movie_android_app/ExampleInstrumentedTest.kt
|       |-- main/
|       |   |-- AndroidManifest.xml
|       |   |-- java/com/mortazavian/movie_android_app/
|       |   |   |-- MainActivity.kt
|       |   |   |-- splash_page/
|       |   |   |-- register_page/
|       |   |   |-- home_page/
|       |   |   |-- detail_page/
|       |   |   |-- search_page/
|       |   |   |-- favorite_page/
|       |   |   |-- shared_component/
|       |   |   |-- utils/
|       |   |-- res/
|       |       |-- anim/
|       |       |-- drawable/
|       |       |-- layout/
|       |       |-- mipmap-*/
|       |       |-- navigation/
|       |       |-- values/
|       |       |-- values-night/
|       |       |-- xml/
|       |-- test/
|           |-- java/com/mortazavian/movie_android_app/ExampleUnitTest.kt
|-- gradle/wrapper/
|   |-- gradle-wrapper.jar
|   |-- gradle-wrapper.properties
|-- build.gradle
|-- settings.gradle
|-- gradle.properties
|-- gradlew
|-- gradlew.bat
|-- .gitignore
|-- README.md
```

### Important Files and Folders

| Path | Purpose |
|---|---|
| `settings.gradle` | Defines the Gradle project name and includes the `app` module. |
| `build.gradle` | Top-level Android Gradle Plugin and Kotlin plugin configuration. |
| `app/build.gradle` | Android app configuration, SDK versions, ViewBinding, and dependencies. |
| `app/src/main/AndroidManifest.xml` | Declares app permissions and activities. |
| `MainActivity.kt` | Hosts the home, search, and favorite fragments with a custom bottom navigation area. |
| `splash_page/SplashActivity.kt` | Shows the splash screen and decides whether to open registration or the main app. |
| `register_page/` | Registration UI, ViewModel, repository, and request/response models. |
| `home_page/` | Home screen, genre/movie ViewModels, repositories, models, and RecyclerView adapters. |
| `detail_page/` | Movie detail activity, detail ViewModel, repository, response model, and image adapter. |
| `search_page/` | Search fragment, search ViewModel, repository, models, and search results adapter. |
| `favorite_page/` | Favorites fragment and adapter for locally saved favorite movies. |
| `shared_component/API.kt` | Retrofit, OkHttp, Gson, base URL, and API service creation. |
| `shared_component/APIService.kt` | Retrofit endpoint definitions. |
| `utils/extensions/SharedPreferences+Format.kt` | SharedPreferences helper extension functions. |
| `app/src/main/res/layout/` | XML layouts for activities, fragments, and list items. |
| `app/src/main/res/drawable/` | Icons, poster images, logo assets, and XML drawables. |
| `app/src/test/` | Local JVM test placeholder from the Android template. |
| `app/src/androidTest/` | Instrumented Android test placeholder from the Android template. |

## Methodology / Workflow

### 1. App Launch

`SplashActivity` starts first. It shows a splash logo, reads a boolean value from `SharedPreferences`, and routes the user to:

- `RegisterActivity` when the local registration flag is not set.
- `MainActivity` when the flag is already set.

### 2. Registration

`RegisterActivity` collects name, student number, email, and password. Before sending the request, it hashes the password with MD5 and creates a `UserInformation` request object.

The registration flow is:

1. User taps `Register`.
2. UI inputs are disabled while the request is running.
3. `RegisterViewModel` calls `RegisterRepository`.
4. `RegisterRepository` sends `POST /register` through Retrofit.
5. If the response status is `200`, the app saves a local registration flag and opens `MainActivity`.

### 3. Home Screen

`HomeFragment` renders:

- A slideshow using bundled poster resources from `res/drawable`.
- A horizontal genre list loaded from `GET /genres`.
- A vertical movie list loaded from `GET /genres/1/movies`.

Movie rows display title, IMDb rating, country, year, and poster. Posters are loaded with Glide.

### 4. Movie Details

When a user taps a movie, `MovieAdapter` or `SearchMovieAdapter` opens `DetailActivity` with a `MOVIE_ID` extra.

`DetailActivity` then calls `GET /movie/{movieID}` and displays:

- Poster
- Title
- IMDb rating
- Runtime
- Release date
- Summary
- Actors
- Horizontal image gallery
- Favorite icon state

### 5. Search

`SearchFragment` watches the text in the search input. For each non-empty query, it calls `GET /movies/1/{searchText}` and displays matching movies in a RecyclerView.

### 6. Favorites

Favorites are stored locally in `SharedPreferences` under `MovieAppPrefs`. The selected movie detail object is serialized to JSON with Gson and stored in a string set. `FarvoriteFragment` reads the saved JSON values, converts them back into `MovieDetails`, and displays them with `FavoriteMoviesAdapter`.

### 7. Architecture

The app follows a lightweight MVVM-style structure:

- Activities and fragments own screen rendering and user events.
- ViewModels expose LiveData and launch coroutine-based requests.
- Repository classes call Retrofit services and wrap responses in `Result`.
- Data classes define API response/request shapes.
- Adapters bind movie and genre data to RecyclerView rows.

## Visual Results

No saved screenshots of the running Android application were found in the current project files.

The project does include drawable image assets that are used inside the app, especially for the home slideshow and splash/register branding.

| Home Slideshow Asset | Preview |
|---|---|
| `her_poster.jpg` | ![Her poster asset](app/src/main/res/drawable/her_poster.jpg) |
| `fear_poster.webp` | ![Fear poster asset](app/src/main/res/drawable/fear_poster.webp) |
| `starwar_poster.jpg` | ![Star Wars poster asset](app/src/main/res/drawable/starwar_poster.jpg) |
| `lamp_poster.webp` | ![Lamp poster asset](app/src/main/res/drawable/lamp_poster.webp) |
| `moonlight_poster.jpg` | ![Moonlight poster asset](app/src/main/res/drawable/moonlight_poster.jpg) |

These poster assets are loaded into the home-page slideshow in `HomeFragment`.

## Installation

### Prerequisites

- Android Studio
- Android SDK with compile SDK 34 installed
- JDK compatible with Android Gradle Plugin 7.4.2, with `JAVA_HOME` configured
- Internet access for Gradle dependency download and API calls

### Clone the Repository

```bash
git clone <repository-url>
cd Movie-Application-Android-main
```

### Build from the Command Line

```bash
./gradlew assembleDebug
```

On Windows:

```bash
gradlew.bat assembleDebug
```

### Open in Android Studio

1. Open Android Studio.
2. Select `Open`.
3. Choose the project root directory.
4. Let Gradle sync the project.
5. Run the `app` configuration on an emulator or connected Android device.

## Usage

### Run on an Emulator or Connected Device

```bash
./gradlew installDebug
```

Then open `Movie-Android-App` on the device.

### In-App Usage Flow

1. Launch the app.
2. Register with name, student number, email, and password.
3. Browse movies and genres on the home screen.
4. Tap a movie to view detailed information.
5. Tap the favorite icon on the detail screen to save or remove the movie locally.
6. Use the search tab to search for movies.
7. Use the favorite tab to view saved movies.

The app requires network access because movie data is loaded from the configured REST API.

## Training / Running the Project

This project does not contain a machine learning model, training script, dataset split, or notebook workflow. Training is not applicable.

For Android development, use the following commands:

```bash
./gradlew assembleDebug
./gradlew test
./gradlew connectedAndroidTest
```

`connectedAndroidTest` requires a running emulator or connected Android device.

## Evaluation

Current tests are the default Android template tests:

- `ExampleUnitTest.kt` checks a simple arithmetic assertion.
- `ExampleInstrumentedTest.kt` checks the application package name on an Android device.

No feature-level unit tests, repository tests, UI tests, API mock tests, or favorite-storage tests are included in the current project files.

## Results

The project does not include saved app screenshots, generated reports, metrics, release APKs, or result logs.

Current runtime outputs are:

- Android screens rendered on a device or emulator.
- Movie and genre data returned by the remote API.
- Favorite movies stored locally through `SharedPreferences`.
- Debug logs from activities, fragments, repositories, and adapters.

Results are not included in the current project files. This section can be updated after running the app and saving screenshots or demo artifacts.

## Requirements

The project uses Gradle and Android dependency declarations in [`app/build.gradle`](app/build.gradle). No `requirements.txt` file is needed because this is not a Python project.

Important Android configuration:

| Setting | Value |
|---|---|
| Application ID | `com.mortazavian.movie_android_app` |
| Namespace | `com.mortazavian.movie_android_app` |
| Min SDK | 24 |
| Target SDK | 34 |
| Compile SDK | 34 |
| Version Code | 1 |
| Version Name | `1.0` |
| Gradle Wrapper | Gradle 7.5 |
| Android Gradle Plugin | 7.4.2 |
| Kotlin Android Plugin | 1.8.0 |
| ViewBinding | Enabled |

## Technologies Used

- Kotlin
- Android SDK
- AndroidX AppCompat
- AndroidX Core KTX
- AndroidX Navigation
- AndroidX Lifecycle ViewModel and LiveData
- Kotlin coroutines
- XML layouts
- ViewBinding
- RecyclerView
- ConstraintLayout
- Material Components
- Retrofit
- OkHttp
- OkHttp Logging Interceptor
- Gson converter
- Glide
- ImageSlideshow
- SharedPreferences
- Gradle
- JUnit
- Espresso

## Future Improvements

- Add real screenshots or GIF demos of the splash, registration, home, search, detail, and favorites screens.
- Move API base URL and other constants into a build config or environment-specific configuration.
- Replace MD5 password hashing with a server-approved authentication flow and secure token handling.
- Add loading, empty, and error UI states for all API-driven screens.
- Debounce search requests to avoid calling the API on every text change.
- Replace local favorite string-set storage with a typed local persistence layer such as Room or DataStore.
- Add repository tests with mocked API responses.
- Add UI tests for registration, navigation, search, detail loading, and favorite toggling.
- Clean up unused commented code and resolve naming typos such as `Farvorite`.
- Update duplicate or old dependency declarations after verifying compatibility.
- Add API documentation or a mock server for easier reviewer testing.
- Add a license file before publishing the project.

## References

- Remote API base URL used by the project: `https://api.cseshirazu307.ir/`
- Android build documentation: https://developer.android.com/build
- Android ViewModel documentation: https://developer.android.com/topic/libraries/architecture/viewmodel
- Android Navigation documentation: https://developer.android.com/guide/navigation
- Android ViewBinding documentation: https://developer.android.com/topic/libraries/view-binding
- Kotlin coroutines documentation: https://kotlinlang.org/docs/coroutines-overview.html
- Retrofit documentation: https://square.github.io/retrofit/
- OkHttp documentation: https://square.github.io/okhttp/
- Glide documentation: https://bumptech.github.io/glide/
- Gson repository: https://github.com/google/gson
- ImageSlideshow repository: https://github.com/denzcoskun/ImageSlideshow

## License

No license file is currently included in this repository. Add a license before publishing if you want to define usage permissions.
