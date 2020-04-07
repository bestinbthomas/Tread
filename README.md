# TreadHill

<hr>

<img src="https://img.shields.io/badge/version-1.0-yellow">

It is recommended to look at the code alongside this documentations.
Follow the links and navigate to those files in documentation as well as in code.

## Overview

This app is made using MVVM Architecture.

ViewModel is responsible for producing any data that is to be displayed.

The ViewModel exposes all the data to the Views and gets the data from Firebase, Vimeo etc. using these [Utility Classes](docs/com.treadhill.app.utilities/index.md)

check the [ViewModel](docs/com.treadhill.app.view-model/-main-view-model/index.md) and the [Actions](docs/com.treadhill.app.view-model/-actions/index.md) it can take from views.

Check [this Package](docs/com.treadhill.app.data-types/index.md) to check the how various data is stored

All interactions with Firebase is Performed by [FirebaseUtils](docs/com.treadhill.app.utilities/-firebase-utils/index.md)

All interactions with Vimeo is Performed by this [VimeoUtils](docs/com.treadhill.app.utilities/-vimeo-utils/index.md)

## OnBoarding

Check The [Onboarding Pacakge](docs/com.treadhill.app.views.onboarding/index.md) and [Onboarding Activity](docs/com.treadhill.app.views.onboarding/-onboarding-activity/index.md)

#### Login and SignUp

various login methords are provided in the firebase API. from which Phone, email and google sign in are used

check [Login](docs/com.treadhill.app.views.onboarding/-log-in-fragment/index.md) and [Signup](docs/com.treadhill.app.views.onboarding/-sign-up-fragment/index.md) Fragments

the auth data is stored in FirebaseAuth object.

###### Phone

the phone number entered is appended with [country code](docs/com.treadhill.app.utilities/-country-spinner-utils/index.md) and sent to Firebase to send an OTP.

the OTP is verified in [VerifyPhoneFragment](docs/com.treadhill.app.views.onboarding/-verify-phone-fragment/index.md)

check the docs [here](https://firebase.google.com/docs/auth/android/phone-auth)

###### Email

The entered Email and password is sent to Firebase to [Login](docs/com.treadhill.app.views.onboarding/-log-in-fragment/log-in-with-email-password.md) or [Sign up](docs/com.treadhill.app.views.onboarding/-sign-up-fragment/sign-up-with-email-password.md)

for more reference check [this](https://firebase.google.com/docs/auth/android/password-auth)

###### Google

Default Google OAuth Login with [Firebase](https://firebase.google.com/docs/auth/android/google-signin)

#### Form

It is a static form defined in [UserDetailsFragment](docs/com.treadhill.app.views.onboarding/-user-details-fragment/index.md) check the layout file in code.

The data is stored in the form of [User](docs/com.treadhill.app.data-types/-user/index.md)

Tt is add to firebase firestore in `users` collection

To add questions add it to the layout, and add the required fields in User](docs/com.treadhill.app.data-types/-user/index.md) class.

## Home Page

[This](https://github.com/aqua30/Android-Bluetooth-Low-Energy-Wrapper-BLE) module is included in the project to handle BLE connection and the callbacks are handled in [MainActivity](docs/com.treadhill.app.views/-main-activity/index.md)

check [HomeFragment](docs/com.treadhill.app.views/-home-fragment/index.md) for implementation.

Videos Horizontal scrolling list and Trainers horizontal scrolling list are implemented as [Recycler Views](https://developer.android.com/guide/topics/ui/layout/recyclerview) and Moving Carousel is an [ViewPager](https://developer.android.com/reference/kotlin/androidx/viewpager/widget/ViewPager) that is scrolled every 2 seconds by a CountdownTimer.

## Workout

The summary is stored as an object of [WorkoutSummary](docs/com.treadhill.app.data-types/-workout-summary/index.md)

The Summary is generated by [WorkoutRecorder](docs/com.treadhill.app.utilities/-workout-recorder/index.md) Class by getting the heart rate and time from [PLayerFragment](docs/com.treadhill.app.views/-player-fragment/index.md) or [CustomWorkoutFragment](docs/com.treadhill.app.views/-custom-workout-fragment/index.md). It calcutales the avgHeartrate, calories Burnt etc.

the generated [WorkoutSummary](docs/com.treadhill.app.data-types/-workout-summary/index.md) pobject is sent posted in Firebase as per [FirebaseUtils](docs/com.treadhill.app.utilities/-firebase-utils/index.md) also it is sent to [WorkoutSummaryFragment](docs/com.treadhill.app.views/-workout-summary-fragment/index.md) to display the summary.

This is used in both Video Workout and Custom Player

#### Video

Video Player is implememted in the [PLayerFragment](docs/com.treadhill.app.views/-player-fragment/index.md)

The video is played using [exoplayer](https://developer.android.com/guide/topics/media/exoplayer) the info of the video id fetched from Vimeo using [VimeoUtils](docs/com.treadhill.app.utilities/-vimeo-utils/index.md) using the video info in db through the ViewModel.

From the videoInfo recieved from vimeo Dash,HLS or MP4 Links are obtained and a MediaSource Object is created using [ExoUtils](docs/com.treadhill.app.utilities/-exo-utils/index.md)

for without HR monior connected, It hides the HR in the view.

## Dashboard

Dashboard is implemented in [DashboardFragment](docs/com.treadhill.app.utilities/-dashboard-fragment/index.md)

The scores, duration and Calories for the graph is stored as [WeakInfo](docs/com.treadhill.app.data-types/-weak-info/index.md) and is fetched and stored in firebase using [FirebaseUtils](docs/com.treadhill.app.utilities/-firebase-utils/index.md) via [ViewModel](docs/com.treadhill.app.view-model/-main-view-model/index.md)

The List shown below is Recycler view with elements [WorkoutSummary](docs/com.treadhill.app.data-types/-workout-summary/index.md)

## Docs

the internal docs are generated by [Dokka](https://github.com/Kotlin/dokka) from the docstrings in the code.

on editing them or adding new ones run

```
./gradlew dokka
```

in the root directory to generate new in root directory and copy the generated docs from app/build/javadoc/data to docs. and update this file is necessary
