package com.example.beedelacruzmijares_regularloan;

import static java.lang.Double.parseDouble;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegularLoan extends AppCompatActivity {
    private static final String TAG = "RegularLoan";
    private DatabaseReference root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regular_loan);

        EditText loanAmountTxt = findViewById(R.id.regular_loan_amount);
        EditText salaryTxt = findViewById(R.id.regular_salary);
        EditText monthsTxt = findViewById(R.id.regular_months);
        Button btnRegularApplyLoan = findViewById(R.id.apply_regular_btn);

        // Initialize Firebase reference
        root = FirebaseDatabase.getInstance().getReference("Loans");

        // Retrieve employee ID from global variables
        String employeeId = GlobalVariables.empid;

        btnRegularApplyLoan.setOnClickListener(v -> {
            if (loanAmountTxt.length() == 0 || monthsTxt.length() == 0 || salaryTxt.length() == 0) {
                Toast.makeText(RegularLoan.this, "Please fill out the needed form to proceed.", Toast.LENGTH_SHORT).show();
            } else {
                double userLoanAmount = parseDouble(loanAmountTxt.getText().toString());
                double salaryNum = parseDouble(salaryTxt.getText().toString());
                double monthsNum = parseDouble(monthsTxt.getText().toString());

                GlobalVariables.setSalary(salaryNum);
                GlobalVariables.setRegularMonths(monthsNum);

                // Calculate maximum loanable amount
                double maxLoanAmount = GlobalVariables.getMaxLoanAmount();

                if (userLoanAmount > maxLoanAmount) {
                    Toast.makeText(this, "Loan amount exceeds the maximum limit based on your salary.", Toast.LENGTH_SHORT).show();
                    return;
                }

                GlobalVariables.setUserLoanAmount(userLoanAmount);

                // Calculate values using GlobalVariables methods
                double loanInterest = GlobalVariables.getRegularLoanInterest();
                double serviceCharge = GlobalVariables.getServiceCharge();
                double monthlyAmortization = GlobalVariables.getRegmonamort();
                double totalAmountPayable = userLoanAmount + loanInterest + serviceCharge;
                String loanStatus = "Pending";

                // Combined toast message
                Toast.makeText(this, "Loan applied successfully! Loan Data Saved Successfully!", Toast.LENGTH_SHORT).show();

                // Delete previous loans
                deletePreviousLoans(employeeId, () -> {
                    // Save new loan data to Firebase after deletion
                    HashMap<String, Object> loanMap = new HashMap<>();
                    loanMap.put("EmployeeID", employeeId);
                    loanMap.put("LoanType", "Regular");
                    loanMap.put("LoanAmount", userLoanAmount);
                    loanMap.put("MonthsToPay", monthsNum);
                    loanMap.put("ServiceCharge", serviceCharge);
                    loanMap.put("Interest", loanInterest);
                    loanMap.put("MonthlyAmortization", monthlyAmortization);
                    loanMap.put("TotalAmountPayable", totalAmountPayable);
                    loanMap.put("LoanStatus", loanStatus);

                    root.push().setValue(loanMap).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(RegularLoan.this, UserHomePage.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(RegularLoan.this, "Failed to Save Loan Data. Try again!", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(e -> {
                        Toast.makeText(RegularLoan.this, "Failed to Save Loan Data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
                });
            }
        });
    }

    private void deletePreviousLoans(String employeeId, Runnable onComplete) {
        Query previousLoansQuery = root.orderByChild("EmployeeID").equalTo(employeeId);
        previousLoansQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot loanSnapshot : snapshot.getChildren()) {
                    loanSnapshot.getRef().removeValue();
                }
                // Run the onComplete action after all previous loans are deleted
                onComplete.run();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RegularLoan.this, "Failed to delete previous loans: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
