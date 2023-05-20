package ru.artsybashev.bacgame;

import java.io.IOException;
import java.util.Arrays;

//  класс для всяких вспомогательных методов, чтоб не засорять основную логику
public class ServiceLib {
    //   этот метод проверяет не стоят ли нули в старших разрядах числа
    //TODO жестко
    public static int[] convertIntToArray(int targetValue, int valueOfNumber) {
        int[] arr = Arrays
                .stream(Long.toString(targetValue).split(""))
                .mapToInt(Integer::parseInt)
                .toArray();
        if (arr.length == valueOfNumber) {
            return arr;
        }
        int[] result = new int[valueOfNumber];
        for(int i = 1; i <= arr.length; i += 1) {
            result[result.length - i] = arr[arr.length - i];
        }
        return result;
    }
    public static String getNormalizedAnswer(int value, String[] mask) {
        String normalizedAnswer;
        if (value > 10 && value < 20) {
            normalizedAnswer = value + " " + mask[0];
            return normalizedAnswer;
        }
        switch (value % 10) {
            case 1:
                normalizedAnswer = value + " " + mask[1];
                break;
            case 2, 3, 4:
                normalizedAnswer = value + " " + mask[2];
                break;
            default:
                normalizedAnswer = value + " " + mask[0];
        }
        return normalizedAnswer;
    }
    public static boolean contains(final int[] array, final int key) {
        //return Arrays.asList(array).contains(key);
        return Arrays.stream(array).anyMatch(i -> i == key);
    }
    //TODO цифры в загаданной строке должны быть уникальными
    public static int[] getRandomNumberArray(int valueOfDigits) {
        int[] digits = new int[valueOfDigits];
        for (var i = 0; i < valueOfDigits; i += 1) {
            //TODO зачем Math.round и проверка на равенство 10. Можно просто к int приводить
            int randomNum = (int) Math.round(Math.random() * 10);
            digits[i] = randomNum == 10 ? 0 : randomNum;
        }
        return digits;
    }
    //TODO очень сложно, но ок
    public static String convertIntArrToString(int[] digits) {
        String[] strArray = Arrays.stream(digits)
                .mapToObj(String::valueOf)
                .toArray(String[]::new);
        return String.join("", strArray);
    }
    public static String getCurrentDirectoryPath() {
        //String slash = System.getProperty("os.name").toLowerCase().contains("windows") ? "\\" : "/";
        String path = App.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String last = path.substring(path.length() - 1);
        //  если в пути не присутствует название  файла
        if (last.equals("/")) {
            return App.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        }
        //  убираем название файла
        String[] arr = path.split("/");
        arr[arr.length - 1] = "";
        return String.join("/", arr);
    }
    public static String getFilePath(String[] args) throws IOException {
        return Checkout.checkArgs1(args)
                ? getAvailableFilepath(new LogFilepathOfUser(args[1]))
                : getAvailableFilepath(new LogFilepathStandard());
    }

    //  реализуем полиморфизм подтипов
    private static String getAvailableFilepath(ILogFilepath path) throws IOException {
        if (!path.isFileAvailable()) {
            path.getErrorMessage();
            path.createFile();
        }
        path.getInfoMessage();
        return path.getFilepath();
    }
}
