package com.example.couniversity_new;

public class Todo {
    String id;
    String work;

    public Todo(){

    }

    public Todo(String id, String work){
        this.id = id;
        this.work = work;
    }

    public void setWork(String work){
        this.work = work;
    }
    public String getWork(){
        return this.work;
    }
    public void setid(String Q){
        this.id = Q;
    }
    public String getid(){
        return this.id;
    }
}
