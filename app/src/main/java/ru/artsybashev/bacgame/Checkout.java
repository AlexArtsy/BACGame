package ru.artsybashev.bacgame;

import java.io.File;

public class Checkout {
    public static boolean checkUserAnswer(String answer, int valueOfDigits) {
        boolean isOnlyDigits = true;
        for(int i = 0; i < answer.length() && isOnlyDigits; i++) {
            if(!Character.isDigit(answer.charAt(i))) {
                isOnlyDigits = false;
            }
        }
        return isOnlyDigits && answer.length() == valueOfDigits;
    }
    public static boolean checkFileExisting(String filepath) {
        File file = new File(filepath);
        return file.exists();
    }
    public static boolean checkArgs0(String[] args) {
        return args.length >= 1;
    }
    public static boolean checkArgs1(String[] args) {
        return args.length >= 2;
    }
}
