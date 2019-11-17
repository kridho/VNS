package com.example.kridho.myskripsi.data;

public class DataSekaliMakan {
    public DataMakanan karbo ;
    public DataMakanan ph ;
    public DataMakanan pn ;
    public DataMakanan s ;
    public DataMakanan l ;

    public DataSekaliMakan(DataMakanan karbo, DataMakanan ph, DataMakanan pn, DataMakanan s, DataMakanan l) {
        this.karbo = karbo;
        this.ph = ph;
        this.pn = pn;
        this.s = s;
        this.l = l;
    }

    public int getTotalHarga() {
        return karbo.getHarga()
            + ph.getHarga()
            + pn.getHarga()
            + s.getHarga()
            + l.getHarga();
    }

    public double getTotalEnergi(){
        return karbo.getEnergi()
                + ph.getEnergi()
                + pn.getEnergi()
                + s.getEnergi()
                + l.getEnergi();
    }

    public double getTotalKarbo(){
        return karbo.getKarbohidrat()
                + ph.getKarbohidrat()
                + pn.getKarbohidrat()
                + s.getKarbohidrat()
                + l.getKarbohidrat();
    }

    public double getTotalLemak(){
        return karbo.getLemak()
                + ph.getLemak()
                + pn.getLemak()
                + s.getLemak()
                + l.getLemak();
    }

    public double getTotalprotein(){
        return karbo.getProtein()
                + ph.getProtein()
                + pn.getProtein()
                + s.getProtein()
                + l.getProtein();
    }

    public DataMakanan getRandomMakanan(int attrIdx) {
        switch (attrIdx) {
            case 1:
                return karbo;
            case 2:
                return ph;
            case 3:
                return pn;
            case 4:
                return s;
            default:
                return l;
        }
    }

    public void setRandomMakanan(int attrIdx, DataMakanan dataMakanan) {
        switch (attrIdx) {
            case 1:
                karbo = dataMakanan;
            case 2:
                ph = dataMakanan;
            case 3:
                pn = dataMakanan;
            case 4:
                s = dataMakanan;
            default:
                l = dataMakanan;
        }
    }
}
