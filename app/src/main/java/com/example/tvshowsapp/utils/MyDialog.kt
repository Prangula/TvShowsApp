package com.example.tvshowsapp.utils

import android.app.Dialog
import android.content.Context
import com.example.tvshowsapp.R


class MyDialog {

    private var dialog: Dialog? = null

    fun showDialog(context: Context) {
        dialog = Dialog(context)
        dialog?.setContentView(R.layout.dialog)
        dialog?.setCancelable(true)
        dialog?.show()
    }

    fun hideDialog() {
        dialog?.dismiss()
    }
}