package ru.artsybashev.bacgame;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class BullsAndCowsGame extends GameEngine {
    private int targetValue;
    private int[] targetValueArr;
    private int valueOfNumbers;
    private Logger log;
    BullsAndCowsGame(int valueOfNumbers, String filePathForLogging) throws FileNotFoundException {
        super(new GameIO(filePathForLogging));
        this.valueOfNumbers = valueOfNumbers;
        this.targetValueArr =  ServiceLib.getRandomNumberArray(valueOfNumbers);
        this.targetValue = ServiceLib.convertIntArrToInt(targetValueArr);
        this.log = new Logger(this);
    }
    public void startGame() throws IOException {
        gameIO.cOut("Start Game: " + targetValue);
        while(!isGameFinished) {
            nextStep();

        }
    }
    private void nextStep() throws IOException {
        System.out.println(targetValueArr);
        this.roundCount += 1;
        int userAnswer = gameIO.readUserVariant();
        if (userAnswer == targetValue) {
            finishGame();
            return;
        }
        int[] userAnswerArr = ServiceLib.convertIntToArray(userAnswer, valueOfNumbers);
        int bulls = checkBullsValue(userAnswerArr);
        int cows = checkCowsValue(userAnswerArr);
        gameIO.writeStepResult(bulls, cows);
        log.logGameStep(userAnswer, bulls, cows);

    }
    private void finishGame() throws IOException {
        this.isGameFinished = true;
        log.setResult(roundCount);
        gameIO.writeLogToFile(log);
    }
    private int checkBullsValue(int[] userAnswer) {
        int count = 0;
        for (var i = 0; i < valueOfNumbers; i += 1) {
            count += userAnswer[i] == targetValueArr[i] ? 1 : 0;
        }
        return count;
    }
    private int checkCowsValue(int[] userAnswer) {
        int count = 0;
        for (var i = 0; i < valueOfNumbers; i += 1) {
            count += userAnswer[i] != targetValueArr[i]
                    && ServiceLib.contains(targetValueArr, userAnswer[i])
                    ? 1
                    : 0;
        }
        return count;
    }
    public int getTargetValue() {
        return this.targetValue;
    }

}
