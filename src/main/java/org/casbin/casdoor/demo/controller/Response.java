package org.casbin.casdoor.demo.controller;

public record Response(String status, String msg, Object data) {

    public static Response ok(Object data) {
        return new Response("ok", "", data);
    }

    public static Response error(String msg) {
        return new Response("error", msg, null);
    }
}
