package com.example.beedelacruzmijares_regularloan;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import android.content.Intent;

public class AdminHomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        // Initialize the DrawerLayout
        drawerLayout = findViewById(R.id.drawer_layout);

        // Setup ActionBarDrawerToggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Initialize NavigationView
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Update the navigation header with admin information
        View headerView = navigationView.getHeaderView(0);
        TextView adminNameTextView = headerView.findViewById(R.id.user_name);
        TextView adminIdTextView = headerView.findViewById(R.id.user_id);

        // Retrieve admin information from GlobalVariables
        String adminId = GlobalVariables.adminid;

        // Set the TextViews with admin information
        adminIdTextView.setText(adminId);

        // Set the default fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new ViewPendingApplicationFragment())
                    .commit();
            navigationView.setCheckedItem(R.id.nav_view_pending_applications);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation menu item clicks
        if (item.getItemId() == R.id.nav_view_pending_applications) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new ViewPendingApplicationFragment())
                    .commit();
        } else if (item.getItemId() == R.id.nav_view_all_records) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new ViewAllRecordsFragment())
                    .commit();
        } else if (item.getItemId() == R.id.nav_logout) {
            Intent intent = new Intent(this, AdminLoginPage.class);
            startActivity(intent);
            finish(); // Close the current activity
        }

        // Close the drawer after selecting an item
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        // Close the drawer if it's open; otherwise, use default back button behavior
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
