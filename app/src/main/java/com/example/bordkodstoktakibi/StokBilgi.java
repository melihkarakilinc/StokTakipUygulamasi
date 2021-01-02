package com.example.bordkodstoktakibi;

import java.io.Serializable;



public class StokBilgi implements Serializable {


    private String stok_id;
    private String barkod_no;
    private String adet;
    private String stok_adi;

    public StokBilgi() {
    }

    public StokBilgi(String stok_id, String barkod_no, String adet, String stok_adi) {
        this.stok_id = stok_id;
        this.barkod_no = barkod_no;
        this.adet = adet;
        this.stok_adi = stok_adi;
    }

    public String getStok_id() {
        return stok_id;
    }

    public void setStok_id(String stok_id) {
        this.stok_id = stok_id;
    }

    public String getBarkod_no() {
        return barkod_no;
    }

    public void setBarkod_no(String barkod_no) {
        this.barkod_no = barkod_no;
    }

    public String getAdet() {
        return adet;
    }

    public void setAdet(String adet) {
        this.adet = adet;
    }

    public String getStok_adi() {
        return stok_adi;
    }

    public void setStok_adi(String stok_adi) {
        this.stok_adi = stok_adi;
    }
}