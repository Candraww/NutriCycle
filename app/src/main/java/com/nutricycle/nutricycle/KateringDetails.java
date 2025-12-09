package com.nutricycle.nutricycle;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
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
import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;
import java.util.Locale;

public class KateringDetails extends AppCompatActivity {

    private KateringMenuData.PackageMenu packageMenu;
    private String selectedContainer = "non-reusable"; // default
    private String selectedPaymentMethod = null;
    private View bottomSheetView;

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
        setupBottomNavigation();
        setupContainerSelection();
        setupOrderButton();
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

    private void setupContainerSelection() {
        MaterialCardView cardReusable = findViewById(R.id.cardReusable);
        MaterialCardView cardNonReusable = findViewById(R.id.cardNonReusable);
        ImageView radioReusable = findViewById(R.id.radioReusable);
        ImageView radioNonReusable = findViewById(R.id.radioNonReusable);

        if (cardReusable != null && cardNonReusable != null) {
            // Set default selection
            updateContainerSelection(radioReusable, radioNonReusable, cardReusable, cardNonReusable, false);

            cardReusable.setOnClickListener(v -> {
                selectedContainer = "reusable";
                updateContainerSelection(radioReusable, radioNonReusable, cardReusable, cardNonReusable, true);
            });

            cardNonReusable.setOnClickListener(v -> {
                selectedContainer = "non-reusable";
                updateContainerSelection(radioReusable, radioNonReusable, cardReusable, cardNonReusable, false);
            });
        }
    }

    private void updateContainerSelection(ImageView radioReusable, ImageView radioNonReusable,
                                         MaterialCardView cardReusable, MaterialCardView cardNonReusable,
                                         boolean isReusable) {
        if (radioReusable != null && radioNonReusable != null) {
            if (isReusable) {
                radioReusable.setImageResource(R.drawable.ic_radio_checked);
                radioReusable.setColorFilter(ContextCompat.getColor(this, R.color.nutri_green_primary));
                radioNonReusable.setImageResource(R.drawable.ic_radio_unchecked);
                radioNonReusable.setColorFilter(ContextCompat.getColor(this, R.color.nutri_text_secondary));
                cardReusable.setStrokeColor(ContextCompat.getColor(this, R.color.nutri_green_primary));
                cardReusable.setStrokeWidth(3);
                cardNonReusable.setStrokeColor(ContextCompat.getColor(this, R.color.nutri_detail_divider));
                cardNonReusable.setStrokeWidth(1);
            } else {
                radioReusable.setImageResource(R.drawable.ic_radio_unchecked);
                radioReusable.setColorFilter(ContextCompat.getColor(this, R.color.nutri_text_secondary));
                radioNonReusable.setImageResource(R.drawable.ic_radio_checked);
                radioNonReusable.setColorFilter(ContextCompat.getColor(this, R.color.nutri_primary));
                cardReusable.setStrokeColor(ContextCompat.getColor(this, R.color.nutri_detail_divider));
                cardReusable.setStrokeWidth(1);
                cardNonReusable.setStrokeColor(ContextCompat.getColor(this, R.color.nutri_primary));
                cardNonReusable.setStrokeWidth(3);
            }
        }
    }

    private void setupOrderButton() {
        MaterialCardView btnOrder = findViewById(R.id.btnOrder);
        if (btnOrder != null) {
            btnOrder.setOnClickListener(v -> showPaymentBottomSheet());
        }
    }

    private void showPaymentBottomSheet() {
        if (bottomSheetView == null) {
            LayoutInflater inflater = LayoutInflater.from(this);
            bottomSheetView = inflater.inflate(R.layout.bottom_sheet_payment, null);
            
            // Add to parent
            android.view.ViewGroup mainLayout = findViewById(R.id.main);
            if (mainLayout != null) {
                mainLayout.addView(bottomSheetView);
            }
            
            setupPaymentMethods();
            setupPaymentButtons();
        }
        
        // Show bottom sheet
        View container = bottomSheetView.findViewById(R.id.bottomSheetContainer);
        if (container != null) {
            container.setVisibility(View.VISIBLE);
        }
    }
    
    private void hidePaymentBottomSheet() {
        View container = bottomSheetView != null ? bottomSheetView.findViewById(R.id.bottomSheetContainer) : null;
        if (container != null) {
            container.setVisibility(View.GONE);
        }
    }

    private void setupPaymentMethods() {
        // Setup all payment method cards
        setupPaymentCard(R.id.paymentMandiri, R.id.checkMandiri, "Mandiri");
        setupPaymentCard(R.id.paymentBRI, R.id.checkBRI, "BRI");
        setupPaymentCard(R.id.paymentBNI, R.id.checkBNI, "BNI");
        setupPaymentCard(R.id.paymentBCA, R.id.checkBCA, "BCA");
        setupPaymentCard(R.id.paymentOVO, R.id.checkOVO, "OVO");
        setupPaymentCard(R.id.paymentDana, R.id.checkDana, "Dana");
        setupPaymentCard(R.id.paymentGoPay, R.id.checkGoPay, "GoPay");
        setupPaymentCard(R.id.paymentQRIS, R.id.checkQRIS, "QRIS");
        setupPaymentCard(R.id.paymentCOD, R.id.checkCOD, "COD");
    }

    private void setupPaymentCard(int cardId, int checkId, String paymentMethod) {
        MaterialCardView card = bottomSheetView.findViewById(cardId);
        ImageView check = bottomSheetView.findViewById(checkId);
        
        if (card != null && check != null) {
            card.setOnClickListener(v -> {
                selectPaymentMethod(paymentMethod, checkId);
            });
        }
    }

    private void selectPaymentMethod(String method, int checkId) {
        selectedPaymentMethod = method;
        
        // Hide all checks
        int[] checkIds = {R.id.checkMandiri, R.id.checkBRI, R.id.checkBNI, R.id.checkBCA,
                         R.id.checkOVO, R.id.checkDana, R.id.checkGoPay, R.id.checkQRIS, R.id.checkCOD};
        for (int id : checkIds) {
            ImageView check = bottomSheetView.findViewById(id);
            if (check != null) {
                check.setVisibility(View.GONE);
            }
        }
        
        // Show selected check
        ImageView selectedCheck = bottomSheetView.findViewById(checkId);
        if (selectedCheck != null) {
            selectedCheck.setVisibility(View.VISIBLE);
            selectedCheck.setImageResource(R.drawable.ic_radio_checked);
        }
        
        // Update card highlight
        updatePaymentCardHighlight(method);
    }

    private void updatePaymentCardHighlight(String method) {
        int[] cardIds = {R.id.paymentMandiri, R.id.paymentBRI, R.id.paymentBNI, R.id.paymentBCA,
                        R.id.paymentOVO, R.id.paymentDana, R.id.paymentGoPay, R.id.paymentQRIS, R.id.paymentCOD};
        String[] methods = {"Mandiri", "BRI", "BNI", "BCA", "OVO", "Dana", "GoPay", "QRIS", "COD"};
        
        for (int i = 0; i < cardIds.length; i++) {
            MaterialCardView card = bottomSheetView.findViewById(cardIds[i]);
            if (card != null) {
                if (methods[i].equals(method)) {
                    card.setStrokeColor(ContextCompat.getColor(this, R.color.nutri_primary));
                    card.setStrokeWidth(3);
                } else {
                    card.setStrokeColor(ContextCompat.getColor(this, R.color.nutri_detail_divider));
                    card.setStrokeWidth(1);
                }
            }
        }
    }

    private void setupPaymentButtons() {
        // Close button
        ImageView btnClose = bottomSheetView.findViewById(R.id.btnClosePayment);
        if (btnClose != null) {
            btnClose.setOnClickListener(v -> hidePaymentBottomSheet());
        }
        
        // Close on background click
        View container = bottomSheetView.findViewById(R.id.bottomSheetContainer);
        if (container != null) {
            container.setOnClickListener(v -> hidePaymentBottomSheet());
        }
        
        // Prevent closing when clicking on bottom sheet itself
        View bottomSheet = bottomSheetView.findViewById(R.id.bottomSheet);
        if (bottomSheet != null) {
            bottomSheet.setOnClickListener(v -> {
                // Do nothing, prevent event bubbling
            });
        }

        // Add payment method button
        MaterialCardView btnAddPayment = bottomSheetView.findViewById(R.id.btnAddPayment);
        if (btnAddPayment != null) {
            btnAddPayment.setOnClickListener(v -> {
                // TODO: Implement add payment method
            });
        }

        // Done button
        MaterialCardView btnDone = bottomSheetView.findViewById(R.id.btnDonePayment);
        if (btnDone != null) {
            btnDone.setOnClickListener(v -> {
                if (selectedPaymentMethod != null) {
                    // TODO: Process order with selected payment method
                    hidePaymentBottomSheet();
                    // Show success message or navigate to order confirmation
                }
            });
        }
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
        card.setCardBackgroundColor(ContextCompat.getColor(this, R.color.white));
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
            protein.setText("Pro: " + nutrition.getProtein() + "g");
            protein.setTextSize(11);
            protein.setTextColor(ContextCompat.getColor(this, R.color.nutri_text_secondary));
            LinearLayout.LayoutParams protParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
            protein.setLayoutParams(protParams);
            nutritionDetails.addView(protein);
            
            // Carbs
            TextView carbs = new TextView(this);
            carbs.setText("Kar: " + nutrition.getCarbs() + "g");
            carbs.setTextSize(11);
            carbs.setTextColor(ContextCompat.getColor(this, R.color.nutri_text_secondary));
            LinearLayout.LayoutParams carbParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
            carbs.setLayoutParams(carbParams);
            nutritionDetails.addView(carbs);
            
            // Fat
            TextView fat = new TextView(this);
            fat.setText("Lem: " + nutrition.getFat() + "g");
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

    private void setupBottomNavigation() {
        // Beranda (Home)
        LinearLayout navHome = findViewById(R.id.navHome);
        if (navHome != null) {
            navHome.setOnClickListener(v -> {
                Intent intent = new Intent(KateringDetails.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            });
        }

        // Keranjang (Cart)
        LinearLayout navCart = findViewById(R.id.navCart);
        if (navCart != null) {
            navCart.setOnClickListener(v -> {
                Intent intent = new Intent(KateringDetails.this, CartActivity.class);
                startActivity(intent);
            });
        }

        // Riwayat (History)
        LinearLayout navHistory = findViewById(R.id.navHistory);
        if (navHistory != null) {
            navHistory.setOnClickListener(v -> {
                Intent intent = new Intent(KateringDetails.this, HistoryActivity.class);
                startActivity(intent);
            });
        }

        // Profile
        LinearLayout navProfile = findViewById(R.id.navProfile);
        if (navProfile != null) {
            navProfile.setOnClickListener(v -> {
                Intent intent = new Intent(KateringDetails.this, ProfileActivity.class);
                startActivity(intent);
            });
        }
    }
}

