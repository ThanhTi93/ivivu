/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author titranthanh
 */
public class Attraction {
    
    private int id;
    private String imgUrl;
    private String name;
    private String description;

    public Attraction(int id, String imgUrl, String name, String description) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.name = name;
        this.description = description;
    }

    public Attraction(String imgUrl, String name, String description) {
        this.imgUrl = imgUrl;
        this.name = name;
        this.description = description;
    }
  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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
    
    public String getShortDescription() {
        return (description != null && description.length() > 30) 
            ? description.substring(0, 30) + "..." 
            : description;
    }
}
