package com.example.baby.math;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.baby.MainActivity;
import com.example.baby.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MathExamActivity extends AppCompatActivity {

    TextView edit1,edit2,edit3,edit4,edit5,edit6,edit7,edit8,edit9,edit10,edit11,edit12,edit13,edit14;
    EditText result1,result2,result3,result4,result5,result6,result7;
    Button resultButton, backButton;
    int correct = 0;
    int wrong = 0;

    private List<EditText> editTextList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_exam);

        edit1 = findViewById(R.id.exam1_1);
        edit2 = findViewById(R.id.exam1_2);
        edit3 = findViewById(R.id.exam2_1);
        edit4 = findViewById(R.id.exam2_2);
        edit5 = findViewById(R.id.exam3_1);
        edit6 = findViewById(R.id.exam3_2);
        edit7 = findViewById(R.id.exam4_1);
        edit8 = findViewById(R.id.exam4_2);
        edit9 = findViewById(R.id.exam5_1);
        edit10 = findViewById(R.id.exam5_2);
        edit11 = findViewById(R.id.exam6_1);
        edit12 = findViewById(R.id.exam6_2);
        edit13 = findViewById(R.id.exam7_1);
        edit14 = findViewById(R.id.exam7_2);
        result1 = findViewById(R.id.result1);
        result2 = findViewById(R.id.result2);
        result3 = findViewById(R.id.result3);
        result4 = findViewById(R.id.result4);
        result5 = findViewById(R.id.result5);
        result6 = findViewById(R.id.result6);
        result7 = findViewById(R.id.result7);
        resultButton = findViewById(R.id.mathResultButton);
        backButton = findViewById(R.id.mathExamBackButton);

        int[] textViewIds = {
                R.id.exam1_1, R.id.exam1_2, R.id.exam2_1, R.id.exam2_2,
                R.id.exam3_1, R.id.exam3_2, R.id.exam4_1, R.id.exam4_2,
                R.id.exam5_1, R.id.exam5_2, R.id.exam6_1, R.id.exam6_2,
                R.id.exam7_1, R.id.exam7_2
        };

        setRandomNumbers(textViewIds);

        editTextList = new ArrayList<>();
        editTextList.add(result1);
        editTextList.add(result2);
        editTextList.add(result3);
        editTextList.add(result4);
        editTextList.add(result5);
        editTextList.add(result6);
        editTextList.add(result7);

        resultButton.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent(MathExamActivity.this, MathResultActivity.class);
            @Override
            public void onClick(View view) {
                String e1 = edit1.getText().toString();
                String e2 = edit2.getText().toString();
                String e3 = edit3.getText().toString();
                String e4 = edit4.getText().toString();
                String e5 = edit5.getText().toString();
                String e6 = edit6.getText().toString();
                String e7 = edit7.getText().toString();
                String e8 = edit8.getText().toString();
                String e9 = edit9.getText().toString();
                String e10 = edit10.getText().toString();
                String e11 = edit11.getText().toString();
                String e12 = edit12.getText().toString();
                String e13 = edit13.getText().toString();
                String e14 = edit14.getText().toString();
                String r1 = result1.getText().toString();
                String r2 = result2.getText().toString();
                String r3 = result3.getText().toString();
                String r4 = result4.getText().toString();
                String r5 = result5.getText().toString();
                String r6 = result6.getText().toString();
                String r7 = result7.getText().toString();
                if (checkAllEditText()){

                    String correct1 = checkAnswer(Integer.parseInt(e1), Integer.parseInt(e2), Integer.parseInt(r1));
                    String correct2 = checkAnswer(Integer.parseInt(e3), Integer.parseInt(e4), Integer.parseInt(r2));
                    String correct3 = checkAnswer(Integer.parseInt(e5), Integer.parseInt(e6), Integer.parseInt(r3));
                    String correct4 = checkAnswer(Integer.parseInt(e7), Integer.parseInt(e8), Integer.parseInt(r4));
                    String correct5 = checkAnswer(Integer.parseInt(e9), Integer.parseInt(e10), Integer.parseInt(r5));
                    String correct6 = checkAnswer(Integer.parseInt(e11), Integer.parseInt(e12), Integer.parseInt(r6));
                    String correct7 = checkAnswer(Integer.parseInt(e13), Integer.parseInt(e14), Integer.parseInt(r7));

                    intent.putExtra("correct", correct);
                    intent.putExtra("wrong", wrong);
                    intent.putExtra("e1", e1);
                    intent.putExtra("e2", e2);
                    intent.putExtra("e3", e3);
                    intent.putExtra("e4", e4);
                    intent.putExtra("e5", e5);
                    intent.putExtra("e6", e6);
                    intent.putExtra("e7", e7);
                    intent.putExtra("e8", e8);
                    intent.putExtra("e9", e9);
                    intent.putExtra("e10", e10);
                    intent.putExtra("e11", e11);
                    intent.putExtra("e12", e12);
                    intent.putExtra("e13", e13);
                    intent.putExtra("e14", e14);
                    intent.putExtra("correct1", correct1);
                    intent.putExtra("correct2", correct2);
                    intent.putExtra("correct3", correct3);
                    intent.putExtra("correct4", correct4);
                    intent.putExtra("correct5", correct5);
                    intent.putExtra("correct6", correct6);
                    intent.putExtra("correct7", correct7);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "빈칸을 모두 채워 주세요!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MathExamActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean checkAllEditText(){
        boolean allFilled = true;

        for (EditText editText: editTextList){
            String text = editText.getText().toString().trim();

            if (text.isEmpty()){
                allFilled = false;
                break;
            }
        }
        return allFilled;
    }

    private void setRandomNumbers(int[] textViewIds) {
        // 1~9까지의 무작위 숫자를 각 텍스트뷰에 설정
        for (int textViewId : textViewIds) {
            int randomNumber = getRandomNumber(1, 10);
            TextView textView = findViewById(textViewId);
            textView.setText(String.valueOf(randomNumber));
        }
    }

    private int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    private String checkAnswer(int a, int b, int answer){
        int result = a*b;
        if (result == answer){
            correct++;
            return String.valueOf(answer);
        }else {
            wrong++;
            return "X";
        }
    }
}
