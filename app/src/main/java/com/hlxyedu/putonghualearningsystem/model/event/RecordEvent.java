package com.hlxyedu.putonghualearningsystem.model.event;

public class RecordEvent {

    public static final String START_RECORD = "start_record";

    private String type;

    public RecordEvent(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
