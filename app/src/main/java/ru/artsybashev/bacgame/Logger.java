package ru.artsybashev.bacgame;

import java.util.ArrayList;
import java.util.List;

public class Logger {
    private String description;
    private List<String> gameSteps = new ArrayList<>();
    private String result;
    private boolean isWindows = System.getProperty("os.name").toLowerCase().contains("windows");
    private String slash;
    private String newLineSymbol;
    Logger(BullsAndCowsGame game){
        this.slash = isWindows ? "\\" : "/";
        this.newLineSymbol = isWindows ? "\r\n" : "\n";
        this.description = "Game №"
                + game.getGameNumber()
                + " "
                + game.getGameDateAndTime()
                + " Загаданная строка "
                + game.getTargetValue()
                + newLineSymbol;
    }
    public String getGameInfo() {
        return description + String.join("", gameSteps) + result;
    }
    public void logGameStep(String userVersion, int bulls, int cows) {
        String[] bullsMask = {"быков", "бык", "быка"};
        String[] cowsMask = {"коров", "корова", "коровы"};
        this.gameSteps.add(
                "\tЗапрос: "
                + userVersion + " Ответ: "
                + ServiceLib.getNormalizedAnswer(bulls, bullsMask)
                + " "
                + ServiceLib.getNormalizedAnswer(cows, cowsMask)
                + newLineSymbol
        );
    }
    public void setResult(int roundCount) {
        String[] tryMatrix = {"попыток", "попыту", "попытки"};
        this.result = "Строка была угадана за " + ServiceLib.getNormalizedAnswer(roundCount, tryMatrix) + newLineSymbol;
    }
}
