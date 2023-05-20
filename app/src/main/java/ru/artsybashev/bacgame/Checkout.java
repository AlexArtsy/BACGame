package ru.artsybashev.bacgame;

import java.io.File;

public class Checkout {
    public static boolean checkUserAnswer(String answer, int valueOfDigits) {
        return isContainOnlyDigits(answer) && isCorrectValueOfDigitsInUserAnswer(answer, valueOfDigits);
    }
    public static boolean isContainOnlyDigits(String answer) {
        boolean isOnlyDigits = true;
        for(int i = 0; i < answer.length() && isOnlyDigits; i++) {
            if(!Character.isDigit(answer.charAt(i))) {
                //TODO здесь можно сразу return false писать
                isOnlyDigits = false;
            }
        }
        return isOnlyDigits;
    }
    public static boolean isCorrectValueOfDigitsInUserAnswer(String answer, int valueOfDigits) {
        return answer.length() == valueOfDigits;
    }
    public static boolean checkFileExisting(String filepath) {
        File file = new File(filepath);
        return file.exists();
    }
    public static int checkAndSetValueOfDigits(String value) {
        int num = Integer.parseInt(value);
        boolean isOnlyDigits = isContainOnlyDigits(value);
        boolean isCorrectRange = num > 0 && num < 11;
        return isOnlyDigits && isCorrectRange ? num : 4;
    }
    public static boolean checkArgs0(String[] args) {
        return args.length >= 1;
    }
    public static boolean checkArgs1(String[] args) {
        return args.length >= 2;
    }
}
