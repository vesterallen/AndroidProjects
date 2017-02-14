package com.example.bmicalculator;

import android.media.MediaCodec;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button calculateBmi;
    private TextView outputBMI;
    private TextView outputWeightStats;
    private EditText inputWeight;
    private EditText inputHeightFeet;
    private EditText inputHeightInches;
    private Double inputWght;
    private Double inputHghtFt;
    private Double inputHghtInchs;
    private Double heightConstant=12.0;
    private Double bmiConstant=703.0;
    private Double bmiOutput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputHeightFeet= (EditText) findViewById(R.id.feetHeight);
        inputHeightInches=(EditText) findViewById(R.id.inchesHeight);
        inputWeight= (EditText) findViewById(R.id.weightInput);
        calculateBmi= (Button) findViewById(R.id.calculateBMI);
        outputBMI= (TextView) findViewById(R.id.bmi_Output);
        outputWeightStats= (TextView) findViewById(R.id.weight_Status_Output);
        calculateBmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!inputWeight.getText().toString().matches(".*\\d+.*")||
                        !inputHeightInches.getText().toString().matches(".*\\d+.*")||
                        !inputHeightFeet.getText().toString().matches(".*\\d+.*")) {
                    Toast.makeText(MainActivity.this, "Enter a proper numeric value", Toast.LENGTH_LONG).show();
                    outputWeightStats.setText("");
                    outputBMI.setText("");
                }
                else {
                    try {
                        inputWght = Double.parseDouble(inputWeight.getText().toString().trim());
                        inputHghtFt = Double.parseDouble(inputHeightFeet.getText().toString().trim());
                        inputHghtInchs = Double.parseDouble(inputHeightInches.getText().toString().trim());
                        if(inputWght<0||inputHghtFt<0||inputHghtInchs<0){
                            Toast.makeText(MainActivity.this, "Enter only positive numeric value", Toast.LENGTH_LONG).show();
                            outputWeightStats.setText("");
                            outputBMI.setText("");
                        }else if(inputWght.equals("")||inputHghtFt.equals("")||inputHghtInchs.equals("")){
                                Toast.makeText(MainActivity.this, "Enter all the fields", Toast.LENGTH_LONG).show();
                                outputWeightStats.setText("");
                                outputBMI.setText("");
                        }
                        else {
                            inputHghtInchs = inputHghtInchs + (inputHghtFt * heightConstant);
                            inputHghtInchs = inputHghtInchs * inputHghtInchs;
                            bmiOutput = (inputWght / inputHghtInchs) * bmiConstant;
                            outputBMI.setText("Your BMI:" + Math.round(bmiOutput * 100.0) / 100.0);
                            if (bmiOutput <= 18.5) {
                                outputWeightStats.setText("You are Underweight");
                            } else if (bmiOutput > 18.5 && bmiOutput < 24.9) {
                                outputWeightStats.setText("You are Normalweight");
                            } else if (bmiOutput > 25.0 && bmiOutput < 29.9) {
                                outputWeightStats.setText("You are Overweight");
                            } else if (bmiOutput >= 30) {
                                outputWeightStats.setText("You have obesity");
                            }
                        }
                    }catch (NumberFormatException ex){
                        Toast.makeText(MainActivity.this, "Enter a proper numeric value", Toast.LENGTH_LONG).show();
                        outputWeightStats.setText("");
                        outputBMI.setText("");
                    }
                }
            }
        });
    }
}
