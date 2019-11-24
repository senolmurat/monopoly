package GameElements.Cards.CommunityChest;

import GameElements.Cards.Card;
import Player.Player;

public class commCard1 extends Card {

    public commCard1() {
        super.setName("Chest1");
        super.setDescription("Advance to GO");
        super.setAltDescription("Mr. Monopoly goes to Go");
    }

    @Override
    public void action(Player player, Player[] playerArray) {
        player.setPosition(1);
    }
}
