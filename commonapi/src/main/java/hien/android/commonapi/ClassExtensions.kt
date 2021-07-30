package hien.android.commonapi

import android.content.Context
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import org.apache.commons.lang3.StringUtils

fun Any?.toFormattedString(): String {
    if (this == null) {
        return StringUtils.EMPTY
    }
    val result = StringBuilder()
    val aClass: Class<*> = this.javaClass
    result.append(aClass.simpleName)
    result.append(" Object {").append(StringUtils.LF)
    var stringBuilder = toStringInternal(this, aClass)
    result.append(stringBuilder.toString())
    val superclass = aClass.superclass
    stringBuilder = toStringInternal(this, superclass)
    result.append(stringBuilder.toString())
    if (superclass == null) {
        return result.append("}").toString()
    }
    val superclassSuperclass = superclass.superclass
    stringBuilder = toStringInternal(this, superclassSuperclass)
    result.append(stringBuilder.toString())
    if (superclassSuperclass == null) {
        return result.append("}").toString()
    }
    stringBuilder = toStringInternal(this, superclassSuperclass.superclass)
    result.append(stringBuilder.toString())
    return result.append("}").toString()
}

private fun toStringInternal(any: Any, aClass: Class<*>?): StringBuilder {
    val result = StringBuilder()
    if (aClass == null) {
        return result
    }
    for (field in aClass.declaredFields) {
        result.append("  ")
        field.isAccessible = true
        val name = field.name
        val value = try {
            field[any]
        } catch (e: IllegalAccessException) {
            "Error on ClassHelper: " + e.message
        }
        result.append("$name : $value").append(StringUtils.LF)
    }
    return result
}

fun Context.saveToPrefs(any: Any, key: String) {
    val edit = PreferenceManager.getDefaultSharedPreferences(this).edit()
    val gson = Gson()
    val json = gson.toJson(any)
    edit.putString(key, json)
    edit.apply()
}

fun Context.readFromPrefs(key: String, clazz: Class<out Any>): Any? {
    val gson = Gson()
    val json: String? = PreferenceManager.getDefaultSharedPreferences(this).getString(key, StringUtils.EMPTY)
    return gson.fromJson(json, clazz)
}