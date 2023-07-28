package com.example.temperatureconvertor;

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

    RadioButton toFahr,toKel;
    Button submit;
    EditText text;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toFahr=findViewById(R.id.tofahr);
        toKel=findViewById(R.id.tokelvin);
        submit=findViewById(R.id.Submit);
        text=findViewById(R.id.editTextText);
        result=findViewById(R.id.textView);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp=text.getText().toString();
                if(temp.equals("")){
                    Toast.makeText(MainActivity.this, "Enter the temperature", Toast.LENGTH_SHORT).show();
                    return;
                }
                DecimalFormat dfTenth = new DecimalFormat("#.#");
                double celsius = Double.parseDouble(temp);
                if(toFahr.isChecked()){
                    double fahrenheit=celsius*1.8+32;
                    result.setText(dfTenth.format(fahrenheit));
                } else if (toKel.isChecked()) {
                    double kelvin=celsius+273;
                    result.setText(dfTenth.format(kelvin));
                }
            }
        });
    }
}