package com.example.couniversity_new;

public class User {

    String id = "";
    String name = "";
    String department = "";
    String password = "";
    String email = "";
    int coupon = 0;

    public User(){ }

    public User(String id){this.id = id;}

    public User(String id , String pw, String name , String email, String department){
        this.id = id;
        this.password = pw;
        this.name = name;
        this.email = email;
        this.department = department;
        this.coupon = 0;
    }


    public User(String id , String pw , String name, String email, String department, int coupon){
        this.id = id;
        this.password = pw;
        this.name = name;
        this.email = email;
        this.department = department;
        this.coupon = coupon;
    }

    public User(User user) {
        this.id = user.getId();
        this.password = user.getPassword();
        this.name = user.getName();
        this.email = user.getEmail();
        this.department = user.getDept();
        this.coupon = user.getCoupon();
    }

    public void setId(String ID) { this.id = ID; }
    public void setPW(String pw){ this.password = pw; }
    public void setEmail(String Email){
        this.email = Email;
    }
    public void setDept(String Dept){
        this.department = Dept;
    }
    public void setName(String Name){
        this.name = Name;
    }
    public void setCoupon(int Coupon){
        this.coupon = Coupon;
    }

    public String getId() { return id; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
    public String getDept() { return department; }
    public String getName() { return name; }
    public int getCoupon() { return coupon; }
}
