package IO;

import Square.PropertySquare;
import org.junit.Test;

import java.util.List;

public class ReadPropertySquareTest {


    @Test
    public void getPropertySquaresList() {
        ReadPropertySquare readPropertySquare = new ReadPropertySquare();
        List<PropertySquare> propertySquaresList = readPropertySquare.getPropertySquaresList();
        for (PropertySquare propertySquare : propertySquaresList)
            System.out.println("Name : " + propertySquare.getName() + " " + "Position : " +propertySquare.getPosition()
                    + " " + "Color : "+ propertySquare.getColour() + " "
                    + "Land Value : " +propertySquare.getLandValue() + " " + "Rent Value : "+ propertySquare.getRent());
    }
}