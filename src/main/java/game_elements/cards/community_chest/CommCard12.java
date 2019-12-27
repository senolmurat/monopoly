package game_elements.cards.community_chest;

import game_elements.cards.Card;
import player.Player;

public class CommCard12 extends Card {

    public CommCard12() {
        super.setName("Chest12");
        super.setDescription("You have won second prize in a beauty contest. Collect $10.");
        super.setAltDescription("Mr. Monopoly preens with a sash and large bouquet");
    }

    @Override
    public void action(Player player, Player[] playerArray) {
        player.getMoney().addMoney(10);
    }
}
