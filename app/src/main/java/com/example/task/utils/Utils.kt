package com.example.task.utils

import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputLayout

class Utils {
    companion object{
        fun isAnyFieldEmpty(requiredFields: List<TextInputLayout>): Boolean {
            var isEmpty = false

            requiredFields.forEach { item ->
                if (item.editText?.text.isNullOrBlank()) {
                    item.error = "required"
                    isEmpty = true
                }
            }

            return isEmpty
        }
    }
}
fun TextInputLayout.setRequired(){
    editText?.addTextChangedListener {
        if (it.isNullOrBlank()) {
            this.error = "Required"
        } else {
            this.error = null
        }
    }
}