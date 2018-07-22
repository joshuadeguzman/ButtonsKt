package io.jmdg.buttonsktapp

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.jmdg.buttonskt.ButtonsKtView
import io.jmdg.buttonskt.constants.BktLayoutParams
import io.jmdg.buttonskt.constants.BktTextPosition
import io.jmdg.buttonskt.entities.ButtonsKt
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Can be reusable for multiple components
        val configuration = ButtonsKt(
                isEnabled = true,
                text = "Sample demo",
                radius = 100f,
                padding = 70,
                margin = 15,
                borderWidth = 10,
                defaultBorderColor = Color.GRAY,
                height = BktLayoutParams.WRAP_CONTENT,
                width = BktLayoutParams.MATCH_PARENT,
                textPosition = BktTextPosition.CENTER
        )

        // Create ButtonsKtView with the desired configuration instance
        val buttonsKtView = ButtonsKtView(this, configuration)

        // Add custom view
        rl_main.addView(buttonsKtView)
    }
}
