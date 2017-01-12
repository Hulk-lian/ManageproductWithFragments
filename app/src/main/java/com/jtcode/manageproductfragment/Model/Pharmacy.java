package com.jtcode.manageproductfragment.Model;


public class Pharmacy {
    int id;
    String cif;
    String adress;
    String phone;
    String email;

    public Pharmacy(int id, String cif, String adress, String phone, String email) {
        this.id = id;
        this.cif = cif;
        this.adress = adress;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
