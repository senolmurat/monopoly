package IO;

import GameElements.Die;
import Player.*;
import org.junit.Before;
import org.junit.Test;

public class DisplayTest {

    Die die = new Die();
    Player playerTest;
    Player playerTest1;
    Display displayTest = new Display();

    {
        try {
            playerTest = new Player(1," Ali ", 2000, die);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void infoMessageBeforeTossDie() {
        displayTest.infoMessageBeforeTossDie(playerTest," Tax Square");
    }

    @Test
    public void infoMessageAfterTossDie() {
        displayTest.infoMessageAfterTossDie(playerTest," Tax Square");
    }

    @Test
    public void infoBasedOnBalance() {
    }

    @Test
    public void selectionSort() {
    }
}