package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
     //class variables also are called 'fields'

    private RadioButton male_button;
    private RadioButton female_button;
    private Button calculate_button;
    private TextView resultText1;
    private EditText feet_button;
    private EditText weight_button;
    private EditText inches_button;
    private EditText age_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         findViews();
         setUpButtonClickListener();

    }
    private void findViews(){
        //ctrl + alt + f
        resultText1      = findViewById(R.id.text_view_result);
        male_button      = findViewById(R.id.radio_button_male);
        female_button    = findViewById(R.id.radio_button_female);
        feet_button      = findViewById(R.id.edit_text_feet);
        inches_button    = findViewById(R.id.edit_text_inches);
        weight_button    = findViewById(R.id.edit_text_weight);
        age_button       = findViewById(R.id.edit_text_age);
        calculate_button = findViewById(R.id.button_calculate);
    }
    private void setUpButtonClickListener() {
        calculate_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            double bmiResult =   calculateBmi();
                String ageText = age_button.getText().toString();
                int age = Integer.parseInt(ageText);

                if(age>=18){
                    displayResult(bmiResult);
                }
                else{
                    displayGuidance(bmiResult);
                }

        }
    }
        );


}


    private double calculateBmi() {

        String feetText = feet_button.getText().toString();
        String inchesText = inches_button.getText().toString();
        String weightText = weight_button.getText().toString();

        //Converting the number 'Strings' into 'int' variables

        int feet = Integer.parseInt(feetText);
        int inches = Integer.parseInt(inchesText);
        int weight = Integer.parseInt(weightText);

        int totalInches = (feet * 12) + inches;
        double heightInMeters = totalInches * 0.0254;
        return weight / (heightInMeters * heightInMeters);
    }
        private void displayResult(double bmi){
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String fullResultString;
        if(bmi<18.5){
            //Display underweight
            fullResultString = bmiTextResult + " - You are underweight";

        }else if(bmi > 25){
            // Display overweight
            fullResultString = bmiTextResult + "- You are overweight";
        }

        else {

            fullResultString = bmiTextResult + "- You are a healthy weight";
        }
      resultText1.setText(fullResultString);


    }
    private void displayGuidance(double bmi){
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String fullResultString;
        if (male_button.isChecked()){
            //Display boy guidance
            fullResultString = bmiTextResult + " - As you are under 18 , please consult with your doctor for the healthy range of boys "    ;    }
         else if(female_button.isChecked()){
             //Display girl guidance
            fullResultString = bmiTextResult + " - As you are under 18 , please consult with your doctor for the healthy range of girls ";
        }else{
             //Display general guidance
            fullResultString = bmiTextResult + " - As you are under 18 , please consult with your doctor for the healthy range ";
        }
        resultText1.setText(fullResultString);
    }
    }