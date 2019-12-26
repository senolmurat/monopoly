package io;

import square.CommunitySquare;
import square.PropertySquare;
import square.UtilitySquare;
import org.junit.Test;

import java.util.List;

public class ReadSquaresTest {

    @Test
    public void getPropertySquaresList() {
        ReadPropertyUtilityCommunitySquares readPropertySquare = new ReadPropertyUtilityCommunitySquares();
        List<PropertySquare> propertySquaresList = readPropertySquare.getPropertySquaresList();
        for (PropertySquare propertySquare : propertySquaresList)
            System.out.println("Name : " + propertySquare.getName() + " " + "Position : " + propertySquare.getPosition()
                    + " " + "Color : " + propertySquare.getType() + " "
                    + " Land Value : " + propertySquare.getLandValue() + " " + " Rent Value : " + propertySquare.getBaseRent()
                    + " First Home Rental : " + propertySquare.getFirstHomeRental() + " Second Home Rental : " + propertySquare.getSecondHomeRental() + " Third Home Rental : " + propertySquare.getThirdHomeRental() +
                    " Fourth Home Rental : " + propertySquare.getFourthHomeRental() + " Otel Rental : " + propertySquare.getOtelRental() + " Builder Price : " + propertySquare.getBuildingPrice());
    }

    @Test
    public void getUtilitySquaresList() {
        ReadPropertyUtilityCommunitySquares readPropertySquare = new ReadPropertyUtilityCommunitySquares();
        List<UtilitySquare> utilitySquaresList = readPropertySquare.getUtilitySquaresList();
        for (UtilitySquare utilitySquare : utilitySquaresList)
            System.out.println("Name : " + utilitySquare.getName() + " " + "Position : " + utilitySquare.getPosition()
                    + " " + "Color : " + utilitySquare.getType() + " "
                    + "Land Value : " + utilitySquare.getLandValue() + " " + "Rent Value : " + utilitySquare.getBaseRent() + " " + "Two Station Value : " + utilitySquare.getTwoStation() + " " + "Three Station Value : "
                    + utilitySquare.getThreeStation() + " " + "Four Station Value : " + utilitySquare.getFourStation());
    }

    @Test
    public void getCommunitySquaresList() {
        ReadPropertyUtilityCommunitySquares readCommunitySquares = new ReadPropertyUtilityCommunitySquares();
        List<CommunitySquare> communitySquareList = readCommunitySquares.getCommunitySquareArrayList();
        for (CommunitySquare communitySquare : communitySquareList)
            System.out.println("Name : " + communitySquare.getName() + " " + "Position : " + communitySquare.getPosition()
                    + " " + "Color : " + communitySquare.getType() + " "
                    + "Land Value : " + communitySquare.getLandValue() + " " + "One Community Value : " + communitySquare.getBaseRent()
                    + " " + "Two Community Value : " + communitySquare.getTwoCommunity());
    }
}