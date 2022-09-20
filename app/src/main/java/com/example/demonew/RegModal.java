package com.example.demonew;

public class RegModal {
    String name,email,num,pass,cpass;

    public RegModal() {

    }

    public RegModal(String name, String email, String num, String pass, String cpass) {
        this.name = name;
        this.email = email;
        this.num = num;
        this.pass = pass;
        this.cpass = cpass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCpass() {
        return cpass;
    }

    public void setCpass(String cpass) {
        this.cpass = cpass;
    }


}
