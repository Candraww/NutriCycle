package com.nutricycle.nutricycle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import android.widget.ImageView;

public class KateringList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_katering_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupToolbar();
        setupCardClicks();
        setupBottomNavigation();
    }

    private void setupToolbar() {
        // Back button handler
        ImageView btnBack = findViewById(R.id.toolbarBack);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        // Update text views
        TextView titleView = findViewById(R.id.toolbarTitle);
        TextView subtitleView = findViewById(R.id.toolbarSubtitle);
        TextView countView = findViewById(R.id.toolbarKateringCount);

        if (titleView != null) {
            titleView.setText("Daftar Katering");
        }
        if (subtitleView != null) {
            subtitleView.setText("Plan sehat setiap minggu");
        }
        if (countView != null) {
            LinearLayout listContainer = findViewById(R.id.listContainer);
            int count = listContainer != null ? listContainer.getChildCount() : 0;
            countView.setText(count + " katering tersedia");
        }
    }

    private void setupCardClicks() {
        // Card FreshFuel Kitchen
        MaterialCardView cardFreshFuel = findViewById(R.id.cardFreshFuel);
        if (cardFreshFuel != null) {
            cardFreshFuel.setOnClickListener(v -> {
                handleOrder("FreshFuel Kitchen", "Rp 325.000 / minggu");
            });
        }

        // Card Rasa Nusantara Sehat
        MaterialCardView cardNusantara = findViewById(R.id.cardNusantara);
        if (cardNusantara != null) {
            cardNusantara.setOnClickListener(v -> {
                handleOrder("Rasa Nusantara Sehat", "Rp 310.000 / minggu");
            });
        }

        // Card Leafy Bowl
        MaterialCardView cardLeafy = findViewById(R.id.cardLeafy);
        if (cardLeafy != null) {
            cardLeafy.setOnClickListener(v -> {
                handleOrder("Leafy Bowl", "Rp 299.000 / minggu");
            });
        }
    }

    private void handleOrder(String packageName, String price) {
        // Navigasi ke halaman detail katering dengan menu lengkap
        Intent intent = new Intent(this, KateringDetails.class);
        intent.putExtra("package_name", packageName);
        intent.putExtra("price", price);
        startActivity(intent);
    }

    private void setupBottomNavigation() {
        // Beranda (Home)
        LinearLayout navHome = findViewById(R.id.navHome);
        if (navHome != null) {
            navHome.setOnClickListener(v -> {
                Intent intent = new Intent(KateringList.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            });
        }

        // Keranjang (Cart)
        LinearLayout navCart = findViewById(R.id.navCart);
        if (navCart != null) {
            navCart.setOnClickListener(v -> {
                Intent intent = new Intent(KateringList.this, CartActivity.class);
                startActivity(intent);
            });
        }

        // Riwayat (History)
        LinearLayout navHistory = findViewById(R.id.navHistory);
        if (navHistory != null) {
            navHistory.setOnClickListener(v -> {
                Intent intent = new Intent(KateringList.this, HistoryActivity.class);
                startActivity(intent);
            });
        }

        // Profile
        LinearLayout navProfile = findViewById(R.id.navProfile);
        if (navProfile != null) {
            navProfile.setOnClickListener(v -> {
                Intent intent = new Intent(KateringList.this, ProfileActivity.class);
                startActivity(intent);
            });
        }
    }
}