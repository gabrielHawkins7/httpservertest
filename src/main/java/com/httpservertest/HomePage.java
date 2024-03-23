package com.httpservertest;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.Scanner;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class HomePage implements HttpHandler{
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("Sending Home Page");
        handleResponse(exchange);
    }
    private void handleResponse(HttpExchange exchange) throws IOException {
        OutputStream oStream = exchange.getResponseBody();
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache m = mf.compile("home.html");  
        StringWriter writer = new StringWriter();
        m.execute(writer, "example");
        exchange.sendResponseHeaders(200, writer.toString().length());
        oStream.write(writer.toString().getBytes());
        oStream.flush();
        oStream.close();

    }

    
}
