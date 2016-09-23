package com.example.mindhelper.mindhelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tcr {

    // 編號、日期時間、顏色、標題、答案1~7、修改、已選擇
    private long id;
    private String ans1_who;
    private String ans1_where;
    private String ans1_when;
    private String ans1_what;

    private String ans2_emo;
    private String ans2_per;
    private String datetime;
    private String note;
   private String pid;
    private String ans1;
    private String ans2;
    private String ans3;
    private String ans4;
    private String ans5;
    private String ans6;
    private String ans7;

    public Tcr() {  //無參數建構子

    }

    public Tcr(long id, String datetime, String note,
               String ans1, String ans2,String ans1_who, String ans1_where, String ans1_when, String ans1_what,
               String ans2_emo,  String ans2_per, String ans3, String ans4, String ans5, String ans6, String ans7,String pid ) {
        this.id = id;
        this.datetime = datetime;
        this.note = note;
        this.ans1 = ans1;
        this.ans2 = ans2;
        this.ans1_who = ans1_who;
        this.ans1_where = ans1_where;
        this.ans1_when = ans1_when;
        this.ans1_what = ans1_what;

        this.ans2_emo = ans2_emo;
        this.ans2_per = ans2_per;
        this.ans3 = ans3;
        this.ans4 = ans4;
        this.ans5 = ans5;
        this.ans6 = ans6;
        this.ans7 = ans7;
        this.pid = pid;
    }

    // 取得編號
    public long getId() {
        return id;
    }
    // 設定編號
    public void setId(long id) {
        this.id = id;
    }

    // 取得日期時間
    public String getDatetime() {
        return datetime;
    }

    // 設定日期時間
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    // 額外提供的設定日期時間方法，接收一個long值的參數
    // 把參數轉換為日期時間後在設定給日期時間欄位變數
    public void setDatetime(long now) {
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd "); //原"yyyy-MM-dd HH:mm"
        datetime = sdf.format(date);
    }

    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public String getPid() {
        return pid;
    }
    public void setPid(String pid) {
        this.pid = pid;
    }
    public String getAns1() {
        return ans1;
    }
    public void setAns1(String ans1) {
        this.ans1 = ans1;
    }

    public String getAns2() {
        return ans2;
    }
    public void setAns2(String ans2) {
        this.ans2 = ans2;
    }
    public String getAns1_who() {
        return ans1_who;
    }
    public void setAns1_who(String ans1_who) {this.ans1_who = ans1_who;}

    public String getAns1_where() {
        return ans1_where;
    }
    public void setAns1_where(String ans1_where) {
        this.ans1_where = ans1_where;
    }

    public String getAns1_when() {
        return ans1_when;
    }
    public void setAns1_when(String ans1_when) {this.ans1_when = ans1_when; }

    public String getAns1_what() {
        return ans1_what;
    }
    public void setAns1_what(String ans1_what) {
        this.ans1_what = ans1_what;
    }

    public String getAns2_emo() { return ans2_emo; }
    public void setAns2_emo(String ans2_emo) { this.ans2_per = ans2_emo; }

    public String getAns2_per() { return ans2_per; }
    public void setAns2_per(String ans2_per) { this.ans2_per = ans2_per; }
    public String getAns3() {
        return ans3;
    }
    public void setAns3(String ans3) {
        this.ans3 = ans3;
    }

    public String getAns4() {
        return ans4;
    }
    public void setAns4(String ans4) {
        this.ans4 = ans4;
    }

    public String getAns5() {
        return ans5;
    }
    public void setAns5(String ans5) {
        this.ans5 = ans5;
    }

    public String getAns6() {
        return ans6;
    }
    public void setAns6(String ans6) {
        this.ans6 = ans6;
    }

    public String getAns7() {
        return ans7;
    }
    public void setAns7(String ans7) {
        this.ans7 = ans7;
    }

}
