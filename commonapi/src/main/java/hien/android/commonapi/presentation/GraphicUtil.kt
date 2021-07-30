package hien.android.commonapi.presentation

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.KeyEvent
import android.widget.Button
import android.widget.Toast

fun toastLong(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
}

fun toastShort(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}

fun showBlockingAlertDialog(
    activity: Activity,
    title: String,
    msg: String,
    textBtn: String,
    btnTextColor: Int,
    isCancelable: Boolean,
    functionRequiredAction: ((alertDialog: AlertDialog) -> Unit?)? = null,
): AlertDialog {
    val alertDialog = AlertDialog.Builder(activity).create()
    alertDialog.setCancelable(isCancelable)
    alertDialog.setTitle(title)
    alertDialog.setMessage(msg)
    alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, textBtn) { _, _ -> }
    alertDialog.setOnKeyListener { _, keyCode, _ ->
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            activity.finish()
        }
        true
    }
    alertDialog.show()

    val button: Button = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
    button.setTextColor(btnTextColor)
    button.setOnClickListener {
        functionRequiredAction?.let { actionFunction -> actionFunction(alertDialog) }
    }
    return alertDialog
}

