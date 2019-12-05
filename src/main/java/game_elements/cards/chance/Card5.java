package game_elements.cards.chance;

import game_elements.cards.Card;
import player.Player;

public class Card5 extends Card {

    public Card5(){
        super.setName("Chance5");
        super.setDescription("You have been elected Chairman of the Board. Pay each player $50.");
        super.setAltDescription("A newsboy shouts an Extra with Mr. Monopoly's headline on its front page");
    }


    @Override
    public void action(Player player, Player[] playerArray) {

        for(int i = 0 ; i < playerArray.length ; i++){

            if(player.getId() == playerArray[i].getId()){
                continue;
            }

            player.getMoney().subtractMoney(50);
            playerArray[i].getMoney().addMoney(50);

        }

    }
}
