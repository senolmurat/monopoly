package game_elements.cards.community_chest;

import game_elements.cards.Card;
import player.Player;

public class CommCard8 extends Card {

    public CommCard8() {
        super.setName("Chest8");
        super.setDescription("It is your birthday. Collect $10 from every player.");
        super.setAltDescription("Mr. Monopoly holds his gift and gets a M sign on the top of it.");
    }

    @Override
    public void action(Player player, Player[] playerArray) {

        for(int i = 0 ; i < playerArray.length ; i++){

            if(player.getId() == playerArray[i].getId()){
                continue;
            }

            if(!playerArray[i].isBankrupt()){
                player.getMoney().addMoney(10);
                playerArray[i].getMoney().subtractMoney(10);
            }

        }
    }
}
