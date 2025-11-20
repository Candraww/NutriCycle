package com.nutricycle.nutricycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupCateringCardClick();
        setupGiziMeterCardClick();
        setupHeaderActions();
        setupBottomNavigation();
    }

    private void setupCateringCardClick() {
        MaterialCardView cardWeeklyCatering = findViewById(R.id.cardWeeklyCatering);
        if (cardWeeklyCatering != null) {
            cardWeeklyCatering.setOnClickListener(v -> {
                // Navigasi ke halaman Daftar Katering
                Intent intent = new Intent(MainActivity.this, KateringList.class);
                startActivity(intent);
            });
        }
    }

    private void setupGiziMeterCardClick() {
        MaterialCardView cardGiziMeter = findViewById(R.id.cardGiziMeter);
        if (cardGiziMeter != null) {
            cardGiziMeter.setOnClickListener(v -> {
                // Navigasi ke halaman Gizi Meter
                Intent intent = new Intent(MainActivity.this, GiziMeterActivity.class);
                startActivity(intent);
            });
        }
    }

    private void setupHeaderActions() {
        // Notification button
        ImageView btnNotification = findViewById(R.id.btnNotification);
        if (btnNotification != null) {
            btnNotification.setOnClickListener(v -> {
                // TODO: Navigate to notifications page or show notification list
                // For now, just show a toast or placeholder
            });
        }

        // Promo box click
        MaterialCardView promoCard = findViewById(R.id.promoCard);
        if (promoCard != null) {
            promoCard.setOnClickListener(v -> {
                // TODO: Navigate to promo page or show promo details
            });
        }
    }

    private void setupBottomNavigation() {
        // Beranda (Home) - tidak perlu navigasi karena sudah di halaman home
        LinearLayout navHome = findViewById(R.id.navHome);
        if (navHome != null) {
            navHome.setOnClickListener(v -> {
                // Already on home page, do nothing
            });
        }

        // Keranjang (Cart)
        LinearLayout navCart = findViewById(R.id.navCart);
        if (navCart != null) {
            navCart.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
            });
        }

        // Riwayat (History)
        LinearLayout navHistory = findViewById(R.id.navHistory);
        if (navHistory != null) {
            navHistory.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(intent);
            });
        }

        // Profile
        LinearLayout navProfile = findViewById(R.id.navProfile);
        if (navProfile != null) {
            navProfile.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            });
        }
    }
}