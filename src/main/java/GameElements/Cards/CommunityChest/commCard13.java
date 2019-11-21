package GameElements.Cards.CommunityChest;

import GameElements.Cards.Card;
import Player.Player;

public class commCard13 extends Card {

    public commCard13() {
        super.setName("Chest13");
        super.setDescription("You are assessed for street repairs: Pay $40 per house you own.");
        super.setAltDescription("Mr. Monopoly, supported by his near-ubiquitous cane in his left hand, holds a pick and shovel over his right shoulder");
    }

    @Override
    public void action(Player player, Player[] playerArray) {
        //decrase player money by numberOfProperty * 40
    }
}
