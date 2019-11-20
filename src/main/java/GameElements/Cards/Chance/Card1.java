package GameElements.Cards.Chance;

import GameElements.Cards.Card;
import Player.Player;

public class Card1 extends Card {

    public Card1(){
        super.setName("Chance1");
        super.setDescription("Advance to \"GO\"");
        super.setAltDescription("Mr. Monopoly hops on both feet.");
    }

    @Override
    public void action(Player player) {
        //TODO Card Actions
        //Set player Position to start square


    }
}
