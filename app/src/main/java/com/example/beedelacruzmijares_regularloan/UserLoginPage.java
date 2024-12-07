package com.example.beedelacruzmijares_regularloan;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

public class UserLoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_login_page);

        TextView registerbacktextview = findViewById(R.id.registerbacktextview);
        registerbacktextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserLoginPage.this, RegistrationPage.class);
                startActivity(intent);
            }
        });

        TextView admintextview = findViewById(R.id.admintextview);
        admintextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserLoginPage.this, AdminLoginPage.class);
                startActivity(intent);
            }
        });



    }
}