package com.nutricycle.nutricycle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KateringMenuData {
    
    public static class NutritionInfo {
        private int calories;      // kkal
        private int protein;       // gram
        private int carbs;         // gram
        private int fat;           // gram
        private int fiber;         // gram
        
        public NutritionInfo(int calories, int protein, int carbs, int fat, int fiber) {
            this.calories = calories;
            this.protein = protein;
            this.carbs = carbs;
            this.fat = fat;
            this.fiber = fiber;
        }
        
        public int getCalories() { return calories; }
        public int getProtein() { return protein; }
        public int getCarbs() { return carbs; }
        public int getFat() { return fat; }
        public int getFiber() { return fiber; }
        
        public String getFormattedString() {
            return calories + " kkal | Pro: " + protein + "g | Kar: " + carbs + "g | Lem: " + fat + "g | Ser: " + fiber + "g";
        }
    }
    
    public static class MenuItem {
        private String day;
        private String mealTime; // "Makan Siang" atau "Makan Malam"
        private String menuName;
        private String description;
        private NutritionInfo nutrition;
        
        public MenuItem(String day, String mealTime, String menuName, String description, NutritionInfo nutrition) {
            this.day = day;
            this.mealTime = mealTime;
            this.menuName = menuName;
            this.description = description;
            this.nutrition = nutrition;
        }
        
        public String getDay() { return day; }
        public String getMealTime() { return mealTime; }
        public String getMenuName() { return menuName; }
        public String getDescription() { return description; }
        public NutritionInfo getNutrition() { return nutrition; }
    }
    
    public static class PackageMenu {
        private String packageName;
        private String subtitle;
        private String price;
        private String calories;
        private String location;
        private float rating;
        private List<MenuItem> menus;
        
        public PackageMenu(String packageName, String subtitle, String price, String calories,
                           String location, float rating, List<MenuItem> menus) {
            this.packageName = packageName;
            this.subtitle = subtitle;
            this.price = price;
            this.calories = calories;
            this.location = location;
            this.rating = rating;
            this.menus = menus;
        }
        
        public String getPackageName() { return packageName; }
        public String getSubtitle() { return subtitle; }
        public String getPrice() { return price; }
        public String getCalories() { return calories; }
        public String getLocation() { return location; }
        public float getRating() { return rating; }
        public List<MenuItem> getMenus() { return menus; }
    }
    
    // Data menu untuk FreshFuel Kitchen
    public static PackageMenu getFreshFuelMenu() {
        List<MenuItem> menus = new ArrayList<>();
        
        // Senin
        menus.add(new MenuItem("Senin", "Makan Siang", "Ayam panggang rempah", 
            "Ayam panggang dengan rempah-rempah, nasi merah, dan sayuran",
            new NutritionInfo(420, 35, 45, 12, 5)));
        menus.add(new MenuItem("Senin", "Makan Malam", "Ikan dori lemon", 
            "Ikan dori panggang dengan lemon, quinoa, dan brokoli",
            new NutritionInfo(380, 32, 38, 10, 6)));
        
        // Selasa
        menus.add(new MenuItem("Selasa", "Makan Siang", "Quinoa bowl sayur", 
            "Quinoa dengan sayuran panggang dan dressing sehat",
            new NutritionInfo(350, 18, 52, 8, 9)));
        menus.add(new MenuItem("Selasa", "Makan Malam", "Dada ayam panggang", 
            "Dada ayam panggang dengan ubi jalar dan asparagus",
            new NutritionInfo(410, 38, 42, 9, 7)));
        
        // Rabu
        menus.add(new MenuItem("Rabu", "Makan Siang", "Salmon panggang", 
            "Salmon panggang dengan nasi merah dan sayuran hijau",
            new NutritionInfo(450, 36, 40, 18, 4)));
        menus.add(new MenuItem("Rabu", "Makan Malam", "Ayam panggang rempah", 
            "Ayam panggang dengan rempah-rempah, nasi merah, dan sayuran",
            new NutritionInfo(420, 35, 45, 12, 5)));
        
        // Kamis
        menus.add(new MenuItem("Kamis", "Makan Siang", "Ikan dori lemon", 
            "Ikan dori panggang dengan lemon, quinoa, dan brokoli",
            new NutritionInfo(380, 32, 38, 10, 6)));
        menus.add(new MenuItem("Kamis", "Makan Malam", "Quinoa bowl sayur", 
            "Quinoa dengan sayuran panggang dan dressing sehat",
            new NutritionInfo(350, 18, 52, 8, 9)));
        
        // Jumat
        menus.add(new MenuItem("Jumat", "Makan Siang", "Dada ayam panggang", 
            "Dada ayam panggang dengan ubi jalar dan asparagus",
            new NutritionInfo(410, 38, 42, 9, 7)));
        menus.add(new MenuItem("Jumat", "Makan Malam", "Salmon panggang", 
            "Salmon panggang dengan nasi merah dan sayuran hijau",
            new NutritionInfo(450, 36, 40, 18, 4)));
        
        return new PackageMenu(
            "FreshFuel Kitchen",
            "High Protein Balance",
            "Rp 325.000 / minggu",
            "350–450 kkal / hari",
            "Jakarta Pusat",
            4.9f,
            menus
        );
    }
    
    // Data menu untuk Rasa Nusantara Sehat
    public static PackageMenu getNusantaraMenu() {
        List<MenuItem> menus = new ArrayList<>();
        
        // Senin
        menus.add(new MenuItem("Senin", "Makan Siang", "Nasi merah rendang jamur", 
            "Rendang jamur dengan nasi merah dan sayuran",
            new NutritionInfo(420, 22, 58, 12, 8)));
        menus.add(new MenuItem("Senin", "Makan Malam", "Tumis sayur woku", 
            "Tumis sayuran dengan bumbu woku dan ikan",
            new NutritionInfo(380, 28, 35, 14, 6)));
        
        // Selasa
        menus.add(new MenuItem("Selasa", "Makan Siang", "Sup bening jahe", 
            "Sup bening dengan jahe, ayam, dan sayuran",
            new NutritionInfo(320, 25, 32, 8, 5)));
        menus.add(new MenuItem("Selasa", "Makan Malam", "Pepes ikan", 
            "Pepes ikan dengan nasi merah dan lalapan",
            new NutritionInfo(410, 30, 48, 10, 7)));
        
        // Rabu
        menus.add(new MenuItem("Rabu", "Makan Siang", "Rawon Sehat", 
            "Rawon dengan daging tanpa lemak dan nasi merah",
            new NutritionInfo(450, 32, 52, 15, 6)));
        menus.add(new MenuItem("Rabu", "Makan Malam", "Nasi merah rendang jamur", 
            "Rendang jamur dengan nasi merah dan sayuran",
            new NutritionInfo(420, 22, 58, 12, 8)));
        
        // Kamis
        menus.add(new MenuItem("Kamis", "Makan Siang", "Tumis sayur woku", 
            "Tumis sayuran dengan bumbu woku dan ikan",
            new NutritionInfo(380, 28, 35, 14, 6)));
        menus.add(new MenuItem("Kamis", "Makan Malam", "Sup bening jahe", 
            "Sup bening dengan jahe, ayam, dan sayuran",
            new NutritionInfo(320, 25, 32, 8, 5)));
        
        // Jumat
        menus.add(new MenuItem("Jumat", "Makan Siang", "Pepes ikan", 
            "Pepes ikan dengan nasi merah dan lalapan",
            new NutritionInfo(410, 30, 48, 10, 7)));
        menus.add(new MenuItem("Jumat", "Makan Malam", "Rawon Sehat", 
            "Rawon dengan daging tanpa lemak dan nasi merah",
            new NutritionInfo(450, 32, 52, 15, 6)));
        
        return new PackageMenu(
            "Rasa Nusantara Sehat",
            "Menu Nusantara Rendah Minyak",
            "Rp 310.000 / minggu",
            "400–500 kkal / hari",
            "Bandung",
            4.7f,
            menus
        );
    }
    
    // Data menu untuk Leafy Bowl
    public static PackageMenu getLeafyBowlMenu() {
        List<MenuItem> menus = new ArrayList<>();
        
        // Senin
        menus.add(new MenuItem("Senin", "Makan Siang", "Bali tempe bowl", 
            "Tempe bali dengan nasi merah dan sayuran",
            new NutritionInfo(380, 20, 55, 12, 10)));
        menus.add(new MenuItem("Senin", "Makan Malam", "Pasta pesto brokoli", 
            "Pasta dengan pesto brokoli dan sayuran",
            new NutritionInfo(360, 16, 48, 14, 8)));
        
        // Selasa
        menus.add(new MenuItem("Selasa", "Makan Siang", "Buddha Bowl", 
            "Bowl dengan quinoa, sayuran, dan dressing",
            new NutritionInfo(340, 15, 52, 10, 11)));
        menus.add(new MenuItem("Selasa", "Makan Malam", "Quinoa Salad Bowl", 
            "Salad quinoa dengan sayuran segar dan dressing",
            new NutritionInfo(320, 14, 50, 9, 12)));
        
        // Rabu
        menus.add(new MenuItem("Rabu", "Makan Siang", "Bali tempe bowl", 
            "Tempe bali dengan nasi merah dan sayuran",
            new NutritionInfo(380, 20, 55, 12, 10)));
        menus.add(new MenuItem("Rabu", "Makan Malam", "Pasta pesto brokoli", 
            "Pasta dengan pesto brokoli dan sayuran",
            new NutritionInfo(360, 16, 48, 14, 8)));
        
        // Kamis
        menus.add(new MenuItem("Kamis", "Makan Siang", "Buddha Bowl", 
            "Bowl dengan quinoa, sayuran, dan dressing",
            new NutritionInfo(340, 15, 52, 10, 11)));
        menus.add(new MenuItem("Kamis", "Makan Malam", "Smoothie bowl tropis", 
            "Smoothie bowl dengan buah tropis dan topping",
            new NutritionInfo(300, 8, 62, 6, 9)));
        
        // Jumat
        menus.add(new MenuItem("Jumat", "Makan Siang", "Quinoa Salad Bowl", 
            "Salad quinoa dengan sayuran segar dan dressing",
            new NutritionInfo(320, 14, 50, 9, 12)));
        menus.add(new MenuItem("Jumat", "Makan Malam", "Bali tempe bowl", 
            "Tempe bali dengan nasi merah dan sayuran",
            new NutritionInfo(380, 20, 55, 12, 10)));
        
        return new PackageMenu(
            "Leafy Bowl",
            "Plant-based Energizer",
            "Rp 299.000 / minggu",
            "320–420 kkal / hari",
            "Jakarta Selatan",
            4.8f,
            menus
        );
    }
    
    public static PackageMenu getMenuByPackageName(String packageName) {
        switch (packageName) {
            case "FreshFuel Kitchen":
                return getFreshFuelMenu();
            case "Rasa Nusantara Sehat":
                return getNusantaraMenu();
            case "Leafy Bowl":
                return getLeafyBowlMenu();
            default:
                return getFreshFuelMenu();
        }
    }
}

