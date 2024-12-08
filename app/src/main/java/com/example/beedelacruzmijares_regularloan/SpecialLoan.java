package com.example.beedelacruzmijares_regularloan;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SpecialLoan extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_loan);

        Button btnSpecialApplyLoan = findViewById(R.id.apply_special_btn);
        btnSpecialApplyLoan.setOnClickListener(v -> {
            // Show a toast
            Toast.makeText(this, "Loan applied successfully!", Toast.LENGTH_SHORT).show();

            // Go back to the UserHomePage
            Intent intent = new Intent(SpecialLoan.this, UserHomePage.class);
            startActivity(intent);
            finish(); // Optional: Finish the current activity to prevent going back
        });
    }
}
