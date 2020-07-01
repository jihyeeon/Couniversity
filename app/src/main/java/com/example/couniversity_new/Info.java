package com.example.couniversity_new;

public class Info {
    long qNum;
    long tNum;
    long rNum;

    public Info(){

    }

    public Info(long qNum, long tNum){
        this.qNum = qNum;
        this.tNum = tNum;
    }

    public Info(long qNum, long tNum, long rNum){
        this.qNum = qNum;
        this.tNum = tNum;
        this.rNum = rNum;
    }

    public void setqNum(long work){
        this.qNum = work;
    }
    public long getqNum(){
        return this.qNum;
    }
    public void settNum(long Q){
        this.tNum = Q;
    }
    public long gettNum(){
        return this.tNum;
    }
    public void setrNum(long r){
        this.rNum = r;
    }
    public long getrNum(){
        return this.rNum;
    }
}
