package com.httpservertest;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.text.StringEscapeUtils;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class MyHttpHandler implements HttpHandler{

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String requestParamValue = null;
        if("GET".equals(httpExchange.getRequestMethod())){
            requestParamValue = handleGetRequest(httpExchange);
        }else if("POST".equals(httpExchange)){
            requestParamValue = handlePostRequest(httpExchange);
        }
        handleResponse(httpExchange, requestParamValue);
    }

    private String handleGetRequest(HttpExchange httpExchange){
        return httpExchange.
                    getRequestURI()
                    .toString()
                    .split("\\?")[1]
                    .split("=")[1];
    }

    private String handlePostRequest(HttpExchange httpExchange){
        return httpExchange.getRequestURI().toString();
        }



    private void handleResponse(HttpExchange httpExchange, String requestParamValue) throws IOException{
        OutputStream outputStream = httpExchange.getResponseBody();
        StringBuilder htmlBuilder = new StringBuilder();

       htmlBuilder.
                append("h1").
                append("Hello: ").
                append(requestParamValue).
                append("</h1>");
        String htmlResponse = StringEscapeUtils.escapeHtml4(htmlBuilder.toString());
        
        //System.out.println(htmlResponse);

        String out = "<h1>Helo: " + requestParamValue + " </h1>";

        httpExchange.sendResponseHeaders(200, out.length());
        outputStream.write(out.getBytes());
        outputStream.flush();
        outputStream.close();
    }
    
    

}
