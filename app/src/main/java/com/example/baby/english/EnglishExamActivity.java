package com.example.baby.english;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.baby.R;
import com.example.baby.korean.KoreanExamActivity;
import com.example.baby.korean.KoreanResultActivity;

public class EnglishExamActivity extends AppCompatActivity {

    Button resultButton;
    EditText orangeEditText;
    EditText cherryEditText;
    EditText grapeEditText;
    int correct = 0;
    int wrong = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english_exam);

        orangeEditText = findViewById(R.id.orangeEditText);
        cherryEditText = findViewById(R.id.cherryEditText);
        grapeEditText = findViewById(R.id.grapeEditText);

        resultButton = findViewById(R.id.englishResultButton);
        Button backButton = findViewById(R.id.englishExamBackButton);

        resultButton.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent(EnglishExamActivity.this, EnglishResultActivity.class);
            @Override
            public void onClick(View view) {
                if (orangeEditText.getText().toString().equals("orange")){
                    correct++;
                }else{
                    wrong++;
                }
                if (cherryEditText.getText().toString().equals("cherry")){
                    correct++;
                }else{
                    wrong++;
                }
                if (grapeEditText.getText().toString().equals("grape")){
                    correct++;
                }else{
                    wrong++;
                }
                intent.putExtra("correct", correct);
                intent.putExtra("wrong", wrong);
                startActivity(intent);
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
