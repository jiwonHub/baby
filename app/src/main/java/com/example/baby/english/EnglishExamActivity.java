package com.example.baby.english;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.baby.MainActivity;
import com.example.baby.R;
import com.example.baby.korean.KoreanExamActivity;
import com.example.baby.korean.KoreanResultActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class EnglishExamActivity extends AppCompatActivity {

    Button resultButton, backButton;
    EditText edit1,edit2,edit3;

    private ImageView examImage1, examImage2, examImage3;

    private Integer[] iamgeResources = {
            R.drawable.orange,
            R.drawable.cherry,
            R.drawable.grape,
            R.drawable.melon,
            R.drawable.mango,
            R.drawable.plum
    };

    int correct = 0;
    int wrong = 0;

    private List<Integer> selectedImages;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english_exam);

        edit1 = findViewById(R.id.englishExamEditText1);
        edit2 = findViewById(R.id.englishExamEditText2);
        edit3 = findViewById(R.id.englishExamEditText3);

        examImage1 = findViewById(R.id.englishExamImage1);
        examImage2 = findViewById(R.id.englishExamImage2);
        examImage3 = findViewById(R.id.englishExamImage3);

        resultButton = findViewById(R.id.englishResultButton);

        selectedImages = getRandomImages(6);

        examImage1.setImageResource(selectedImages.get(0));
        examImage2.setImageResource(selectedImages.get(1));
        examImage3.setImageResource(selectedImages.get(2));

        backButton = findViewById(R.id.englishExamBackButton);

        resultButton.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent(EnglishExamActivity.this, EnglishResultActivity.class);

            Integer selected1 = selectedImages.get(0);
            Integer selected2 = selectedImages.get(1);
            Integer selected3 = selectedImages.get(2);
            @Override
            public void onClick(View view) {

                String fruit1 = checkAnswer(edit1, selected1);
                String fruit2 = checkAnswer(edit2, selected2);
                String fruit3 = checkAnswer(edit3, selected3);

                intent.putExtra("selected1", selected1);
                intent.putExtra("selected2", selected2);
                intent.putExtra("selected3", selected3);
                intent.putExtra("fruit1", fruit1);
                intent.putExtra("fruit2", fruit2);
                intent.putExtra("fruit3", fruit3);
                intent.putExtra("correct", correct);
                intent.putExtra("wrong", wrong);
                startActivity(intent);
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EnglishExamActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private List<Integer> getRandomImages(int count){
        List<Integer> result = new ArrayList<>();
        List<Integer> availableImages = new ArrayList<>(Arrays.asList(iamgeResources));

        Random random = new Random();
        for (int i = 0; i < count; i++){
            int randomIndex = random.nextInt(availableImages.size());
            int selectedImage = availableImages.remove(randomIndex);
            result.add(selectedImage);
        }
        return result;
    }

    @SuppressLint("NonConstantResourceId")
    private String checkAnswer(EditText editText, Integer imageViewId) {
        String userAnswer = editText.getText().toString().trim();

        switch (imageViewId){
            case R.drawable.orange:
                if (userAnswer.equals("orange")){
                    correct++;
                    return "orange";
                }else {
                    wrong++;
                    return "틀렸음!";
                }
            case R.drawable.cherry:
                if (userAnswer.equals("cherry")){
                    correct++;
                    return "cherry";
                }else {
                    wrong++;
                    return "틀렸음!";
                }
            case R.drawable.grape:
                if (userAnswer.equals("grape")){
                    correct++;
                    return "grape";
                }else {
                    wrong++;
                    return "틀렸음!";
                }
            case R.drawable.melon:
                if (userAnswer.equals("melon")){
                    correct++;
                    return "melon";
                }else {
                    wrong++;
                    return "틀렸음!";
                }
            case R.drawable.mango:
                if (userAnswer.equals("mango")){
                    correct++;
                    return "mango";
                }else {
                    wrong++;
                    return "틀렸음!";
                }
            case R.drawable.plum:
                if (userAnswer.equals("plum")){
                    correct++;
                    return "plum";
                }else {
                    wrong++;
                    return "틀렸음!";
                }
        }
        return "";
    }
}
