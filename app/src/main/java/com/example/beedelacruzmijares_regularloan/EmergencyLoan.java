package com.example.beedelacruzmijares_regularloan;

import static java.lang.Double.parseDouble;

import android.os.Bundle;
import android.util.Log;
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

public class EmergencyLoan extends AppCompatActivity {
    private static final String TAG = "EmergencyLoan";
    private DatabaseReference root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_loan);

        Button btnRegularApplyLoan = findViewById(R.id.apply_emergency_btn);
        EditText loantxt = findViewById(R.id.emergency_loan_amount);
        EditText monthstxt = findViewById(R.id.emergency_months);

        // Initialize Firebase reference
        root = FirebaseDatabase.getInstance().getReference("Loans");

        // Retrieve employee ID from global variable
        String employeeId = GlobalVariables.empid;

        btnRegularApplyLoan.setOnClickListener(v -> {
            if (monthstxt.length() == 0 || loantxt.length() == 0) {
                Toast.makeText(EmergencyLoan.this, "Please fill out the needed form to proceed.", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    double monthsnum = parseDouble(monthstxt.getText().toString());
                    double loannum = parseDouble(loantxt.getText().toString());

                    // Validate loan amount
                    if (loannum < 5000 || loannum > 25000) {
                        Toast.makeText(EmergencyLoan.this, "Emergency loan amount must be between 5K and 25K.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Use cash method if months to pay is greater than 6
                    boolean isCash = monthsnum > 6;
                    if (isCash) {
                        Toast.makeText(EmergencyLoan.this, "Months should be between 1 to 6. Applying for Cash method.", Toast.LENGTH_SHORT).show();
                    }

                    GlobalVariables.setEmergencyLoanAmount(loannum);
                    GlobalVariables.setEmergencyMonths(monthsnum);

                    // Calculate values
                    double serviceCharge = new GlobalVariables(this).getEmergencyServicecharge();
                    double interest = isCash ? 0 : (loannum * 0.006);
                    double monthlyAmortization = isCash ? 0 : (loannum + serviceCharge + interest) / monthsnum;
                    double totalAmountPayable = loannum + serviceCharge + interest;
                    String loanStatus = "Pending";

                    // Delete previous loans first, then add the new loan
                    deletePreviousLoans(employeeId, () -> {
                        // Save new loan data to Firebase after deletion
                        HashMap<String, Object> loanMap = new HashMap<>();
                        loanMap.put("EmployeeID", employeeId);
                        loanMap.put("LoanType", "Emergency");
                        loanMap.put("LoanAmount", loannum);
                        loanMap.put("MonthsToPay", monthsnum); // Save the exact months entered by user
                        loanMap.put("ServiceCharge", serviceCharge);
                        loanMap.put("Interest", interest);
                        loanMap.put("MonthlyAmortization", monthlyAmortization);
                        loanMap.put("TotalAmountPayable", totalAmountPayable);
                        loanMap.put("LoanStatus", loanStatus);

                        root.push().setValue(loanMap).addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(EmergencyLoan.this, UserHomePage.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(EmergencyLoan.this, "Failed to Save Loan Data. Try again!", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(e -> {
                            Log.e(TAG, "Error saving loan data", e);
                            Toast.makeText(EmergencyLoan.this, "Failed to Save Loan Data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
                    });
                } catch (Exception e) {
                    Log.e(TAG, "Error processing loan application", e);
                    Toast.makeText(EmergencyLoan.this, "An error occurred: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
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
                Toast.makeText(EmergencyLoan.this, "Failed to delete previous loans: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
