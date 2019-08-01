package org.openelisglobal.common;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.openelisglobal.common.log.LogEvent;

public class JSONUtils {

    private JSONUtils() {

    }

    /**
     * String safe method for extracting a JSONObject. Supports String and
     * JSONObject
     *
     * @param obj the object to get JSONObject from
     * @return the extracted JSONObject
     * @throws ParseException
     */
    public static JSONObject getAsObject(Object obj) throws ParseException {
        if (obj == null) {
            return null;
        } else if (obj.getClass().equals(JSONObject.class)) {
            return (JSONObject) obj;
        } else if (obj.getClass().equals(String.class)) {
            JSONParser parser = new JSONParser();
            return (JSONObject) parser.parse((String) obj);
        } else {
            LogEvent.logError("ValidationHelper", "convertToJSONObject",
                    obj.getClass().getSimpleName() + " cannot be converted to JSONObjects");
            throw new ClassCastException();
        }
    }

    /**
     * String safe method for extracting a JSONArray. Supports String and JSONArray
     * 
     * @param obj
     * @return
     * @throws ParseException
     */
    public static JSONArray getAsArray(Object obj) throws ParseException {
        if (obj == null) {
            return null;
        } else if (obj.getClass().equals(JSONArray.class)) {
            return (JSONArray) obj;
        } else if (obj.getClass().equals(String.class)) {
            JSONParser parser = new JSONParser();
            return (JSONArray) parser.parse((String) obj);
        } else {
            LogEvent.logError("ValidationHelper", "convertToJSONArray",
                    obj.getClass().getSimpleName() + " cannot be converted to JSONArray");
            throw new ClassCastException();
        }
    }
}
