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
    int rankPont = 0;
    TextView resultCount;
    Button toMainButton;
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
        String r1 = intent.getStringExtra("r1");
        String r2 = intent.getStringExtra("r2");
        String r3 = intent.getStringExtra("r3");
        String r4 = intent.getStringExtra("r4");
        String r5 = intent.getStringExtra("r5");
        String r6 = intent.getStringExtra("r6");
        String r7 = intent.getStringExtra("r7");
        boolean c1 = intent.getBooleanExtra("correct1", true);
        boolean c2 = intent.getBooleanExtra("correct2", true);
        boolean c3 = intent.getBooleanExtra("correct3", true);
        boolean c4 = intent.getBooleanExtra("correct4", true);
        boolean c5 = intent.getBooleanExtra("correct5", true);
        boolean c6 = intent.getBooleanExtra("correct6", true);
        boolean c7 = intent.getBooleanExtra("correct7", true);

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
        result1.setText(r1);
        result2.setText(r2);
        result3.setText(r3);
        result4.setText(r4);
        result5.setText(r5);
        result6.setText(r6);
        result7.setText(r7);

        if (!c1){
            result1.setBackgroundResource(R.drawable.shape_rectangle_red);
        }
        if (!c2){
            result2.setBackgroundResource(R.drawable.shape_rectangle_red);
        }
        if (!c3){
            result3.setBackgroundResource(R.drawable.shape_rectangle_red);
        }
        if (!c4){
            result4.setBackgroundResource(R.drawable.shape_rectangle_red);
        }
        if (!c5){
            result5.setBackgroundResource(R.drawable.shape_rectangle_red);
        }
        if (!c6){
            result6.setBackgroundResource(R.drawable.shape_rectangle_red);
        }
        if (!c7){
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
