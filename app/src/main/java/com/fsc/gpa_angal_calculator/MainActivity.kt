/*
        ALBERT ANG
        HOME PROJECT 1
        9/25/2022

 */
package com.fsc.gpa_angal_calculator

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import java.lang.Exception


class MainActivity : AppCompatActivity(){
    // access the constrain layout
    private lateinit var myScreen:ConstraintLayout

    // grab the textview for the output of GPA
    private lateinit var myResultText:TextView

    // get the button
    lateinit var myButton:Button

    // store the fields to array list
    private var creditInputs = arrayListOf<EditText>()
    private var gradeInputs = arrayListOf<EditText>()

    // counter for how many taps user taps the button
    private var buttonTap:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get the app screen
        myScreen = findViewById(R.id.appScreen)

        // assign the textview
        myResultText =findViewById(R.id.txt_info)

        // get the button view
        myButton = findViewById(R.id.btnCalc)

        // get all input fields for credit
        creditInputs.add(findViewById(R.id.inp_num_credit_1))
        creditInputs.add(findViewById(R.id.inp_num_credit_2))
        creditInputs.add(findViewById(R.id.inp_num_credit_3))
        creditInputs.add(findViewById(R.id.inp_num_credit_4))
        creditInputs.add(findViewById(R.id.inp_num_credit_5))

        // get all input fields for grade
        gradeInputs.add(findViewById(R.id.inp_num_grade))
        gradeInputs.add(findViewById(R.id.inp_num_grade2))
        gradeInputs.add(findViewById(R.id.inp_num_grade3))
        gradeInputs.add(findViewById(R.id.inp_num_grade4))
        gradeInputs.add(findViewById(R.id.inp_num_grade5))

        for(q in 0..4) {
            creditInputs[q].addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    myButton.text = "Calculate GPA"
                }

            })
        }

        for(w in 0..4) {
            gradeInputs[w].addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    myButton.text = "Calculate GPA"
                }

            })
        }

    }

    // this method is for the calculate GPA button
    fun gradeSelectedFun(v:View) {
        if (buttonTap == 0) {
            // keep track of how many fields are empty before proceeding to calculation
            var numCredEmpty: Int = 0
            var numGradeEmpty = 0

            // assign variables to store sum of all inputs
            var totalCred: Double = 0.0
            var calcGpa:Double = 0.0

            // loop though the fields and check of they are empty
            for(a in creditInputs.indices) {
                if(creditInputs[a].text.isEmpty()) {
                    numCredEmpty ++
                }
            }

            for(b in gradeInputs.indices) {
                if(gradeInputs[b].text.isEmpty()) {
                    numGradeEmpty++
                }
            }

            if( (numCredEmpty > 0) || (numGradeEmpty > 0)) {
                Toast.makeText(applicationContext,"Please enter credit and grade\nto the empty field(s)",Toast.LENGTH_LONG).show()
            }else{
                buttonTap = 1
                myButton.text = "Clear"

                // add all the total credits (for the 4.0 scale)
                for(x in creditInputs.indices) {
                    totalCred += (creditInputs[x].text.toString().toDouble())
                }

                // calculate gpa
                for(z in 0..4) {
                    calcGpa += (creditInputs[z].text.toString().toDouble() * gradeInputs[z].text.toString().toDouble())
                }

                // set the background screen based on user's gpa
                if(calcGpa <= 60) {
                    myScreen.setBackgroundColor(Color.RED)
                }else if(calcGpa in 61.0..79.0) {
                    myScreen.setBackgroundColor(Color.YELLOW)
                }else{
                    myScreen.setBackgroundColor(Color.GREEN)
                }

                // output the gpa and 4 point scale
                myResultText.text = "GPA: $calcGpa\n4.0: ${calcGpa/totalCred}"

            }
        }else {
            // To clear out all the fields
            for (credField in creditInputs.indices) {
                creditInputs[credField].setText("")
            }
            for (gradeField in gradeInputs.indices) {
                gradeInputs[gradeField].setText("")
            }
            myScreen.setBackgroundColor(Color.WHITE)
            buttonTap = 0
        }


    }


}