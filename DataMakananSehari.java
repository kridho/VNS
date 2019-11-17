package com.example.kridho.myskripsi.data;

import android.util.Log;

import java.util.Random;

public class DataMakananSehari {
    public DataSekaliMakan pagi;
    public DataSekaliMakan siang;
    public DataSekaliMakan malam;
    public double fitness = 0;
    public double pinalty = 0;
    public DataMakananSehari(DataSekaliMakan pagi, DataSekaliMakan siang, DataSekaliMakan malam) {
        this.pagi = pagi;
        this.siang = siang;
        this.malam = malam;
    }

    public int getTotalHarga(){
        return  pagi.getTotalHarga() + siang.getTotalHarga() + malam.getTotalHarga();
    }

    public  double getTotalEnergi(){
        return  pagi.getTotalEnergi() + siang.getTotalEnergi() + malam.getTotalEnergi();
    }

    public  double getTotalKarbo(){
        return  pagi.getTotalKarbo() + siang.getTotalKarbo() + malam.getTotalEnergi();
    }

    public  double getTotalLemak(){
        return  pagi.getTotalLemak() + siang.getTotalLemak() + malam.getTotalLemak();
    }

    public  double getTotalProtein(){
        return  pagi.getTotalprotein() + siang.getTotalprotein() + malam.getTotalprotein();
    }

    public void setFitness (double kebutuhanEnergi, double kebutuhanKarbo, double kebutuhanLemak, double kebutuhanProtein){
      double pinaltiEnergi = Math.abs(kebutuhanEnergi-getTotalEnergi());
      double pinatiKarbo = Math.abs(kebutuhanKarbo -getTotalKarbo());
      double pinaltiLemak = Math.abs(kebutuhanLemak - getTotalLemak());
      double pinaltiProtein = Math.abs(kebutuhanProtein - getTotalProtein());
      double totalPinalti = pinaltiEnergi + pinatiKarbo + pinaltiLemak + pinaltiProtein ;

      pinalty = totalPinalti;
      fitness = 10000/(totalPinalti+getTotalHarga());
    }

    public DataSekaliMakan getRandomSekaliMakan() {
        int random = new Random().nextInt(2);
        Log.d("randomSekaliMakan", String.valueOf(random));
        switch (random) {
            case 0:
                return pagi;
            case 1:
                return siang;
            default:
                return malam;
        }
    }

    public DataMakanan getRandomMakan(int randomPagiSiangMalam, int attribute) {
        switch (randomPagiSiangMalam) {
            case 1:
                return pagi.getRandomMakanan(attribute);
            case 2:
                return siang.getRandomMakanan(attribute);
            default:
                return malam.getRandomMakanan(attribute);
        }
    }

    public void setRandomMakanan(int randomPagiSiangMalam, int attribute, DataMakanan dataMakanan) {
        switch (randomPagiSiangMalam) {
            case 1:
                pagi.setRandomMakanan(attribute, dataMakanan);
            case 2:
                siang.setRandomMakanan(attribute, dataMakanan);
            default:
                malam.setRandomMakanan(attribute, dataMakanan);
        }
    }
}
