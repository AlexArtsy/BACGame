package ru.artsybashev.bacgame;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

//  класс для всяких вспомогательных методов, чтоб не засорять основную логику
public class ServiceLib {
    //   этот метод проверяет не стоят ли нули в старших разрядах числа
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
    public static int[] getRandomNumberArray(int valueOfDigits) {
        int[] digits = new int[valueOfDigits];
        for (var i = 0; i < valueOfDigits; i += 1) {
            int randomNum = (int) Math.round(Math.random() * 10);
            digits[i] = randomNum == 10 ? 0 : randomNum;
        }
        return digits;
    }
    public static int convertIntArrToInt(int[] digits) {
        String[] strArray = Arrays.stream(digits)
                .mapToObj(String::valueOf)
                .toArray(String[]::new);
        return Integer.parseInt(String.join("", strArray));
    }
    public static String getCurrenDirectoryPath() {
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
    public static void generateSpareFile() throws IOException {
        String newFilepath = getCurrenDirectoryPath() + "newLog.txt";
        File file = new File(newFilepath);
        file.createNewFile();
        if (Checkout.checkFileExisting(newFilepath) ) {
            System.out.println("Создан новый файл");
        } else {
            System.out.println("Ошибка! Файл не создан!");
        }
    }
}
