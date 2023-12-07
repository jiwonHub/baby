package com.example.baby.korean;

import static com.example.baby.DBKey.DB_RANK;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.baby.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class KoreanExamActivity extends AppCompatActivity {
    Button resultButton;
    int correct = 0;
    int wrong = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_korean_exam);

        EditText peachEdit = findViewById(R.id.peachEditText);
        EditText pineAppleEdit = findViewById(R.id.pineAppleEditText);
        EditText bananaEdit = findViewById(R.id.bananaEditText);

        resultButton = findViewById(R.id.resultButton);

        resultButton.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent(KoreanExamActivity.this, KoreanResultActivity.class);
            @Override
            public void onClick(View view) {
                if (peachEdit.getText().toString().equals("복숭아")){
                    correct++;
                }else{
                    wrong++;
                }
                if (pineAppleEdit.getText().toString().equals("파인애플")){
                    correct++;
                }else{
                    wrong++;
                }
                if (bananaEdit.getText().toString().equals("바나나")){
                    correct++;
                }else{
                    wrong++;
                }
                intent.putExtra("correct", correct);
                intent.putExtra("wrong", wrong);
                startActivity(intent);
            }
        });
    }


}
