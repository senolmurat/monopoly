package game_elements.cards.chance;

import game_elements.cards.Card;
import player.Player;
import square.Square;

public class Card1 extends Card {

    private Square[] board;

    public Card1(Square[] boardArr){
        super.setName("Chance1");
        super.setDescription("Advance to \"GO\"");
        super.setAltDescription("Mr. Monopoly hops on both feet.");
        board = boardArr;
    }

    @Override
    public void action(Player player, Player[] playerArray ) {
       player.setPosition(board[0]);
    }
}
