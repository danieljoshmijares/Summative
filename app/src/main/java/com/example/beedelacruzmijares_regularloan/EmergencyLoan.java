package com.example.beedelacruzmijares_regularloan;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class EmergencyLoan extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_loan);

        Button btnRegularApplyLoan = findViewById(R.id.apply_emergency_btn);
        btnRegularApplyLoan.setOnClickListener(v -> {
            // Show a toast
            Toast.makeText(this, "Loan applied successfully!", Toast.LENGTH_SHORT).show();

            // Go back to the UserHomePage
            Intent intent = new Intent(EmergencyLoan.this, UserHomePage.class);
            startActivity(intent);
            finish(); // Optional: Finish the current activity to prevent going back
        });
    }
}