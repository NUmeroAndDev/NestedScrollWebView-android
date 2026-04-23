# Android NestedScrollWebView 

[![](https://jitpack.io/v/NUmeroAndDev/NestedScrollWebView-android.svg)](https://jitpack.io/#NUmeroAndDev/NestedScrollWebView-android)

`NestedScrollWebView` is an Android library that provides a custom `WebView` which seamlessly integrates with Android's nested scrolling system.

By implementing `NestedScrollingChild3`, `NestedScrollWebView` allows you to place a `WebView` inside standard nested scrolling parents (like `CoordinatorLayout`, `SwipeRefreshLayout`, and `AppBarLayout`) or Jetpack Compose nested scrolling components, and have scroll events correctly dispatched between them.

## Installation

This library is distributed via [JitPack](https://jitpack.io).

**Step 1.** Add the JitPack repository to your root `settings.gradle.kts` (or root `build.gradle`):

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}
```

**Step 2.** Add the dependency to your module's `build.gradle.kts` or `build.gradle`:

```kotlin
dependencies {
    implementation("com.github.NUmeroAndDev:nestedscrollwebview:$version")
}
```

## Usage

You can use `NestedScrollWebView` in both the standard Android View system and Jetpack Compose.

### Android View System (XML Layouts)

Use `com.github.numeroanddev.nestedscrollwebview.NestedScrollWebView` instead of the standard `android.webkit.WebView` in your layout XML.

This enables you to use it with `SwipeRefreshLayout`, `AppBarLayout` (like collapsing toolbars), and other nested scroll parents.

```xml
<androidx.swiperefreshlayout.widget.SwipeRefreshLayout
    android:id="@+id/swipe_refresh_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <com.github.numeroanddev.nestedscrollwebview.NestedScrollWebView
        android:id="@+id/webview"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

</androidx.swiperefreshlayout.widget.SwipeRefreshLayout>
```

### Jetpack Compose

You can easily wrap `NestedScrollWebView` using `AndroidView` in Compose.
To make it work with Compose's nested scrolling ecosystem (e.g. `TopAppBarDefaults.exitUntilCollapsedScrollBehavior()` or `PullToRefreshBox`), apply `Modifier.nestedScroll(rememberNestedScrollInteropConnection())` to the `AndroidView`.

```kotlin
@Composable
fun Example() {
    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = { ... },
    ) {
        AndroidView(
            factory = {
                NestedScrollWebView(it).apply {
                    webViewClient = WebViewClient()
                    settings.javaScriptEnabled = true
                    loadUrl(...)
                }
            },
            modifier = Modifier.nestedScroll(rememberNestedScrollInteropConnection())
        )
    }
}
```