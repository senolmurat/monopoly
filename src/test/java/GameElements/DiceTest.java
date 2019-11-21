package GameElements;

import GameElements.Dice;
import org.junit.Test;

import static org.junit.Assert.*;

public class DiceTest {

    Dice diceTest = new Dice();

    @Test
    public void getFaces() {
        int[] diceTestArray = diceTest.getFaces();
        assertTrue(diceTestArray.length == 2);
        assertTrue(diceTestArray[0] <= 6 && diceTestArray[0] >= 1);
        assertTrue(diceTestArray[1] <= 6 && diceTestArray[1] >= 1);
    }

}