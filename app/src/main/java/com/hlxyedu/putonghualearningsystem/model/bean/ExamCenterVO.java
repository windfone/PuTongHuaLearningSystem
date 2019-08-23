package com.hlxyedu.putonghualearningsystem.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class ExamCenterVO implements Parcelable {


    /**
     * paperTitle : 普通话模拟考试试卷第一套
     * paperNo : PTH_001
     * paperContent : [{"sontitle":"第一题，请在4分钟时间内朗读单音节汉字100个","sonContent":["哲","洽","许","腾","缓","昂","翻","容","选","闻","悦","围","波","信","铭","欧","测","敷","闰","巢","字","披","翁","辆","申","按","捐","旗","黑","咬","瞥","贺","失","广","晒","兵","卦","拔","君","仍","胸","撞","非","眸","葬","昭","览","脱","嫩","所","德","柳","砚","甩","豹","壤","凑","坑","绞","崔","我","初","蔽","匀","铝","枪","柴","搭","穷","董","池","款","杂","此","艘","粉","阔","您","镁","帘","械","搞","堤","捡","魂","躺","瘸","蛀","游","蠢","固","浓","钾","酸","莫","捧","队","耍","踹","儿"],"answerTime":240},{"sontitle":"第二题，请在4分钟时间内朗读双音节字词50个","sonContent":["国王","今日","虐待","难怪","产品","外面","掉头","遭受","人群","压力","材料","露馅儿","窘迫","亏损","翱翔","永远","佛典","花瓶儿","沙尘","存在","请求","累赘","发愣","做活儿","篡夺","似乎","怎么","赔偿","勘察","一辈子","辨别","调整","少女","完全","妨碍","霓虹灯","疯狂","从而","入学","夸奖","回去","酒盅儿","秧歌","夏季","钢铁","通讯","敏感","不速之客"],"answerTime":240},{"sontitle":"第三题，请在4分钟时间内朗读短文","sonContent":["在浩瀚无垠的沙漠里，有一片美丽的绿洲，绿洲里藏着一颗闪光的珍珠。这颗珍珠就是敦煌莫高窟。它坐落在我国甘肃省敦煌市三危山和鸣沙山的怀抱中。","","鸣沙山东麓是平均高度为十七米的崖壁。在一千六百多米长的崖壁上，凿有大小洞窟七百余个，形成了规模宏伟的石窟群。其中四百九十二个洞窟中，共有彩色塑像两千一百余尊，各种壁画共四万五千多平方米。莫高窟是我国古代无数艺术匠师留给人类的珍贵文化遗产。","","莫高窟的彩塑，每一尊都是一件精美的艺术品。最大的有九层楼那么高，最小的还不如一个手掌大。这些彩塑个性鲜明，神态各异。有慈眉善目的菩萨，有威风凛凛的天王，还有强壮勇猛的力士\u2026\u2026","","莫高窟壁画的内容丰富多彩，有的是描绘古代劳动人民打猎、捕鱼、耕田、收割的情景，有的是描绘人们奏乐、舞蹈、演杂技的场面，还有的是描绘大自然的美丽风光。其中最引人注目的是飞天。壁画上的飞天，有的臂挎花篮，采摘鲜花；有的反弹琵琶，轻拨银弦；有的倒悬身子，自天而降；有的彩带飘拂，漫天遨游；有的舒展着双臂，翩翩起舞。看着这些精美动人的壁画，就像走进了......"],"answerTime":240},{"sontitle":"第四题，请在4分钟时间内任选一道题目聊一聊","sonContent":["1.我喜爱的职业","","2.购物(消费)的感受"],"answerTime":240}]
     */

    private String paperTitle;
    private String paperNo;
    private List<PaperContentVO> paperContent;

    public String getPaperTitle() {
        return paperTitle;
    }

    public void setPaperTitle(String paperTitle) {
        this.paperTitle = paperTitle;
    }

    public String getPaperNo() {
        return paperNo;
    }

    public void setPaperNo(String paperNo) {
        this.paperNo = paperNo;
    }

    public List<PaperContentVO> getPaperContent() {
        return paperContent;
    }

    public void setPaperContent(List<PaperContentVO> paperContent) {
        this.paperContent = paperContent;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.paperTitle);
        dest.writeString(this.paperNo);
        dest.writeList(this.paperContent);
    }

    public ExamCenterVO() {
    }

    protected ExamCenterVO(Parcel in) {
        this.paperTitle = in.readString();
        this.paperNo = in.readString();
        this.paperContent = new ArrayList<PaperContentVO>();
        in.readList(this.paperContent, PaperContentVO.class.getClassLoader());
    }

    public static final Parcelable.Creator<ExamCenterVO> CREATOR = new Parcelable.Creator<ExamCenterVO>() {
        @Override
        public ExamCenterVO createFromParcel(Parcel source) {
            return new ExamCenterVO(source);
        }

        @Override
        public ExamCenterVO[] newArray(int size) {
            return new ExamCenterVO[size];
        }
    };
}
