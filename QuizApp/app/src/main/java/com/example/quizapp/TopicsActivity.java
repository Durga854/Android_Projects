package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class TopicsActivity extends AppCompatActivity {

    CardView opps,dbms,os,cn;
    ImageView ic_back;
    public static ArrayList<ModelClass> listOfQ;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);
        opps=findViewById(R.id.oops);
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
        os=findViewById(R.id.os);
        dbms=findViewById(R.id.dbms);
        cn=findViewById(R.id.cn);
        opps.setClickable(true);
        dbms.setClickable(true);
        os.setClickable(true);
        cn.setClickable(true);

        opps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listOfQ=new ArrayList<>();
                databaseReference= FirebaseDatabase.getInstance().getReference("OOPS");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for( DataSnapshot dataSnapshot:snapshot.getChildren()){
                            ModelClass modelClass=dataSnapshot.getValue(ModelClass.class);
                            listOfQ.add(modelClass);
                        }
                        Intent intent=new Intent(getApplicationContext(),DashboardActivity.class);
                        startActivity(intent);
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        dbms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listOfQ=new ArrayList<>();
                databaseReference= FirebaseDatabase.getInstance().getReference("DBMS");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for( DataSnapshot dataSnapshot:snapshot.getChildren()){
                            ModelClass modelClass=dataSnapshot.getValue(ModelClass.class);
                            Toast.makeText(TopicsActivity.this, modelClass.getQuestion(), Toast.LENGTH_SHORT).show();
                            listOfQ.add(modelClass);
                        }
                        Intent intent=new Intent(getApplicationContext(),DashboardActivity.class);
                        startActivity(intent);
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        os.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listOfQ=new ArrayList<>();
                databaseReference= FirebaseDatabase.getInstance().getReference("OS");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for( DataSnapshot dataSnapshot:snapshot.getChildren()){
                            ModelClass modelClass=dataSnapshot.getValue(ModelClass.class);
                            Toast.makeText(TopicsActivity.this, modelClass.getQuestion(), Toast.LENGTH_SHORT).show();
                            listOfQ.add(modelClass);
                        }
                        Intent intent=new Intent(getApplicationContext(),DashboardActivity.class);
                        startActivity(intent);
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        cn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listOfQ=new ArrayList<>();
                databaseReference= FirebaseDatabase.getInstance().getReference("CN");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for( DataSnapshot dataSnapshot:snapshot.getChildren()){
                            ModelClass modelClass=dataSnapshot.getValue(ModelClass.class);
                            Toast.makeText(TopicsActivity.this, modelClass.getQuestion(), Toast.LENGTH_SHORT).show();
                            listOfQ.add(modelClass);
                        }
                        Intent intent=new Intent(getApplicationContext(),DashboardActivity.class);
                        startActivity(intent);
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }
}