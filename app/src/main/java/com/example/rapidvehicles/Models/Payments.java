package com.example.rapidvehicles.Models;

public class Payments {

    private String cardnumber;
    private String cardonname;
    private String cvv;
    private String edate; //expiredate
    private String total;
    private String clocation; //current location
    private String pdate; // paymentdate
    private String vtype;//vehicle type
    private String num_rent_day;
    private String locationstatus; //radiobtns in the homescreen
    private String drivestatus;

    public Payments() {
    }

    public Payments(String cardnumber, String cardonname, String cvv, String edate, String total, String clocation, String pdate, String vtype, String num_rent_day, String locationstatus, String drivestatus) {
        this.cardnumber = cardnumber;
        this.cardonname = cardonname;
        this.cvv = cvv;
        this.edate = edate;
        this.total = total;
        this.clocation = clocation;
        this.pdate = pdate;
        this.vtype = vtype;
        this.num_rent_day = num_rent_day;
        this.locationstatus = locationstatus;
        this.drivestatus = drivestatus;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public String getTotal() {
        return total;
    }

    public String getNum_rent_day() {
        return num_rent_day;
    }

    public String getCardonname() {
        return cardonname;
    }

    public String getCvv() {
        return cvv;
    }

    public String getEdate() {
        return edate;
    }



    public String getClocation() {
        return clocation;
    }

    public String getPdate() {
        return pdate;
    }

    public String getVtype() {
        return vtype;
    }



    public String getLocationstatus() {
        return locationstatus;
    }

    public String getDrivestatus() {
        return drivestatus;
    }
}
