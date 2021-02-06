package com.example.colormemory.view.common.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.DialogFragment
import com.example.colormemory.R
import com.example.colormemory.utils.clearOnError
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegisterDialog : DialogFragment() {

    private var okButtonClick: (String) -> Unit = {}
    private var cancelButtonClick: () -> Unit = {}

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = activity?.layoutInflater?.inflate(R.layout.layout_pop_dialog, null)
        val builder = AlertDialog.Builder(activity ?: return this.dialog!!)
        builder.setView(view).setCancelable(false)

        val dialog = builder.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setGravity(Gravity.CENTER)
        init(view)
        return dialog
    }

    private fun init(view: View?) {

        view?.findViewById<TextView>(R.id.textView_OK)?.setOnClickListener {
            val name = view.findViewById<TextInputEditText>(R.id.tie_name)
            val til_name = view.findViewById<TextInputLayout>(R.id.til_name)
            attachErrorListener(name, til_name)

            if (isValid(name, til_name)) {
                dismiss()
                okButtonClick(name.text.toString())
            }
        }

        view?.findViewById<TextView>(R.id.textView_cancel)?.setOnClickListener {
            dismiss()
            cancelButtonClick()
        }

    }

    private fun attachErrorListener(
        name: TextInputEditText,
        tilName: TextInputLayout
    ) {
        name.doOnTextChanged { _, _, _, _ -> tilName.clearOnError() }
    }

    private fun isValid(
        name: TextInputEditText,
        tilName: TextInputLayout
    ): Boolean {
        return when {
            TextUtils.isEmpty(name.text) -> {
                tilName.error = resources.getString(R.string.empty_name_error)
                false
            }
            name.text?.length!! < 3 -> {
                tilName.error = resources.getString(R.string.name_size_error)
                false
            }
            else -> true
        }
    }

    class Builder {

        private var okButtonClick: (String) -> Unit = {}
        private var cancelButtonClick: () -> Unit = {}
        private var isCancellable = false

        fun okButtonClick(block: (String) -> Unit): Builder {
            okButtonClick = block
            return this
        }

        fun cancelButtonClick(block: () -> Unit): Builder {
            cancelButtonClick = block
            return this
        }

        fun isCancellable(isCancellable: Boolean): Builder {
            this.isCancellable = isCancellable
            return this
        }

        fun build(): RegisterDialog {
            val fragment = RegisterDialog()

            fragment.okButtonClick = okButtonClick
            fragment.cancelButtonClick = cancelButtonClick
            fragment.isCancelable = isCancellable

            return fragment
        }

    }

}