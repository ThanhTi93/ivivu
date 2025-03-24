/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author titranthanh
 */
public class TourTransportation {
    
      private int id ;
      private String name ;
      private String description ;
      private String icon ;
      private boolean status;
      
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public TourTransportation(int id, String name, String description, String icon,boolean status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.status = status;
    }

    public TourTransportation(String name, String description, String icon,boolean status) {
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.status = status;
    }
      
      
}
