package game.elements.cards.community.chest;

import game.elements.cards.Card;
import player.Player;

public class commCard7 extends Card {

    public commCard7() {
        super.setName("Chest7");
        super.setDescription("Income tax refund. Collect $20.");
        super.setAltDescription("Mr Monopoly faints back against a man displaying the Refund paper");
    }

    @Override
    public void action(Player player, Player[] playerArray) {
        player.getMoney().addMoney(20);
    }
}
