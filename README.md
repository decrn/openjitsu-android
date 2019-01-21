# OpenJitsu Android

OpenJitsu is an open-source, user-driven platform for learning Brazilian Jiu-Jitsu. It offers users an intuitive app that breaks down key positions into submisssions and defenses, allowing beginners to quickly grasp concepts like Mount, Guard and Side Control, as well as advanced break downs, historical information and instructional step-by-steps for specific moves that appeal to more advanced practitioners.

The app uses the bleeding edge *Jetpack AndroidX component libraries* to present a modern and clean [Google Material Design 2.0](https://material.io/design)-conform look and feel with several other frameworks and libraries under the hood:

* **RxJava**, **RxKotlin** and **RxAndroid** for better async and observables
* **Glide** for image loading and caching
* **Dagger2** for Dependency Injection
* **Retrofit2** with the **OkHttp3** client for HTTP/network functionality
* **Room2** for local persistence
* **Espresso** for instrumented e2e testing

### Screenshots

![Login view](/showcase/login.png)
![Master view](/showcase/master.png)
![Detail view](/showcase/detail.png)
![Comments view](/showcase/comments.png)

## Installation

Clone this repository and import into **Android Studio**
```bash
git clone https://github.com/decrn/openjitsu-android openjitsu-android
```


## Running

MinSDK level 19, TargetSDK level 28.
Run with Android Studio >= 3.2

## Maintainers

This project is mantained by:
* [Denis Carnier](http://github.com/decrn)


## Contributing

1. Fork it
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Commit your changes (`git commit -m 'Add some feature'`)
5. Push your branch (`git push origin my-new-feature`)
6. Create a new Pull Request
