package ru.artsybashev.bacgame;

import java.io.File;

public class Checkout {
    public static void checkUserAnswer() {

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
