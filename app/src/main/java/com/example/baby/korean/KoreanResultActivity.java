package com.example.baby.korean;

import static com.example.baby.DBKey.DB_RANK;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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


public class KoreanResultActivity extends AppCompatActivity {

    private DatabaseReference rankDB;
    String userName;
    int rankPont = 0;
    TextView resultCount;
    Button toMainButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_korean_result);

        resultCount = findViewById(R.id.koreanResultCount);
        toMainButton = findViewById(R.id.toMainButton);

        SharedPreferences preferences = getSharedPreferences("kakao_user", Context.MODE_PRIVATE);
        userName = preferences.getString("userName", "");

        Intent intent = getIntent();
        int correct = intent.getIntExtra("correct", 0);
        int wrong = intent.getIntExtra("wrong", 0);
        int total = correct + wrong;

        resultCount.setText(String.valueOf(correct));

        rankDB = FirebaseDatabase.getInstance().getReference().child(DB_RANK).child(userName);
        rankDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
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
                Intent intent = new Intent(KoreanResultActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void updateRankPointInFirebase(int rankPoint){
        rankDB.child("rankPoint").setValue(rankPoint);
        rankDB.child("userName").setValue(userName);
    }
}
