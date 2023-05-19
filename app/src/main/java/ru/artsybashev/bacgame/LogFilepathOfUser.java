package ru.artsybashev.bacgame;

import java.io.File;
import java.io.IOException;

public class LogFilepathOfUser implements ILogFilepath {
    private String path;
    private String spareFilepath = ServiceLib.getCurrentDirectoryPath() + "newLog.txt";
    private boolean isSpareFileExist = false;
    private String infoMessage = "Ваш log-файл находится в дирректории: ";

    LogFilepathOfUser(String path) {
        this.path = path;
        this.isSpareFileExist = Checkout.checkFileExisting(spareFilepath);
    }
    public void getErrorMessage() {
        System.out.println("Введенный вами файл не доступен или путь не корректен");
    }
    public void getInfoMessage() {
        System.out.println(infoMessage);
        System.out.println(path);
    }
    public boolean isFileAvailable() {
        if (!Checkout.checkFileExisting(path)) {
            // проверяем создан ли уже резервный файл
            if (isSpareFileExist) {
                this.path = spareFilepath;
                this.infoMessage = "Используется резервный файл: ";
                return true;
            }
            return false;
        }
        return true;
    }
    public String getFilepath() {
        return path;
    }
    public void createFile() throws IOException {
        File file = new File(spareFilepath);
        file.createNewFile();
        if (Checkout.checkFileExisting(spareFilepath) ) {
            this.path = spareFilepath;
            this.infoMessage = "Используется резервный файл: ";
        } else {
            System.out.println("Ошибка! Резервный файл не создан!");
        }
    }
}
