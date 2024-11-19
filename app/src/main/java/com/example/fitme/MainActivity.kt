package com.example.fitme

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val bmi_but = findViewById<Button>(R.id.bmi)
        val wt_et = findViewById<EditText>(R.id.weight)
        val ht_et = findViewById<EditText>(R.id.height)
        //val wt = wt_et.text.toString().toInt()
        //val ht = ht_et.text.toString().toInt()
        bmi_but.setOnClickListener {
            val wtString:String?= wt_et.text.toString()
            val htString:String? = ht_et.text.toString()
            val wt:Float?= wtString?.toFloat()
            val ht:Float?= htString?.toFloat()
            val bmi: String? = (wt?.div((ht!! *ht))).toString()
            if(wtString.isNullOrEmpty() || htString.isNullOrEmpty())
                Toast.makeText(this,"The Input Values are Null",Toast.LENGTH_LONG).show()
            else {
                val intent = Intent(this, result_recommendation::class.java)
                intent.putExtra("BMI", bmi)
                startActivity(intent)
            }
        }
    }
}