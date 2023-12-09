package com.example.baby.english;

import static com.example.baby.DBKey.DB_RANK;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.baby.MainActivity;
import com.example.baby.R;
import com.example.baby.korean.KoreanResultActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EnglishResultActivity extends AppCompatActivity {

    private DatabaseReference rankDB;
    String userName, fruit1, fruit2, fruit3;
    int rankPont = 0;
    int selected1, selected2, selected3;
    TextView resultCount, resultText1,resultText2,resultText3;
    Button toMainButton, backButton;
    ImageView resultImage1, resultImage2,resultImage3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english_result);

        resultCount = findViewById(R.id.englishResultCount);

        toMainButton = findViewById(R.id.englishToMainButton);
        backButton = findViewById(R.id.englishResultBackButton);

        // 결과 화면 사용자 정답 결과 텍스트 3개
        resultText1 = findViewById(R.id.englishResultEditText1);
        resultText2 = findViewById(R.id.englishResultEditText2);
        resultText3 = findViewById(R.id.englishResultEditText3);

        // 결과 화면 이미지 3개
        resultImage1 = findViewById(R.id.englishResultImage1);
        resultImage2 = findViewById(R.id.englishResultImage2);
        resultImage3 = findViewById(R.id.englishResultImage3);

        SharedPreferences preferences = getSharedPreferences("kakao_user", Context.MODE_PRIVATE);
        userName = preferences.getString("userName", "");

        Intent intent = getIntent();
        int correct = intent.getIntExtra("correct", 0);
        int wrong = intent.getIntExtra("wrong", 0);
        int total = correct + wrong;

        // 사용자 정답 입력 값 3개 받기
        fruit1 = intent.getStringExtra("fruit1");
        fruit2 = intent.getStringExtra("fruit2");
        fruit3 = intent.getStringExtra("fruit3");

        // 주어진 랜덤 이미지 3개 받기
        selected1 = intent.getIntExtra("selected1", -1);
        selected2 = intent.getIntExtra("selected2", -1);
        selected3 = intent.getIntExtra("selected3", -1);

        // 몇개 맞췄는지
        resultCount.setText(String.valueOf(correct));

        // 주어진 랜덤 이미지 3개 설정
        resultImage1.setImageResource(selected1);
        resultImage2.setImageResource(selected2);
        resultImage3.setImageResource(selected3);

        // 결과 화면 사용자 정답 결과 텍스트 3개 설정
        resultText1.setText(fruit1);
        resultText2.setText(fruit2);
        resultText3.setText(fruit3);

        // 틀린문제 빨간색 도색
        if (fruit1.equals("틀렸음!")){
            resultText1.setBackgroundResource(R.drawable.shape_rectangle_red);
        }
        if (fruit2.equals("틀렸음!")){
            resultText2.setBackgroundResource(R.drawable.shape_rectangle_red);
        }
        if (fruit3.equals("틀렸음!")){
            resultText3.setBackgroundResource(R.drawable.shape_rectangle_red);
        }

        // 랭킹 데이터베이스 초기화
        rankDB = FirebaseDatabase.getInstance().getReference().child(DB_RANK).child(userName);
        rankDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // 데이터 가져오기 성공 시 랭킹 포인트 가져오기
                rankPont = snapshot.child("rankPoint").getValue(Integer.class) != null
                        ? snapshot.child("rankPoint").getValue(Integer.class) : 100;
                if (total != 0){
                    rankPont += (10*correct);
                    rankPont -= (10*wrong);
                }
                updateRankPointInFirebase(rankPont);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"error", Toast.LENGTH_SHORT).show();
            }
        });

        toMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EnglishResultActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    // 점수 계산 후 업데이트
    private void updateRankPointInFirebase(int rankPoint){
        rankDB.child("rankPoint").setValue(rankPoint);
        rankDB.child("userName").setValue(userName);
    }
}
