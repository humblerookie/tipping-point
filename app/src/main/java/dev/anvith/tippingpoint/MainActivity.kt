package dev.anvith.tippingpoint

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener

class MainActivity : AppCompatActivity() {

    lateinit var billAmount: EditText
    lateinit var tipPercentage: EditText
    lateinit var increaseTip: ImageButton
    lateinit var decreaseTip: ImageButton
    lateinit var totalTip: TextView
    lateinit var totalAmount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeViews()
        addDataChangeListeners()
    }

    private fun initializeViews() {
        billAmount = findViewById(R.id.bill_amount)
        tipPercentage = findViewById(R.id.tip_percentage)
        increaseTip = findViewById(R.id.increase_tip)
        decreaseTip = findViewById(R.id.decrease_tip)

        totalTip = findViewById(R.id.total_tip)
        totalAmount = findViewById(R.id.total_amount)
    }

    private fun addDataChangeListeners() {
        tipPercentage.addTextChangedListener {
            if (it.toString().isNullOrBlank()) {
                tipPercentage.setText("15")
            } else {
                calculateAndUpdateTip()
            }
        }
        billAmount.addTextChangedListener {
            if (it.toString().isNullOrBlank()) {
                billAmount.setText("0")
            } else {
                calculateAndUpdateTip()
            }
        }
        increaseTip.setOnClickListener {
            tipPercentage.setText((tipPercentage.text.toString().toInt() + 1).toString())
        }
        decreaseTip.setOnClickListener {
            with(tipPercentage.text.toString().toInt()) {
                if (this > 0) {
                    tipPercentage.setText((this - 1).toString())
                }
            }
        }
    }

    private fun calculateAndUpdateTip() {
        val percentage = tipPercentage.text.toString().toInt()
        val amount = billAmount.text.toString().toInt()
        val tip = amount * percentage * 0.01
        totalTip.text = getString(R.string.total_tip, tip.toString())
        totalAmount.text = getString(R.string.total_amount, (tip + amount).toString())
    }
}