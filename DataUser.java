package com.example.kridho.myskripsi.data;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class DataUser {


    /**
     * id_user : 1
     * id_ : 1
     * nama : kridho cokro
     * umur : 50
     * tinggi_badan : 170
     * berat_badan : 80
     * aktifitas : 1.6
     * gender : Pria
     * diet : Empat
     * tanggal_date : 2019-10-06 00:34:46
     */

    @SerializedName("id_user")
    private String idUser;
    @SerializedName("id_")
    private String id;
    @SerializedName("nama")
    private String nama;
    @SerializedName("umur")
    private double umur;
    @SerializedName("tinggi_badan")
    private float tinggiBadan;
    @SerializedName("berat_badan")
    private double beratBadan;
    @SerializedName("aktifitas")
    private double aktifitas;
    @SerializedName("gender")
    private String gender;
    @SerializedName("diet")
    private String diet;
    @SerializedName("tanggal_date")
    private String tanggalDate;

    public static DataUser objectFromData(String str) {

        return new Gson().fromJson(str, DataUser.class);
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public double getUmur() {
        return umur;
    }

    public void setUmur(double umur) {
        this.umur = umur;
    }

    public float getTinggiBadan() {
        return tinggiBadan;
    }

    public void setTinggiBadan(float tinggiBadan) {
        this.tinggiBadan = tinggiBadan;
    }

    public double getBeratBadan() {
        return beratBadan;
    }

    public void setBeratBadan(double beratBadan) {
        this.beratBadan = beratBadan;
    }

    public double getAktifitas() {
        return aktifitas;
    }

    public void setAktifitas(double aktifitas) {
        this.aktifitas = aktifitas;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getTanggalDate() {
        return tanggalDate;
    }

    public void setTanggalDate(String tanggalDate) {
        this.tanggalDate = tanggalDate;
    }
}
