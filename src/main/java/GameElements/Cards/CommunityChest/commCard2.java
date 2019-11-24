package GameElements.Cards.CommunityChest;

import GameElements.Cards.Card;
import Player.Player;

public class commCard2 extends Card {

    public commCard2() {
        super.setName("Chest2");
        super.setDescription("Bank error in your favor. Collect $50");
        super.setAltDescription("Mr. Monopoly falls back in astonishment as an arm presents a sheaf of cash out of a bank teller's window");
    }

    @Override
    public void action(Player player, Player[] playerArray) {
        player.getMoney().addMoney(50);
    }
}
