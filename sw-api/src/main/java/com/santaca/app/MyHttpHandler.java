package com.santaca.app;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class MyHttpHandler implements  HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String param = "/";
        System.out.println("Method:" + exchange.getRequestMethod());
        if (exchange.getRequestMethod().equals("GET")) {
            System.out.println("Dentro del IF");
            param = exchange.getRequestURI().toString().split("\\?")[1].split("=")[1];
            MakeRequest req = new MakeRequest(param);
            String result = req.call_api();
            System.out.println("Entramos en el manejador");
            System.out.println(result);

            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, result.getBytes().length);

            OutputStream os = exchange.getResponseBody();
            os.write(result.getBytes());
            os.close();

            
        } else {
            throw new UnsupportedOperationException("Unimplemented method 'handle'");
        }
    }
    
}
