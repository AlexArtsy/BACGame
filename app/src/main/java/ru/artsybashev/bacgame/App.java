/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ru.artsybashev.bacgame;

import java.io.IOException;

public class App {
    private static String filepath;
    private static int numOfDigits = 4;
    public static void main(String[] args) throws IOException {
        //  проверяем ввел ли пользователь количество разрядов
        if (Checkout.checkArgs0(args) && Checkout.isContainOnlyDigits(args[0])) {
            numOfDigits = Integer.parseInt(args[0]) < 10 ? Integer.parseInt(args[0]) : 4;
        }
        // проверяем указан ли путь к файлу и существует ли файл
        if (Checkout.checkArgs1(args) && Checkout.checkFileExisting(args[1])) {
            filepath = args[1];
        } else {
            // проверяем доступен ли служебный Log файл
            if (Checkout.checkFileExisting("./app/src/main/resources/log.txt")) {
                filepath = "./app/src/main/resources/log.txt";
            } else if(Checkout.checkFileExisting(ServiceLib.getCurrentDirectoryPath() + "newLog.txt")) {
                filepath = ServiceLib.getCurrentDirectoryPath() + "newLog.txt";
            } else {    //   если служебный и резервный файлы не доступны, генерируем новый
                System.out.println("Log файл не существует!");
                ServiceLib.generateSpareFile();
                filepath = ServiceLib.getCurrentDirectoryPath() + "newLog.txt";
            }
        }

        System.out.println("Log файл находится в директории: " + filepath);
        BullsAndCowsGame game = new BullsAndCowsGame(numOfDigits, filepath);
        game.startGame();
    }
}
