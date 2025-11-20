package com.nutricycle.nutricycle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupToolbar();
        setupBottomNavigation();
    }

    private void setupToolbar() {
        MaterialToolbar toolbar = findViewById(R.id.pageToolbar);
        if (toolbar != null) {
            toolbar.setNavigationOnClickListener(v -> finish());
        }
    }

    private void setupBottomNavigation() {
        // Beranda (Home)
        LinearLayout navHome = findViewById(R.id.navHome);
        if (navHome != null) {
            navHome.setOnClickListener(v -> {
                Intent intent = new Intent(CartActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            });
        }

        // Keranjang (Cart) - already here, do nothing
        LinearLayout navCart = findViewById(R.id.navCart);
        if (navCart != null) {
            navCart.setOnClickListener(v -> {
                // Already on cart page
            });
        }

        // Riwayat (History)
        LinearLayout navHistory = findViewById(R.id.navHistory);
        if (navHistory != null) {
            navHistory.setOnClickListener(v -> {
                Intent intent = new Intent(CartActivity.this, HistoryActivity.class);
                startActivity(intent);
            });
        }

        // Profile
        LinearLayout navProfile = findViewById(R.id.navProfile);
        if (navProfile != null) {
            navProfile.setOnClickListener(v -> {
                Intent intent = new Intent(CartActivity.this, ProfileActivity.class);
                startActivity(intent);
            });
        }
    }
}

