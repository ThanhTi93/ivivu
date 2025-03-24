/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author titranthanh
 */
public class TourService {
    
      private int id ;
      private String name ;
      private String description ;
      private boolean status;
      
    public TourService(int id, String name, String description ,boolean status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public TourService(String name, String description,boolean status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
       
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    
     // Thêm hàm rút gọn mô tả (30 ký tự)
    public String getShortDescription() {
        return (description != null && description.length() > 30) 
            ? description.substring(0, 30) + "..." 
            : description;
    }
}
