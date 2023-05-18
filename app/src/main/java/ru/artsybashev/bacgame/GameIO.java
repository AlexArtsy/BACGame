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
    private int readNumber () throws FileNotFoundException {
        FileReader reader = new FileReader(this.filePath);
        Scanner scanner = new Scanner(reader);
        ArrayList<String> list = new ArrayList<>();
        while(scanner.hasNextLine()) {
            list.add(scanner.nextLine());
        }
        scanner.close();
        String numberOfLastGame = "0";
        for(int i = 0; i < list.size(); i += 1) {
            String str = list.get(i);
            if(str.contains("Game")) {
                int startIndex = str.indexOf("№") + 1;
                numberOfLastGame = str.substring(startIndex, str.indexOf(" ", startIndex));
            }
        }
        return Integer.parseInt(numberOfLastGame) + 1;
    }
    public int readGameNumber() throws FileNotFoundException {
        return readNumber();
    }
    public String writeStepResult(int bulls, int cows) {
        String[] bullsMatrix = {"быков", "бык", "быка"};
        String[] cowsMatrix = {"коров", "корова", "коровы"};
        String result = ServiceLib.getNormalizedAnswer(bulls, bullsMatrix)
                + " "
                + ServiceLib.getNormalizedAnswer(cows, cowsMatrix);
        cOut(result);
        return result;
    }
    public void writeLogToFile(Logger log) throws IOException {
        FileWriter writer = new FileWriter(filePath, true);
        writer.write(log.getGameInfo());
        writer.flush();
    }
    public String readUserAnswer() throws IOException {
        String userAnswer = cIn();
        return userAnswer;
    }
    public String cIn() throws IOException {
        String consoleInput = in.nextLine();
        return consoleInput;
        //return 0;
    }
    public void cOut(String str) {
        System.out.println(str);
    }
}
