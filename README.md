# DroidConSg Hack App

This is app is a combination of some of the interesting talks of DroidConSG 2019 plus the exoplayer talk in Day2. I did this 
app during the talks and after droidconsg day #1. This app play the video of droidconsg 2018 in portrait mode and in landscape mode. For landscape mode the player is  either MediaPlayer or Exoplayer, depending on the config from the FeatureFeedRepository.


## Clean Architecture Inspired by Kotlin Multiplatform by Britt Barak (11:30 talk)

Used proper division of source codes. Plus repository patern.
<a href="https://github.com/objectiveCarlo/android_droidconsghack/tree/master/app/src/main/java/ph/carlo/android/cldroidconhack"><img src="https://raw.githubusercontent.com/objectiveCarlo/android_droidconsghack/master/__images/cleanarchitecture.png" title="CleanArchictecture" alt="CleanArchictecture"></a>

<!-- [![CleanArchictecture](https://raw.githubusercontent.com/objectiveCarlo/android_droidconsghack/master/__images/cleanarchitecture.png)](https://github.com/objectiveCarlo/android_droidconsghack/tree/master/app/src/main/java/ph/carlo/android/cldroidconhack) -->

## Use dependency injection Inspired by Effective DI ... by Adit Lal (13:00 talk)
I did not use Dagger here but implemented my own DI with the help of a singleton class and Android's BuildConfig


### Dependency Manager 
<a href="https://github.com/objectiveCarlo/android_droidconsghack/blob/master/app/src/main/java/ph/carlo/android/cldroidconhack/dependency/DependencyManager.kt"><img src="https://raw.githubusercontent.com/objectiveCarlo/android_droidconsghack/master/__images/dependencymanager.png" title="CleanArchictecture" alt="CleanArchictecture"></a>

### Build Config
<a href="https://github.com/objectiveCarlo/android_droidconsghack/blob/master/app/build.gradle"><img src="https://raw.githubusercontent.com/objectiveCarlo/android_droidconsghack/master/__images/simpledi.png" title="CleanArchictecture" alt="CleanArchictecture"></a>


## Use of remote config/feature toggle Inspired by Remote Config and Beyond by Ayushi Gupta (11:00 talk)
The landscape player implementation is being controlled by a remote config. 
```javascript
{
  "features": {
    "player": {
      "exoplayer": false,
      "mediaplayer": true
    }
  }
}
```


