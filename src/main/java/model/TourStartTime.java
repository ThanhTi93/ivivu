/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;


/**
 *
 * @author titranthanh
 */
public class TourStartTime {

    private int id;
    private LocalDate dateStart;
    private int id_tour ;
    private int price;
    private int quantity;
    private int discount;
    private boolean status;   

    public TourStartTime(int id, LocalDate dateStart, int id_tour, int price, int quantity, int discount, boolean status) {
        this.id = id;
        this.dateStart = dateStart;
        this.id_tour = id_tour;
        this.price = price;
        this.quantity = quantity;
        this.discount = discount;
        this.status = status;
    }

    public TourStartTime(LocalDate dateStart, int id_tour, int price, int quantity, int discount, boolean status) {
        this.dateStart = dateStart;
        this.id_tour = id_tour;
        this.price = price;
        this.quantity = quantity;
        this.discount = discount;
        this.status = status;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

  

    public int getId_tour() {
        return id_tour;
    }

    public void setId_tour(int id_tour) {
        this.id_tour = id_tour;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
