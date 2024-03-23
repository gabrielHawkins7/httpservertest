package com.httpservertest;


import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinMustache;

public class Main {
    public static void main(String[] args){
        var app = Javalin.create(
            config ->{
                config.fileRenderer(new JavalinMustache());
            }
        ).start(7070);
        
        app.get("/", ctx -> ctx.render("home.html"));
    }

}