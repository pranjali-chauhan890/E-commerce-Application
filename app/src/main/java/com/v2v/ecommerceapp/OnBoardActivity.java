package com.v2v.ecommerceapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OnBoardActivity extends AppCompatActivity {

    Button nextButton;
    TextView loginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        nextButton = findViewById(R.id.nextButton);
        loginText = findViewById(R.id.loginSwitch);

        nextButton.setOnClickListener(v -> {
            startActivity(new Intent(OnBoardActivity.this, LoginActivity.class));
            finish();
        });

        loginText.setOnClickListener(v -> {
            startActivity(new Intent(OnBoardActivity.this, LoginActivity.class));
            finish();
        });
    }
}
