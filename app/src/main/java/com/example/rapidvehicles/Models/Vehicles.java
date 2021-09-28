package com.example.rapidvehicles.Models;

public class Vehicles {

    private int id;
    private int image;
    private String vname;
    private String ratings;
    private String passangers;
    private float price;


    public Vehicles() {
    }

    public Vehicles(int id,int image, String vname, String ratings, String passangers,float price) {
        this.id = id;
        this.image = image;
        this.vname = vname;
        this.ratings = ratings;
        this.passangers = passangers;
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public String getVname() {
        return vname;
    }

    public String getRatings() {
        return ratings;
    }

    public String getPassangers() {
        return passangers;
    }

    public int getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }
}
