package  com.example.mindhelper.mindhelper;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Daily {
    private long id;

    // 把需要的資料包裝為欄位變數
    /* private double latitude;
    private double longitude;
    private double accuracy; */
    private String datetime;
    private String note;
    private String activity;
    private String date;
    private String time;
    private String mood;
    private String hold;
    private String p_id;

    private String a08, a09, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21; //代表從早上八點至晚上九點的活動內容 ，可空值
    private String m08, m09, m10, m11, m12, m13, m14, m15, m16, m17, m18, m19, m20, m21; //代表從早上八點至晚上九點的掌控度，可空值
    private String p08, p09, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21;//代表從早上八點至晚上九點的樂趣程度 ，可空值

    // 提供一個沒有參數的建構子
    public Daily() {

    }

    // 提供一個包含所有資料參數的建構子
    public Daily(long id, String datetime, String note,
                 String a08, String a09, String a10, String a11, String a12,
                 String a13, String a14, String a15, String a16, String a17, String a18, String a19, String a20, String a21,
                 String m08, String m09, String m10, String m11, String m12,
                 String m13, String m14, String m15, String m16, String m17, String m18, String m19, String m20, String m21,
                 String p08, String p09, String p10, String p11, String p12,
                 String p13, String p14, String p15, String p16, String p17, String p18, String p19, String p20, String p21,String activity,String date , String time, String mood ,String hold,String p_id) {
         /*this.latitude = latitude;
        this.longitude = longitude;
        this.accuracy = accuracy; */
        this.id = id;
        this.datetime = datetime;
        this.note = note;

        this.a08 = a08; this.a09 = a09 ; this.a10 = a10;
        this.a11 = a11; this.a12 = a12; this.a13 = a13;
        this.a14 = a14; this.a15 = a15; this.a16 = a16;
        this.a17 = a17; this.a18 = a18; this.a19 = a19;
        this.a20 = a20; this.a21 = a21;

        this.m08 = m08; this.m09 = m09 ; this.m10 = m10;
        this.m11 = m11; this.m12 = m12; this.m13 = m13;
        this.m14 = m14; this.m15 = m15; this.m16 = m16;
        this.m17 = m17; this.m18 = m18; this.m19 = m19;
        this.m20 = m20; this.m21 = m21;

        this.p08 = p08; this.p09 = p09 ; this.p10 = p10;
        this.p11 = p11;this.p12 = p12; this.p13 = p13;
        this.p14 = p14;this.p15 = p15; this.p16 = p16;
        this.p17 = p17;this.p18 = p18; this.p19 = p19;
        this.p20 = p20;this.p21 = p21;
        this.activity = activity; this.time = time; this.mood = mood; this.hold = hold; this.p_id = p_id; this.date = date;


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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); //原"yyyy-MM-dd HH:mm"
        datetime = sdf.format(date);
    }

    // 取得說明
    public String getNote() {
        return note;
    }
    // 設定說明
    public void setNote(String note) {
        this.note = note;
    }

    //a08~a21，早上八點至晚上九點的活動
    public String getA08(){return a08;}  // 取得a08
    public void setA08(String a08){this.a08 = a08;}  // 設定a08

    public String getA09(){return a09;}
    public void setA09(String a09){this.a09 = a09;}

    public String getA10(){return a10;}
    public void setA10(String a10){this.a10 = a10;}

    public String getA11(){return a11;}
    public void setA11(String a11){this.a11 = a11;}

    public String getA12(){return a12;}
    public void setA12(String a12){this. a12= a12;}

    public String getA13(){return a13;}
    public void setA13(String a13){this. a13= a13;}

    public String getA14(){return a14;}
    public void setA14(String a14){this. a14= a14;}

    public String getA15(){return a15;}
    public void setA15(String a15){this. a15= a15;}

    public String getA16(){return a16;}
    public void setA16(String a16){this. a16= a16;}

    public String getA17(){return a17;}
    public void setA17(String a17){this. a17= a17;}

    public String getA18(){return a18;}
    public void setA18(String a18){this. a18= a18;}

    public String getA19(){return a19;}
    public void setA19(String a19){this.a19 = a19;}

    public String getA20(){return a20;}
    public void setA20(String a20){this.a20 = a20;}

    public String getA21(){return a21;}
    public void setA21(String a21){this. a21= a21;}

    //m08~m21，早上八點至晚上九點的掌控度
    public String getM08(){return m08;}
    public void setM08(String m08){this.m08 = m08;}

    public String getM09(){return m09;}
    public void setM09(String m09){this.m09 = m09;}

    public String getM10(){return m10;}
    public void setM10(String m10){this.m10 = m10;}

    public String getM11(){return m11;}
    public void setM11(String m11){this.m11 = m11;}

    public String getM12(){return m12;}
    public void setM12(String m12){this.m12 = m12;}

    public String getM13(){return m13;}
    public void setM13(String m13){this.m13 = m13;}

    public String getM14(){return m14;}
    public void setM14(String m14){this.m14 = m14;}

    public String getM15(){return m15;}
    public void setM15(String m15){this.m15 = m15;}

    public String getM16(){return m16;}
    public void setM16(String m16){this.m16 = m16;}

    public String getM17(){return m17;}
    public void setM17(String m17){this.m17 = m17;}

    public String getM18(){return m18;}
    public void setM18(String m18){this.m18 = m18;}

    public String getM19(){return m19;}
    public void setM19(String m19){this.m19 = m19;}

    public String getM20(){return m20;}
    public void setM20(String m20){this.m20 = m20;}

    public String getM21(){return m21;}
    public void setM21(String m21){this.m21 = m21;}

    //p08~p21，早上八點至晚上九點的愉悅度
    public String getP08(){return p08;}
    public void setP08(String p08){this.p08 = p08;}

    public String getP09(){return p09;}
    public void setP09(String p09){this.p09 = p09;}

    public String getP10(){return p10;}
    public void setP10(String p10){this.p10 = p10;}

    public String getP11(){return p11;}
    public void setP11(String p11){this.p11 = p11;}

    public String getP12(){return p12;}
    public void setP12(String p12){this.p12 = p12;}

    public String getP13(){return p13;}
    public void setP13(String p13){this.p13 = p13;}

    public String getP14(){return p14;}
    public void setP14(String p14){this.p14 = p14;}

    public String getP15(){return p15;}
    public void setP15(String p15){this.p15 = p15;}

    public String getP16(){return p16;}
    public void setP16(String p16){this.p16 = p16;}

    public String getP17(){return p17;}
    public void setP17(String p17){this.p17 = p17;}

    public String getP18(){return p18;}
    public void setP18(String p18){this.p18 = p18;}

    public String getP19(){return p19;}
    public void setP19(String p19){this.p19 = p19;}

    public String getP20(){return p20;}
    public void setP20(String p20){this.p20 = p20;}

    public String getP21(){return p21;}
    public void setP21(String p21){this.p21 = p21;}

    public String getDate() {
        return date;
    }
    public void setDate(String date) {this.date = date; }

    public String getActivity() {
        return activity;
    }
    public void setActivity(String activity) {this.activity=activity; }

    public String getMood() {
        return mood;
    }
    public void setMood(String mood) {this.mood=mood; }

    public String getHold() {
        return hold;
    }
    public void setHold(String hold) {this.hold = hold; }

    public String getP_id() {
        return p_id;
    }
    public void setP_id(String p_id){this.p_id=p_id; }

    public String getTime() {
        return time;
    }
    public void setTime(String time) {this.time=time; }
}