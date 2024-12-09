package com.example.beedelacruzmijares_regularloan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ViewAllRecordsFragment extends Fragment {
    private RecyclerView recyclerView;
    private UserSummaryAdapter adapter;
    private List<User> userList; // Assume this is populated with all loan records

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_all_records, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewAllRecords);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new UserSummaryAdapter(userList, user -> {
            // Handle item click to show detailed loan info
            //Fragment fragment = UserLoanDetailsFragment.newInstance(user);
            getFragmentManager().beginTransaction()
                    //.replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
        });
        recyclerView.setAdapter(adapter);

        return view;
    }
}
