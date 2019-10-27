public class Board {

    private Square[] board;
    private int size = 38;


    private Board(){
        board = new Square[size];

        //Setting positions and names for all squares
        for(int i = 0 ; i < size ; i++){
            board[i].setName("Square_" + (i+1));
            board[i].setPosition(i+1);
        }

        //****************SQUARES********************************
        //Initializing start square
        board[0] = new StartSquare("Start" , 1);

        //Initializing Jail Square
        board[9] = new JailSquare("Jail" , 10);

        //Initializing Tax Squares
        board[11] = new TaxSquare("Tax Square 1" , 12 , 13);
        board[35] = new TaxSquare("Tax Square 2" , 36,  13);

        //****************SQUARES********************************
    }


    public Square[] getBoard(){ return board; }


}
