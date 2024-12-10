package com.example.beedelacruzmijares_regularloan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserLoginPage extends AppCompatActivity {

    private EditText editTextEmpID, editTextPassword;
    private Button buttonLogin;
    private TextView registerBackTextView, adminTextView;

    private DatabaseReference root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_login_page);

        // Initialize Firebase reference
        root = FirebaseDatabase.getInstance().getReference("Users");

        // Initialize UI elements
        editTextEmpID = findViewById(R.id.employee_id); // Replace with your EditText ID for Employee ID
        editTextPassword = findViewById(R.id.password); // Replace with your EditText ID for Password
        buttonLogin = findViewById(R.id.login_btn);
        registerBackTextView = findViewById(R.id.registerbacktextview);
        adminTextView = findViewById(R.id.admintextview);

        // Login button click listener
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        // Redirect to Registration page
        registerBackTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserLoginPage.this, RegistrationPage.class);
                startActivity(intent);
            }
        });

        // Redirect to Admin login page
        adminTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserLoginPage.this, AdminLoginPage.class);
                startActivity(intent);
            }
        });
    }

    // Login function
    private void loginUser() {
        String empID = editTextEmpID.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Validate input fields
        if (empID.isEmpty()) {
            editTextEmpID.setError("Employee ID is required");
            editTextEmpID.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        // Check credentials in Firebase
        root.child(empID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Retrieve the password and date hired from Firebase
                    String dbPassword = snapshot.child("Password").getValue(String.class);
                    String dateHired = snapshot.child("DateHired").getValue(String.class);

                    if (dbPassword != null && dbPassword.equals(password)) {
                        // Store the employee ID and date hired in GlobalVariables
                        GlobalVariables.empid = empID;
                        GlobalVariables.date = dateHired;

                        // Login successful
                        Toast.makeText(UserLoginPage.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                        // Redirect to UserHomePage
                        Intent intent = new Intent(UserLoginPage.this, UserHomePage.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Incorrect password
                        Toast.makeText(UserLoginPage.this, "Invalid Password!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // User does not exist
                    Toast.makeText(UserLoginPage.this, "Employee ID not found!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle potential Firebase errors
                Toast.makeText(UserLoginPage.this, "Database Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
