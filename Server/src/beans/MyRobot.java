/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;

/**
 *
 * @author RaposoESilvaCarlosJose
 */
public class MyRobot implements Serializable {

    private String ip;
    private String hostname;
    private int id;
    private int pw;

    public MyRobot(String ip, int id, int pw) {
        this.ip = ip;
        this.id = id;
        this.pw = pw;
    }

    public MyRobot() {
        ip = "0.0.0.0";
        id = 0;
        pw = 0;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPw() {
        return pw;
    }

    public void setPw(int pw) {
        this.pw = pw;
    }

    @Override
    public String toString(){
        return hostname + " @" + ip + " [id: " + id + ", pw: " + pw + "]";
    }



}
