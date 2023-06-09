package ru.artsybashev.bacgame;

import java.io.FileNotFoundException;
import java.io.IOException;

public class BullsAndCowsGame extends GameEngine {
    private String targetValue;
    //TODO уверен что тебе нужна эта переменная?
    private String userAnswer;
    private int[] targetValueArr;
    private int valueOfDigits;
    //TODO смотри комментарий в 25 строке
//     private static Logger log = ...
    private Logger log;

    //   поля для тестирования
    public boolean testMode = false;
    public String stepResult;
    BullsAndCowsGame(int valueOfDigits, String filePathForLogging) throws FileNotFoundException {
        super(new GameIO(filePathForLogging));
        this.valueOfDigits = valueOfDigits;
        //  такой геморой с генерацией случайного значения связан с тем, что пытался генерировать огромные числа
        this.targetValueArr =  ServiceLib.getRandomNumberArray(valueOfDigits);
        this.targetValue = ServiceLib.convertIntArrToString(targetValueArr);
        //TODO если используешь логгер, то он обычно объявляется как static поле и сразу инициализируется
        this.log = new Logger(this);
    }
    public void startGame() throws IOException {
        gameIO.cOut("Игра начата! Загадано " + valueOfDigits + "-значное число");
        while(!isGameFinished) {
            nextStep();
        }
    }
    private void nextStep() throws IOException {
        this.roundCount += 1;
        this.userAnswer = !testMode ? gameIO.readUserAnswer() : userAnswer; //  костыль для тестирования
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
        //TODO зачем тебе переменная stepResult ?
        stepResult = gameIO.writeStepResult(bulls, cows);
        log.logGameStep(userAnswer, bulls, cows);
    }
    private void finishGame() throws IOException {
        this.isGameFinished = true;
        gameIO.cOut("Вы угадали, игра закончена");
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
    public void setTargetValue(int[] valueArr) {
        this.targetValueArr = valueArr;
        this.targetValue = ServiceLib.convertIntArrToString(valueArr);
    }
    public void setUserAnswer(String value) {
        this.userAnswer = value;
    }
    public void doNextStep() throws IOException {
        nextStep();
    }

}
