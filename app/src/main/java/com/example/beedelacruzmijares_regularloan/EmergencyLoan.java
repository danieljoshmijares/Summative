package com.example.beedelacruzmijares_regularloan;

import static java.lang.Double.parseDouble;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class EmergencyLoan extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_loan);

        Button btnRegularApplyLoan = findViewById(R.id.apply_emergency_btn);
        EditText loantxt = findViewById(R.id.emergency_loan_amount);
        EditText monthstxt = findViewById(R.id.emergency_months);
        btnRegularApplyLoan.setOnClickListener(v -> {
            if (monthstxt.length() == 0 || loantxt.length() == 0) {
                Toast.makeText(EmergencyLoan.this, "Please fill out the needed form to proceed.", Toast.LENGTH_SHORT).show();
            }
            else {
                double monthsnum = parseDouble(monthstxt.getText().toString());
                if(monthsnum < 1){
                    Toast.makeText(this, "Please input the correct month number", Toast.LENGTH_SHORT).show();
                }
                else {
                    double loannum = parseDouble(loantxt.getText().toString());
                    GlobalVariables.setEmergencyLoanAmount(loannum);
                    GlobalVariables.setEmergencyMonths(monthsnum);
                    // Show a toast
                    if (GlobalVariables.emmonths <= 6) { //Payable in 6 Months
                        Toast.makeText(this, "Loan applied successfully! Applied for Payable in 6 Months!", Toast.LENGTH_SHORT).show();
                    }
                    else { //After 6 months = Cash
                        Toast.makeText(this, "Loan applied successfully! Applied for Cash!", Toast.LENGTH_SHORT).show();

                    }
                    // Go back to the UserHomePage
                    Intent intent = new Intent(EmergencyLoan.this, UserHomePage.class);
                    startActivity(intent);
                    finish(); // Optional: Finish the current activity to prevent going back
                }}});
    }
}