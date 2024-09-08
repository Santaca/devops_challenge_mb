package com.santaca.app;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class MyHttpHandler implements  HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equals("GET")) {
            MakeRequest req = new MakeRequest("/");
            String result = req.call_api();

            JSONObject jsonfy = new JSONObject(result);

            String results_aux = jsonfy.get("results").toString();
            JSONArray arr = new JSONArray(results_aux);
            
            String sorted_arr = sort_json_array(arr).toString();

            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, sorted_arr.getBytes().length);

            OutputStream os = exchange.getResponseBody();
            os.write(sorted_arr.getBytes());
            os.close();

        } else {
            String error = "Method Not Allowed";
            exchange.getResponseHeaders().set("Content-Type", "application/text");
            exchange.sendResponseHeaders(405, error.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(error.getBytes());
            os.close();
            throw new UnsupportedOperationException("Unimplemented method 'handle'");
        }
    }
    
 public static JSONArray sort_json_array(JSONArray arr) {
        
        List<JSONObject> list = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {
            list.add(arr.getJSONObject(i));
        }
        Collections.sort(list, new Comparator<JSONObject>() {
            @Override
            public int compare(JSONObject a, JSONObject b) {
                String nameA = a.getString("name");
                String nameB = b.getString("name");
                return nameA.compareToIgnoreCase(nameB);  
            }
        });

        JSONArray sorted_arr = new JSONArray();
        for (JSONObject jsonObj : list) {
            sorted_arr.put(jsonObj);
        }

        return sorted_arr;
    }
    
}
