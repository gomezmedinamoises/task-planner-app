package com.example.task_planner_app.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import androidx.core.graphics.drawable.toDrawable
import com.example.task_planner_app.R

object Common {

    fun showLoadingDialog(context: Context) : Dialog {
        val progressDialog = Dialog(context)
        progressDialog.let { dialog ->
            dialog.show()
            dialog.window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
            dialog.setContentView(R.layout.progress_dialog)
            dialog.setCancelable(false)
            dialog.setCanceledOnTouchOutside(false)

            return dialog
        }
    }
}