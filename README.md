![logo](https://i.imgur.com/259nc51.png)
## ButtonsKt
> A customisable button kit for Android

[![Kotlin](https://img.shields.io/badge/Kotlin-1.2.51-green.svg?style=flat-square)](http://kotlinlang.org)
[![RxJava](https://img.shields.io/badge/Support-27.1.1-6ab344.svg?style=flat-square)](https://github.com/ReactiveX/RxJava/releases/tag/v2.1.10)
[![Build Status](https://img.shields.io/travis/joshuadeguzman/ButtonsKt.svg?style=flat-square)](https://travis-ci.org/joshuadeguzman/ButtonsKt)
[![GitHub (pre-)release](https://img.shields.io/github/release/joshuadeguzman/ButtonsKt/all.svg?style=flat-square)
](./../../releases)

ButtonsKt eliminates boilterplate xml files through ease of customization by exposing button customizations 
(icons, borders & outlines, corner radius, background and more!) 
whilst being lightweight, plus runtime xml selection of icons from FontAwesome.

#### FEATURES
###### Container
* Corner Radius
* Border (width, color)
* Layout (padding, margin)

###### Background
* Easy Ripple Effect Integration (defaultBackgroundColor, focusedBackgroundColor, disabledBackgroundColor)
* Support for SDK below API Lollipop (StateListDrawable instead of RippleDrawable)

###### Icons
* Choose from your resource directory
* Choose from FontAwesome Icons (ttf based, not images!)
* Tint (enabledColor & disabledColor)
* Layout (iconArea, iconMargin, iconPadding)

###### Text
* Choose font your resource directory
* Choose from the available fonts (eg. OpenSans, Gilroy, ...)
* Style (size, alignment, gravity, style, isTextAllCaps)
* Color (textColor, disabledTextColor)

###### Additional Overrides
* isEnabled
* isRippleEffectEnabled
> For more info, refer to [DOCS](https://jmdg.io/ButtonsKt)

#### BUTTONSKT ANATOMY
> Based on Google's Android Material Design

![anatomy](https://i.imgur.com/jNhcAZI.png)

1. Icon
2. Text
3. Background
4. Border (Outline)
5. Ripple Effect

#### INSTALLATION

```Gradle
// Project level build.gradle
// ...
repositories {
    maven { url 'https://jitpack.io' }
}
// ...

// Module level build.gradle
dependencies {
    // Important, be sure to explicitly declare the latest version of kotlin
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:[KOTLIN_VERSION]

    // Replace version with release version, e.g. 1.0.0-alpha, -SNAPSHOT
    implementation "io.jmdg:buttonskt:[VERSION]"
}
```

#### BASIC USAGE
> __TIP__: Load URI xmlns:app="http://schemas.android.com/apk/res-auto" to render runtime custom attributes

```XML
<RootLayout
...
xmlns:app="http://schemas.android.com/apk/res-auto"
...
/>

  <io.jmdg.buttonskt.ButtonsKtView
        android:id="@+id/bkt_demo_xml_2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="10dp"
        android:textAlignment="center"
        ...
        app:bkt_isEnabled="false"
        app:bkt_isRippleEffectEnabled="true"
        app:bkt_cornerRadius="10dp"
        app:bkt_borderWidth="2dp"
        app:bkt_defaultBackgroundColor="@color/colorPrimary"
        app:bkt_focusedBackgroundColor="@color/colorPrimaryDark"
        app:bkt_focusedBackgroundColor="@color/colorPrimaryDisabled"
        app:bkt_text="ButtonsKt"
        app:bkt_textSize="12dp"
        app:bkt_textStyle="normal"
        app:bkt_textAllCaps="false"
        app:bkt_defaultFont="opensans_light"
        app:bkt_textColor="@android:color/white"
        app:bkt_disabledTextColor="@android:color/darker_gray"
        app:bkt_iconResource="fa_cloud_download_alt"
        app:bkt_iconArea="30dp"
        app:bkt_iconTint="@color/colorAccent"
        app:bkt_iconPadding="0dp"
        app:bkt_iconMargin="10dp"
        ... 
        />
...
</RootLayout>

```
> You can also do it programmatically with ease and reusability of specific configurations

```Kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    ...
    // Configuration instance
    // You are opt to change specific & named configurations
    // This configuration can be reused for adding multiple views programatically
    val configuration = ButtonsKt(
            text = "DECLARED VIA CODE",
            textSize = 18f,
            textStyle = BktTextStyle.BOLD,
            ...
    )

    // Create ButtonsKtView with the desired configuration instance
    val buttonsKtView = ButtonsKtView(this, configuration)

    // Add custom view
    rl_main.addView(buttonsKtView)
    ...
}
```

#### META

Joshua de Guzman | code@jmdg.io

Distributed under the MIT license.

#### CONTRIBUTING

1. Fork it (<https://github.com/joshuadeguzman/ButtonsKt/fork>)
2. Create your feature branch (`git checkout -b feature/fooBar`)
3. Commit your changes (`git commit -am 'Add some fooBar'`)
4. Push to the branch (`git push origin feature/fooBar`)
5. Create a new Pull Request
