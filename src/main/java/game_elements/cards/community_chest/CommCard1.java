package game_elements.cards.community_chest;

import game_elements.cards.Card;
import player.Player;
import square.Square;

public class CommCard1 extends Card {

    private Square[] board;

    public CommCard1(Square[] boardArr) {
        super.setName("Chest1");
        super.setDescription("Advance to GO");
        super.setAltDescription("Mr. Monopoly goes to Go");
        board = boardArr;
    }

    @Override
    public void action(Player player, Player[] playerArray) {
        player.setPosition(board[0]);
    }
}
