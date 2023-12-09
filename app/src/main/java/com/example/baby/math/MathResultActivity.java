package com.example.baby.math;

import static com.example.baby.DBKey.DB_RANK;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class MathResultActivity extends AppCompatActivity {

    private DatabaseReference rankDB;
    String userName;
    String userId;
    int rankPont = 0;
    TextView resultCount;
    Button toMainButton;
    EditText edit1,edit2,edit3,edit4,edit5,edit6,edit7,edit8,edit9,edit10,edit11,edit12,edit13,edit14;
    EditText result1,result2,result3,result4,result5,result6,result7;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_result);

        edit1 = findViewById(R.id.rexam1_1);
        edit2 = findViewById(R.id.rexam1_2);
        edit3 = findViewById(R.id.rexam2_1);
        edit4 = findViewById(R.id.rexam2_2);
        edit5 = findViewById(R.id.rexam3_1);
        edit6 = findViewById(R.id.rexam3_2);
        edit7 = findViewById(R.id.rexam4_1);
        edit8 = findViewById(R.id.rexam4_2);
        edit9 = findViewById(R.id.rexam5_1);
        edit10 = findViewById(R.id.rexam5_2);
        edit11 = findViewById(R.id.rexam6_1);
        edit12 = findViewById(R.id.rexam6_2);
        edit13 = findViewById(R.id.rexam7_1);
        edit14 = findViewById(R.id.rexam7_2);
        result1 = findViewById(R.id.rresult1);
        result2 = findViewById(R.id.rresult2);
        result3 = findViewById(R.id.rresult3);
        result4 = findViewById(R.id.rresult4);
        result5 = findViewById(R.id.rresult5);
        result6 = findViewById(R.id.rresult6);
        result7 = findViewById(R.id.rresult7);
        resultCount = findViewById(R.id.mathResultCount);
        toMainButton = findViewById(R.id.mathResultButton);

        SharedPreferences preferences = getSharedPreferences("kakao_user", Context.MODE_PRIVATE);
        userName = preferences.getString("userName", "");
        userId = preferences.getString("userId", "");

        Intent intent = getIntent();
        int correct = intent.getIntExtra("correct", 0);
        int wrong = intent.getIntExtra("wrong", 0);

        String e1 = intent.getStringExtra("e1");
        String e2 = intent.getStringExtra("e2");
        String e3 = intent.getStringExtra("e3");
        String e4 = intent.getStringExtra("e4");
        String e5 = intent.getStringExtra("e5");
        String e6 = intent.getStringExtra("e6");
        String e7 = intent.getStringExtra("e7");
        String e8 = intent.getStringExtra("e8");
        String e9 = intent.getStringExtra("e9");
        String e10 = intent.getStringExtra("e10");
        String e11 = intent.getStringExtra("e11");
        String e12 = intent.getStringExtra("e12");
        String e13 = intent.getStringExtra("e13");
        String e14 = intent.getStringExtra("e14");
        String c1 = intent.getStringExtra("correct1");
        String c2 = intent.getStringExtra("correct2");
        String c3 = intent.getStringExtra("correct3");
        String c4 = intent.getStringExtra("correct4");
        String c5 = intent.getStringExtra("correct5");
        String c6 = intent.getStringExtra("correct6");
        String c7 = intent.getStringExtra("correct7");

        int total = correct + wrong;

        edit1.setText(e1);
        edit2.setText(e2);
        edit3.setText(e3);
        edit4.setText(e4);
        edit5.setText(e5);
        edit6.setText(e6);
        edit7.setText(e7);
        edit8.setText(e8);
        edit9.setText(e9);
        edit10.setText(e10);
        edit11.setText(e11);
        edit12.setText(e12);
        edit13.setText(e13);
        edit14.setText(e14);
        result1.setText(c1);
        result2.setText(c2);
        result3.setText(c3);
        result4.setText(c4);
        result5.setText(c5);
        result6.setText(c6);
        result7.setText(c7);

        if (c1.equals("X")){
            result1.setBackgroundResource(R.drawable.shape_rectangle_red);
        }
        if (c2.equals("X")){
            result2.setBackgroundResource(R.drawable.shape_rectangle_red);
        }
        if (c3.equals("X")){
            result3.setBackgroundResource(R.drawable.shape_rectangle_red);
        }
        if (c4.equals("X")){
            result4.setBackgroundResource(R.drawable.shape_rectangle_red);
        }
        if (c5.equals("X")){
            result5.setBackgroundResource(R.drawable.shape_rectangle_red);
        }
        if (c6.equals("X")){
            result6.setBackgroundResource(R.drawable.shape_rectangle_red);
        }
        if (c7.equals("X")){
            result7.setBackgroundResource(R.drawable.shape_rectangle_red);
        }

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
                Intent intent = new Intent(MathResultActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void updateRankPointInFirebase(int rankPoint){
        rankDB.child("rankPoint").setValue(rankPoint);
        rankDB.child("userName").setValue(userName);
    }
}
