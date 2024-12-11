package com.example.beedelacruzmijares_regularloan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class LoanApplicationAdapter extends RecyclerView.Adapter<LoanApplicationAdapter.LoanViewHolder> {

    private List<LoanApplication> loanList;

    public LoanApplicationAdapter(List<LoanApplication> loanList) {
        this.loanList = loanList;
    }

    @NonNull
    @Override
    public LoanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_summary, parent, false);
        return new LoanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoanViewHolder holder, int position) {
        LoanApplication loan = loanList.get(position);
        holder.tvEmployeeId.setText("Employee ID: " + loan.getEmployeeId());
        holder.tvLoanType.setText("Loan Type: " + loan.getLoanType());
        holder.tvLoanAmount.setText("Loan Amount: " + loan.getLoanAmount());
        holder.tvMonthsToPay.setText("Months to Pay: " + loan.getMonthsToPay());
        holder.tvServiceCharge.setText("Service Charge: " + loan.getServiceCharge());
        holder.tvInterest.setText("Interest: " + loan.getInterest());
        holder.tvMonthlyAmortization.setText("Monthly Amortization: " + loan.getMonthlyAmortization());
        holder.tvTotalAmountPayable.setText("Total Amount Payable: " + loan.getTotalAmountPayable());
        holder.tvLoanStatus.setText("Loan Status: " + loan.getLoanStatus());
    }

    @Override
    public int getItemCount() {
        return loanList.size();
    }

    static class LoanViewHolder extends RecyclerView.ViewHolder {

        TextView tvEmployeeId, tvLoanType, tvLoanAmount, tvMonthsToPay, tvServiceCharge, tvInterest, tvMonthlyAmortization, tvTotalAmountPayable, tvLoanStatus;

        public LoanViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEmployeeId = itemView.findViewById(R.id.tvEmployeeId);
            tvLoanType = itemView.findViewById(R.id.tvLoanType);
            tvLoanAmount = itemView.findViewById(R.id.tvLoanAmount);
            tvMonthsToPay = itemView.findViewById(R.id.tvMonthsToPay);
            tvServiceCharge = itemView.findViewById(R.id.tvServiceCharge);
            tvInterest = itemView.findViewById(R.id.tvInterest);
            tvMonthlyAmortization = itemView.findViewById(R.id.tvMonthlyAmortization);
            tvTotalAmountPayable = itemView.findViewById(R.id.tvTotalAmountPayable);
            tvLoanStatus = itemView.findViewById(R.id.tvLoanStatus);
        }
    }
}
