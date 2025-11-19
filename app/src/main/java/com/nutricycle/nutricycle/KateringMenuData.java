package com.nutricycle.nutricycle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KateringMenuData {
    
    public static class MenuItem {
        private String day;
        private String mealTime; // "Sarapan" atau "Makan Siang"
        private String menuName;
        private String description;
        
        public MenuItem(String day, String mealTime, String menuName, String description) {
            this.day = day;
            this.mealTime = mealTime;
            this.menuName = menuName;
            this.description = description;
        }
        
        public String getDay() { return day; }
        public String getMealTime() { return mealTime; }
        public String getMenuName() { return menuName; }
        public String getDescription() { return description; }
    }
    
    public static class PackageMenu {
        private String packageName;
        private String subtitle;
        private String price;
        private String calories;
        private List<MenuItem> menus;
        
        public PackageMenu(String packageName, String subtitle, String price, String calories, List<MenuItem> menus) {
            this.packageName = packageName;
            this.subtitle = subtitle;
            this.price = price;
            this.calories = calories;
            this.menus = menus;
        }
        
        public String getPackageName() { return packageName; }
        public String getSubtitle() { return subtitle; }
        public String getPrice() { return price; }
        public String getCalories() { return calories; }
        public List<MenuItem> getMenus() { return menus; }
    }
    
    // Data menu untuk FreshFuel Kitchen
    public static PackageMenu getFreshFuelMenu() {
        List<MenuItem> menus = new ArrayList<>();
        
        // Senin
        menus.add(new MenuItem("Senin", "Sarapan", "Oatmeal dengan Buah", "Oatmeal dengan pisang, stroberi, dan madu"));
        menus.add(new MenuItem("Senin", "Makan Siang", "Ayam panggang rempah", "Ayam panggang dengan rempah-rempah, nasi merah, dan sayuran"));
        
        // Selasa
        menus.add(new MenuItem("Selasa", "Sarapan", "Smoothie Bowl Protein", "Smoothie bowl dengan protein powder, granola, dan buah"));
        menus.add(new MenuItem("Selasa", "Makan Siang", "Ikan dori lemon", "Ikan dori panggang dengan lemon, quinoa, dan brokoli"));
        
        // Rabu
        menus.add(new MenuItem("Rabu", "Sarapan", "Telur Orak-arik dengan Roti", "Telur orak-arik dengan roti gandum dan sayuran"));
        menus.add(new MenuItem("Rabu", "Makan Siang", "Quinoa bowl sayur", "Quinoa dengan sayuran panggang dan dressing sehat"));
        
        // Kamis
        menus.add(new MenuItem("Kamis", "Sarapan", "Greek Yogurt dengan Berries", "Greek yogurt dengan blueberry, raspberry, dan kacang"));
        menus.add(new MenuItem("Kamis", "Makan Siang", "Dada ayam panggang", "Dada ayam panggang dengan ubi jalar dan asparagus"));
        
        // Jumat
        menus.add(new MenuItem("Jumat", "Sarapan", "Avocado Toast", "Roti gandum dengan alpukat, telur rebus, dan tomat"));
        menus.add(new MenuItem("Jumat", "Makan Siang", "Salmon panggang", "Salmon panggang dengan nasi merah dan sayuran hijau"));
        
        return new PackageMenu(
            "FreshFuel Kitchen",
            "High Protein Balance",
            "Rp 325.000 / minggu",
            "350–450 kkal / hari",
            menus
        );
    }
    
    // Data menu untuk Rasa Nusantara Sehat
    public static PackageMenu getNusantaraMenu() {
        List<MenuItem> menus = new ArrayList<>();
        
        // Senin
        menus.add(new MenuItem("Senin", "Sarapan", "Bubur Ayam Sehat", "Bubur ayam dengan nasi merah dan topping sehat"));
        menus.add(new MenuItem("Senin", "Makan Siang", "Nasi merah rendang jamur", "Rendang jamur dengan nasi merah dan sayuran"));
        
        // Selasa
        menus.add(new MenuItem("Selasa", "Sarapan", "Nasi Uduk Sehat", "Nasi uduk dengan telur dan sambal"));
        menus.add(new MenuItem("Selasa", "Makan Siang", "Tumis sayur woku", "Tumis sayuran dengan bumbu woku dan ikan"));
        
        // Rabu
        menus.add(new MenuItem("Rabu", "Sarapan", "Lontong Sayur Sehat", "Lontong sayur dengan kuah bening"));
        menus.add(new MenuItem("Rabu", "Makan Siang", "Sup bening jahe", "Sup bening dengan jahe, ayam, dan sayuran"));
        
        // Kamis
        menus.add(new MenuItem("Kamis", "Sarapan", "Ketoprak Sehat", "Ketoprak dengan tahu, lontong, dan bumbu kacang"));
        menus.add(new MenuItem("Kamis", "Makan Siang", "Pepes ikan", "Pepes ikan dengan nasi merah dan lalapan"));
        
        // Jumat
        menus.add(new MenuItem("Jumat", "Sarapan", "Gado-gado", "Gado-gado dengan sayuran segar dan bumbu kacang"));
        menus.add(new MenuItem("Jumat", "Makan Siang", "Rawon Sehat", "Rawon dengan daging tanpa lemak dan nasi merah"));
        
        return new PackageMenu(
            "Rasa Nusantara Sehat",
            "Menu Nusantara Rendah Minyak",
            "Rp 310.000 / minggu",
            "400–500 kkal / hari",
            menus
        );
    }
    
    // Data menu untuk Leafy Bowl
    public static PackageMenu getLeafyBowlMenu() {
        List<MenuItem> menus = new ArrayList<>();
        
        // Senin
        menus.add(new MenuItem("Senin", "Sarapan", "Chia Pudding", "Chia pudding dengan buah dan granola"));
        menus.add(new MenuItem("Senin", "Makan Siang", "Bali tempe bowl", "Tempe bali dengan nasi merah dan sayuran"));
        
        // Selasa
        menus.add(new MenuItem("Selasa", "Sarapan", "Green Smoothie", "Smoothie hijau dengan bayam, pisang, dan protein"));
        menus.add(new MenuItem("Selasa", "Makan Siang", "Pasta pesto brokoli", "Pasta dengan pesto brokoli dan sayuran"));
        
        // Rabu
        menus.add(new MenuItem("Rabu", "Sarapan", "Acai Bowl", "Acai bowl dengan topping buah dan granola"));
        menus.add(new MenuItem("Rabu", "Makan Siang", "Buddha Bowl", "Bowl dengan quinoa, sayuran, dan dressing"));
        
        // Kamis
        menus.add(new MenuItem("Kamis", "Sarapan", "Overnight Oats", "Overnight oats dengan buah dan kacang"));
        menus.add(new MenuItem("Kamis", "Makan Siang", "Smoothie bowl tropis", "Smoothie bowl dengan buah tropis dan topping"));
        
        // Jumat
        menus.add(new MenuItem("Jumat", "Sarapan", "Avocado Smoothie", "Smoothie alpukat dengan madu dan granola"));
        menus.add(new MenuItem("Jumat", "Makan Siang", "Quinoa Salad Bowl", "Salad quinoa dengan sayuran segar dan dressing"));
        
        return new PackageMenu(
            "Leafy Bowl",
            "Plant-based Energizer",
            "Rp 299.000 / minggu",
            "320–420 kkal / hari",
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

