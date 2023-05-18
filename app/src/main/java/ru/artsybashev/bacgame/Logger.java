package ru.artsybashev.bacgame;

import java.util.ArrayList;

public class Logger {
    private String description;
    private ArrayList<String> gameSteps = new ArrayList<String>();
    private String result;
    Logger(BullsAndCowsGame game){
        this.description = "Game №" + game.getGameNumber() + " " + game.getGameDateAndTime() + " Загаданная строка " + game.getTargetValue() +"\n";
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
                + "\n"
        );
    }
    public void setResult(int roundCount) {
        String[] tryMatrix = {"попыток", "попыту", "попытки"};
        this.result = "Строка была угадана за " + ServiceLib.getNormalizedAnswer(roundCount, tryMatrix) + "\n";

    }
}
