/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

/**
 *
 * @author Joseph Ramírez
 */
public class User {
    private String user="";
    private String password="";
    private String userPDC="";
    private String pwdPDC="";
    private String pwdPDCIE="";

    public User(String user,String password,String userPDC,String pwdPDC,String pwdPDCIE) {
        this.user=user;
        this.password=password;
        this.userPDC=userPDC;
        this.pwdPDC=pwdPDC;
        this.pwdPDCIE=pwdPDCIE;
    }

    
    
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserPDC() {
        return userPDC;
    }

    public void setUserPDC(String userPDC) {
        this.userPDC = userPDC;
    }

    public String getPwdPDC() {
        return pwdPDC;
    }

    public void setPwdPDC(String pwdPDC) {
        this.pwdPDC = pwdPDC;
    }

    public String getPwdPDCIE() {
        return pwdPDCIE;
    }

    public void setPwdPDCIE(String pwdPDCIE) {
        this.pwdPDCIE = pwdPDCIE;
    }

    @Override
    public String toString() {
        return "User{" + "user=" + user + ", password=" + password + ", userPDC=" + userPDC + ", pwdPDC=" + pwdPDC + ", pwdPDCIE=" + pwdPDCIE + '}';
    }
    
    
    
}
