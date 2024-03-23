package com.httpservertest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.text.StringEscapeUtils;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class HomeLogin implements HttpHandler{
    private String currentUser = "";
    private int currentUserHash = 0;

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // TODO Auto-generated method stub
        handlePostRequest(exchange);
        throw new UnsupportedOperationException("Unimplemented method 'handle'");
    }

    public void handlePostRequest(HttpExchange exchange) throws IOException{
        
        InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);
        String query = "";
        query = br.readLine();
        System.out.println(query);  
        String[] q = query.split("[=&]");
        for(String s : q){
            System.out.println(s);
        }
        currentUser = q[1];
        currentUserHash = q[1].hashCode();

        sendMainPage(exchange);

    }

    public void sendMainPage(HttpExchange exchange) throws IOException{

        Map<String, Object> data = new HashMap<>();
        data.put("username", currentUser);
        
        String message = "<h1>This Is the Home Page</h1>";
        

        data.put("message", StringEscapeUtils.escapeJava(message));

        OutputStream oStream = exchange.getResponseBody();
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache m = mf.compile("mainpage.html");  
        StringWriter writer = new StringWriter();
        m.execute(writer, data);
        exchange.sendResponseHeaders(200, writer.toString().length());
        oStream.write(writer.toString().getBytes());
        oStream.flush();
        oStream.close();
    }
    
}
