# Kotlin Multiple Platform Mobile + Ktor + Coroutine + SqlDelight (iOS Only)

<p align="center">
  <img src="cache_no_cache_demo.gif" height="400" />
</p>

A cross platform application which written by Kotlin Multiplatform Mobile using Kotlin for shared features. Swift for iOS specific features, and Kotlin for Android specific features.
This demo is only run on iOS for demo purpose!

## Getting Started

These instructions will get you building and running the project on your local machine for development and testing purposes. See usage and supported commands for notes on how to use the application.

## Prerequisites

- Android Studio 4.2 or 2020.3.1 Canary 8 or higher.
- Xcode 13 or higher.
- Java SDK 11
- Kotlin plugin (in Android Studio)
- Kotlin Multiplatform Mobile (in Android Studio)

For more detail, please access this link: https://kotlinlang.org/docs/kmm-setup.html

## 3rd party

- Ktor (call RESTFul APIs)
- Coroutine (asynchronous functions)
- Sqldelight (persist structured data)

## Setup

- Open project with Android Studio.
- Go to `File` > `Sync Project with Gradle Files`.

## Run

![](screenshot_1.png)

## Courotine
The application use Courotine for asynchronous functions and multithreading. Here is an sample:

```kotlin
GlobalScope.async {
  val urlString1 = "http://itunes.apple.com/search?term=a&entity=song";
  val urlString2 = "http://itunes.apple.com/search?term=b&entity=song";

  val request1: Deferred<HttpResponse> = async { httpClient.get(urlString1) }
  val request2: Deferred<HttpResponse> = async { httpClient.get(urlString2) }

  // Parallel requests
  val response1: HttpResponse = request1.await();
  val response2: HttpResponse = request2.await();

  val text1: String = response1.readText();
  val text2: String = response2.readText();

  val jsonSerialzer =  Json { ignoreUnknownKeys = true }

  val result1: ResultCollection =
                jsonSerialzer.decodeFromString(text1);
  val result2: ResultCollection =
                jsonSerialzer.decodeFromString(text2);
  return@async ResultCollection(
                result1.resultCount + result2.resultCount,
                result1.results + result2.results
  );
}
```

## License

This project is licensed under the MIT License - see the LICENSE file for details
