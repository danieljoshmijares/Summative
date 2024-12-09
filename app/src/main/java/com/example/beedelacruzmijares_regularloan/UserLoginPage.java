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

        Button buttonLogin = findViewById(R.id.login_btn);
        EditText idtxt = findViewById(R.id.employeeid);
        EditText passwordtxt = findViewById(R.id.password);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idtxt.length() == 0 || passwordtxt.length() == 0) {
                    Toast.makeText(UserLoginPage.this, "Please fill out all fields.", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (idtxt.getText().toString().equals(GlobalVariables.empid) && passwordtxt.getText().toString().equals(GlobalVariables.password)) {
                        Toast.makeText(UserLoginPage.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UserLoginPage.this, UserHomePage.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(UserLoginPage.this, "Incorrect credentials.", Toast.LENGTH_SHORT).show();
                    }

                }
            }});

        TextView registerBackTextview = findViewById(R.id.registerbacktextview);
        registerBackTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserLoginPage.this, RegistrationPage.class);
                startActivity(intent);
            }
        });

        TextView adminTextView = findViewById(R.id.admintextview);
        adminTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserLoginPage.this, AdminLoginPage.class);
                startActivity(intent);
            }
        });



    }
}