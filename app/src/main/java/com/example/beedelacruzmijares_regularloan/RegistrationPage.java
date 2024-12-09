package com.example.beedelacruzmijares_regularloan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegistrationPage extends AppCompatActivity {

    private EditText employeeId, name, dateHired, password, confirmPassword;
    private Button registerBtn;
    private FirebaseDatabase database;
    private DatabaseReference root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration_page);

        // Initialize Firebase
        database = FirebaseDatabase.getInstance();
        root = database.getReference("Users");

        // Initialize UI components
        employeeId = findViewById(R.id.memid);
        name = findViewById(R.id.name);
        dateHired = findViewById(R.id.date);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmp);
        registerBtn = findViewById(R.id.register_btn);

        // Register button functionality
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String empId = employeeId.getText().toString();
                String empName = name.getText().toString();
                String empDateHired = dateHired.getText().toString();
                String empPassword = password.getText().toString();
                String empConfirmPassword = confirmPassword.getText().toString();

                // Error trapping
                if (empId.isEmpty() || empName.isEmpty() || empDateHired.isEmpty() ||
                        empPassword.isEmpty() || empConfirmPassword.isEmpty()) {
                    Toast.makeText(RegistrationPage.this, "All fields are required!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!empPassword.equals(empConfirmPassword)) {
                    Toast.makeText(RegistrationPage.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Save data to Firebase
                HashMap<String, String> userMap = new HashMap<>();
                userMap.put("EmployeeID", empId);
                userMap.put("EmployeeName", empName);
                userMap.put("DateHired", empDateHired);
                userMap.put("Password", empPassword);

                root.child(empId).setValue(userMap).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegistrationPage.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                        // Navigate to Login Page
                        Intent intent = new Intent(RegistrationPage.this, UserLoginPage.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(RegistrationPage.this, "Registration Failed. Try again!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

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
