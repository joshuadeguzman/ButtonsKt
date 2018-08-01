package io.jmdg.buttonskt

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Joshua de Guzman on 01/08/2018.
 */

@RunWith(AndroidJUnit4::class)
class ButtonsKtTest {
    @Test
    fun checkFontsExistence() {
        val context = InstrumentationRegistry.getTargetContext()
        val fontsArray = context.resources.getStringArray(R.array.DefaultFontResource)
        fontsArray.forEach {
            Assert.assertNotNull(context.assets.open(it))
        }
    }
}
