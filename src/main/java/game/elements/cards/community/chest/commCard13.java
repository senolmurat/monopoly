package game.elements.cards.community.chest;

import game.elements.cards.Card;
import player.Player;

public class commCard13 extends Card {

    public commCard13() {
        super.setName("Chest13");
        super.setDescription("You are assessed for street repairs: Pay $40 per house you own.");
        super.setAltDescription("Mr. Monopoly, supported by his near-ubiquitous cane in his left hand, holds a pick and shovel over his right shoulder");
    }

    @Override
    public void action(Player player, Player[] playerArray) {
        player.getMoney().subtractMoney( (player.getProperties().size()) * 40 );
    }
}
