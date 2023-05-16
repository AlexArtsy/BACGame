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
    public void logGameStep(int userVersion, int bulls, int cows) {
        this.gameSteps.add(
                "\tЗапрос: "
                + userVersion + " Ответ: "
                + ServiceLib.getNormalizedAnswer(bulls, true)
                + " "
                + ServiceLib.getNormalizedAnswer(cows, false)
                + "\n"
        );
    }
    public void setResult(int roundCount) {
        this.result = "Строка была угадана за " + roundCount + " попыток\n";
    }
}
