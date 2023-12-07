package com.example.baby.my;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.baby.DBKey;
import com.example.baby.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.common.KakaoSdk;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

public class MyActivity extends AppCompatActivity {


    private TextView profileName;
    private TextView profileDate;
    private ImageView profileImage;
    private RecyclerView recyclerView;
    private DatabaseReference rankDB;
    private List<RankModel> rankList = new ArrayList<>();
    private RankAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        profileName = findViewById(R.id.profileName);
        profileDate = findViewById(R.id.profileDate);
        profileImage = findViewById(R.id.profileImage);

        rankDB = FirebaseDatabase.getInstance().getReference().child(DBKey.DB_RANK);
        recyclerView = findViewById(R.id.rankRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RankAdapter(rankList);
        recyclerView.setAdapter(adapter);

        SharedPreferences preferences = getSharedPreferences("kakao_user", Context.MODE_PRIVATE);

        Calendar currentTime = Calendar.getInstance();

        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        int minute = currentTime.get(Calendar.MINUTE);
        String period = (hour >= 12) ? "오후" : "오전";
        String formattedTime = String.format("%s %d:%02d", period, hour % 12, minute);

        String userName = preferences.getString("userName", "");
        String userImage = preferences.getString("userImage", "");

        Log.d("user", userName);

        profileName.setText(userName);
        Glide.with(profileImage).load(userImage).into(profileImage);
        profileDate.setText(formattedTime);

        ValueEventListener rankValue = new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                rankList.clear();
                for (DataSnapshot childSnapshot: snapshot.getChildren()){
                    String userName = childSnapshot.child("userName").getValue(String.class);
                    Integer rankPoint = childSnapshot.child("rankPoint").getValue(Integer.class);
                    Log.d("rank", String.valueOf(rankPoint));

                    if (userName != null && rankPoint != null){
                        RankModel rankItem = new RankModel(userName,rankPoint);
                        Log.d("rank", String.valueOf(rankItem));
                        rankList.add(rankItem);
                    }
                    Log.d("rank1", rankList.toString());
                }
                Collections.sort(rankList, (o1, o2) -> Integer.compare(o2.getRankPoint(), o1.getRankPoint()));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        rankDB.addValueEventListener(rankValue);
    }

}
