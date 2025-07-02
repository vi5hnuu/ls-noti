# LsNoti 🍭

Elegant, swipeable, and stackable snackbar system for **Jetpack Compose** — powered by `Material3`, built for **clean UX**, and offering **custom positioning** and styling.

---

### 🚀 Features

- 📌 **Stackable snackbars** — displayed in an overlay.
- ✨ **Swipe-to-dismiss** with smooth state management.
- 🎨 **Custom themes**, **colors**, and **durations**.
- 📍 **Position anywhere**: top, center, bottom (with 9 alignment positions).
- 📏 **Custom width and max stack height**.
- 📦 **Tiny footprint**, no third-party deps.

---

### 🧰 Installation (via JitPack)

Add JitPack to your root `settings.gradle`:

```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
````

Then add the library:

```kotlin
implementation("com.github.vi5hnuu:ls-noti:1.0.0")
```

---

## 🛠️ Installation
```kotlin
Ensure you're using the Jetpack Compose **BOM** to avoid version conflicts.

// Compose BOM for version alignment
implementation(platform("androidx.compose:compose-bom:2024.05.00"))

// Required dependencies
implementation("androidx.compose.material3:material3")
implementation("androidx.compose.animation:animation-android")
implementation("androidx.compose.ui:ui")
implementation("androidx.compose.runtime:runtime")

// Or via version catalogs:
implementation(platform(libs.androidx.compose.bom))
api(libs.androidx.material3)
implementation(libs.androidx.animation.android)
implementation(libs.androidx.ui)
implementation(libs.androidx.runtime)

```

### ✨ Usage

```kotlin
val snackbarState = rememberLsSnackbarHostState()

Scaffold(
    snackbarHost = {
        LsSnackbarHost(
            state = snackbarState,
            maxStackHeightFraction = 0.7f,
            maxStackWidthFraction = 0.7f,
            position = LsSnacksPosition.TopCenter // 🆕 9 alignments!
        )
    }
) {
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = {
            snackbarState.show(
                LsSnackbarData(
                    message = "Operation completed",
                    type = LsSnackbarType.Success,
                    durationMillis = 5000
                )
            )
        }) {
            Text("Show Success Snackbar")
        }
    }
}
```

---

### 🔁 Snackbar Positions (9 available)

```kotlin
enum class LsSnacksPosition {
    TopStart, TopCenter, TopEnd,
    CenterStart, Center, CenterEnd,
    BottomStart, BottomCenter, BottomEnd
}
```

Position your host at any corner or center — based on **UX need**. Ideal for mobile, tablet, and desktop Compose.

---

### 🎨 Snackbar Types

```kotlin
enum class LsSnackbarType {
    Success, Error, Warning, Info
}
```

Each type comes with built-in themed background colors. You can override it with `backgroundColor`.

---

### ⏱️ Duration Control

```kotlin
open class LsSnackbarData(
    val message: String,
    val type: LsSnackbarType = Info,
    val animation: LsSnackbarAnimation = Bounce, // 🛠️ Coming soon!
    val durationMillis: Long = 3000 // >0 means auto-dismiss
)
```

* Set `durationMillis > 0` for timed dismissal.
* Want to keep the snackbar until user action? Use a high value like `60_000`.

---

### 🔮 Roadmap

* 🌀 Snackbar animation support (`LsSnackbarAnimation`) — *coming in next release*.
* 💡 Auto stacking limit config.
* 💅 Entry/Exit transitions.

---

### 💼 License

MIT License © [Vishnu Kumar](https://github.com/vi5hnuu)

---

### 💬 Feedback & Contributions

Feel free to open issues, suggestions, or PRs on [GitHub](https://github.com/vi5hnuu/ls-noti).
Let's build a better Compose ecosystem — one UI component at a time! ✨

## 📌 Test View
---
<img src="https://code-sprout-content.laxmi.solutions/thumbnails/ls-noti-jetpack-1.webp" height="400" />
<img src="https://code-sprout-content.laxmi.solutions/thumbnails/ls-noti-jetpack-2.webp" height="400" />
<img src="https://code-sprout-content.laxmi.solutions/thumbnails/ls-noti-jetpack-3.webp" height="400" />
<img src="https://code-sprout-content.laxmi.solutions/thumbnails/ls-noti-jetpack-4.webp" height="400" />

---

## ✅ License

MIT License. Created by [Vishnu kumar](https://github.com/vi5hnuu).

---

## 🔗 Connect with Me
Feel free to connect with me on social media:
* [GitHub](https://github.com/vi5hnuu)
* [LinkedIn](https://www.linkedin.com/in/vi5hnukumar/)
* [PlayStore](https://play.google.com/store/apps/dev?id=7222030998756299864)

---
