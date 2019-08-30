package com.hlxyedu.putonghualearningsystem.model.event;

import com.hlxyedu.putonghualearningsystem.model.bean.DetailVO;

import java.util.List;

public class WordFollowEvent {

    private String type;

    private List<DetailVO> detailVOS;

    public List<DetailVO> getDetailVOS() {
        return detailVOS;
    }

    public void setDetailVOS(List<DetailVO> detailVOS) {
        this.detailVOS = detailVOS;
    }

    public WordFollowEvent(List<DetailVO> detailVOS){
        this.detailVOS = detailVOS;
    }

}
