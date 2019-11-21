package GameElements.Cards.CommunityChest;

import GameElements.Cards.Card;
import Player.Player;

public class commCard3 extends Card {

    public commCard3() {
        super.setName("Chest3");
        super.setDescription("Doctor's fees. Pay $25");
        super.setAltDescription("Mr. Monopoly angrily brandishes crutches as he stomps with a leg cast");
    }

    @Override
    public void action(Player player, Player[] playerArray) {
        //decrase player money bby 25
    }
}
