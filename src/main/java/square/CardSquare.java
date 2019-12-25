package square;

import game_elements.cards.Card;
import game_elements.cards.chance.*;
import game_elements.cards.community_chest.*;
import io.Display;
import player.Player;

public class CardSquare extends Square {

    private Card[] chance = new Card[6];
    private Card[] communityChest = new Card[15];
    private Player[] players;
    private Square[] board;
    private Display display = new Display();

    public CardSquare(String name, int position , Player[] playerArray , Square[] boardArr) {
        super.setName(name);
        super.setPosition(position);
        players = playerArray;
        board = boardArr;
        createCards(chance , communityChest);
    }


    @Override
    public void squareAction(Player player) {

        if(super.getName() == "Chance"){

            int chanceCard = (int)(Math.random() * 6) % 6 ;
            chance[chanceCard].action(player , players);
            display.infoMessageCardForSquares(chance[chanceCard].getDescription(), player);

        }
        else if(super.getName() == "Community Chest"){

            int chanceCard = (int)(Math.random() * 15) % 15 ;
            communityChest[chanceCard].action(player , players);
            display.infoMessageCardForSquares(communityChest[chanceCard].getDescription(), player);
        }
    }

    @Override
    public String landedOn() {
        return super.getName(); //Will get either "Chance" or "Community Chest"
    }

    private void createCards(Card[] chance , Card[] communityChest){

        chance[0] = new Card1(board);
        chance[1] = new Card2(board);
        chance[2] = new Card3();
        chance[3] = new Card4();
        chance[4] = new Card5();
        chance[5] = new Card6();

        communityChest[0] = new CommCard1(board);
        communityChest[1] = new CommCard2();
        communityChest[2] = new CommCard3();
        communityChest[3] = new CommCard4();
        communityChest[4] = new CommCard5(board);
        communityChest[5] = new CommCard6();
        communityChest[6] = new CommCard7();
        communityChest[7] = new CommCard8();
        communityChest[8] = new CommCard9();
        communityChest[9] = new CommCard10();
        communityChest[10] = new CommCard11();
        communityChest[11] = new CommCard12();
        communityChest[12] = new CommCard13();
        communityChest[13] = new CommCard14();
        communityChest[14] = new CommCard15();

    }

}
