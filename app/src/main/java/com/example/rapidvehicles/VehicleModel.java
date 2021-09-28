package com.example.rapidvehicles;

public class VehicleModel {

    String modal,type,plate,vurl, passenger, price;

    VehicleModel(){

    }

    public VehicleModel(String modal, String type, String plate, String vurl, String price, String passenger) {
        this.modal = modal;
        this.type = type;
        this.plate = plate;
        this.vurl = vurl;
        this.price = price;
        this.passenger = passenger;
    }

    public String getModal() {
        return modal;
    }

    public void setModal(String modal) {
        this.modal = modal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getVurl() {
        return vurl;
    }

    public void setVurl(String vurl) {
        this.vurl = vurl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPassenger() {
        return passenger;
    }

    public void setPassenger(String passenger) {
        this.passenger = passenger;
    }

}


