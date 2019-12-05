package game_elements.cards.community_chest;

import game_elements.cards.Card;
import player.Player;

public class CommCard1 extends Card {

    public CommCard1() {
        super.setName("Chest1");
        super.setDescription("Advance to GO");
        super.setAltDescription("Mr. Monopoly goes to Go");
    }

    @Override
    public void action(Player player, Player[] playerArray) {
        //player.setPosition(1);
    }
}
