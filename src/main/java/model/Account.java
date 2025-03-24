/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author titranthanh
 */
public class Account {
    private int id ;
    private String email ;
    private String username ;
    private String imgUrl ;
    private String pass ;
    private String phone ;
    private String roler ;
    private String address ;

    public Account(int id, String email, String username, String imgUrl, String pass, String phone, String roler, String address) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.imgUrl = imgUrl;
        this.pass = pass;
        this.phone = phone;
        this.roler = roler;
        this.address = address;
    }

    public Account() {
    }

    public Account(String username, String pass, String email) {
        this.email = email;
        this.username = username;
        this.pass = pass;
    }
    
    
    
}
