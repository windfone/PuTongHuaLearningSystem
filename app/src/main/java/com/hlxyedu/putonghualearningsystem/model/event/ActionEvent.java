package com.hlxyedu.putonghualearningsystem.model.event;


/**
 * 作者：weidingqiang on 2018/1/18 16:05
 * 邮箱：dqwei@iflytek.com
 */

public class ActionEvent {

    public static final String PROGRESS_FINISH = "PROGRESS_FINISH";

    public static final String NEXT = "NEXT";

    public static final String WAIT = "WAIT";

    public static final String COUNTDOWN = "COUNTDOWN";

    public static final String RECORD = "RECORD";

    public static final String JUMP = "JUMP";

    private String type;

    private int second;

    private String questionNo;

    private String problemTitle;

//    private AnswerVO answerVO;

    public ActionEvent(String type){
        this.type = type;
    }

    public ActionEvent(String type,String problemTitle,int second){
        this.type = type;
        this.problemTitle = problemTitle;
        this.second = second;
    }

    public ActionEvent(String type, int second, String questionNo){
        this.type = type;
        this.second = second;
        this.questionNo = questionNo;
    }

//    public ActionEvent(String type, AnswerVO answerVO){
//        this.type = type;
//        this.answerVO = answerVO;
//    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public String getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(String questionNo) {
        this.questionNo = questionNo;
    }

    public String getProblemTitle() {
        return problemTitle;
    }

    public void setProblemTitle(String problemTitle) {
        this.problemTitle = problemTitle;
    }

    //    public AnswerVO getAnswerVO() {
//        return answerVO;
//    }
//
//    public void setAnswerVO(AnswerVO answerVO) {
//        this.answerVO = answerVO;
//    }
}
