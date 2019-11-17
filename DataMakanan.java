package com.example.kridho.myskripsi.data;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class DataMakanan {

    /**
     * id : 1
     * Nama_Makanan : sayur sop
     * Energi : 27.0
     * Karbohidrat : 1.0
     * Protein : 1.3
     * Lemak : 2.0
     * Berat_Makanan : 100
     * Harga : 10000
     * type : s
     */

    @SerializedName("id")
    private int id;
    @SerializedName("Nama_Makanan")
    private String namaMakanan;
    @SerializedName("Energi")
    private double energi;
    @SerializedName("Karbohidrat")
    private double karbohidrat;
    @SerializedName("Protein")
    private double protein;
    @SerializedName("Lemak")
    private double lemak;
    @SerializedName("Berat_Makanan")
    private int beratMakanan;
    @SerializedName("Harga")
    private int harga;
    @SerializedName("type")
    private String type;

    public static DataMakanan objectFromData(String str) {

        return new Gson().fromJson(str, DataMakanan.class);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaMakanan() {
        return namaMakanan;
    }

    public void setNamaMakanan(String namaMakanan) {
        this.namaMakanan = namaMakanan;
    }

    public double getEnergi() {
        return energi;
    }

    public void setEnergi(double energi) {
        this.energi = energi;
    }

    public double getKarbohidrat() {
        return karbohidrat;
    }

    public void setKarbohidrat(double karbohidrat) {
        this.karbohidrat = karbohidrat;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getLemak() {
        return lemak;
    }

    public void setLemak(double lemak) {
        this.lemak = lemak;
    }

    public int getBeratMakanan() {
        return beratMakanan;
    }

    public void setBeratMakanan(int beratMakanan) {
        this.beratMakanan = beratMakanan;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
