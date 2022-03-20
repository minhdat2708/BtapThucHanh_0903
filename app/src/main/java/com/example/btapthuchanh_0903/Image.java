package com.example.btapthuchanh_0903;

public class Image {
    private String id_image;
    private String nameImage;
    private int image;

    public Image(String nameImage, int image, String id_image) {
        this.nameImage = nameImage;
        this.image = image;
        this.id_image = id_image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNameImage() {
        return nameImage;
    }

    public void setNameImage(String nameImage) {
        this.nameImage = nameImage;
    }

    public String getId_image() {
        return id_image;
    }

    public void setId_image(String id_image) {
        this.id_image = id_image;
    }
}
