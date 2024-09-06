package com.santaca.app;

import java.io.IOException;
import java.io.OutputStream;

import org.json.JSONObject;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class MyHttpHandler implements  HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equals("GET")) {
            System.out.println("Dentro del IF");
            String param = exchange.getRequestURI().toString();
            System.out.println("Llamamos al path: " + param);
            MakeRequest req = new MakeRequest("/");
            String result = req.call_api();

            JSONObject jsonfy = new JSONObject(result);
            System.out.println(jsonfy.length());

            String results_aux = jsonfy.get("results").toString();
            // JSONArray arr = new JSONArray(results_aux);
            
            // JSONArray sorted_arr = order_json_array(arr);

            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, results_aux.getBytes().length);

            OutputStream os = exchange.getResponseBody();
            os.write(results_aux.getBytes());
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
    
    // private JSONArray order_json_array(JSONArray arr) {
    //     System.out.println("Ordenamos el array");
    //     System.out.println(arr.opt(0));
    //     return null;
    // }
    
}
