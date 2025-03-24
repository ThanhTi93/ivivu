/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author titranthanh
 */
public class TourImg {
    
    private int id ;
    private int idTourPackages;
    private String imgUrl ;

    public TourImg(int id, int idTourPackages, String imgUrl) {
        this.id = id;
        this.idTourPackages = idTourPackages;
        this.imgUrl = imgUrl;
    }

    public TourImg(int idTourPackages, String imgUrl) {
        this.idTourPackages = idTourPackages;
        this.imgUrl = imgUrl;
    }
  
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    
    
}
