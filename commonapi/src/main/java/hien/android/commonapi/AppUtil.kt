package hien.android.commonapi

import android.content.Context
import android.content.Intent

fun launchOtherApp(context: Context, appId: String, functionWhenAppNotFound: (() -> Unit?)? = null) {
    val launchIntent: Intent? = context.packageManager.getLaunchIntentForPackage(appId)
    if (launchIntent != null) {
        context.startActivity(launchIntent) // Null pointer check in case package name was not found
    } else {
        functionWhenAppNotFound?.let { actionFunction -> actionFunction() }
    }
}

