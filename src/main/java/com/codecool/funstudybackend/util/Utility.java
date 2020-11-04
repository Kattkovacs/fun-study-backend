package com.codecool.funstudybackend.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class Utility {

    public boolean myAssertEquals(JSONObject expected, JSONObject actual){
        try {
            JSONArray definitions = actual.getJSONArray("definitions");
            JSONObject definitionContainer = definitions.getJSONObject(0);
            String actualDefinition = definitionContainer.getString("definition");
            String actualWord = actual.getString("word");

            if(expected.getString("word").equals(actualWord) && expected.getString("definition").equals(actualDefinition)){
                return true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
}
