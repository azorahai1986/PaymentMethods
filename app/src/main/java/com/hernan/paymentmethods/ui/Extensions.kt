package com.hernan.paymentmethods.ui

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.hernan.paymentmethods.R

fun EditText.validate(validator: (String) -> Boolean, onValidStateChange: (Boolean) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val text = s.toString()
            val isValid = validator(text)
            if (isValid) {
                this@validate.setTextColor(ContextCompat.getColor(context, R.color.black))
            } else {
                this@validate.setTextColor(ContextCompat.getColor(context, R.color.red))
            }
            onValidStateChange(isValid)
        }

        override fun afterTextChanged(s: Editable?) {}
    })
}

fun EditText.creditCardName(
    textView: TextView,
) {
    this.addTextChangedListener(object : TextWatcher {
        private var isFormatting: Boolean = false
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (isFormatting) return
            val text = s.toString().replace(" ", "")
            val formattedText = StringBuilder()

            for (i in text.indices) {
                if (i > 0 && i % 4 == 0) {
                    formattedText.append(" ")
                }
                formattedText.append(text[i])
            }

            isFormatting = true
            setText(formattedText.toString())
            setSelection(formattedText.length)
            isFormatting = false
            textView.text = when {
                text.startsWith("4") -> "Visa"
                text.startsWith("5") -> "MasterCard"
                text.startsWith("3") -> "American Express"
                text.startsWith("6") -> "Discover"
                else -> "Tarjeta Desconocida"
            }
        }
        override fun afterTextChanged(s: Editable?) {}
    })
}

fun EditText.creditCardDate() {
    this.addTextChangedListener(object : TextWatcher {
        private var isFormatting: Boolean = false

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            if (isFormatting) return
            val text = s.toString().replace("/", "")
            val formattedText = text.chunked(2).joinToString("/")
            isFormatting = true
            setText(formattedText)
            setSelection(formattedText.length)
            isFormatting = false
        }
    })
}