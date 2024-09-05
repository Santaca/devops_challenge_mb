package com.santaca.app;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.github.kevinsawicki.http.HttpRequest;

public class App {

    String BASE_URL = "https://swapi.dev/api/people/";

    private JSONArray call_api() {
        String req = HttpRequest.get(BASE_URL).body();
        JSONObject json = new JSONObject(req);
        JSONArray json_arr = json.getJSONArray("results");
        return json_arr;
    }

    private Object order_people(JSONObject people) {

        Iterator<String> keys = people.keys();
        Object elem = new Object();
        
        if (keys.hasNext()) {
            String key = keys.next();
            System.out.println("Clave: " + key);
            elem = people.get(key);
        }


        return elem;
    }

    public static void main(String[] args) {
        App app = new App();
        System.out.print(app.call_api());
        // JSONObject resp = app.call_api();
        // System.out.print("Primer elemento: " + app.order_people(resp));
    }
}
