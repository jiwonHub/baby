package com.example.baby.korean;

import static com.example.baby.DBKey.DB_RANK;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.baby.MainActivity;
import com.example.baby.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class KoreanExamActivity extends AppCompatActivity {
    private ImageView examImage1, examImage2, examImage3;

    private Integer[] iamgeResources = {
        R.drawable.peach,
        R.drawable.pine_apple,
        R.drawable.banana,
        R.drawable.apple,
        R.drawable.watermelon,
        R.drawable.strawberry
    };
    Button resultButton, backButton;
    EditText edit1, edit2, edit3;

    Map<Integer, String> imageToAnswerMap = new HashMap<>();
    int correct = 0;
    int wrong = 0;
    private List<Integer> selectedImages;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_korean_exam);

        edit1 = findViewById(R.id.koreanExamEditText1);
        edit2 = findViewById(R.id.koreanExamEditText2);
        edit3 = findViewById(R.id.koreanExamEditText3);

        examImage1 = findViewById(R.id.koreanExamImage1);
        examImage2 = findViewById(R.id.koreanExamImage2);
        examImage3 = findViewById(R.id.koreanExamImage3);

        resultButton = findViewById(R.id.resultButton);
        selectedImages = getRandomImages(6);

        examImage1.setImageResource(selectedImages.get(0));
        examImage2.setImageResource(selectedImages.get(1));
        examImage3.setImageResource(selectedImages.get(2));

        backButton = findViewById(R.id.koreanExamBackButton);

        resultButton.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent(KoreanExamActivity.this, KoreanResultActivity.class);
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
                Intent intent = new Intent(KoreanExamActivity.this, MainActivity.class);
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
            case R.drawable.peach:
                if (userAnswer.equals("복숭아")){
                    correct++;
                    return "복숭아";
                }else {
                    wrong++;
                    return "틀렸음!";
                }
            case R.drawable.pine_apple:
                if (userAnswer.equals("파인애플")){
                    correct++;
                    return "파인애플";
                }else {
                    wrong++;
                    return "틀렸음!";
                }
            case R.drawable.banana:
                if (userAnswer.equals("바나나")){
                    correct++;
                    return "바나나";
                }else {
                    wrong++;
                    return "틀렸음!";
                }
            case R.drawable.apple:
                if (userAnswer.equals("사과")){
                    correct++;
                    return "사과";
                }else {
                    wrong++;
                    return "틀렸음!";
                }
            case R.drawable.watermelon:
                if (userAnswer.equals("수박")){
                    correct++;
                    return "수박";
                }else {
                    wrong++;
                    return "틀렸음!";
                }
            case R.drawable.strawberry:
                if (userAnswer.equals("딸기")){
                    correct++;
                    return "딸기";
                }else {
                    wrong++;
                    return "틀렸음!";
                }
        }
        return "";
    }

}
