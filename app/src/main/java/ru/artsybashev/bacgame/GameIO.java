package ru.artsybashev.bacgame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameIO {
    private String filePath;
    private Scanner in; //  ввод пользователя с консоли
    GameIO(String filePath) {
        this.filePath = filePath;
        this.in = new Scanner(System.in);
    }
    public void closeSystemInScanner() {
        this.in.close();
    }
    public int readNumber () throws FileNotFoundException {
        FileReader reader = new FileReader(this.filePath);
        Scanner scanner = new Scanner(reader);
        ArrayList<String> list = new ArrayList<>();
        while(scanner.hasNextLine()) {
            list.add(scanner.nextLine());
        }
        scanner.close();
        String number = "1";
        for(int i = 0; i < list.size(); i += 1) {
            String str = list.get(i);
            if(str.contains("Game")) {
                int startIndex = str.indexOf("№") + 1;
                number = str.substring(startIndex, str.indexOf(" ", startIndex));
            }
        }
        return Integer.parseInt(number);
    }
    public int readGameNumber() throws FileNotFoundException {
        return readNumber() + 1;
    }
    public void writeStepResult(int bulls, int cows) {
        cOut(
                ServiceLib.getNormalizedAnswer(bulls, true) //  true - быки
                + " "
                + ServiceLib.getNormalizedAnswer(cows, false) //    false - коровы
        );
    }
    public void writeLogToFile(Logger log) throws IOException {
        //System.out.println(log.getGameInfo());
        FileWriter writer = new FileWriter(filePath, true);
        writer.write(log.getGameInfo());
        writer.flush();
    }
    public int cIn() {
        int consoleInput = in.nextInt();
        //in.close(); // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        return consoleInput;
    }
    public void cOut(String str) {
        System.out.println(str);
    }
    public int readUserVariant() {
        int userAnswer = cIn();
        return userAnswer;
    }


}
