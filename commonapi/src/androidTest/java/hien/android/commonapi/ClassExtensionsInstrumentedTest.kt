package hien.android.commonapi

import android.content.Context
import androidx.preference.PreferenceManager
import androidx.test.platform.app.InstrumentationRegistry
import hien.android.commonapi.readFromPrefs
import hien.android.commonapi.saveToPrefs
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class ClassExtensionsInstrumentedTest {
    private lateinit var context: Context
    private lateinit var key1: String
    private lateinit var key2: String

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        key1 = "key1"
        key2 = "key2"
        PreferenceManager.getDefaultSharedPreferences(context).edit().clear().commit()
    }

    @After
    fun tearDown() {
        PreferenceManager.getDefaultSharedPreferences(context).edit().clear().commit()
    }

    @Test
    fun saveToPrefs() {
        val value = "AAA"
        context.saveToPrefs(value, key1)
        val fromPrefs = context.readFromPrefs(key1, String::class.java)
        assertEquals(value, fromPrefs)
    }

    @Test
    fun readFromPrefs() {
        var fromPrefs = context.readFromPrefs(key2, String::class.java)
        assertNull(fromPrefs)

        val value = "BBB"
        context.saveToPrefs(value, key2)
        fromPrefs = context.readFromPrefs(key2, String::class.java)
        assertEquals(value, fromPrefs)
    }

}