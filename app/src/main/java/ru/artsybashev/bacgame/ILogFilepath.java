package ru.artsybashev.bacgame;

import java.io.IOException;

//TODO у тебя классы, которые реализуют данный интерфейс имеют очень много общих реализаций
// Тут есть три варианта
// 1) Прописать в методах интерфейса и объявить их default (так себе идея, но может сработать)
// 2) Создать абстрактный класс, который реализует данный интерфейс и всю общую логику обоих твоих классов прописать в нем
//  а твои классы унаследовать от этого абстрактного
// 3) Подумать, может стоит придумать другое решение
public interface ILogFilepath {
    void getErrorMessage();
    void getInfoMessage();
    boolean isFileAvailable();
    String getFilepath();
    void createFile() throws IOException;
}
