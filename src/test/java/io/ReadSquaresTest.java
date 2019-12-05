package io;

import square.PropertySquare;
import square.UtilitySquare;
import org.junit.Test;

import java.util.List;

public class ReadSquaresTest {

    @Test
    public void getPropertySquaresList() {
        ReadSquares readPropertySquare = new ReadSquares();
        List<PropertySquare> propertySquaresList = readPropertySquare.getPropertySquaresList();
        for (PropertySquare propertySquare : propertySquaresList)
            System.out.println("Name : " + propertySquare.getName() + " " + "Position : " +propertySquare.getPosition()
                    + " " + "Color : "+ propertySquare.getColour() + " "
                    + "Land Value : " +propertySquare.getLandValue() + " " + "Rent Value : "+ propertySquare.getRent());
    }

    @Test
    public void getUtilitySquaresList() {
        ReadSquares readPropertySquare = new ReadSquares();
        List<UtilitySquare> utilitySquaresList = readPropertySquare.getUtilitySquaresList();
        for (UtilitySquare utilitySquare : utilitySquaresList)
            System.out.println("Name : " + utilitySquare.getName() + " " + "Position : " +utilitySquare.getPosition()
                    + " " + "Color : "+ utilitySquare.getType() + " "
                    + "Land Value : " +utilitySquare.getLandValue() + " " + "Rent Value : "+ utilitySquare.getRent());
    }
}