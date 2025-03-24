/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author titranthanh
 */
public class PackageService {
    private int id ;
    private int id_tour_packages ;
    private int id_tour_service ;

    public PackageService(int id, int id_tour_packages, int id_tour_service) {
        this.id = id;
        this.id_tour_packages = id_tour_packages;
        this.id_tour_service = id_tour_service;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_tour_packages() {
        return id_tour_packages;
    }

    public void setId_tour_packages(int id_tour_packages) {
        this.id_tour_packages = id_tour_packages;
    }

    public int getId_tour_service() {
        return id_tour_service;
    }

    public void setId_tour_service(int id_tour_service) {
        this.id_tour_service = id_tour_service;
    }
    
    
}
