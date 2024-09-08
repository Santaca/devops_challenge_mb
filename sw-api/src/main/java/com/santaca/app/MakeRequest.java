package com.santaca.app;

import com.github.kevinsawicki.http.HttpRequest;

public class MakeRequest {
    String BASE_URL = "https://swapi.dev/api/people/";
    String path = "/";

    public MakeRequest(String path) {
        this.path = path;
    }

    public String call_api() {
        System.out.println("LLamamos al api externo: " + path);
        String result = "";
        try {
            result = HttpRequest.get(BASE_URL + this.path).body();
        } catch (HttpRequest.HttpRequestException e) {
            System.err.println(e.getMessage());
        }

        return result;
    }
}   