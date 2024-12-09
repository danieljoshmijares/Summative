package com.example.beedelacruzmijares_regularloan;

import static java.lang.Double.parseDouble;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SpecialLoan extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_loan);

        Button btnSpecialApplyLoan = findViewById(R.id.apply_special_btn);
        EditText loantxt = findViewById(R.id.special_loan_amount);
        EditText monthstxt = findViewById(R.id.special_months);
        btnSpecialApplyLoan.setOnClickListener(v -> {
            if (monthstxt.length() == 0 || loantxt.length() == 0) {
                Toast.makeText(SpecialLoan.this, "Please fill out the needed form to proceed.", Toast.LENGTH_SHORT).show();
            }
            else {
                double monthsnum = parseDouble(monthstxt.getText().toString());
                double loannum = parseDouble(loantxt.getText().toString());
                if(monthsnum < 1 || monthsnum > 18){
                    Toast.makeText(this, "Please input the correct month number", Toast.LENGTH_SHORT).show();
                }
                else if (loannum < 50000 || loannum > 100000){
                    Toast.makeText(this, "Please input the correct loanable amount from 50k up to 100k", Toast.LENGTH_SHORT).show();
                }
                //!!!!IMPORTANT!!!!

                //IMPORTANT: Also pls put an else-if here that tracks if the user is 5 years or above in terms of Date Hired

                //!!!!IMPORTANT!!!!

                else {
                    GlobalVariables.setSpecialLoanAmount(loannum);
                    GlobalVariables.setSpecialMonths(monthsnum);

                    // Show a toast
                    Toast.makeText(this, "Loan applied successfully!", Toast.LENGTH_SHORT).show();

                    // Go back to the UserHomePage
                    Intent intent = new Intent(SpecialLoan.this, UserHomePage.class);
                    startActivity(intent);
                    finish(); // Optional: Finish the current activity to prevent going back
                }}});
    }
}
