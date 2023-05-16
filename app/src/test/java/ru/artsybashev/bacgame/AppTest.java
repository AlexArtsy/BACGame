/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ru.artsybashev.bacgame;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @BeforeAll
    public static void beforeAll() throws FileNotFoundException {
        BullsAndCowsGame game = new BullsAndCowsGame(4, "/home/alexarts/BACGame/app/src/main/resources/log.txt");
    }
    @Test
    public void testServiceLibContains() {
        int[] testArray = {1, 3, -5, 68, 99};
        assertTrue(ServiceLib.contains(testArray, 99));
        assertTrue(!ServiceLib.contains(testArray, 9));
    }
    public void testServiceLibConvertIntArrToInt() {

    }
    public void testServiceLibgetNormalizedAnswer() {
        String[] testMask1 = {"шагов", "шаг", "шага"};
        String[] testMask2 = {"козлов", "козел", "козла"};
        var expected11 = "2 шага";
        var expected12 = "67 шагов";
        var expected13 = "1 шаг";
        var expected21 = "4 козла";
        var expected22 = "999 козлов";
        var expected23 = "1 козел";
        assertSame(expected11, ServiceLib.getNormalizedAnswer(2, testMask1));

    }

}
