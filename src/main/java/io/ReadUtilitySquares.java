package io;

import lombok.Getter;
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
public class ReadUtilitySquares {


    ArrayList<UtilitySquare> utilitySquaresList = new ArrayList<>();
    JSONArray squares;

    public ReadUtilitySquares() {
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

    private void createSquares(JSONObject properties) {

        JSONObject jsonObject = (JSONObject) properties.get("UtilitySquare");
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


