package com.example.btapthuchanh_0903;

public class Devices {
    private int id;
    private String name;
    private String description;
    private int image;
    boolean status;

    public Devices(int id, String name, String description, int image, boolean status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
