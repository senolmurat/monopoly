package GameElements.Cards.CommunityChest;

import GameElements.Cards.Card;
import Player.Player;

public class commCard14 extends Card {

    public commCard14() {
        super.setName("Chest14");
        super.setDescription("You have won second prize in a beauty contest. Collect $10.");
        super.setAltDescription("Mr. Monopoly preens with a sash and large bouquet");
    }

    @Override
    public void action(Player player, Player[] playerArray) {
        player.getMoney().addMoney(10);
    }
}
