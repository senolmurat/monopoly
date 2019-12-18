package io;

import lombok.Getter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Getter
public class Reader {

    private int numberOfPlayers;
    private int startingMoney;
    private int passingStartPrize;
    private String[] names;
    private int numberOfGoToJailSquare;
    private int numberOfDices;


    public Reader() {
        ReadFile();
    }

    private void ReadFile() {

        try {
            File file = new File("input.txt");
            Scanner fileReader = new Scanner(file);

            fileReader.nextLine();//To skip lines starting with #
            this.numberOfPlayers = Integer.parseInt(fileReader.nextLine());
            fileReader.nextLine();
            this.startingMoney = Integer.parseInt((fileReader.nextLine()));
            fileReader.nextLine();
            this.passingStartPrize = Integer.parseInt((fileReader.nextLine()));
            fileReader.nextLine();
            this.numberOfGoToJailSquare = Integer.parseInt((fileReader.nextLine()));
            fileReader.nextLine();
            this.numberOfDices = Integer.parseInt(fileReader.nextLine());
            fileReader.nextLine();

            names = new String[numberOfPlayers];
            for (int i = 0; i < numberOfPlayers; i++) {
                names[i] = fileReader.next();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found!");
        } catch (Exception e2) {
            System.out.println("An Exception occurred...");
        }
    }

}
