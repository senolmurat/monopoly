package game_elements.cards.community_chest;

import game_elements.cards.Card;
import player.Player;

public class CommCard3 extends Card {

    public CommCard3() {
        super.setName("Chest3");
        super.setDescription("Doctor's fees. Pay $25");
        super.setAltDescription("Mr. Monopoly angrily brandishes crutches as he stomps with a leg cast");
    }

    @Override
    public void action(Player player, Player[] playerArray) {
        player.getMoney().subtractMoney(25);
    }
}
