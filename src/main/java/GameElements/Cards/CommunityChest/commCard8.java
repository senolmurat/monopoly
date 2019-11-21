package GameElements.Cards.CommunityChest;

import GameElements.Cards.Card;
import Player.Player;

public class commCard8 extends Card {

    public commCard8() {
        super.setName("Chest8");
        super.setDescription("It is your birthday. Collect $10 from every player.");
        super.setAltDescription("Mr. Monopoly holds his gift and gets a M sign on the top of it.");
    }

    @Override
    public void action(Player player, Player[] playerArray) {
        //incraees player money by 20
    }
}
