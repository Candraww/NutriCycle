package com.nutricycle.nutricycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

        setupBackButton();
        setupOrderButtons();
    }

    private void setupBackButton() {
        View backButton = findViewById(R.id.backButton);
        if (backButton != null) {
            backButton.setOnClickListener(v -> finish());
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