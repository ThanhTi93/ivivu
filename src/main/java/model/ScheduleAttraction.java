/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author titranthanh
 */
public class ScheduleAttraction {
    
    private int id ;
    private int tour_schedule_id ;
    private int attraction_id ;

    public ScheduleAttraction(int id, int tour_schedule_id, int attraction_id) {
        this.id = id;
        this.tour_schedule_id = tour_schedule_id;
        this.attraction_id = attraction_id;
    }

    public ScheduleAttraction(int tour_schedule_id, int attraction_id) {
        this.tour_schedule_id = tour_schedule_id;
        this.attraction_id = attraction_id;
    }

    public ScheduleAttraction() {
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTour_schedule_id() {
        return tour_schedule_id;
    }

    public void setTour_schedule_id(int tour_schedule_id) {
        this.tour_schedule_id = tour_schedule_id;
    }

    public int getAttraction_id() {
        return attraction_id;
    }

    public void setAttraction_id(int attraction_id) {
        this.attraction_id = attraction_id;
    }
    
    
}
