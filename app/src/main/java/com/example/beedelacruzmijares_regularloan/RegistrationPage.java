package com.example.beedelacruzmijares_regularloan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegistrationPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration_page);

        Button buttonRegister = findViewById(R.id.register_btn);
        EditText idtxt = findViewById(R.id.memid);
        EditText nametxt = findViewById(R.id.name);
        EditText datetxt = findViewById(R.id.date);
        EditText passwordtxt = findViewById(R.id.password);
        EditText confirmpassword = findViewById(R.id.confirmp);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idtxt.length() == 0 || nametxt.length() == 0 || datetxt.length() == 0 || passwordtxt.length() == 0 || confirmpassword.length() == 0) {
                    Toast.makeText(RegistrationPage.this, "Please fill out all fields.", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (!passwordtxt.getText().toString().equals(confirmpassword.getText().toString())) {
                        Toast.makeText(RegistrationPage.this, "Password does not match", Toast.LENGTH_SHORT).show();
                    } else {
                        GlobalVariables.empid = idtxt.getText().toString();
                        GlobalVariables.empname = nametxt.getText().toString();
                        GlobalVariables.date = datetxt.getText().toString();
                        GlobalVariables.password = passwordtxt.getText().toString();


                        Toast.makeText(RegistrationPage.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegistrationPage.this, UserLoginPage.class);
                        startActivity(intent);
                    }
                }}});

        TextView loginBackTextview = findViewById(R.id.loginbacktextview);
        loginBackTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationPage.this, UserLoginPage.class);
                startActivity(intent);
            }
        });
    }
}