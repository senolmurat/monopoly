package game_elements.cards.community_chest;

import game_elements.cards.Card;
import player.Player;
import square.Square;

public class CommCard5 extends Card {

    private Square[] board;

    public CommCard5(Square[] boardArr) {
        super.setName("Chest5");
        super.setDescription("Go to Jail. Go directly to Jail. Do not pass GO");
        super.setAltDescription("A truncheon-carrying policeman in a dark-colored uniform hauls a surprised Mr Monopoly along by the feet");
        board = boardArr;
    }

    @Override
    public void action(Player player, Player[] playerArray) {
        player.goToJail(board[10]);
    }
}
