package com.nutricycle.nutricycle;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;
import java.util.Locale;

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

        setupToolbar();
    }

    private void setupToolbar() {
        MaterialToolbar toolbar = findViewById(R.id.pageToolbar);
        if (toolbar != null) {
            toolbar.setNavigationOnClickListener(v -> finish());
        }
    }

    private void setupUI() {
        if (packageMenu == null) return;

        // Set header info
        TextView tvSubtitle = findViewById(R.id.tvSubtitle);
        TextView tvPrice = findViewById(R.id.tvPrice);
        TextView tvCalories = findViewById(R.id.tvCalories);
        TextView tvHeaderName = findViewById(R.id.tvHeaderName);
        TextView tvHeaderSubtitle = findViewById(R.id.tvHeaderSubtitle);
        TextView tvHeaderRating = findViewById(R.id.tvHeaderRating);
        TextView tvHeaderLocation = findViewById(R.id.tvHeaderLocation);
        MaterialToolbar toolbar = findViewById(R.id.pageToolbar);
        TextView toolbarTitle = toolbar != null ? toolbar.findViewById(R.id.toolbarTitle) : null;
        TextView toolbarSubtitle = toolbar != null ? toolbar.findViewById(R.id.toolbarSubtitle) : null;
        TextView toolbarInfo = toolbar != null ? toolbar.findViewById(R.id.toolbarKateringInfo) : null;

        if (tvSubtitle != null) tvSubtitle.setText(packageMenu.getSubtitle());
        if (tvPrice != null) tvPrice.setText(packageMenu.getPrice());
        if (tvCalories != null) tvCalories.setText(packageMenu.getCalories());
        if (tvHeaderName != null) tvHeaderName.setText(packageMenu.getPackageName());
        if (tvHeaderSubtitle != null) tvHeaderSubtitle.setText(packageMenu.getSubtitle());
        if (tvHeaderRating != null) {
            String ratingText = String.format(Locale.getDefault(), "%.1f / 5", packageMenu.getRating());
            tvHeaderRating.setText(ratingText);
        }
        if (tvHeaderLocation != null) tvHeaderLocation.setText(packageMenu.getLocation());
        if (toolbarTitle != null) toolbarTitle.setText(packageMenu.getPackageName());
        if (toolbarSubtitle != null) toolbarSubtitle.setText(packageMenu.getSubtitle());
        if (toolbarInfo != null) {
            String info = String.format(Locale.getDefault(), "%.1f / 5  •  %s", packageMenu.getRating(), packageMenu.getLocation());
            toolbarInfo.setText(info);
        }

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
        card.setCardElevation(16);
        card.setCardElevation(2);
        card.setStrokeWidth(1);
        card.setStrokeColor(ContextCompat.getColor(this, R.color.nutri_detail_divider));
        
        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        );
        cardParams.setMargins(0, 0, 0, 12);
        card.setLayoutParams(cardParams);
        
        LinearLayout contentLayout = new LinearLayout(this);
        contentLayout.setOrientation(LinearLayout.VERTICAL);
        contentLayout.setPadding(16, 16, 16, 16);
        
        // Meal time label
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
        description.setText(menu.getDescription());
        description.setTextSize(13);
        description.setTextColor(ContextCompat.getColor(this, R.color.nutri_text_secondary));
        description.setPadding(0, 0, 0, 8);
        contentLayout.addView(description);
        
        // Nutrition Info
        if (menu.getNutrition() != null) {
            KateringMenuData.NutritionInfo nutrition = menu.getNutrition();
            
            // Container for nutrition info
            LinearLayout nutritionLayout = new LinearLayout(this);
            nutritionLayout.setOrientation(LinearLayout.VERTICAL);
            nutritionLayout.setPadding(12, 12, 12, 12);
            nutritionLayout.setBackgroundResource(R.drawable.meal_option_background);
            
            // Nutrition header
            TextView nutritionHeader = new TextView(this);
            nutritionHeader.setText("Nilai Gizi");
            nutritionHeader.setTextSize(12);
            nutritionHeader.setTextColor(ContextCompat.getColor(this, R.color.nutri_detail_subtitle));
            nutritionHeader.setTypeface(null, Typeface.BOLD);
            nutritionHeader.setPadding(0, 0, 0, 6);
            nutritionLayout.addView(nutritionHeader);
            
            // Nutrition details in horizontal layout
            LinearLayout nutritionDetails = new LinearLayout(this);
            nutritionDetails.setOrientation(LinearLayout.HORIZONTAL);
            nutritionDetails.setWeightSum(4);
            
            // Calories
            TextView calories = new TextView(this);
            calories.setText(nutrition.getCalories() + " kkal");
            calories.setTextSize(11);
            calories.setTextColor(ContextCompat.getColor(this, R.color.nutri_detail_accent));
            calories.setTypeface(null, Typeface.BOLD);
            LinearLayout.LayoutParams calParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
            calories.setLayoutParams(calParams);
            nutritionDetails.addView(calories);
            
            // Protein
            TextView protein = new TextView(this);
            protein.setText("P: " + nutrition.getProtein() + "g");
            protein.setTextSize(11);
            protein.setTextColor(ContextCompat.getColor(this, R.color.nutri_text_secondary));
            LinearLayout.LayoutParams protParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
            protein.setLayoutParams(protParams);
            nutritionDetails.addView(protein);
            
            // Carbs
            TextView carbs = new TextView(this);
            carbs.setText("K: " + nutrition.getCarbs() + "g");
            carbs.setTextSize(11);
            carbs.setTextColor(ContextCompat.getColor(this, R.color.nutri_text_secondary));
            LinearLayout.LayoutParams carbParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
            carbs.setLayoutParams(carbParams);
            nutritionDetails.addView(carbs);
            
            // Fat
            TextView fat = new TextView(this);
            fat.setText("L: " + nutrition.getFat() + "g");
            fat.setTextSize(11);
            fat.setTextColor(ContextCompat.getColor(this, R.color.nutri_text_secondary));
            LinearLayout.LayoutParams fatParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
            fat.setLayoutParams(fatParams);
            nutritionDetails.addView(fat);
            
            nutritionLayout.addView(nutritionDetails);
            
            // Fiber (optional, below)
            TextView fiber = new TextView(this);
            fiber.setText("Serat: " + nutrition.getFiber() + "g");
            fiber.setTextSize(10);
            fiber.setTextColor(ContextCompat.getColor(this, R.color.nutri_muted_text));
            fiber.setPadding(0, 4, 0, 0);
            nutritionLayout.addView(fiber);
            
            contentLayout.addView(nutritionLayout);
        }
        
        card.addView(contentLayout);
        container.addView(card);
    }
}

