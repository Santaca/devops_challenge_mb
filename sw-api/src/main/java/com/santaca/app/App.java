package com.santaca.app;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public class App {
    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress("0.0.0.0", 9090), 0);
            server.createContext("/sw", new MyHttpHandler());
            server.setExecutor(null);
            server.start();
            System.out.println("Servidor corriendo en purto 9090");

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
