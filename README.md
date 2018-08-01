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
* Easy ripple effect integration via drawable colors (Default, Focused, Disabled)
* Support for SDK below Lollipop: StateListDrawable instead of RippleDrawable

###### Icons
* Choose from your resource file 
* Choose from FontAwesome Icons (ttf based, not images!)
* Tint (enabledColor & disabledColor)
* Layout (iconArea, iconMargin, iconPadding)

###### Text
* Choose from the available fonts (eg. OpenSans, Gilroy, ...)
* Style (size, alignment, gravity, style)
* Color (textColor, disabledTextColor)

###### Additional Overrides
* isEnabled
* isRippleEffectEnabled
> For more info, refer to [DOCS](https://jmdg.io/ButtonsKt)

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
    // Replace version with release version, e.g. 1.0.0-alpha, -SNAPSHOT
    implementation "io.jmdg:buttonskt:[VERSION]"
}
```

#### BASIC USAGE
> __TIP__: Load URI xmlns:app="http://schemas.android.com/apk/res-auto" to render runtime custom attributes

> __TIP__: See ``DOCS`` for more information.

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
        app:bkt_borderWidth="2dp"
        app:bkt_defaultBackgroundColor="@color/colorPrimary"
        app:bkt_disabledTextColor="@android:color/darker_gray"
        app:bkt_focusedBackgroundColor="@color/colorPrimaryDark"
        app:bkt_iconResource="fa_cloud_download_alt"
        app:bkt_radius="10dp"
        app:bkt_text="DOWNLOAD"
        app:bkt_textColor="@android:color/white"
        app:bkt_textSize="7dp"
        app:bkt_textStyle="bold" />
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
            radius = 30f,
    )

    // Create ButtonsKtView with the desired configuration instance
    val buttonsKtView = ButtonsKtView(this, configuration)

    // Add custom view
    rl_main.addView(buttonsKtView)
    ...
}
```

#### BUTTONSKT ANATOMY
> Based on Google's Android Material Design

![anatomy](https://i.imgur.com/jNhcAZI.png)

#### META

Joshua de Guzman | code@jmdg.io

Distributed under the MIT license. See ``LICENSE`` for more information.

#### CONTRIBUTING

1. Fork it (<https://github.com/joshuadeguzman/ButtonsKt/fork>)
2. Create your feature branch (`git checkout -b feature/fooBar`)
3. Commit your changes (`git commit -am 'Add some fooBar'`)
4. Push to the branch (`git push origin feature/fooBar`)
5. Create a new Pull Request
