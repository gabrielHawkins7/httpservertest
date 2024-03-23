package com.httpservertest;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class LoginPage implements HttpHandler{

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        handleGetResponse(exchange);
    }
    private void handleGetResponse(HttpExchange exchange) throws IOException {
        OutputStream oStream = exchange.getResponseBody();
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache m = mf.compile("loginpage.html");  
        StringWriter writer = new StringWriter();
        m.execute(writer, "example");


        exchange.sendResponseHeaders(200, writer.toString().length());
        oStream.write(writer.toString().getBytes());
        oStream.flush();
        oStream.close();
    }


    
}
