package com.example.beedelacruzmijares_regularloan;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

public class AdminLoginPage extends AppCompatActivity {

    private static final String TAG = "AdminLoginPage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_login_page);

        TextView registerbacktextview = findViewById(R.id.userloginbacktextview);
        EditText idtext = findViewById(R.id.admin_id);
        EditText passwordtext = findViewById(R.id.admin_password);
        Button buttonLogin = findViewById(R.id.admin_login_btn);

        // Login button click listener
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String adminid = "admin";
                String adminpassword = "123";
                String id = idtext.getText().toString().trim();
                String pass = passwordtext.getText().toString().trim();
                if (idtext.length() == 0 || passwordtext.length() == 0) {
                    Toast.makeText(AdminLoginPage.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                } else if (id.equals(adminid) && pass.equals(adminpassword)) {
                    Toast.makeText(AdminLoginPage.this, "Admin Login Successful!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Navigating to AdminHomePage");
                    Intent intent = new Intent(AdminLoginPage.this, AdminHomePage.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(AdminLoginPage.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerbacktextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLoginPage.this, UserLoginPage.class);
                startActivity(intent);
            }
        });
    }
}
