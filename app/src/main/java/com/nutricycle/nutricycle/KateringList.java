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
        setupOrderButtons();
    }

    private void setupToolbar() {
        MaterialToolbar toolbar = findViewById(R.id.pageToolbar);
        if (toolbar != null) {
            toolbar.setNavigationOnClickListener(v -> finish());

            TextView titleView = toolbar.findViewById(R.id.toolbarTitle);
            TextView subtitleView = toolbar.findViewById(R.id.toolbarSubtitle);
            TextView countView = toolbar.findViewById(R.id.toolbarKateringCount);

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
    }

    private void setupOrderButtons() {
        // Button untuk FreshFuel Kitchen
        TextView btnOrderFreshFuel = findViewById(R.id.btnOrderFreshFuel);
        if (btnOrderFreshFuel != null) {
            btnOrderFreshFuel.setOnClickListener(v -> {
                handleOrder("FreshFuel Kitchen", "Rp 325.000 / minggu");
            });
        }

        // Button untuk Rasa Nusantara Sehat
        TextView btnOrderNusantara = findViewById(R.id.btnOrderNusantara);
        if (btnOrderNusantara != null) {
            btnOrderNusantara.setOnClickListener(v -> {
                handleOrder("Rasa Nusantara Sehat", "Rp 310.000 / minggu");
            });
        }

        // Button untuk Leafy Bowl
        TextView btnOrderLeafy = findViewById(R.id.btnOrderLeafy);
        if (btnOrderLeafy != null) {
            btnOrderLeafy.setOnClickListener(v -> {
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
}