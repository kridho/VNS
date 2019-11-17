package com.example.kridho.myskripsi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kridho.myskripsi.R;
import com.example.kridho.myskripsi.data.DataMakananSehari;
import com.example.kridho.myskripsi.data.DataSekaliMakan;

import java.util.ArrayList;
import java.util.List;

public class KomposisiAdapter extends RecyclerView.Adapter<KomposisiAdapter.ViewHolder> {
    List<DataMakananSehari>list = new ArrayList<>();
    Context context;

    public KomposisiAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<DataMakananSehari> tripList) {
        this.list = tripList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view_new, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final DataMakananSehari model = list.get(position);
        holder.pagiK.setText(model.pagi.karbo.getNamaMakanan());
        holder.pagiPH.setText(model.pagi.ph.getNamaMakanan());
        holder.pagiPN.setText(model.pagi.pn.getNamaMakanan());
        holder.pagiS.setText(model.pagi.s.getNamaMakanan());
        holder.pagiL.setText(model.pagi.l.getNamaMakanan());
        holder.pagiHarga.setText(String.valueOf(model.pagi.getTotalHarga()));

        holder.siangK.setText(model.siang.karbo.getNamaMakanan());
        holder.siangPH.setText(model.siang.ph.getNamaMakanan());
        holder.siangPN.setText(model.siang.pn.getNamaMakanan());
        holder.siangS.setText(model.siang.s.getNamaMakanan());
        holder.siangL.setText(model.siang.l.getNamaMakanan());
        holder.siangHarga.setText(String.valueOf(model.siang.getTotalHarga()));

        holder.malamK.setText(model.malam.karbo.getNamaMakanan());
        holder.malamPH.setText(model.malam.ph.getNamaMakanan());
        holder.malamPN.setText(model.malam.pn.getNamaMakanan());
        holder.malamS.setText(model.malam.s.getNamaMakanan());
        holder.malamL.setText(model.malam.l.getNamaMakanan());
        holder.malamHarga.setText(String.valueOf(model.malam.getTotalHarga()));

        holder.totalHarga.setText("Total Harga : " + model.getTotalHarga());
        holder.totalEnergi.setText("Total Energi : " +String.format("%.2f",(model.getTotalEnergi())));
        holder.totalKarbo.setText("Total Karbohidrat : " +String.format("%.2f",(model.getTotalKarbo())));
        holder.totalLemak.setText("Total Lemak : " +String.format("%.2f",(model.getTotalLemak())));
        holder.totalProtein.setText("Total Protein : " +String.format("%.2f",(model.getTotalProtein())));

        //PAGI DESAIN
        holder.pagiKenergi.setText(String.valueOf(model.pagi.karbo.getEnergi()));
        holder.pagiKkarbo.setText(String.valueOf(model.pagi.karbo.getKarbohidrat()));
        holder.pagiKprotein.setText(String.valueOf(model.pagi.karbo.getProtein()));
        holder.pagiKlemak.setText(String.valueOf(model.pagi.karbo.getLemak()));
        holder.pagiKberat.setText(String.valueOf(model.pagi.karbo.getBeratMakanan()));

        holder.pagiPhenergi.setText(String.valueOf(model.pagi.ph.getEnergi()));
        holder.pagiPhkarbo.setText(String.valueOf(model.pagi.ph.getKarbohidrat()));
        holder.pagiPhprotein.setText(String.valueOf(model.pagi.ph.getProtein()));
        holder.pagiPhlemak.setText(String.valueOf(model.pagi.ph.getLemak()));
        holder.pagiPhberat.setText(String.valueOf(model.pagi.ph.getBeratMakanan()));

        holder.pagiPnenergi.setText(String.valueOf(model.pagi.pn.getEnergi()));
        holder.pagiPnkarbo.setText(String.valueOf(model.pagi.pn.getKarbohidrat()));
        holder.pagiPnprotein.setText(String.valueOf(model.pagi.pn.getProtein()));
        holder.pagiPnlemak.setText(String.valueOf(model.pagi.pn.getLemak()));
        holder.pagiPnberat.setText(String.valueOf(model.pagi.pn.getBeratMakanan()));

        holder.pagiSenergi.setText(String.valueOf(model.pagi.s.getEnergi()));
        holder.pagiSkarbo.setText(String.valueOf(model.pagi.s.getKarbohidrat()));
        holder.pagiSprotein.setText(String.valueOf(model.pagi.s.getProtein()));
        holder.pagiSlemak.setText(String.valueOf(model.pagi.s.getLemak()));
        holder.pagiSberat.setText(String.valueOf(model.pagi.s.getBeratMakanan()));

        holder.pagiLenergi.setText(String.valueOf(model.pagi.l.getEnergi()));
        holder.pagiLkarbo.setText(String.valueOf(model.pagi.l.getKarbohidrat()));
        holder.pagiLprotein.setText(String.valueOf(model.pagi.l.getProtein()));
        holder.pagiLlemak.setText(String.valueOf(model.pagi.l.getLemak()));
        holder.pagiLberat.setText(String.valueOf(model.pagi.l.getBeratMakanan()));

        //SIANG DESAIN
        holder.siangKenergi.setText(String.valueOf(model.siang.karbo.getEnergi()));
        holder.siangKkarbo.setText(String.valueOf(model.siang.karbo.getKarbohidrat()));
        holder.siangKprotein.setText(String.valueOf(model.siang.karbo.getProtein()));
        holder.siangKlemak.setText(String.valueOf(model.siang.karbo.getLemak()));
        holder.siangKberat.setText(String.valueOf(model.siang.karbo.getBeratMakanan()));

        holder.siangPhenergi.setText(String.valueOf(model.siang.ph.getEnergi()));
        holder.siangPhkarbo.setText(String.valueOf(model.siang.ph.getKarbohidrat()));
        holder.siangPhprotein.setText(String.valueOf(model.siang.ph.getProtein()));
        holder.siangPhlemak.setText(String.valueOf(model.siang.ph.getLemak()));
        holder.siangPhberat.setText(String.valueOf(model.siang.ph.getBeratMakanan()));

        holder.siangPnenergi.setText(String.valueOf(model.siang.pn.getEnergi()));
        holder.siangPnkarbo.setText(String.valueOf(model.siang.pn.getKarbohidrat()));
        holder.siangPnprotein.setText(String.valueOf(model.siang.pn.getProtein()));
        holder.siangPnlemak.setText(String.valueOf(model.siang.pn.getLemak()));
        holder.siangPnberat.setText(String.valueOf(model.siang.pn.getBeratMakanan()));

        holder.siangSenergi.setText(String.valueOf(model.siang.s.getEnergi()));
        holder.siangSkarbo.setText(String.valueOf(model.siang.s.getKarbohidrat()));
        holder.siangSprotein.setText(String.valueOf(model.siang.s.getProtein()));
        holder.siangSlemak.setText(String.valueOf(model.siang.s.getLemak()));
        holder.siangSberat.setText(String.valueOf(model.siang.s.getBeratMakanan()));

        holder.siangLenergi.setText(String.valueOf(model.siang.l.getEnergi()));
        holder.siangLkarbo.setText(String.valueOf(model.siang.l.getKarbohidrat()));
        holder.siangLprotein.setText(String.valueOf(model.siang.l.getProtein()));
        holder.siangLlemak.setText(String.valueOf(model.siang.l.getLemak()));
        holder.siangLberat.setText(String.valueOf(model.siang.l.getBeratMakanan()));

        //MALAM DESAIN
        holder.malamKenergi.setText(String.valueOf(model.malam.karbo.getEnergi()));
        holder.malamKkarbo.setText(String.valueOf(model.malam.karbo.getKarbohidrat()));
        holder.malamKprotein.setText(String.valueOf(model.malam.karbo.getProtein()));
        holder.malamKlemak.setText(String.valueOf(model.malam.karbo.getLemak()));
        holder.malamKberat.setText(String.valueOf(model.malam.karbo.getBeratMakanan()));

        holder.malamPhenergi.setText(String.valueOf(model.malam.ph.getEnergi()));
        holder.malamPhkarbo.setText(String.valueOf(model.malam.ph.getKarbohidrat()));
        holder.malamPhprotein.setText(String.valueOf(model.malam.ph.getProtein()));
        holder.malamPhlemak.setText(String.valueOf(model.malam.ph.getLemak()));
        holder.malamPhberat.setText(String.valueOf(model.malam.ph.getBeratMakanan()));

        holder.malamPnenergi.setText(String.valueOf(model.malam.pn.getEnergi()));
        holder.malamPnkarbo.setText(String.valueOf(model.malam.pn.getKarbohidrat()));
        holder.malamPnprotein.setText(String.valueOf(model.malam.pn.getProtein()));
        holder.malamPnlemak.setText(String.valueOf(model.malam.pn.getLemak()));
        holder.malamPnberat.setText(String.valueOf(model.malam.pn.getBeratMakanan()));

        holder.malamSenergi.setText(String.valueOf(model.malam.s.getEnergi()));
        holder.malamSkarbo.setText(String.valueOf(model.malam.s.getKarbohidrat()));
        holder.malamSprotein.setText(String.valueOf(model.malam.s.getProtein()));
        holder.malamSlemak.setText(String.valueOf(model.malam.s.getLemak()));
        holder.malamSberat.setText(String.valueOf(model.malam.s.getBeratMakanan()));

        holder.malamLenergi.setText(String.valueOf(model.malam.l.getEnergi()));
        holder.malamLkarbo.setText(String.valueOf(model.malam.l.getKarbohidrat()));
        holder.malamLprotein.setText(String.valueOf(model.malam.l.getProtein()));
        holder.malamLlemak.setText(String.valueOf(model.malam.l.getLemak()));
        holder.malamLberat.setText(String.valueOf(model.malam.l.getBeratMakanan()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView pagiK, pagiPH, pagiPN, pagiS, pagiL, pagiHarga;
        TextView siangK, siangPH, siangPN, siangS, siangL, siangHarga;
        TextView malamK, malamPH, malamPN, malamS, malamL, malamHarga;
        //PAGI
        TextView pagiKenergi, pagiKkarbo, pagiKprotein, pagiKlemak, pagiKberat;
        TextView pagiPhenergi, pagiPhkarbo, pagiPhprotein, pagiPhlemak, pagiPhberat;
        TextView pagiPnenergi, pagiPnkarbo, pagiPnprotein, pagiPnlemak, pagiPnberat;
        TextView pagiSenergi, pagiSkarbo, pagiSprotein, pagiSlemak, pagiSberat;
        TextView pagiLenergi, pagiLkarbo, pagiLprotein, pagiLlemak, pagiLberat;

        //SIANG
        TextView siangKenergi, siangKkarbo, siangKprotein, siangKlemak, siangKberat;
        TextView siangPhenergi, siangPhkarbo, siangPhprotein, siangPhlemak, siangPhberat;
        TextView siangPnenergi, siangPnkarbo, siangPnprotein, siangPnlemak, siangPnberat;
        TextView siangSenergi, siangSkarbo, siangSprotein, siangSlemak, siangSberat;
        TextView siangLenergi, siangLkarbo, siangLprotein, siangLlemak, siangLberat;

        //MALAM
        TextView malamKenergi, malamKkarbo, malamKprotein, malamKlemak, malamKberat;
        TextView malamPhenergi, malamPhkarbo, malamPhprotein, malamPhlemak, malamPhberat;
        TextView malamPnenergi, malamPnkarbo, malamPnprotein, malamPnlemak, malamPnberat;
        TextView malamSenergi, malamSkarbo, malamSprotein, malamSlemak, malamSberat;
        TextView malamLenergi, malamLkarbo, malamLprotein, malamLlemak, malamLberat;
        TextView totalHarga;
        TextView totalEnergi, totalKarbo, totalLemak, totalProtein;
        ViewHolder(View view){
            super(view);
            pagiK = view.findViewById(R.id.pagi_k);
            pagiPH = view.findViewById(R.id.pagi_ph);
            pagiPN = view.findViewById(R.id.pagi_pn);
            pagiS = view.findViewById(R.id.pagi_s);
            pagiL = view.findViewById(R.id.pagi_l);
            pagiHarga = view.findViewById(R.id.pagi_harga);

            siangK = view.findViewById(R.id.siang_k);
            siangPH = view.findViewById(R.id.siang_ph);
            siangPN = view.findViewById(R.id.siang_pn);
            siangS = view.findViewById(R.id.siang_s);
            siangL = view.findViewById(R.id.siang_l);
            siangHarga = view.findViewById(R.id.siang_harga);

            malamK = view.findViewById(R.id.malam_k);
            malamPH = view.findViewById(R.id.malam_ph);
            malamPN = view.findViewById(R.id.malam_pn);
            malamS = view.findViewById(R.id.malam_s);
            malamL = view.findViewById(R.id.malam_l);
            malamHarga = view.findViewById(R.id.malam_harga);

            totalHarga = view.findViewById(R.id.total_harga);
            totalEnergi = view.findViewById(R.id.total_energi);
            totalKarbo = view.findViewById(R.id.total_karbo);
            totalLemak = view.findViewById(R.id.total_lemak);
            totalProtein = view.findViewById(R.id.total_protein);

            //Kadar Gizi Karbo
            pagiKenergi = view.findViewById(R.id.pagi_kEnergi);
            pagiKkarbo = view.findViewById(R.id.pagi_Kkarbo);
            pagiKprotein = view.findViewById(R.id.pagi_Kprotein);
            pagiKlemak = view.findViewById(R.id.pagi_Klemak);
            pagiKberat = view.findViewById(R.id.pagi_KBerat);

            //Kadar Gizi Protein Hewani

            pagiPhenergi = view.findViewById(R.id.pagi_Phenergi);
            pagiPhkarbo = view.findViewById(R.id.pagi_Phkarbo);
            pagiPhprotein = view.findViewById(R.id.pagi_Phprotein);
            pagiPhlemak = view.findViewById(R.id.pagi_Phlemak);
            pagiPhberat = view.findViewById(R.id.pagi_PhBerat);

            //kadar gizi nabati
            pagiPnenergi = view.findViewById(R.id.pagi_Pnenergi);
            pagiPnkarbo = view.findViewById(R.id.pagi_Pnkarbo);
            pagiPnprotein = view.findViewById(R.id.pagi_Pnprotein);
            pagiPnlemak = view.findViewById(R.id.pagi_Pnlemak);
            pagiPnberat = view.findViewById(R.id.pagi_PnBerat);

            //Kadar Gizi  Sayur

            pagiSenergi = view.findViewById(R.id.pagi_Senergi);
            pagiSkarbo = view.findViewById(R.id.pagi_Skarbo);
            pagiSprotein = view.findViewById(R.id.pagi_Sprotein);
            pagiSlemak = view.findViewById(R.id.pagi_Slemak);
            pagiSberat = view.findViewById(R.id.pagi_SBerat);

            //Kada Gizi Lainya
            pagiLenergi = view.findViewById(R.id.pagi_Lenergi);
            pagiLkarbo = view.findViewById(R.id.pagi_Lkarbo);
            pagiLprotein = view.findViewById(R.id.pagi_Lprotein);
            pagiLlemak = view.findViewById(R.id.pagi_Llemak);
            pagiLberat = view.findViewById(R.id.pagi_LBerat);

            //SIANG VIEWBND
            //Kadar Gizi Karbo
            siangKenergi = view.findViewById(R.id.siang_kEnergi);
            siangKkarbo = view.findViewById(R.id.siang_Kkarbo);
            siangKprotein = view.findViewById(R.id.siang_Kprotein);
            siangKlemak = view.findViewById(R.id.siang_Klemak);
            siangKberat = view.findViewById(R.id.siang_KBerat);

            //Kadar Gizi Protein Hewani

            siangPhenergi = view.findViewById(R.id.siang_Phenergi);
            siangPhkarbo = view.findViewById(R.id.siang_Phkarbo);
            siangPhprotein = view.findViewById(R.id.siang_Phprotein);
            siangPhlemak = view.findViewById(R.id.siang_Phlemak);
            siangPhberat = view.findViewById(R.id.siang_PhBerat);

            //kadar gizi nabati
            siangPnenergi = view.findViewById(R.id.siang_Pnenergi);
            siangPnkarbo = view.findViewById(R.id.siang_Pnkarbo);
            siangPnprotein = view.findViewById(R.id.siang_Pnprotein);
            siangPnlemak = view.findViewById(R.id.siang_Pnlemak);
            siangPnberat = view.findViewById(R.id.siang_PnBerat);

            //Kadar Gizi  Sayur

            siangSenergi = view.findViewById(R.id.siang_Senergi);
            siangSkarbo = view.findViewById(R.id.siang_Skarbo);
            siangSprotein = view.findViewById(R.id.siang_Sprotein);
            siangSlemak = view.findViewById(R.id.siang_Slemak);
            siangSberat = view.findViewById(R.id.siang_SBerat);

            //Kada Gizi Lainya
            siangLenergi = view.findViewById(R.id.siang_Lenergi);
            siangLkarbo = view.findViewById(R.id.siang_Lkarbo);
            siangLprotein = view.findViewById(R.id.siang_Lprotein);
            siangLlemak = view.findViewById(R.id.siang_Llemak);
            siangLberat = view.findViewById(R.id.siang_LBerat);

            //MALAM VIEWBND
            //Kadar Gizi Karbo
            malamKenergi = view.findViewById(R.id.malam_kEnergi);
            malamKkarbo = view.findViewById(R.id.malam_Kkarbo);
            malamKprotein = view.findViewById(R.id.malam_Kprotein);
            malamKlemak = view.findViewById(R.id.malam_Klemak);
            malamKberat = view.findViewById(R.id.malam_KBerat);

            //Kadar Gizi Protein Hewani

            malamPhenergi = view.findViewById(R.id.malam_Phenergi);
            malamPhkarbo = view.findViewById(R.id.malam_Phkarbo);
            malamPhprotein = view.findViewById(R.id.malam_Phprotein);
            malamPhlemak = view.findViewById(R.id.malam_Phlemak);
            malamPhberat = view.findViewById(R.id.malam_PhBerat);

            //kadar gizi nabati
            malamPnenergi = view.findViewById(R.id.malam_Pnenergi);
            malamPnkarbo = view.findViewById(R.id.malam_Pnkarbo);
            malamPnprotein = view.findViewById(R.id.malam_Pnprotein);
            malamPnlemak = view.findViewById(R.id.malam_Pnlemak);
            malamPnberat = view.findViewById(R.id.malam_PnBerat);

            //Kadar Gizi  Sayur

            malamSenergi = view.findViewById(R.id.malam_Senergi);
            malamSkarbo = view.findViewById(R.id.malam_Skarbo);
            malamSprotein = view.findViewById(R.id.malam_Sprotein);
            malamSlemak = view.findViewById(R.id.malam_Slemak);
            malamSberat = view.findViewById(R.id.malam_SBerat);

            //Kada Gizi Lainya
            malamLenergi = view.findViewById(R.id.malam_Lenergi);
            malamLkarbo = view.findViewById(R.id.malam_Lkarbo);
            malamLprotein = view.findViewById(R.id.malam_Lprotein);
            malamLlemak = view.findViewById(R.id.malam_Llemak);
            malamLberat = view.findViewById(R.id.malam_LBerat);
        }
    }

}


