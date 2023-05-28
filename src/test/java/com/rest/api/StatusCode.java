package com.rest.api;

public enum StatusCode {
    CODE_200(200,""),
    CODE_201(201,""),
    CODE_400(400,"body required all filed name"),
    CODE_401(401,"Invalid Access Token ");

   public final int code;
   public final String message;
    StatusCode(int code, String message) {
        this.message=message;
        this.code=code;
    }
}
