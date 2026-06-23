package com.fooddelivery.Utils;

public class HelperUtils {
    private HelperUtils(){
   }

    public static String generateCode(String prefix) {
                return generateCode(prefix);
    }
    public static String  generateCode(String prefix, int length){
        return generateCode(prefix,length);
    }
    public static double calculateDistance(double lat1, double lng1, double lat2, double lng2) {
        return Math.sqrt( Math.pow(lat2 - lat1, 2)  + Math.pow(lng2 - lng1, 2) );
    }

    public static double calculateTotal(double subtotal, double fee) {
        return subtotal + fee;
    }
    public static double calculateTotal(double subtotal, double fee, double discount) {
        return (subtotal + fee ) - discount;
    }
   public static String formatCurrency(double amount){
        return "$" + amount;
   }
    public static String formatCurrency(double amount, String currencyCode){
        return currencyCode+ " " +amount;
    }
    public static boolean isBusinessOpen(String openTime, String closeTime) {
        return openTime.compareTo(closeTime) < 0;
    }
}
