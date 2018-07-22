![logo](https://i.imgur.com/259nc51.png)
## ButtonsKt
> A customisable button kit for Android

ButtonsKt eliminates boilterplate xml files through ease of customization by exposing button customizations 
(borders & outlines, radius & corners,background color w/ states and easy implementation of ripple effect!, icons, and more!) 
whilst being lightweight, plus runtime xml selection of icons from FontAwesome!

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

#### USAGE
> __TIP__: Load URI xmlns:app="http://schemas.android.com/apk/res-auto" to render runtime custom attributes
```XML
<RootLayout
...
xmlns:app="http://schemas.android.com/apk/res-auto"/>

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
