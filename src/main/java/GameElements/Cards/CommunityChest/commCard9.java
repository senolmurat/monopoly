package GameElements.Cards.CommunityChest;

import GameElements.Cards.Card;
import Player.Player;

public class commCard9 extends Card {

    public commCard9() {
        super.setName("Chest9");
        super.setDescription("Hospital Fees. Pay $50.");
        super.setAltDescription("A bored nurse holds out her hand for payment while Mr. Monopoly holds 2 swaddled infants, one in each arm");
    }

    @Override
    public void action(Player player, Player[] playerArray) {
        player.getMoney().subtractMoney(50);
    }
}
