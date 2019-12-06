package com.hlxyedu.putonghualearningsystem.model.event;

public class TabSelectEvent {

    public static final String CHANGE = "change";

    private String type;

    private int pos;

    public TabSelectEvent(String type, int pos) {
        this.type = type;
        this.pos = pos;
    }

    public String getType() {
        return type;
    }

    public int getPos() {
        return pos;
    }

}
