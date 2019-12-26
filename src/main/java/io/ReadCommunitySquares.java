package io;

import lombok.Getter;
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
public class ReadCommunitySquares {


    ArrayList<CommunitySquare> communitySquareArrayList = new ArrayList<>();
    JSONArray squares;

    public ReadCommunitySquares() {
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

        JSONObject jsonObject = (JSONObject) properties.get("CommunitySquare");
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
    }

}


