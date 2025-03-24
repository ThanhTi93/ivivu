/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author titranthanh
 */
public class TourSchedule {
    
    private int id ;
    private String name ;
    private int dayTour ;
    private String description ;
    private int id_tour ;
    private boolean status;
    private List<Attraction> attractions;
    
    public TourSchedule(int id, String name, int dayTour, String description, int id_tour,boolean status) {
        this.id = id;
        this.name = name;
        this.dayTour = dayTour;
        this.description = description;
        this.id_tour = id_tour;
        this.status = status;
    }

    public TourSchedule(String name, int dayTour, String description, int id_tour,boolean status) {
        this.name = name;
        this.dayTour = dayTour;
        this.description = description;
        this.id_tour = id_tour;
        this.status = status;
    }
   // Constructor đầy đủ có danh sách attractions
    public TourSchedule(int id, String name, int dayTour, String description, int id_tour, boolean status, List<Attraction> attractions) {
        this.id = id;
        this.name = name;
        this.dayTour = dayTour;
        this.description = description;
        this.id_tour = id_tour;
        this.status = status;
        this.attractions = attractions;
    }
    
     // Getter và Setter cho danh sách attractions
    public List<Attraction> getAttractions() {
        return attractions;
    }

    public void setAttractions(List<Attraction> attractions) {
        this.attractions = attractions;
    }
    
    public int getId_tour() {
        return id_tour;
    }

    public void setId_tour(int id_tour) {
        this.id_tour = id_tour;
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

    public int getDayTour() {
        return dayTour;
    }

    public void setDayTour(int dayTour) {
        this.dayTour = dayTour;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

     
           
}
