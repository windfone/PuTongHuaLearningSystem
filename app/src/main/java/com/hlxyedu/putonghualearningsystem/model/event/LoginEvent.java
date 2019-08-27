package com.hlxyedu.putonghualearningsystem.model.event;

import com.hlxyedu.putonghualearningsystem.model.bean.DataVO;

import java.util.ArrayList;

public class LoginEvent {

    public static final String LOGIN = "login";

    private String type;

    private int pos;

    private ArrayList<DataVO> lists;

    private String title;

    private String conTitle;

    public LoginEvent(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public LoginEvent(String type,int pos,ArrayList<DataVO> lists,String title,String conTitle){
        this.type = type;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public ArrayList<DataVO> getLists() {
        return lists;
    }

    public void setLists(ArrayList<DataVO> lists) {
        this.lists = lists;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getConTitle() {
        return conTitle;
    }

    public void setConTitle(String conTitle) {
        this.conTitle = conTitle;
    }
}
