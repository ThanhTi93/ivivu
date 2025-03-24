/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author titranthanh
 */
public class TourPackages {
    
    private int id;
    private String name;
    private String description;   
    private int location_id ;

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

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public TourPackages(int id, String name, String description, int location_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location_id = location_id;
    }
    
      public TourPackages( String name, String description, int location_id) {
        this.name = name;
        this.description = description;
        this.location_id = location_id;
    }
    
}
