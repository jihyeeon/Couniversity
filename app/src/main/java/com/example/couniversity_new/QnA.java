package com.example.couniversity_new;

public class QnA {
    long Qid;
    String id;
    String Qtitle;
    String Qcontent;
    long coupon;

    public QnA(){

    }


    public QnA(long Q, String id, String T, String C,  long CP){
        this.Qid=Q;
        this.id = id;
        this.Qtitle=T;
        this.Qcontent=C;
        this.coupon=CP;
    }

    public void setQid(long Q){
        this.Qid=Q;
    }
    public long getQid(){
        return this.Qid;
    }
    public void setid(String Q){
        this.id=Q;
    }
    public String getid(){
        return this.id;
    }
    public void setQtitle(String T){
        this.Qtitle=T;
    }
    public String getQtitle(){
        return this.Qtitle;
    }
    public void setQcontent(String C){
        this.Qcontent=C;
    }
    public String getQcontent(){
        return this.Qcontent;
    }

    public void setCoupon(int CP){
        this.coupon=CP;
    }
    public long getCoupon(){
        return this.coupon;
    }
}
