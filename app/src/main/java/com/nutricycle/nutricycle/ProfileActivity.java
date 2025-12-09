package com.nutricycle.nutricycle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;

public class ProfileActivity extends AppCompatActivity {

    private boolean isLoggedIn = false; // Change this based on your auth logic

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupToolbar();
        setupBottomNavigation();
        setupProfileContent();
    }

    private void setupToolbar() {
        MaterialToolbar toolbar = findViewById(R.id.pageToolbar);
        // No navigation icon in profile page as it's accessible from bottom nav
    }

    private void setupProfileContent() {
        // Login/Register button
        Button btnLoginRegister = findViewById(R.id.btnLoginRegister);
        if (btnLoginRegister != null) {
            btnLoginRegister.setOnClickListener(v -> {
                // TODO: Navigate to login/register activity
                Toast.makeText(this, "Login/Register clicked", Toast.LENGTH_SHORT).show();
            });
        }

        // Edit Profile button
        ImageButton btnEditProfile = findViewById(R.id.btnEditProfile);
        if (btnEditProfile != null) {
            btnEditProfile.setOnClickListener(v -> {
                // TODO: Navigate to edit profile activity
                Toast.makeText(this, "Edit Profile clicked", Toast.LENGTH_SHORT).show();
            });
        }

        // Complete Profile
        LinearLayout itemCompleteProfile = findViewById(R.id.itemCompleteProfile);
        if (itemCompleteProfile != null) {
            itemCompleteProfile.setOnClickListener(v -> {
                // TODO: Navigate to complete profile activity
                Toast.makeText(this, "Complete Profile clicked", Toast.LENGTH_SHORT).show();
            });
        }

        // Address
        LinearLayout itemAddress = findViewById(R.id.itemAddress);
        if (itemAddress != null) {
            itemAddress.setOnClickListener(v -> {
                // TODO: Navigate to address management activity
                Toast.makeText(this, "Address clicked", Toast.LENGTH_SHORT).show();
            });
        }

        // Payment
        LinearLayout itemPayment = findViewById(R.id.itemPayment);
        if (itemPayment != null) {
            itemPayment.setOnClickListener(v -> {
                // TODO: Navigate to payment methods activity
                Toast.makeText(this, "Payment clicked", Toast.LENGTH_SHORT).show();
            });
        }

        // Voucher
        LinearLayout itemVoucher = findViewById(R.id.itemVoucher);
        if (itemVoucher != null) {
            itemVoucher.setOnClickListener(v -> {
                // TODO: Navigate to voucher activity
                Toast.makeText(this, "Voucher clicked", Toast.LENGTH_SHORT).show();
            });
        }

        // Gizi Meter
        LinearLayout itemGiziMeter = findViewById(R.id.itemGiziMeter);
        if (itemGiziMeter != null) {
            itemGiziMeter.setOnClickListener(v -> {
                Intent intent = new Intent(ProfileActivity.this, GiziMeterActivity.class);
                startActivity(intent);
            });
        }

        // Sign Out
        Button btnSignOut = findViewById(R.id.btnSignOut);
        if (btnSignOut != null) {
            btnSignOut.setOnClickListener(v -> {
                // TODO: Implement sign out logic
                Toast.makeText(this, "Sign Out clicked", Toast.LENGTH_SHORT).show();
            });
        }

        // Update visibility based on login status
        updateLoginState();
    }

    private void updateLoginState() {
        LinearLayout loggedOutContainer = findViewById(R.id.loggedOutContainer);
        LinearLayout loggedInContainer = findViewById(R.id.loggedInContainer);
        Button btnSignOut = findViewById(R.id.btnSignOut);

        if (loggedOutContainer != null && loggedInContainer != null) {
            if (isLoggedIn) {
                loggedOutContainer.setVisibility(android.view.View.GONE);
                loggedInContainer.setVisibility(android.view.View.VISIBLE);
                if (btnSignOut != null) {
                    btnSignOut.setVisibility(android.view.View.VISIBLE);
                }
            } else {
                loggedOutContainer.setVisibility(android.view.View.VISIBLE);
                loggedInContainer.setVisibility(android.view.View.GONE);
                if (btnSignOut != null) {
                    btnSignOut.setVisibility(android.view.View.GONE);
                }
            }
        }
    }

    private void setupBottomNavigation() {
        // Beranda (Home)
        LinearLayout navHome = findViewById(R.id.navHome);
        if (navHome != null) {
            navHome.setOnClickListener(v -> {
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            });
        }

        // Keranjang (Cart)
        LinearLayout navCart = findViewById(R.id.navCart);
        if (navCart != null) {
            navCart.setOnClickListener(v -> {
                Intent intent = new Intent(ProfileActivity.this, CartActivity.class);
                startActivity(intent);
            });
        }

        // Riwayat (History)
        LinearLayout navHistory = findViewById(R.id.navHistory);
        if (navHistory != null) {
            navHistory.setOnClickListener(v -> {
                Intent intent = new Intent(ProfileActivity.this, HistoryActivity.class);
                startActivity(intent);
            });
        }

        // Profile - already here, do nothing
        LinearLayout navProfile = findViewById(R.id.navProfile);
        if (navProfile != null) {
            navProfile.setOnClickListener(v -> {
                // Already on profile page
            });
        }
    }
}

