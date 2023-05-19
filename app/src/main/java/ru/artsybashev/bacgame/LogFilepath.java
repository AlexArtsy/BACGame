package ru.artsybashev.bacgame;

import java.io.IOException;

public interface LogFilepath {
    void getErrorMessage();
    void getInfoMessage();
    boolean isFileAvailable();
    String getFilepath();
    void createFile() throws IOException;
}
