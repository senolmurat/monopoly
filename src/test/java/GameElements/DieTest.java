package GameElements;

import org.junit.Test;

import static org.junit.Assert.*;

public class DieTest {

    Die dieTest = new Die();

    @Test
    public void getFaces() {
        int[] diceTestArray = dieTest.getFaces();
        assertTrue(diceTestArray.length == 2);
        assertTrue(diceTestArray[0] <= 6 && diceTestArray[0] >= 1);
        assertTrue(diceTestArray[1] <= 6 && diceTestArray[1] >= 1);
    }

}