package com.example.beedelacruzmijares_regularloan;

import static java.lang.Double.parseDouble;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class RegularLoan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_regular_loan);

        EditText salarytxt = findViewById(R.id.regular_salary);
        EditText monthstxt = findViewById(R.id.regular_months);
        Button btnRegularApplyLoan = findViewById(R.id.apply_regular_btn);
        btnRegularApplyLoan.setOnClickListener(v -> {
            if (monthstxt.length() == 0 || salarytxt.length() == 0) {
                Toast.makeText(RegularLoan.this, "Please fill out the needed form to proceed.", Toast.LENGTH_SHORT).show();
            }
            else {
                double monthsnum = parseDouble(monthstxt.getText().toString());
                if(monthsnum < 1 || monthsnum > 24){
                    Toast.makeText(this, "Please input the correct month number", Toast.LENGTH_SHORT).show();
                }
                else {
                    double salarynum = parseDouble(salarytxt.getText().toString());
                    GlobalVariables.setSalary(salarynum);
                    GlobalVariables.setRegularMonths(monthsnum);

                    // Show a toast
                    Toast.makeText(this, "Loan applied successfully!", Toast.LENGTH_SHORT).show();
                    // Go back to the UserHomePage
                    Intent intent = new Intent(RegularLoan.this, UserHomePage.class);
                    startActivity(intent);
                    finish(); // Optional: Finish the current activity to prevent going back
                }}});
    }
}