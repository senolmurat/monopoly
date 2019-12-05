package game_elements.cards.community.chest;

import game_elements.cards.Card;
import player.Player;

public class commCard12 extends Card {

    public commCard12() {
        super.setName("Chest12");
        super.setDescription("Life insurance matures , Collect $100.");
        super.setAltDescription("Below an I N S sign stands a bent Mr Monopoly, his long beard brushing the floor");
    }

    @Override
    public void action(Player player, Player[] playerArray) {
        player.getMoney().addMoney(100);
    }
}
