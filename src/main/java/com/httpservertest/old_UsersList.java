package com.httpservertest;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class old_UsersList implements HttpHandler{

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        handleGetResponse(exchange);
    }
    private void handleGetResponse(HttpExchange exchange) throws IOException {
        OutputStream oStream = exchange.getResponseBody();

        DBManager dbmanager = new DBManager();
        Map<Integer, String> users = dbmanager.getUsers();
         String replace = "";

        for(int i : users.keySet()){
            replace += i + " : " + users.get(i) + "\n";
        }

        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache m = mf.compile("userslist.html");  
        HashMap<String, String> map = new HashMap<>();
        map.put("users_list",replace);
        
        StringWriter writer = new StringWriter();
        m.execute(writer, map);

        System.out.println(replace);
        System.out.println(writer.toString());

        exchange.sendResponseHeaders(200, writer.toString().length());
        oStream.write(writer.toString().getBytes());
        oStream.flush();
        oStream.close();
    }


    
}
