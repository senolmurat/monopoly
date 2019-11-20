package IO;

import Square.PropertySquare;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadPropertySquare {


    ArrayList<PropertySquare> propertySquaresList = new ArrayList<>();
    JSONArray propertySquares;

    public ReadPropertySquare() {
        ReadJSON();
    }

    private void ReadJSON() {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("properties.json")) {
            Object obj = jsonParser.parse(reader);
            propertySquares = (JSONArray) obj;
            propertySquares.forEach(properties -> createProperties((JSONObject) properties));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private  void createProperties(JSONObject properties) {

        JSONObject jsonObject = (JSONObject) properties.get("PropertySquare");
        String name = (String) jsonObject.get("name");
        long position = (long) jsonObject.get("position");
        String color = (String) jsonObject.get("color");
        long landValue = (long) jsonObject.get("landValue");
        long rent = (long) jsonObject.get("rent");
        PropertySquare propertySquare = new PropertySquare(name, (int) position, color, (int) landValue, (int) rent);
        propertySquaresList.add(propertySquare);
    }

    public ArrayList<PropertySquare> getPropertySquaresList() {
        return propertySquaresList;
    }
}


