package com.example.fitme

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class result_recommendation : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result_recommendation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Intent intent = getIntent()
        val bmi_res = findViewById<TextView>(R.id.bmi_res)
        var bmi: String? = intent.getStringExtra("BMI")
        bmi_res.text=bmi
        val bt_food = findViewById<Button>(R.id.rec_food)
        val bt_exercise = findViewById<Button>(R.id.rec_exercise)

        val queue = Volley.newRequestQueue(this)

        bt_food.setOnClickListener {
            makeApiRequest(queue, bmi!!, "food") // Call API with BMI and "food" tag
        }

        bt_exercise.setOnClickListener {
            makeApiRequest(queue, bmi!!, "exercise") // Call API with BMI and "exercise" tag
        }
    }

    private fun makeApiRequest(queue: RequestQueue, bmi: String, type: String) {
        //val url = "https://font-notebook-smaller-likelihood.trycloudflare.com/predict?bmi=$bmi"
        val url ="https://pizza-give-books-star.trycloudflare.com/predict?bmi=$bmi"

        // Create the JSON request
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                val prediction = response.getString("prediction")
                // Handle the prediction response based on the "type" (food/exercise)
                handlePrediction(prediction, type) // Pass prediction and type for handling
            },
            Response.ErrorListener { error ->
                Toast.makeText(this@result_recommendation, "Error: ${error.message}", Toast.LENGTH_LONG).show()
                // Handle API error
            })

        // Add the request to the queue
        queue.add(jsonObjectRequest)
    }
    @SuppressLint("SetTextI18n", "SuspiciousIndentation")
    private fun handlePrediction(prediction: String, type: String) {
        // Implement logic to handle the prediction based on type (food/exercise)
        // You can update UI elements, display recommendations, or perform other actions
        // Here's a basic example using Toast:
        //val message = "Prediction for $type: $prediction"
        //Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        val food_txt = findViewById<TextView>(R.id.food)
        val exercise_txt = findViewById<TextView>(R.id.exercise)

        if(type =="food")
        {
            if (prediction.toInt() == 0)
                food_txt.text="Balanced Diet"
                //Log.d("food","Balanced Diet")
            else if (prediction.toInt() == 1)
                food_txt.text="Lean Proteins and Vegetables"
                //Log.d("food","Lean Proteins and Vegetables")
            else if (prediction.toInt() == 2)
                food_txt.text="Whole Grains and Fruits"
                //Log.d("food","Whole Grains and Fruits")
            else if (prediction.toInt() == 3)
                food_txt.text="Reduced Carbohydrates"
                //Log.d("food","Reduced Carbohydrates")
            else if (prediction.toInt() == 4)
                food_txt.text="Moderate Carbohydrates"
                //Log.d("food","Moderate Carbohydrates")
            else if (prediction.toInt() == 5)
                food_txt.text="High Protein,LowFat"
                //Log.d("food","High Protein,LowFat")
        }
        else if(type == "exercise")
        {
            if (prediction.wtoInt() == 0)
                exercise_txt.text="Strength Training, Moderate Cardio,Jogging"
                //Log.d("exercise","Strength Training, Moderate Cardio,Jogging")
            else if (prediction.toInt() == 1)
                exercise_txt.text="Balanced Exercise', 'Flexibility and Mobility','Light-Weight Dumbell Exercise"
                //Log.d("exercise","Balanced Exercise', 'Flexibility and Mobility','Light-Weight Dumbell Exercise")
            else if (prediction.toInt() == 2)
                exercise_txt.text="Cardio Exercise', 'Strength Training', 'Weight Lifting Exercises"
                //Log.d("exercise","Cardio Exercise', 'Strength Training', 'Weight Lifting Exercises")
            else if (prediction.toInt() == 3)
                exercise_txt.text="Low-impact Cardio, Strength Training"
                //Log.d("exercise","Low-impact Cardio, Strength Training")
            else if (prediction.toInt() == 4)
                exercise_txt.text="Flexibility','High Mobility"
                //Log.d("exercise","Flexibility','High Mobility")
            else if (prediction.toInt() == 5)
                exercise_txt.text="Contact General Physician"
                Log.d("exercise","Personal Fitness Trainer Required")
        }
    }
}
