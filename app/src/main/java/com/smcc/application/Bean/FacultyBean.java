package com.smcc.application.Bean;

/**
 * Created by admin on 8/4/2017.
 */

public class FacultyBean {
   //id":"1","name":"sateesh","password":"sateesh","mobile":"9010990285","email":"satee107@gmail.com","branch":"cse"
   String id;
    String name;
    String password;
    Integer mobile;
    String email;
    String branch;

    public FacultyBean(String fid, String fname, String fpassword, Integer fmobile, String femail, String fbranch) {
        this.id = fid;
        this.name = fname;
        this.password = fpassword;
        this.mobile = fmobile;
        this.email = femail;
        this.branch = fbranch;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getMobile() {
        return mobile;
    }

    public void setMobile(Integer mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }


}
