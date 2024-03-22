package com.httpservertest;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class HomePage implements HttpHandler{

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("Trying to serve Page");
        handleResponse(exchange);
    }

    private void handleResponse(HttpExchange exchange) throws IOException {
        OutputStream oStream = exchange.getResponseBody();
        StringBuilder html = new StringBuilder();

        //String out = "<h1>Helo World</h1>";
        File file = new File("src/main/resources/home.html");
        Scanner sc = new Scanner(file);
        

        while(sc.hasNextLine()){
            //System.out.println(sc.nextLine());
            html.append(sc.nextLine().toString());
        }
        sc.close();

        String out = html.toString();


        exchange.sendResponseHeaders(200, out.length());
        oStream.write(out.getBytes());
        oStream.flush();
        oStream.close();

    }

    
}
