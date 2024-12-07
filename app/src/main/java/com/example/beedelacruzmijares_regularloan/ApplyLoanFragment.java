package com.example.beedelacruzmijares_regularloan;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ApplyLoanFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_apply_loan, container, false);

        Button btnEmergencyLoan = view.findViewById(R.id.btn_emergency_loan);
        Button btnSpecialLoan = view.findViewById(R.id.btn_special_loan);
        Button btnRegularLoan = view.findViewById(R.id.btn_regular_loan);

        btnEmergencyLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToLoanForm("emergency");
            }
        });

        btnSpecialLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToLoanForm("special");
            }
        });

        btnRegularLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToLoanForm("regular");
            }
        });

        return view;
    }

    private void navigateToLoanForm(String type) {
        Intent intent;
        switch (type) {
            case "emergency":
                intent = new Intent(getActivity(), EmergencyLoan.class);
                break;
            case "special":
                intent = new Intent(getActivity(), SpecialLoan.class);
                break;
            case "regular":
                intent = new Intent(getActivity(), RegularLoan.class);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        startActivity(intent);
    }
}
