package com.example.newhomeapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.mozilla.javascript.Scriptable

class Calculator : Fragment() {

    private lateinit var btn_1: ImageView
    private lateinit var btn_2: ImageView
    private lateinit var btn_3: ImageView
    private lateinit var btn_4: ImageView
    private lateinit var btn_5: ImageView
    private lateinit var btn_6: ImageView
    private lateinit var btn_7: ImageView
    private lateinit var btn_8: ImageView
    private lateinit var btn_9: ImageView
    private lateinit var btn_0: ImageView
    private lateinit var btn_dot: ImageView
    private lateinit var btn_equal: ImageView
    private lateinit var btn_ac: ImageView
    private lateinit var btn_module: ImageView
    private lateinit var btn_slash: ImageView
    private lateinit var btn_plus: ImageView
    private lateinit var btn_minus: ImageView
    private lateinit var btn_multiplication: ImageView

    private lateinit var inputTxt: TextView
    private lateinit var outputTxt: TextView

    private var data: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the weather fragment layout
        val view = inflater.inflate(R.layout.activity_calculator, container, false)
        outputTxt = view.findViewById(R.id.outputTxt)
        inputTxt = view.findViewById(R.id.inputTxt)

        btn_0 = view.findViewById(R.id.btn_0)
        btn_1 = view.findViewById(R.id.btn_1)
        btn_2 = view.findViewById(R.id.btn_2)
        btn_3 = view.findViewById(R.id.btn_3)
        btn_4 = view.findViewById(R.id.btn_4)
        btn_5 = view.findViewById(R.id.btn_5)
        btn_6 = view.findViewById(R.id.btn_6)
        btn_7 = view.findViewById(R.id.btn_7)
        btn_8 = view.findViewById(R.id.btn_8)
        btn_9 = view.findViewById(R.id.btn_9)

        btn_dot = view.findViewById(R.id.btn_dot)
        btn_equal = view.findViewById(R.id.btn_equal)
        btn_ac = view.findViewById(R.id.btn_ac)
        btn_module = view.findViewById(R.id.btn_module)
        btn_slash = view.findViewById(R.id.btn_slash)
        btn_plus = view.findViewById(R.id.btn_plus)
        btn_minus = view.findViewById(R.id.btn_minus)
        btn_multiplication = view.findViewById(R.id.btn_multiplication)

        btn_0.setOnClickListener {
            data += "0"
            inputTxt.text = data
        }

        btn_1.setOnClickListener {
            data += "1"
            inputTxt.text = data
        }

        btn_2.setOnClickListener {
            data += "2"
            inputTxt.text = data
        }

        btn_3.setOnClickListener {
            data += "3"
            inputTxt.text = data
        }

        btn_4.setOnClickListener {
            data += "4"
            inputTxt.text = data
        }

        btn_5.setOnClickListener {
            data += "5"
            inputTxt.text = data
        }

        btn_6.setOnClickListener {
            data += "6"
            inputTxt.text = data
        }

        btn_7.setOnClickListener {
            data += "7"
            inputTxt.text = data
        }

        btn_8.setOnClickListener {
            data += "8"
            inputTxt.text = data
        }

        btn_9.setOnClickListener {
            data += "9"
            inputTxt.text = data
        }

        btn_ac.setOnClickListener {
            inputTxt.text = ""
            outputTxt.text = ""
            data = ""
        }

        btn_dot.setOnClickListener {
            data += "."
            inputTxt.text = data
        }

        btn_plus.setOnClickListener {
            data += "+"
            inputTxt.text = data
        }

        btn_minus.setOnClickListener {
            data += "-"
            inputTxt.text = data
        }

        btn_module.setOnClickListener {
            data += "%"
            inputTxt.text = data
        }

        btn_slash.setOnClickListener {
            data += "÷"
            inputTxt.text = data
        }

        btn_multiplication.setOnClickListener {
            data += "×"
            inputTxt.text = data
        }

        btn_equal.setOnClickListener {
            if (data.isNotEmpty()) {
                var finalResult = ""
                val rhino: org.mozilla.javascript.Context = org.mozilla.javascript.Context.enter()
                rhino.optimizationLevel = -1
                val scriptable: Scriptable = rhino.initStandardObjects()
                data = data.replace("×".toRegex(), "*")
                data = data.replace("%".toRegex(), "/100")
                data = data.replace("÷".toRegex(), "/")
                finalResult =
                    rhino.evaluateString(scriptable, data, "JavaScript", 1, null).toString()

                val resultInt = finalResult.toDouble().toInt() // Convert the result to an integer

                outputTxt.text = resultInt.toString()
            } else {
                outputTxt.text = ""
            }
        }

        return view
    }
}
