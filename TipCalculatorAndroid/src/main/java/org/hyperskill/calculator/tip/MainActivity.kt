package org.hyperskill.calculator.tip

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.slider.Slider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*val textView: TextView = findViewById(R.id.text_view)
        val editText: EditText = findViewById(R.id.edit_text)
        val seekBar: Slider = findViewById(R.id.slider)*/

        edit_text.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    calculate()
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }
            }
        )

        slider.addOnChangeListener(Slider.OnChangeListener { _, _, _ -> calculate() })
    }

    fun calculate(){
        val text = edit_text.text.toString()
        if(text == "")
            text_view.text = null
        else {
            val calc = (text.toDouble() * slider.value / 100)
            text_view.text = ("Tip amount: ${String.format("%.2f", calc)}")
        }
    }
}