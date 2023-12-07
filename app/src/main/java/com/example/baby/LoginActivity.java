package com.example.baby;

import static android.content.ContentValues.TAG;

import static com.example.baby.DBKey.DB_RANK;
import static com.example.baby.DBKey.DB_USER;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.baby.my.MyActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.common.KakaoSdk;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import java.util.Calendar;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private Button logoutButton;
    private String userName;
    private String userImage;
    private Long userId;
    private DatabaseReference userDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.loginButton);
        logoutButton = findViewById(R.id.logoutButton);

        KakaoSdk.init(this, getString(R.string.kakao_native_app_key));

        Function2<OAuthToken, Throwable, Unit> callback = new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                Log.e(TAG, "CallBack Method");
                if (oAuthToken != null) {
                    updateKakaoLoginUi();
                }else{
                    Log.e(TAG, "invoke: login fail");
                }
                return null;
            }
        };

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UserApiClient.getInstance().isKakaoTalkLoginAvailable(LoginActivity.this)){
                    UserApiClient.getInstance().loginWithKakaoTalk(LoginActivity.this, callback);
                }else{
                    UserApiClient.getInstance().loginWithKakaoAccount(LoginActivity.this, callback);
                }
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserApiClient.getInstance().logout(new Function1<Throwable, Unit>() {
                    @Override
                    public Unit invoke(Throwable throwable) {
                        updateKakaoLoginUi();
                        return null;
                    }
                });
            }
        });
    }

    private void updateKakaoLoginUi() {

        SharedPreferences preferences = getSharedPreferences("kakao_user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);

        UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
            @Override
            public Unit invoke(User user, Throwable throwable) {

                if (user != null) {
                    assert user.getKakaoAccount() != null;
                    assert user.getKakaoAccount().getProfile() != null;
                    userName = (user.getKakaoAccount().getProfile().getNickname());
                    userImage = user.getKakaoAccount().getProfile().getProfileImageUrl();
                    userId = user.getId();
                    userDB = FirebaseDatabase.getInstance().getReference().child(DB_USER);
                    userDB.child("userName").setValue(userName);
                    Log.d("user", userImage);
                    editor.putString("userName", userName);
                    editor.putString("userImage", userImage);
                    editor.putString("userId", String.valueOf(userId));
                    editor.commit();
                    startActivity(intent);
                    loginButton.setVisibility(View.GONE);
                    logoutButton.setVisibility(View.VISIBLE);
                }else{
                    logoutButton.setVisibility(View.INVISIBLE);
                    loginButton.setVisibility(View.VISIBLE);
                }
                return null;
            }
        });
    }


}
