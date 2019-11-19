package GameElements.Cards.Chance;

import GameElements.Cards.Card;
import Player.Player;

public class Card5 extends Card {

    public Card5(){
        super.setName("Chance5");
        super.setDescription("You have been elected Chairman of the Board. Pay each player $50.");
        super.setAltDescription("A newsboy shouts an Extra with Mr. Monopoly's headline on its front page");
    }


    @Override
    public void action(Player player) {
        //Decrase player money by 50
    }
}
