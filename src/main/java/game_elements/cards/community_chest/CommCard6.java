package game_elements.cards.community_chest;

import game_elements.cards.Card;
import player.Player;

public class CommCard6 extends Card {

    public CommCard6() {
        super.setName("Chest7");
        super.setDescription("Income tax refund. Collect $20.");
        super.setAltDescription("Mr Monopoly faints back against a man displaying the Refund paper");
    }

    @Override
    public void action(Player player, Player[] playerArray) {
        player.getMoney().addMoney(20);
    }
}
