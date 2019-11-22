package Player;

import GameElements.*;
import Player.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    private Player player;
    private boolean isMoving = true;
    Board board = new Board(200);
    Dice dice = new Dice();

    @Test
    public void tossDie() throws Exception {
        Player player = new Player(1, "Ali", 2000, dice);
        System.out.print("Faces of Dices " + player.tossDie(dice, board));
        assertTrue((player.tossDie(dice, board) >= 2) && (player.tossDie(dice, board) <= 12));

    }

}