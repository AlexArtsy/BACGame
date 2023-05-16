package ru.artsybashev.bacgame;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GameEngine {
    protected int gameNumber;
    protected GameIO gameIO;
    protected boolean isGameFinished;
    protected int roundCount;
    private String dateAndTime;
    GameEngine(GameIO gameIO) throws FileNotFoundException {
        this.gameIO = gameIO;
        this.gameNumber = gameIO.readGameNumber();
        this.roundCount = 0;
        this.isGameFinished = false;
        this.dateAndTime = DateTimeFormatter
                .ofPattern("dd.MM.yyyy hh:mm")
                .format(LocalDateTime.now());
    }
    public String getGameNumber() {
        return Integer.toString(this.gameNumber);
    }
    public String getGameDateAndTime() {
        return this.dateAndTime;
    }
    public int getRoundCount() {
        return this.roundCount;
    }
}
