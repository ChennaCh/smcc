package com.smcc.application.Bean;

/**
 * Created by chinn on 8/3/2017.
 */

public class AdminBean {

    private String name;
    private String password;
    private String udate;

    public AdminBean(String aname,String apassword,String adate){
        this.name = aname;
        this.password = apassword;
        this.udate = adate;
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

    public String getUdate() {
        return udate;
    }

    public void setUdate(String udate) {
        this.udate = udate;
    }

}
