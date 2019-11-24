package GameElements.Cards.CommunityChest;

import GameElements.Cards.Card;
import Player.Player;

public class commCard10 extends Card {

    public commCard10() {
        super.setName("Chest10");
        super.setDescription("School fees. Pay $50.");
        super.setAltDescription("A bespectacled schoolboy unhappily receives a head pat and a dime Rockefeller style from Mr. Monopoly.");
    }

    @Override
    public void action(Player player, Player[] playerArray) {
        player.getMoney().subtractMoney(50);
    }

}
