package ru.artsybashev.bacgame;

import java.io.FileNotFoundException;
import java.io.IOException;

public class BullsAndCowsGame extends GameEngine {
    private String targetValue;
    private String userAnswer;
    private int[] targetValueArr;
    private int valueOfDigits;
    private Logger log;
    public boolean testMode = false;
    BullsAndCowsGame(int valueOfDigits, String filePathForLogging) throws FileNotFoundException {
        super(new GameIO(filePathForLogging));
        this.valueOfDigits = valueOfDigits;
        this.targetValueArr =  ServiceLib.getRandomNumberArray(valueOfDigits);
        this.targetValue = ServiceLib.convertIntArrToString(targetValueArr);
        //this.targetValue = ServiceLib.convertIntArrToString({0, 1, 2, 3});
        this.log = new Logger(this);
    }
    public void startGame() throws IOException {
        gameIO.cOut("Start Game: " + targetValue);
        while(!isGameFinished) {
            nextStep();
        }
    }
    private void nextStep() throws IOException {
        this.roundCount += 1;
        this.userAnswer = !testMode ? gameIO.readUserAnswer() : userAnswer;
        if (!Checkout.checkUserAnswer(userAnswer, valueOfDigits)) {
            gameIO.cOut("Некорректное значение, введите число из " + valueOfDigits + " знаков");
            this.roundCount -= 1;
            return;
        }
        if (targetValue.equals(userAnswer)) {
            finishGame();
            return;
        }
        int[] userAnswerArr = ServiceLib.convertIntToArray(Integer.parseInt(userAnswer), valueOfDigits);
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
        for (var i = 0; i < valueOfDigits; i += 1) {
            count += userAnswer[i] == targetValueArr[i] ? 1 : 0;
        }
        return count;
    }
    private int checkCowsValue(int[] userAnswer) {
        int count = 0;
        for (var i = 0; i < valueOfDigits; i += 1) {
            count += userAnswer[i] != targetValueArr[i]
                    && ServiceLib.contains(targetValueArr, userAnswer[i])
                    ? 1
                    : 0;
        }
        return count;
    }
    //  для тестов
    public String getTargetValue() {
        return targetValue;
    }
    public void setTargetValue(String value) {
        this.targetValue = value;
    }
    public void setUserAnswer(String value) {
        this.userAnswer = value;
    }
    public void doNextStep() throws IOException {
        nextStep();
    }

}
