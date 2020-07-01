package com.example.couniversity_new;

public class Delivery {
    String Rid;
    long rNum;
    String Dtitle;
    String Dcontent;
    String type;
    String Sid;
    long coupon;

    public Delivery(){

    }

    public Delivery(String Rid, long rNum, String Title, String Content, String TP, String S, long CP){
        this.Rid=Rid;
        this.rNum=rNum;
        this.Dtitle=Title;
        this.Dcontent=Content;
        this.type=TP;
        this.Sid=S;
        this.coupon=CP;
    }

    public void setRid(String R){
        this.Rid=R;
    }
    public String getRid(){
        return this.Rid;
    }
    public void setDtitle(String T){
        this.Dtitle=T;
    }
    public String getDtitle(){
        return this.Dtitle;
    }
    public void setDcontent(String C){
        this.Dcontent=C;
    }
    public String getDcontent(){
        return this.Dcontent;
    }
    public void setType(String TP){
        this.type=TP;
    }
    public String getType(){
        return this.type;
    }
    public void setSid(String S){
        this.Sid=S;
    }
    public String getSid(){
        return this.Sid;
    }
    public void setCoupon(long CP){
        this.coupon=CP;
    }
    public long getCoupon(){
        return this.coupon;
    }
    public void setrNum(long CP){
        this.rNum=CP;
    }
    public long getrNum(){
        return this.rNum;
    }
}
