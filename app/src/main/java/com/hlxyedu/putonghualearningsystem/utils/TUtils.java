package com.hlxyedu.putonghualearningsystem.utils;

public class TUtils {

    public static String formatMusicTime(long duration){
        String minute = duration / 60000 < 10 ? "0"+ duration / 60000 : duration / 60000 +"";
        long second = duration % 60000;
        String seconds = Math.round(second/1000) < 10 ? "0"+ Math.round(second/1000) : Math.round(second/1000) +"";
        return minute + ":" + seconds;
    }
}
