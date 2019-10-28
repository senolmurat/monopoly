import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    Player playerTest;
    Dice diceTest;

    @Test
    public void tossDie() {
        playerTest = new Player(1, "Ali");
        diceTest = new Dice();
        assertTrue((playerTest.tossDie(diceTest)) >= 2 && (playerTest.tossDie(diceTest) <= 12));
    }

}