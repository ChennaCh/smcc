package com.smcc.application.Bean;

/**
 * Created by chinn on 8/10/2017.
 */

public class GetFeedbackBean {
    String id;
    String name;
    String mobile;
    String email;
    String branch;
    String msg;

    public GetFeedbackBean(String gid, String gname, String gmobile, String gemail, String gbranch, String gmsg){
        this.id = gid;
        this.name = gname;
        this.mobile = gmobile;
        this.email = gemail;
        this.branch = gbranch;
        this.msg = gmsg;
        this.msg = gmsg;
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

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
