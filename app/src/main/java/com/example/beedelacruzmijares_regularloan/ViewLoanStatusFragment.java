package com.example.beedelacruzmijares_regularloan;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ViewLoanStatusFragment extends Fragment {
    private DatabaseReference root;
    private TextView tvLoanType, tvLoanAmount, tvMonthsToPay, tvServiceCharge, tvInterest, tvMonthlyAmortization, tvTotalAmountPayable, tvLoanStatus;
    private Button btnBackToMenu, btnCancelLoan;
    private String latestLoanKey;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_loan_status, container, false);

        // Initialize Firebase reference
        root = FirebaseDatabase.getInstance().getReference("Loans");

        // Initialize UI elements
        tvLoanType = view.findViewById(R.id.tvLoanType);
        tvLoanAmount = view.findViewById(R.id.tvLoanAmount);
        tvMonthsToPay = view.findViewById(R.id.tvMonthsToPay);
        tvServiceCharge = view.findViewById(R.id.tvServiceCharge);
        tvInterest = view.findViewById(R.id.tvInterest);
        tvMonthlyAmortization = view.findViewById(R.id.tvMonthlyAmortization);
        tvTotalAmountPayable = view.findViewById(R.id.tvTotalAmountPayable);
        tvLoanStatus = view.findViewById(R.id.tvLoanStatus);
        btnBackToMenu = view.findViewById(R.id.btnBackToMenu);
        btnCancelLoan = view.findViewById(R.id.btnCancelLoan);

        // Retrieve employee ID from global variables
        String employeeId = GlobalVariables.empid;

        // Fetch the latest loan application for the logged-in user
        Query latestLoanQuery = root.orderByChild("EmployeeID").equalTo(employeeId).limitToLast(1);

        latestLoanQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot loanSnapshot : snapshot.getChildren()) {
                        latestLoanKey = loanSnapshot.getKey();
                        String loanType = loanSnapshot.child("LoanType").getValue(String.class);
                        double loanAmount = loanSnapshot.child("LoanAmount").getValue(Double.class);
                        double monthsToPay = loanSnapshot.child("MonthsToPay").getValue(Double.class);
                        double serviceCharge = loanSnapshot.child("ServiceCharge").getValue(Double.class);
                        double interest = loanSnapshot.child("Interest").getValue(Double.class);
                        double monthlyAmortization = loanSnapshot.child("MonthlyAmortization").getValue(Double.class);
                        double totalAmountPayable = loanSnapshot.child("TotalAmountPayable").getValue(Double.class);
                        String loanStatus = loanSnapshot.child("LoanStatus").getValue(String.class);

                        // Update UI elements with loan data
                        tvLoanType.setText("Loan Type: " + loanType);
                        tvLoanAmount.setText("Loan Amount: " + loanAmount);
                        tvMonthsToPay.setText("Months to Pay: " + monthsToPay);
                        tvServiceCharge.setText("Service Charge: " + serviceCharge);
                        tvInterest.setText("Interest: " + interest);
                        tvMonthlyAmortization.setText("Monthly Amortization: " + monthlyAmortization);
                        tvTotalAmountPayable.setText("Total Amount Payable: " + totalAmountPayable);
                        tvLoanStatus.setText("Loan Status: " + loanStatus);
                    }
                } else {
                    Toast.makeText(getActivity(), "No loan application found.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "Failed to retrieve loan data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Button to go back to the home page
        btnBackToMenu.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), UserHomePage.class));
        });

        // Button to cancel the latest loan application
        btnCancelLoan.setOnClickListener(v -> {
            if (latestLoanKey != null) {
                root.child(latestLoanKey).removeValue().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(getActivity(), "Loan application cancelled successfully.", Toast.LENGTH_SHORT).show();
                        // Clear the UI elements
                        tvLoanType.setText("Loan Type: ");
                        tvLoanAmount.setText("Loan Amount: ");
                        tvMonthsToPay.setText("Months to Pay: ");
                        tvServiceCharge.setText("Service Charge: ");
                        tvInterest.setText("Interest: ");
                        tvMonthlyAmortization.setText("Monthly Amortization: ");
                        tvTotalAmountPayable.setText("Total Amount Payable: ");
                        tvLoanStatus.setText("Loan Status: ");
                    } else {
                        Toast.makeText(getActivity(), "Failed to cancel loan application. Try again!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(e -> {
                    Toast.makeText(getActivity(), "Failed to cancel loan application: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
            } else {
                Toast.makeText(getActivity(), "No loan application to cancel.", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
