package com.santaca.app;

import com.github.kevinsawicki.http.HttpRequest;

public class MakeRequest {
    String BASE_URL = "https://swapi.dev/api/people/";
    String path = "/";

    public MakeRequest(String path) {
        this.path = path;
    }

    public String call_api() {
        System.out.println("Entra a la llamada");
        String result = "";
        try {
            result = HttpRequest.get(BASE_URL + this.path).body();
        } catch (HttpRequest.HttpRequestException e) {
            e.printStackTrace();
        }

        return result;
    }

    // private JSONObject convert_to_json(String json) {
        
    //     JSONObject json_obj = new JSONObject(json);
    //     JSONArray json_arr = json_obj.getJSONArray("results");

    //     for (int i = 0; i < json_arr.length(); i++) {
    //         JSONObject explrObject = json_arr.getJSONObject(i);
    //         System.out.println(explrObject);
    //     }

    //     return null;
    // }
}   
