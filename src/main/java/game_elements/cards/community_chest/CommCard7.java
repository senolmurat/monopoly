package game_elements.cards.community_chest;

import game_elements.cards.Card;
import player.Player;

public class CommCard7 extends Card {

    public CommCard7() {
        super.setName("Chest9");
        super.setDescription("Hospital Fees. Pay $50.");
        super.setAltDescription("A bored nurse holds out her hand for payment while Mr. Monopoly holds 2 swaddled infants, one in each arm");
    }

    @Override
    public void action(Player player, Player[] playerArray) {
        player.getMoney().subtractMoney(50);
    }
}
