import GameElements.*;
import Player.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    private Player player;
    private boolean isMoving = true;
    Board board = new Board(10, 100,200);
    Dice dice = new Dice();

    @Test
    public void tossDie() throws Exception {
        Player player = new Player(1, "Ali", 2000, dice);
        System.out.print("Faces of Dices " + player.tossDie(dice, isMoving, board));
        assertTrue((player.tossDie(dice, isMoving, board) >= 2) && (player.tossDie(dice, isMoving, board) <= 12));

    }

}