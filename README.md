# JunoFQ

## Config
It's necessary to create two constants in gradle.properties

API_KEY = "YOUR_KEY"

SECRET_KEY = "YOUR_KEY"

## Debug keystore
The project contains a debug.keystore configured in gradle. It's just necessary to insert the following hash in the Foursquare's dev panel:

57:B2:5C:20:A1:38:5C:D7:A5:09:DA:3B:51:48:9D:71:77:FE:72:7E

## Architecture

### Arch 
Just boilerplate code to support the architecture.

### App 
UI implementation

Frameworks: Koin, Anko, Architecture components (ViewModel and LiveData)

### Data
Responsible for performing and providing the data.

Frameworks: Koin, LiveData, RxJava, Retrofit, Room

### Model
Data classes.

Frameworks: Anko, Room

### Comments
I couldn't finish all the features and tests on time.

https://github.com/feharaujo/CitiesSearch

Please, take a look at this project that I did some days ago. It's very similar the main idea of this project.
This project I explored very deep the Clean architecture and covered with unit tests most of the main layers.

#### Others projects


 https://github.com/feharaujo/CitiesSearch

- Java, Tests, Clean architecture, MVP


 https://github.com/feharaujo/News-App

- Kotlin, Dagger, Retrofit, Arch Components, MVVM


 https://github.com/feharaujo/FlutterRGA

- Flutter project, example of a workshop that I presented at R/GA


