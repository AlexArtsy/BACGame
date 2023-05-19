package ru.artsybashev.bacgame;

import java.io.IOException;

public interface ILogFilepath {
    void getErrorMessage();
    void getInfoMessage();
    boolean isFileAvailable();
    String getFilepath();
    void createFile() throws IOException;
}
