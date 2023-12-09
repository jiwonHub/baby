package com.example.baby.my;

import static com.example.baby.DBKey.DB_USER;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

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

import java.util.ArrayList;
import java.util.List;

public class FriendAddDialog extends Dialog {

    private RoomDatabase database;
    private DatabaseReference userDB;
    private List<FriendsEntity> userList;

    public FriendAddDialog(Context context, RoomDatabase database){

        super(context);
        this.database = database;
    }

    private Button addButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_add_dialog);

        EditText nameEditText = findViewById(R.id.dialogEditText);
        addButton = findViewById(R.id.dialogAdd);
        cancelButton = findViewById(R.id.dialogCancel);

        userDB = FirebaseDatabase.getInstance().getReference().child(DB_USER);
        ValueEventListener userValue = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userList = new ArrayList<>();
                for(DataSnapshot childSnapshot: snapshot.getChildren()){
                    String userName = childSnapshot.child("userName").getValue(String.class);
                    FriendsEntity user = new FriendsEntity(userName);
                    userList.add(user);
                }
                Log.d("list", userList.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        userDB.addValueEventListener(userValue);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = String.valueOf(nameEditText.getText());
                FriendsEntity user = new FriendsEntity(name);
                boolean containString = userList.contains(user);
                if (containString){
                    FriendsEntity friendsEntity = new FriendsEntity(name);
                    database.friendsDao().insert(friendsEntity);
                    Toast.makeText(getContext(), "친구 추가 완료!", Toast.LENGTH_SHORT).show();
                    dismiss();
                }else{
                    Toast.makeText(getContext(), "존재하지 않는 유저거나 잘못 입력하셨습니다!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}
