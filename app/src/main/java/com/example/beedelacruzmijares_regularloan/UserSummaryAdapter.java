package com.example.beedelacruzmijares_regularloan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserSummaryAdapter extends RecyclerView.Adapter<UserSummaryAdapter.ViewHolder> {
    private List<User> userList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(User user);
    }

    public UserSummaryAdapter(List<User> userList, OnItemClickListener listener) {
        this.userList = userList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_summary, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = userList.get(position);
        holder.tvUserName.setText(user.getName());
        holder.tvLoanStatus.setText(user.getLoanStatus());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(user));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvUserName, tvLoanStatus;

        public ViewHolder(View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvLoanStatus = itemView.findViewById(R.id.tvLoanStatus);
        }
    }
}

