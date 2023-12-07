package com.example.baby.korean;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.baby.R;

public class KoreanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_korean);

        TextView koreanStudy = findViewById(R.id.koreanStudy);
        TextView koreanExam = findViewById(R.id.koreanExam);

        koreanStudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KoreanActivity.this, KoreanStudyActivity.class);
                startActivity(intent);
            }
        });
        koreanExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KoreanActivity.this, KoreanExamActivity.class);
                startActivity(intent);
            }
        });

    }
}
