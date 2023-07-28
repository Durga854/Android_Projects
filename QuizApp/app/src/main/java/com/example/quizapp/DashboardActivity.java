package com.example.quizapp;


import static com.example.quizapp.TopicsActivity.listOfQ;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    CountDownTimer countDownTimer;
    int timerValue=120;
    ModelClass modelClass;
    List<ModelClass> allQuestions;
    ProgressBar progressBar;
    TextView cardQuestion,optionA,optionB,optionC,optionD;
    CardView cardA,cardB,cardC,cardD;
    int index=0;
    int correctCount=0;
    int wrongCount=0;
    int total=0;
    ImageView ic_back;
    LinearLayout nextBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        progressBar=findViewById(R.id.quiz_timer);
        cardQuestion=findViewById(R.id.card_question);
        optionA=findViewById(R.id.card_optiona);
        optionB=findViewById(R.id.card_optionb);
        optionC=findViewById(R.id.card_optionc);
        optionD=findViewById(R.id.card_optiond);
        cardA=findViewById(R.id.cardA);
        cardB=findViewById(R.id.cardB);
        cardC=findViewById(R.id.cardC);
        cardD=findViewById(R.id.cardD);
        nextBtn=findViewById(R.id.nextBtn);
        ic_back=findViewById(R.id.ic_back);
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TopicsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        allQuestions=listOfQ;
        Collections.shuffle(allQuestions);
        modelClass= listOfQ.get(index);

        setAllData();
        countDownTimer=new CountDownTimer(20000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerValue=timerValue-1;
                progressBar.setProgress(timerValue);
            }

            @Override
            public void onFinish() {
                Dialog dialog=new Dialog(DashboardActivity.this);
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);

                dialog.setContentView(R.layout.time_out_dialog);
                dialog.findViewById(R.id.btn_tryagain).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(DashboardActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                });
                dialog.show();
            }
        }.start();
    }
    private void setAllData(){
        cardQuestion.setText(modelClass.getQuestion());
        optionA.setText(modelClass.getoA());
        optionB.setText(modelClass.getoB());
        optionC.setText(modelClass.getoC());
        optionD.setText(modelClass.getoD());
    }
    public void Correct(CardView c){
        disableBtn();
        c.setBackgroundColor(getResources().getColor(R.color.green));
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correctCount++;
                total++;
                if(index<listOfQ.size()-1){
                    index++;
                    resetColor();
                    enableBtn();
                    modelClass=listOfQ.get(index);
                    setAllData();
                }
                else{
                    GameWon();
                }
            }
        });
    }
    public void Wrong(CardView c){
        disableBtn();
        c.setBackgroundColor(getResources().getColor(R.color.red));
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongCount++;
                total++;
                if(index<listOfQ.size()-1){
                    index++;
                    resetColor();
                    enableBtn();
                    modelClass=listOfQ.get(index);
                    setAllData();
                }
                else{
                    GameWon();
                }
            }
        });

    }
    private void GameWon(){
        Intent intent=new Intent(getApplicationContext(),WonActivity.class);
        intent.putExtra("correct",correctCount);
        intent.putExtra("wrong",wrongCount);
        intent.putExtra("total",total);
        startActivity(intent);
    }

    void enableBtn(){
        cardA.setClickable(true);
        cardB.setClickable(true);
        cardC.setClickable(true);
        cardD.setClickable(true);
    }
    void disableBtn(){
        cardA.setClickable(false);
        cardB.setClickable(false);
        cardC.setClickable(false);
        cardD.setClickable(false);
    }
    void resetColor(){
        cardA.setBackgroundColor(getResources().getColor(R.color.white));
        cardB.setBackgroundColor(getResources().getColor(R.color.white));
        cardC.setBackgroundColor(getResources().getColor(R.color.white));
        cardD.setBackgroundColor(getResources().getColor(R.color.white));

    }

    public void optionAClick(View view) {
        if(modelClass.getoA().equals(modelClass.getAns())){
            Correct(cardA);
        }
        else{
            Wrong(cardA);
        }
    }
    public void optionDClick(View view) {
        if(modelClass.getoD().equals(modelClass.getAns())){
            Correct(cardD);
        }
        else{
            Wrong(cardD);
        }
    }
    public void optionCClick(View view) {
        if(modelClass.getoC().equals(modelClass.getAns())){
            Correct(cardC);
        }
        else{
            Wrong(cardC);
        }
    }
    public void optionBClick(View view) {
        if(modelClass.getoB().equals(modelClass.getAns())){
            Correct(cardB);
        }
        else{
            Wrong(cardB);
        }
    }
}