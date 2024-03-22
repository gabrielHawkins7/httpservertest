package com.httpservertest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.sun.net.httpserver.HttpServer;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");

        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)Executors.newFixedThreadPool(10);
        addRoutes(server);
        server.setExecutor(threadPoolExecutor);
        server.start();
        System.out.println("Server Started on Port 8001");

    }

    static void addRoutes(HttpServer server){
        server.createContext("/test", new MyHttpHandler());
        server.createContext("/", new HomePage());
        server.createContext("/submit", new SubmitHandler());
    }
}