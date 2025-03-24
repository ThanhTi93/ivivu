package model;

/**
 *
 * @author titranthanh
 */
public class Location {

    private int id;
    private String imgUrl;
    private String name;
    private String description;
    private int category_id ;
    private boolean status;

    public Location(int id, String imgUrl, String name, String description, int category_id, boolean status) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.name = name;
        this.description = description;
        this.category_id = category_id;
        this.status = status;
    }

    public Location(String imgUrl, String name, String description, int category_id, boolean status) {
        this.imgUrl = imgUrl;
        this.name = name;
        this.description = description;
        this.category_id = category_id;
        this.status = status;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
  
    public Location() {
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

       // Thêm hàm rút gọn mô tả (30 ký tự)
    public String getShortDescription() {
        return (description != null && description.length() > 30) 
            ? description.substring(0, 30) + "..." 
            : description;
    }
}
