package com.nutricycle.nutricycle;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class KateringDetails extends AppCompatActivity {

    private KateringMenuData.PackageMenu packageMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_katering_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ambil data dari intent
        String packageName = getIntent().getStringExtra("package_name");
        if (packageName != null) {
            packageMenu = KateringMenuData.getMenuByPackageName(packageName);
            setupUI();
        }

        setupBackButton();
    }

    private void setupBackButton() {
        ImageView backButton = findViewById(R.id.backButton);
        if (backButton != null) {
            backButton.setOnClickListener(v -> finish());
        }
    }

    private void setupUI() {
        if (packageMenu == null) return;

        // Set header info
        TextView tvPackageName = findViewById(R.id.tvPackageName);
        TextView tvSubtitle = findViewById(R.id.tvSubtitle);
        TextView tvPrice = findViewById(R.id.tvPrice);
        TextView tvCalories = findViewById(R.id.tvCalories);

        if (tvPackageName != null) tvPackageName.setText(packageMenu.getPackageName());
        if (tvSubtitle != null) tvSubtitle.setText(packageMenu.getSubtitle());
        if (tvPrice != null) tvPrice.setText(packageMenu.getPrice());
        if (tvCalories != null) tvCalories.setText(packageMenu.getCalories());

        // Setup menu items
        setupMenuItems();
    }

    private void setupMenuItems() {
        LinearLayout menuContainer = findViewById(R.id.menuContainer);
        if (menuContainer == null || packageMenu == null) return;

        menuContainer.removeAllViews();

        List<KateringMenuData.MenuItem> menus = packageMenu.getMenus();
        String currentDay = "";

        for (KateringMenuData.MenuItem menu : menus) {
            // Add day header if new day
            if (!menu.getDay().equals(currentDay)) {
                currentDay = menu.getDay();
                addDayHeader(menuContainer, currentDay);
            }

            // Add menu item
            addMenuItem(menuContainer, menu);
        }
    }

    private void addDayHeader(LinearLayout container, String day) {
        TextView dayHeader = new TextView(this);
        dayHeader.setText(day);
        dayHeader.setTextSize(16);
        dayHeader.setTextColor(ContextCompat.getColor(this, R.color.nutri_card_text));
        dayHeader.setTypeface(null, android.graphics.Typeface.BOLD);
        dayHeader.setPadding(0, 24, 0, 12);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 16, 0, 0);
        dayHeader.setLayoutParams(params);

        container.addView(dayHeader);
    }

    private void addMenuItem(LinearLayout container, KateringMenuData.MenuItem menu) {

        MaterialCardView card = new MaterialCardView(this);

        // Card style
        card.setRadius(16f); // corner radius
        card.setCardElevation(4f); // shadow
        card.setStrokeWidth(1);
        card.setStrokeColor(
                ContextCompat.getColor(this, R.color.nutri_detail_divider)
        );
        card.setCardBackgroundColor(
                ContextCompat.getColor(this, R.color.white)
        );

        // LayoutParams untuk card
        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        cardParams.setMargins(0, 0, 0, 12);
        card.setLayoutParams(cardParams);


        // Isi card (vertical)
        LinearLayout contentLayout = new LinearLayout(this);
        contentLayout.setOrientation(LinearLayout.VERTICAL);
        contentLayout.setPadding(24, 24, 24, 24);


        // Meal time
        TextView mealTime = new TextView(this);
        mealTime.setText(menu.getMealTime());
        mealTime.setTextSize(12);
        mealTime.setTextColor(ContextCompat.getColor(this, R.color.nutri_detail_subtitle));
        mealTime.setPadding(0, 0, 0, 4);
        contentLayout.addView(mealTime);

        // Menu name
        TextView menuName = new TextView(this);
        menuName.setText(menu.getMenuName());
        menuName.setTextSize(15);
        menuName.setTextColor(ContextCompat.getColor(this, R.color.nutri_card_text));
        menuName.setTypeface(null, Typeface.BOLD);
        menuName.setPadding(0, 0, 0, 4);
        contentLayout.addView(menuName);

        // Description
        TextView description = new TextView(this);

    }
}

