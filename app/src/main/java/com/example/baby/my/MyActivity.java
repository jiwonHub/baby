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
import com.example.baby.db.DatabaseInitializer;
import com.example.baby.db.FriendsEntity;
import com.example.baby.db.RoomDatabase;
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
    private ImageView profileImage;
    private RecyclerView recyclerView;
    private DatabaseReference rankDB;
    private List<RankModel> rankList = new ArrayList<>();
    private List<FriendsEntity> friendsList = new ArrayList<>();
    private RankAdapter adapter;

    private Button friendAddButton, myBackButton;
    RoomDatabase roomDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        profileName = findViewById(R.id.profileName);
        profileImage = findViewById(R.id.profileImage);
        friendAddButton = findViewById(R.id.friendAddButton);

        rankDB = FirebaseDatabase.getInstance().getReference().child(DBKey.DB_RANK);
        recyclerView = findViewById(R.id.rankRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RankAdapter(rankList);
        recyclerView.setAdapter(adapter);

        myBackButton = findViewById(R.id.myBackButton);

        roomDatabase = DatabaseInitializer.initDatabase(this);

        SharedPreferences preferences = getSharedPreferences("kakao_user", Context.MODE_PRIVATE);

        String userName = preferences.getString("userName", "");
        String userImage = preferences.getString("userImage", "");

        Log.d("user", userName);

        profileName.setText(userName);
        Glide.with(profileImage).load(userImage).into(profileImage);

        friendAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FriendAddDialog friendAddDialog = new FriendAddDialog(MyActivity.this, roomDatabase);
                friendAddDialog.show();
            }
        });

        ValueEventListener rankValue = new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (friendsList != null){
                    friendsList = roomDatabase.friendsDao().getAll();
                }

                rankList.clear();
                for (DataSnapshot childSnapshot: snapshot.getChildren()){
                    String userName = childSnapshot.child("userName").getValue(String.class);
                    Integer rankPoint = childSnapshot.child("rankPoint").getValue(Integer.class);
                    FriendsEntity user = new FriendsEntity(userName);
                    if (userName != null && rankPoint != null && friendsList.contains(user)){
                            RankModel rankItem = new RankModel(userName,rankPoint);
                            rankList.add(rankItem);
                    }
                }
                Collections.sort(rankList, (o1, o2) -> Integer.compare(o2.getRankPoint(), o1.getRankPoint()));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        rankDB.addValueEventListener(rankValue);

        myBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
