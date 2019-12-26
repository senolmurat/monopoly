package io;

import lombok.Getter;
import square.PropertySquare;
import square.UtilitySquare;
import square.CommunitySquare;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@Getter
public class ReadPropertyUtilityCommunitySquares {


    ArrayList<PropertySquare> propertySquaresList = new ArrayList<>();
    ArrayList<CommunitySquare> communitySquareArrayList = new ArrayList<>();
    ArrayList<UtilitySquare> utilitySquaresList = new ArrayList<>();
    JSONArray squares;

    public ReadPropertyUtilityCommunitySquares() {
        ReadJSON();
    }

    private void ReadJSON() {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("properties.json")) {
            Object obj = jsonParser.parse(reader);
            squares = (JSONArray) obj;
            squares.forEach(properties -> createSquares((JSONObject) properties));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //TODO: remove duplicates.
    private void createSquares(JSONObject properties) {

        JSONObject jsonObject = (JSONObject) properties.get("PropertySquare");
        if (jsonObject != null) {
            String name = (String) jsonObject.get("name");
            long position = (long) jsonObject.get("position");
            String color = (String) jsonObject.get("color");
            long landValue = (long) jsonObject.get("landValue");
            long baseRent = (long) jsonObject.get("baseRent");
            long firstHomeRental = (long) jsonObject.get("firstHomeRental");
            long secondHomeRental = (long) jsonObject.get("secondHomeRental");
            long thirdHomeRental = (long) jsonObject.get("thirdHomeRental");
            long fourthHomeRental = (long) jsonObject.get("fourthHomeRental");
            long otelRental = (long) jsonObject.get("otelRental");
            long buildingPrice = (long) jsonObject.get("buildingPrice");
            PropertySquare propertySquare = new PropertySquare(name, (int) position, color, (int) landValue, (int) baseRent, (int) firstHomeRental, (int) secondHomeRental, (int) thirdHomeRental, (int) fourthHomeRental, (int) otelRental, (int) buildingPrice);
            propertySquaresList.add(propertySquare);
        }
        jsonObject = (JSONObject) properties.get("CommunitySquare");
        if (jsonObject != null) {
            String name = (String) jsonObject.get("name");
            long position = (long) jsonObject.get("position");
            String color = (String) jsonObject.get("color");
            long landValue = (long) jsonObject.get("landValue");
            long baseRent = (long) jsonObject.get("baseRent");
            long twoCommunity = (long) jsonObject.get("twoCommunity");
            CommunitySquare communitySquare = new CommunitySquare(name, (int) position, color, (int) landValue, (int) baseRent, (int) twoCommunity);
            communitySquareArrayList.add(communitySquare);
        }
        jsonObject = (JSONObject) properties.get("UtilitySquare");
        if (jsonObject != null) {
            String name = (String) jsonObject.get("name");
            long position = (long) jsonObject.get("position");
            String color = (String) jsonObject.get("color");
            long landValue = (long) jsonObject.get("landValue");
            long baseRent = (long) jsonObject.get("baseRent");
            long twoStation = (long) jsonObject.get("twoStation");
            long threeStation = (long) jsonObject.get("threeStation");
            long fourStation = (long) jsonObject.get("fourStation");

            UtilitySquare utilitySquare = new UtilitySquare(name, (int) position, color, (int) landValue, (int) baseRent, color, (int) twoStation, (int) threeStation, (int) fourStation);
            utilitySquaresList.add(utilitySquare);
        }
    }

}


