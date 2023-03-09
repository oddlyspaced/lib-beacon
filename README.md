# lib-beacon
Root based SDK for controlling the LED's on the back of Nothing Phone 1.

## Dependencies
- libsu [Magisk]

## Adding to your project
1. Clone this repository
2. Copy the lib folder to your project root
3. Add the following line to settings.gradle
	- `include ':lib'`
4. Add the following dependency to your app gradle
	- `implementation project(":lib")`

(Sample settings.gradle)
```groovy
pluginManagement {  
  repositories {  
  gradlePluginPortal()  
  google()  
  mavenCentral()  
  maven { url 'https://jitpack.io' }  
 }
}  
dependencyResolutionManagement {  
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)  
  repositories {  
  google()  
  mavenCentral()  
  maven { url 'https://jitpack.io' }  
 }
}  
rootProject.name = "Beacon"  
include ':app'  
include ':lib'
```

(Sample dependencies section)
```
dependencies {  
  implementation 'androidx.core:core-ktx:1.9.0'  
  implementation 'androidx.appcompat:appcompat:1.5.1'  
  implementation 'com.google.android.material:material:1.7.0'  
  implementation 'androidx.constraintlayout:constraintlayout:2.1.4'  
  testImplementation 'junit:junit:4.13.2'  
  androidTestImplementation 'androidx.test.ext:junit:1.1.4'  
  androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.0'  
  
  implementation "com.github.topjohnwu.libsu:core:5.0.2"  
  implementation project(":lib")  
}
```

## Classes
There are 3 main set of classes that exist to control the leds.
- `RootLedControllerImpl`
	- Provides low level individual control to each LED/Section on the device.
	- Used as base for all animations.
- `LedAnimator`
	- Contains various methods that concern to a particular animation for the device.
- `ResourceAnimator`
	- Accepts an enum representation of a media resource and handles the playback of the media along with the animation of the Led in sync.
	- Used for replicating the stock Led Ringtone patterns from scratch.

## Usage
Please look at the sample app source provided to understand the functioning of the lib. The classes have interdependence with a fair amount of state management, therefore the app source would present a better practical representation of how to use the lib.
