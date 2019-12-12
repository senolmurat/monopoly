package io;

import lombok.Getter;
import lombok.Setter;
import square.PropertySquare;
import square.UtilitySquare;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@Getter
public class ReadSquares {


    ArrayList<PropertySquare> propertySquaresList = new ArrayList<>();
    ArrayList<UtilitySquare> utilitySquaresList = new ArrayList<>();
    JSONArray squares;

    public ReadSquares() {
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
            long rent = (long) jsonObject.get("rent");
            PropertySquare propertySquare = new PropertySquare(name, (int) position, color, (int) landValue, (int) rent);
            propertySquaresList.add(propertySquare);
        } else {
            jsonObject = (JSONObject) properties.get("UtilitySquare");
            String name = (String) jsonObject.get("name");
            long position = (long) jsonObject.get("position");
            String color = (String) jsonObject.get("color");
            long landValue = (long) jsonObject.get("landValue");
            long rent = (long) jsonObject.get("rent");
            UtilitySquare utilitySquare = new UtilitySquare(name, (int) position, color, (int) landValue, (int) rent);
            utilitySquaresList.add(utilitySquare);

        }
    }

}


