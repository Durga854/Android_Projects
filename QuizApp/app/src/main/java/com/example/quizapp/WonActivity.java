package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class WonActivity extends AppCompatActivity {
    CircularProgressBar circularProgressBar;
    TextView resultText;
    int correct,wrong,total;
    LinearLayout btnShare;
    ImageView ic_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won);
        circularProgressBar=findViewById(R.id.resultProgressBar);
        resultText=findViewById(R.id.resultText);
        correct=getIntent().getIntExtra("correct",0);
        wrong=getIntent().getIntExtra("wrong",0);
        total=getIntent().getIntExtra("total",0);
        circularProgressBar.setProgressMax(total);
        circularProgressBar.setProgress(correct);
        resultText.setText(correct+"/"+total);
        btnShare=findViewById(R.id.btnShare);
        ic_back=findViewById(R.id.ic_back);
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String message="I got "+correct+"on my quiz";
                    Intent intent= new Intent(Intent.ACTION_SEND);

                    intent.setType("text/plain");
                    intent.setPackage("com.whatsapp");
                    intent.putExtra(Intent.EXTRA_TEXT, message);

                    if (intent.resolveActivity(getPackageManager()) == null) {
                        Toast.makeText(getApplicationContext(),"Please install whatsapp first.",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        startActivity(intent);
                    }
                }
                catch (Exception e){
                    Toast.makeText(WonActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}