package com.example.baby.math;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.baby.R;

import java.util.ArrayList;
import java.util.List;

public class MathExamActivity extends AppCompatActivity {

    EditText edit1;
    EditText edit2;
    EditText edit3;
    EditText edit4;
    EditText edit5;
    EditText edit6;
    EditText edit7;
    EditText edit8;
    EditText edit9;
    EditText edit10;
    EditText edit11;
    EditText edit12;
    EditText edit13;
    EditText edit14;
    EditText result1;
    EditText result2;
    EditText result3;
    EditText result4;
    EditText result5;
    EditText result6;
    EditText result7;
    Button resultButton;
    boolean correct1 = true;
    boolean correct2 = true;
    boolean correct3 = true;
    boolean correct4 = true;
    boolean correct5 = true;
    boolean correct6 = true;
    boolean correct7 = true;

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

        editTextList = new ArrayList<>();
        editTextList.add(edit1);
        editTextList.add(edit2);
        editTextList.add(edit3);
        editTextList.add(edit4);
        editTextList.add(edit5);
        editTextList.add(edit6);
        editTextList.add(edit7);
        editTextList.add(edit8);
        editTextList.add(edit9);
        editTextList.add(edit10);
        editTextList.add(edit11);
        editTextList.add(edit12);
        editTextList.add(edit13);
        editTextList.add(edit14);
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
                    if (Integer.parseInt(e1)*Integer.parseInt(e2) == Integer.parseInt(r1)){
                        correct++;
                        correct1 = true;
                    }else{
                        wrong++;
                        correct1 = false;
                    }
                    if (Integer.parseInt(e3)*Integer.parseInt(e4) == Integer.parseInt(r2)){
                        correct++;
                        correct2 = true;
                    }else{
                        wrong++;
                        correct2 = false;
                    }
                    if (Integer.parseInt(e5)*Integer.parseInt(e6) == Integer.parseInt(r3)){
                        correct++;
                        correct3 = true;
                    }else{
                        wrong++;
                        correct3 = false;
                    }
                    if (Integer.parseInt(e7)*Integer.parseInt(e8) == Integer.parseInt(r4)){
                        correct++;
                        correct4 = true;
                    }else{
                        wrong++;
                        correct4 = false;
                    }
                    if (Integer.parseInt(e9)*Integer.parseInt(e10) == Integer.parseInt(r5)){
                        correct++;
                        correct5 = true;
                    }else{
                        wrong++;
                        correct5 = false;
                    }
                    if (Integer.parseInt(e11)*Integer.parseInt(e12) == Integer.parseInt(r6)){
                        correct++;
                        correct6 = true;
                    }else{
                        wrong++;
                        correct6 = false;
                    }
                    if (Integer.parseInt(e13)*Integer.parseInt(e14) == Integer.parseInt(r7)){
                        correct++;
                        correct7 = true;
                    }else{
                        wrong++;
                        correct7 = false;
                    }
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
                    intent.putExtra("r1", r1);
                    intent.putExtra("r2", r2);
                    intent.putExtra("r3", r3);
                    intent.putExtra("r4", r4);
                    intent.putExtra("r5", r5);
                    intent.putExtra("r6", r6);
                    intent.putExtra("r7", r7);
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
}
