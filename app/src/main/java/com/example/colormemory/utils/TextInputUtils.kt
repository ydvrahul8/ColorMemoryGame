package com.example.colormemory.utils

import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.clearOnError() {
    error = null
    isErrorEnabled = false
}