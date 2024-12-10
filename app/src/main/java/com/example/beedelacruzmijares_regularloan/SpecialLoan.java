package com.example.beedelacruzmijares_regularloan;

import static java.lang.Double.parseDouble;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class SpecialLoan extends AppCompatActivity {
    private static final String TAG = "SpecialLoan";
    private DatabaseReference root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_loan);

        Button btnSpecialApplyLoan = findViewById(R.id.apply_special_btn);
        EditText loantxt = findViewById(R.id.special_loan_amount);
        EditText monthstxt = findViewById(R.id.special_months);

        // Initialize Firebase reference
        root = FirebaseDatabase.getInstance().getReference("Loans");

        // Retrieve employee ID and date hired from global variables
        String employeeId = GlobalVariables.empid;
        String dateHired = GlobalVariables.date;

        // Check if employee ID and date hired are not null
        if (employeeId == null || dateHired == null) {
            Log.e(TAG, "Employee ID or Date Hired is null.");
            Toast.makeText(SpecialLoan.this, "Error: Employee ID or Date Hired is null.", Toast.LENGTH_SHORT).show();
            return;
        }

        btnSpecialApplyLoan.setOnClickListener(v -> {
            try {
                if (monthstxt.length() == 0 || loantxt.length() == 0) {
                    Toast.makeText(SpecialLoan.this, "Please fill out the needed form to proceed.", Toast.LENGTH_SHORT).show();
                } else {
                    double monthsnum = parseDouble(monthstxt.getText().toString());
                    double loannum = parseDouble(loantxt.getText().toString());

                    // Validate loan amount
                    if (loannum < 50000 || loannum > 100000) {
                        Toast.makeText(SpecialLoan.this, "Please input the correct loanable amount from 50K up to 100K", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Validate months to pay
                    if (monthsnum < 1 || monthsnum > 18) {
                        Toast.makeText(SpecialLoan.this, "Please input the correct month number between 1 and 18", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Calculate if the member is already 5 years or above based on Date Hired
                    long yearsWorked = getYearsWorked(dateHired);
                    Log.d(TAG, "Years worked: " + yearsWorked);
                    if (yearsWorked < 5) {
                        Toast.makeText(SpecialLoan.this, "Member must be employed for at least 5 years to apply for a Special Loan", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Set loan details
                    GlobalVariables.setSpecialLoanAmount(loannum);
                    GlobalVariables.setSpecialMonths(monthsnum);

                    // Calculate values using GlobalVariables methods
                    double loanInterest = GlobalVariables.getTotalLoanInterest();
                    double totalLoanAmount = GlobalVariables.getTotalLoanAmount();
                    double monthlyAmortization = GlobalVariables.getMothlyAmort();
                    double serviceCharge = 0; // Assume there's no service charge for Special Loan
                    double totalAmountPayable = totalLoanAmount;
                    String loanStatus = "Pending";

                    // Combined toast message
                    Toast.makeText(SpecialLoan.this, "Loan applied successfully! Loan Data Saved Successfully!", Toast.LENGTH_SHORT).show();

                    // Save data to Firebase
                    HashMap<String, Object> loanMap = new HashMap<>();
                    loanMap.put("EmployeeID", employeeId);
                    loanMap.put("LoanType", "Special");
                    loanMap.put("LoanAmount", loannum);
                    loanMap.put("MonthsToPay", monthsnum);
                    loanMap.put("ServiceCharge", serviceCharge);
                    loanMap.put("Interest", loanInterest);
                    loanMap.put("MonthlyAmortization", monthlyAmortization);
                    loanMap.put("TotalAmountPayable", totalAmountPayable);
                    loanMap.put("LoanStatus", loanStatus);

                    root.push().setValue(loanMap).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(SpecialLoan.this, UserHomePage.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(SpecialLoan.this, "Failed to Save Loan Data. Try again!", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(e -> {
                        Log.e(TAG, "Error saving loan data", e);
                        Toast.makeText(SpecialLoan.this, "Failed to Save Loan Data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
                }
            } catch (Exception e) {
                Log.e(TAG, "Error processing loan application", e);
                Toast.makeText(SpecialLoan.this, "An error occurred: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private long getYearsWorked(String dateHired) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            Date hireDate = sdf.parse(dateHired);
            Date currentDate = new Date();

            // Calculate difference in years
            long diffInMillies = currentDate.getTime() - hireDate.getTime();
            long diffInDays = diffInMillies / (1000L * 60 * 60 * 24);
            long diffInYears = diffInDays / 365;

            Log.d(TAG, "Hire Date: " + hireDate + ", Current Date: " + currentDate + ", Difference in Years: " + diffInYears);
            return diffInYears;
        } catch (ParseException e) {
            Log.e(TAG, "Error parsing date", e);
            return 0;
        }
    }
}
