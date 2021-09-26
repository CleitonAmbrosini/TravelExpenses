package com.example.gastodeviagem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import java.lang.NumberFormatException


class MainActivity : AppCompatActivity(), View.OnClickListener {

    var message: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonCalculate.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.buttonCalculate) {
            calculate()
        }
    }

    private fun calculate() {
        if (isValidForm() && !thereAreAZeroValue()) {
            try {
                val distance = editDistance.text.toString().toFloat()
                val price = editPrice.text.toString().toFloat()
                val autonomy = editAutonomy.text.toString().toFloat()
                val totalValue = (distance * price) / autonomy
                textTotalValue.text = "R$ ${"%.2f".format(totalValue)}"
            } catch (nfe: NumberFormatException) {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }

    private fun isValidForm(): Boolean {
        if(editDistance.text.toString() != ""
            && editPrice.text.toString() != ""
            && editAutonomy.text.toString() != "") {
            return true
        }
        message = getString(R.string.preencha_todos_campos)
        return false
    }

    private fun thereAreAZeroValue(): Boolean {
        if(editDistance.text.toString() == "0"
            || editPrice.text.toString() == "0"
            || editAutonomy.text.toString() == "0") {
            message = getString(R.string.informe_valores_validos)
            return true
        }
        return false
    }



}
