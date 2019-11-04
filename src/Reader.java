import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Reader {

    private int numberOfPlayers;
    private int startingMoney;
    private int numberOfTaxSquare;
    private int taxAmount;
    private int goSquare_money;
    private String[] names;



    Reader() throws Exception {
        ReadFile();
    }

    private void ReadFile() throws Exception {

        File file = new File("input.txt");
        Scanner fileReader = new Scanner(file);


        fileReader.nextLine();//To skip lines starting with #
        this.numberOfPlayers = Integer.parseInt(fileReader.nextLine());
        fileReader.nextLine();
        this.startingMoney = Integer.parseInt( ( fileReader.nextLine() ) );
        fileReader.nextLine();
        this.numberOfTaxSquare = Integer.parseInt( ( fileReader.nextLine() ) );
        fileReader.nextLine();
        this.taxAmount = Integer.parseInt( ( fileReader.nextLine() ) );
        fileReader.nextLine();
        this.goSquare_money = Integer.parseInt( ( fileReader.nextLine() ) );
        fileReader.nextLine();

        names = new String[numberOfPlayers];
        for(int i = 0 ; i < numberOfPlayers ; i++){
            names[i] = fileReader.next();
        }

    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public int getStartingMoney() {
        return startingMoney;
    }

    public int getNumberOfTaxSquare() {
        return numberOfTaxSquare;
    }

    public int getTaxAmount() {
        return taxAmount;
    }

    public int getGoSquare_money() {
        return goSquare_money;
    }

    public String[] getNames() {
        return names;
    }
}
