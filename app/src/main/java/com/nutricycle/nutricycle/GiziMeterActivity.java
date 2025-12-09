package com.nutricycle.nutricycle;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GiziMeterActivity extends AppCompatActivity {

    // Data dummy untuk demo
    private NutritionSummary totalSummary;
    private List<WeekData> weekDataList;
    private List<MonthData> monthDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gizi_meter);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupToolbar();
        setupBottomNavigation();
        initializeDummyData();
        setupTotalSummary();
        setupCharts();
        setupWeekStats();
        setupMonthStats();
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
                Intent intent = new Intent(GiziMeterActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            });
        }

        // Keranjang (Cart)
        LinearLayout navCart = findViewById(R.id.navCart);
        if (navCart != null) {
            navCart.setOnClickListener(v -> {
                Intent intent = new Intent(GiziMeterActivity.this, CartActivity.class);
                startActivity(intent);
            });
        }

        // Riwayat (History)
        LinearLayout navHistory = findViewById(R.id.navHistory);
        if (navHistory != null) {
            navHistory.setOnClickListener(v -> {
                Intent intent = new Intent(GiziMeterActivity.this, HistoryActivity.class);
                startActivity(intent);
            });
        }

        // Profile
        LinearLayout navProfile = findViewById(R.id.navProfile);
        if (navProfile != null) {
            navProfile.setOnClickListener(v -> {
                Intent intent = new Intent(GiziMeterActivity.this, ProfileActivity.class);
                startActivity(intent);
            });
        }
    }

    private void initializeDummyData() {
        // Total summary
        totalSummary = new NutritionSummary(8750, 420, 1050, 280, 180);

        // Week data
        weekDataList = new ArrayList<>();
        weekDataList.add(new WeekData("Week 1", 2100, 98, 250, 65, 42));
        weekDataList.add(new WeekData("Week 2", 2200, 105, 265, 68, 45));
        weekDataList.add(new WeekData("Week 3", 2150, 102, 258, 70, 43));
        weekDataList.add(new WeekData("Week 4", 2300, 115, 277, 77, 50));

        // Month data
        monthDataList = new ArrayList<>();
        monthDataList.add(new MonthData("Januari", 8500, 400, 1020, 270, 170, 5.2f));
        monthDataList.add(new MonthData("Februari", 9200, 440, 1100, 290, 185, 8.2f));
        monthDataList.add(new MonthData("Maret", 8800, 420, 1055, 275, 175, -4.3f));
        monthDataList.add(new MonthData("April", 9500, 455, 1140, 305, 195, 8.0f));
    }

    private void setupTotalSummary() {
        TextView tvTotalCalories = findViewById(R.id.tvTotalCalories);
        TextView tvTotalProtein = findViewById(R.id.tvTotalProtein);
        TextView tvTotalCarbs = findViewById(R.id.tvTotalCarbs);
        TextView tvTotalFat = findViewById(R.id.tvTotalFat);
        TextView tvTotalFiber = findViewById(R.id.tvTotalFiber);

        if (tvTotalCalories != null) {
            tvTotalCalories.setText(String.format(Locale.getDefault(), "%,d", totalSummary.calories));
        }
        if (tvTotalProtein != null) {
            tvTotalProtein.setText(String.format(Locale.getDefault(), "%.0fg", totalSummary.protein));
        }
        if (tvTotalCarbs != null) {
            tvTotalCarbs.setText(String.format(Locale.getDefault(), "%.0fg", totalSummary.carbs));
        }
        if (tvTotalFat != null) {
            tvTotalFat.setText(String.format(Locale.getDefault(), "%.0fg", totalSummary.fat));
        }
        if (tvTotalFiber != null) {
            tvTotalFiber.setText(String.format(Locale.getDefault(), "%.0fg", totalSummary.fiber));
        }
    }

    private void setupCharts() {
        // Setup Calories Chart
        SimpleBarChart chartCalories = findViewById(R.id.chartCalories);
        if (chartCalories != null && weekDataList != null) {
            List<SimpleBarChart.BarData> caloriesData = new ArrayList<>();
            for (WeekData week : weekDataList) {
                caloriesData.add(new SimpleBarChart.BarData(week.weekName.replace("Week ", "W"), week.calories));
            }
            chartCalories.setData(caloriesData);
        }

        // Setup Macros Chart (using average protein as example)
        SimpleBarChart chartMacros = findViewById(R.id.chartMacros);
        if (chartMacros != null && weekDataList != null) {
            List<SimpleBarChart.BarData> macrosData = new ArrayList<>();
            for (WeekData week : weekDataList) {
                // Show total macros (protein + carbs + fat) as example
                int totalMacros = (int) (week.protein + week.carbs + week.fat);
                macrosData.add(new SimpleBarChart.BarData(week.weekName.replace("Week ", "W"), totalMacros));
            }
            chartMacros.setData(macrosData);
        }
    }

    private void setupWeekStats() {
        LinearLayout weekContainer = findViewById(R.id.weekContainer);
        if (weekContainer == null || weekDataList == null) return;

        weekContainer.removeAllViews();

        // Find min and max calories for highlighting
        int maxCalories = 0;
        int minCalories = Integer.MAX_VALUE;
        for (WeekData week : weekDataList) {
            if (week.calories > maxCalories) maxCalories = week.calories;
            if (week.calories < minCalories) minCalories = week.calories;
        }

        for (WeekData week : weekDataList) {
            MaterialCardView card = createWeekCard(week, week.calories == maxCalories, week.calories == minCalories);
            weekContainer.addView(card);
        }
    }

    private MaterialCardView createWeekCard(WeekData week, boolean isHighest, boolean isLowest) {
        MaterialCardView card = new MaterialCardView(this);
        card.setCardBackgroundColor(ContextCompat.getColor(this, R.color.white));
        card.setCardElevation(4);
        card.setRadius(16f);
        card.setStrokeWidth(isHighest || isLowest ? 3 : 1);
        card.setStrokeColor(ContextCompat.getColor(this, 
            isHighest ? R.color.nutri_green_primary : 
            isLowest ? R.color.nutri_teal : 
            R.color.nutri_detail_divider));

        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        );
        cardParams.setMargins(0, 0, 0, 16);
        card.setLayoutParams(cardParams);

        LinearLayout content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setPadding(20, 20, 20, 20);

        // Week label
        TextView weekLabel = new TextView(this);
        weekLabel.setText(week.weekName);
        weekLabel.setTextSize(16);
        weekLabel.setTextColor(ContextCompat.getColor(this, R.color.nutri_card_text));
        weekLabel.setTypeface(null, android.graphics.Typeface.BOLD);
        if (isHighest) {
            weekLabel.append(" ⭐ Tertinggi");
            weekLabel.setTextColor(ContextCompat.getColor(this, R.color.nutri_green_primary));
        } else if (isLowest) {
            weekLabel.append(" 📉 Terendah");
            weekLabel.setTextColor(ContextCompat.getColor(this, R.color.nutri_teal));
        }
        content.addView(weekLabel);

        // Calories
        TextView calories = new TextView(this);
        calories.setText(String.format(Locale.getDefault(), "Kalori: %,d kkal", week.calories));
        calories.setTextSize(14);
        calories.setTextColor(ContextCompat.getColor(this, R.color.nutri_text_secondary));
        calories.setPadding(0, 8, 0, 4);
        content.addView(calories);

        // Macros
        LinearLayout macrosLayout = new LinearLayout(this);
        macrosLayout.setOrientation(LinearLayout.HORIZONTAL);
        macrosLayout.setWeightSum(3);

        TextView protein = new TextView(this);
        protein.setText(String.format(Locale.getDefault(), "Pro: %.0fg", week.protein));
        LinearLayout.LayoutParams pParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
        protein.setLayoutParams(pParams);
        protein.setTextSize(12);
        protein.setTextColor(ContextCompat.getColor(this, R.color.nutri_text_secondary));
        macrosLayout.addView(protein);

        TextView carbs = new TextView(this);
        carbs.setText(String.format(Locale.getDefault(), "Kar: %.0fg", week.carbs));
        LinearLayout.LayoutParams cParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
        carbs.setLayoutParams(cParams);
        carbs.setTextSize(12);
        carbs.setTextColor(ContextCompat.getColor(this, R.color.nutri_text_secondary));
        macrosLayout.addView(carbs);

        TextView fat = new TextView(this);
        fat.setText(String.format(Locale.getDefault(), "Lem: %.0fg", week.fat));
        LinearLayout.LayoutParams fParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
        fat.setLayoutParams(fParams);
        fat.setTextSize(12);
        fat.setTextColor(ContextCompat.getColor(this, R.color.nutri_text_secondary));
        macrosLayout.addView(fat);

        content.addView(macrosLayout);

        card.addView(content);
        return card;
    }

    private void setupMonthStats() {
        LinearLayout monthContainer = findViewById(R.id.monthContainer);
        if (monthContainer == null || monthDataList == null) return;

        monthContainer.removeAllViews();

        for (MonthData month : monthDataList) {
            MaterialCardView card = createMonthCard(month);
            monthContainer.addView(card);
        }
    }

    private MaterialCardView createMonthCard(MonthData month) {
        MaterialCardView card = new MaterialCardView(this);
        card.setCardBackgroundColor(ContextCompat.getColor(this, R.color.white));
        card.setCardElevation(4);
        card.setRadius(16f);
        card.setStrokeWidth(1);
        card.setStrokeColor(ContextCompat.getColor(this, R.color.nutri_detail_divider));

        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        );
        cardParams.setMargins(0, 0, 0, 16);
        card.setLayoutParams(cardParams);

        LinearLayout content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setPadding(20, 20, 20, 20);

        // Month header with trend
        LinearLayout headerLayout = new LinearLayout(this);
        headerLayout.setOrientation(LinearLayout.HORIZONTAL);
        headerLayout.setWeightSum(2);

        TextView monthLabel = new TextView(this);
        monthLabel.setText(month.monthName);
        monthLabel.setTextSize(16);
        monthLabel.setTextColor(ContextCompat.getColor(this, R.color.nutri_card_text));
        monthLabel.setTypeface(null, android.graphics.Typeface.BOLD);
        LinearLayout.LayoutParams labelParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
        monthLabel.setLayoutParams(labelParams);
        headerLayout.addView(monthLabel);

        TextView trend = new TextView(this);
        String trendText = month.trend > 0 ? 
            String.format(Locale.getDefault(), "↑ +%.1f%%", month.trend) :
            String.format(Locale.getDefault(), "↓ %.1f%%", month.trend);
        trend.setText(trendText);
        trend.setTextSize(12);
        trend.setTextColor(ContextCompat.getColor(this, 
            month.trend > 0 ? R.color.nutri_green_primary : R.color.nutri_teal));
        trend.setTypeface(null, android.graphics.Typeface.BOLD);
        LinearLayout.LayoutParams trendParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
        trend.setGravity(android.view.Gravity.END);
        trend.setLayoutParams(trendParams);
        headerLayout.addView(trend);

        content.addView(headerLayout);

        // Calories
        TextView calories = new TextView(this);
        calories.setText(String.format(Locale.getDefault(), "Kalori: %,d kkal", month.calories));
        calories.setTextSize(14);
        calories.setTextColor(ContextCompat.getColor(this, R.color.nutri_text_secondary));
        calories.setPadding(0, 8, 0, 4);
        content.addView(calories);

        // Macros
        LinearLayout macrosLayout = new LinearLayout(this);
        macrosLayout.setOrientation(LinearLayout.HORIZONTAL);
        macrosLayout.setWeightSum(3);

        TextView protein = new TextView(this);
        protein.setText(String.format(Locale.getDefault(), "Pro: %.0fg", month.protein));
        LinearLayout.LayoutParams pParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
        protein.setLayoutParams(pParams);
        protein.setTextSize(12);
        protein.setTextColor(ContextCompat.getColor(this, R.color.nutri_text_secondary));
        macrosLayout.addView(protein);

        TextView carbs = new TextView(this);
        carbs.setText(String.format(Locale.getDefault(), "Kar: %.0fg", month.carbs));
        LinearLayout.LayoutParams cParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
        carbs.setLayoutParams(cParams);
        carbs.setTextSize(12);
        carbs.setTextColor(ContextCompat.getColor(this, R.color.nutri_text_secondary));
        macrosLayout.addView(carbs);

        TextView fat = new TextView(this);
        fat.setText(String.format(Locale.getDefault(), "Lem: %.0fg", month.fat));
        LinearLayout.LayoutParams fParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
        fat.setLayoutParams(fParams);
        fat.setTextSize(12);
        fat.setTextColor(ContextCompat.getColor(this, R.color.nutri_text_secondary));
        macrosLayout.addView(fat);

        content.addView(macrosLayout);

        card.addView(content);
        return card;
    }

    // Data classes
    private static class NutritionSummary {
        int calories;
        float protein;
        float carbs;
        float fat;
        float fiber;

        NutritionSummary(int calories, float protein, float carbs, float fat, float fiber) {
            this.calories = calories;
            this.protein = protein;
            this.carbs = carbs;
            this.fat = fat;
            this.fiber = fiber;
        }
    }

    private static class WeekData {
        String weekName;
        int calories;
        float protein;
        float carbs;
        float fat;
        float fiber;

        WeekData(String weekName, int calories, float protein, float carbs, float fat, float fiber) {
            this.weekName = weekName;
            this.calories = calories;
            this.protein = protein;
            this.carbs = carbs;
            this.fat = fat;
            this.fiber = fiber;
        }
    }

    private static class MonthData {
        String monthName;
        int calories;
        float protein;
        float carbs;
        float fat;
        float fiber;
        float trend; // percentage change

        MonthData(String monthName, int calories, float protein, float carbs, float fat, float fiber, float trend) {
            this.monthName = monthName;
            this.calories = calories;
            this.protein = protein;
            this.carbs = carbs;
            this.fat = fat;
            this.fiber = fiber;
            this.trend = trend;
        }
    }
}

