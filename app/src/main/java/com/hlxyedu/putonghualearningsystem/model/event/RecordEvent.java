package com.hlxyedu.putonghualearningsystem.model.event;

public class RecordEvent {

    public static final String START_RECORD = "start_record";

    private String type;

    private int second;

    public RecordEvent(String type){
        this.type = type;
    }

    public RecordEvent(String type,int second){
        this.type = type;
        this.second = second;
    }

    public String getType() {
        return type;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }
}
