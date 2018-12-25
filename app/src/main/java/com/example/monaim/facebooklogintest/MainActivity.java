package com.example.monaim.facebooklogintest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookActivity;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.Logger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class MainActivity extends AppCompatActivity {


    private LoginButton loginButton;
    private TextView statusTv;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //FacebookSdk.setApplicationId(getString(R.string.facebook_app_id));
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_main);

        init();
        loginWithFb();

    }

    public void loginWithFb() {
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                statusTv.setText("Login success: "+ loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                statusTv.setText("Login Canceled:");
            }

            @Override
            public void onError(FacebookException error) {
                statusTv.setText("Login Canceled:"+ error.getMessage());
            }
        });
    }

    public void init() {
        callbackManager = CallbackManager.Factory.create();
        loginButton = findViewById(R.id.fb_lg_btn);
        statusTv = findViewById(R.id.status_tv);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
