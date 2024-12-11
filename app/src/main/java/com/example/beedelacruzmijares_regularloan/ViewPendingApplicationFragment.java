package com.example.beedelacruzmijares_regularloan;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import com.example.beedelacruzmijares_regularloan.UserSummaryAdapter;

import java.util.List;

public class ViewPendingApplicationFragment extends Fragment {
    private RecyclerView recyclerView;
    private LoanApplicationAdapter adapter;
    private List<LoanApplication> loanList;
    private DatabaseReference root;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pending_application, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewPendingApplications);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        loanList = new ArrayList<>();
        adapter = new LoanApplicationAdapter(loanList);
        recyclerView.setAdapter(adapter);

        // Initialize Firebase reference
        root = FirebaseDatabase.getInstance().getReference("Loans");

        // Retrieve pending loan applications from Firebase
        loadPendingLoanApplications();

        return view;
    }

    private void loadPendingLoanApplications() {
        root.orderByChild("LoanStatus").equalTo("Pending").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                loanList.clear();
                for (DataSnapshot loanSnapshot : snapshot.getChildren()) {
                    LoanApplication loan = loanSnapshot.getValue(LoanApplication.class);
                    loanList.add(loan);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle potential Firebase errors
            }
        });
    }
}
