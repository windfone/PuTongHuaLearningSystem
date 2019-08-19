package com.hlxyedu.putonghualearningsystem.model.event;

public class LoginEvent {

    public static final String LOGIN = "login";

    private String type;

    public LoginEvent(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
