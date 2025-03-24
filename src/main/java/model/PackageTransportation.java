/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author titranthanh
 */
public class PackageTransportation {
    
    private int id ;
    private int idTourPackages ;
    private int idTourTransportation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTourPackages() {
        return idTourPackages;
    }

    public void setIdTourPackages(int idTourPackages) {
        this.idTourPackages = idTourPackages;
    }

    public int getIdTourTransportation() {
        return idTourTransportation;
    }

    public void setIdTourTransportation(int idTourTransportation) {
        this.idTourTransportation = idTourTransportation;
    }

    public PackageTransportation(int id, int idTourPackages, int idTourTransportation) {
        this.id = id;
        this.idTourPackages = idTourPackages;
        this.idTourTransportation = idTourTransportation;
    }

    public PackageTransportation(int idTourPackages, int idTourTransportation) {
        this.idTourPackages = idTourPackages;
        this.idTourTransportation = idTourTransportation;
    }
    
    
}
