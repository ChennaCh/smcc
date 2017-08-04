package com.smcc.application.Bean;

/**
 * Created by chinn on 8/4/2017.
 */

public class AddFacultyBean {

    String username,password,mobile,email,type;

    public AddFacultyBean(String addusername, String addpassword, String addmobile, String addemail, String addtype){
        this.username = addusername;
        this.password = addpassword;
        this.mobile = addmobile;
        this.email = addemail;
        this.type = addtype;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
