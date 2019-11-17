package com.example.kridho.myskripsi;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kridho.myskripsi.Utils.Constant;
import com.example.kridho.myskripsi.adapter.KomposisiAdapter;
import com.example.kridho.myskripsi.data.DataMakanan;
import com.example.kridho.myskripsi.data.DataMakananSehari;
import com.example.kridho.myskripsi.data.DataSekaliMakan;
import com.example.kridho.myskripsi.data.DataUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KomposisiActivity extends AppCompatActivity {
    List<DataMakanan>listKarbo = new ArrayList<>();
    List<DataMakanan>listPH = new ArrayList<>();
    List<DataMakanan>listPN = new ArrayList<>();
    List<DataMakanan>listS = new ArrayList<>();
    List<DataMakanan>listL  = new ArrayList<>();

    private double protein, lemak, karbohidrat, energi;
    private int iterasi,kmax;
    private String status;
    TextView harga;

    RecyclerView recyclerView;
    KomposisiAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_komposisi);
        recyclerView = findViewById(R.id.rv_listKomposisi);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new KomposisiAdapter(this);
        recyclerView.setAdapter(adapter);

        protein = getIntent().getDoubleExtra("protein",protein);
        karbohidrat = getIntent().getDoubleExtra("karbohidrat", karbohidrat);
        lemak = getIntent().getDoubleExtra("lemak",lemak);
        energi = getIntent().getDoubleExtra("energi",energi);
        iterasi = getIntent().getIntExtra("iterasi", iterasi);
        kmax = getIntent().getIntExtra("kmax", kmax);
        status = getIntent().getStringExtra("status");
        harga = findViewById(R.id.tv_harga);
        Log.e("protein",String.valueOf(protein));
        Log.e("Karbohidrat",String.valueOf(karbohidrat));
        Log.e("lemak",String.valueOf(lemak));
        Log.e("energi",String.valueOf(energi));
        Log.e("iterasi",String.valueOf(iterasi));
        Log.e("kmax",String.valueOf(kmax));
        Log.e("status",String.valueOf(status));
        getData();
    }

    void mappingData(List<DataMakanan> list) {
        listKarbo.clear();
        listPH.clear();
        listPN.clear();
        listS.clear();
        listL.clear();
        for (DataMakanan dataMakanan : list) {
            switch (dataMakanan.getType()) {
                case "k":
                    listKarbo.add(dataMakanan);
                    break;
                case "ph":
                    listPH.add(dataMakanan);
                    break;
                case "pn":
                    listPN.add(dataMakanan);
                    break;
                case "s":
                    listS.add(dataMakanan);
                    break;
                case "l":
                    listL.add(dataMakanan);
                    break;
            }
        }
    }

    int getRandom(int max) {
        final int min = 0;
        return new Random().nextInt((max - min)) + min;
    }

//    void randomMakanan() {
//        List<DataMakananSehari> makananSeminggu = new ArrayList<>();
//        for (int i = 0; i < 7; i++) {
//            DataMakananSehari makananSehari = getLocalSearch();
//            makananSeminggu.add(makananSehari);
//        }
//        adapter.setData(makananSeminggu);
//    }

    void start() {
        List<DataMakananSehari> makananSeminggu = new ArrayList<>();
        long totalSeminggu = 0;

        for (int i = 0; i < 7; i ++) {
            DataMakananSehari paten = getMakanSehariTerpaten();
            totalSeminggu += paten.getTotalHarga();
            makananSeminggu.add(paten);
        }
        Log.e("TOTAL HARGA SEMINGGGU", String.valueOf(totalSeminggu));
        if(totalSeminggu > 500000){
            start();
        }
        adapter.setData(makananSeminggu);
        harga.setText(String.valueOf(totalSeminggu));
    }

    DataMakananSehari getMakanSehariTerpaten() {
        DataMakananSehari initAwal = getInitAwal();
        List<DataMakananSehari> nilaiTetangga = new ArrayList<>();

        for (int i = 0; i < kmax; i ++) {
            if (nilaiTetangga.size() > 1) {
//                nilaiTetangga = getShaking(nilaiTetangga);
            }
            DataMakananSehari localSearch = getLocalSearch();
            DataMakananSehari solusi = getMoveOrNot(initAwal, localSearch);
            nilaiTetangga.add(solusi);
        }
        return getFitnesGede(nilaiTetangga);
    }

    DataMakananSehari getFitnesGede(List<DataMakananSehari> nilaiTetangga) {
        DataMakananSehari fitnessGede = nilaiTetangga.get(0);
        for (int i = 1; i < nilaiTetangga.size(); i++) {
            if (nilaiTetangga.get(i).fitness > fitnessGede.fitness) {
                fitnessGede = nilaiTetangga.get(i);
            }
        }
        return fitnessGede;
    }

    DataMakananSehari getInitAwal() {
        return getMakananSehari();
    }

    int getRandomTetanggaIdx(int tetanggaPertama, int totalTetangga) {
        int tetanggaRandom = getRandom(totalTetangga);
        if (tetanggaPertama == tetanggaRandom) {
            return getRandomTetanggaIdx(tetanggaPertama, totalTetangga);
        }
        return tetanggaRandom;
    }

    List<DataMakananSehari> getShaking(List<DataMakananSehari> nilaiTetangga) {
        int randAttribute = getRandom(5);
        int totalTetangga = nilaiTetangga.size();
        int tetanggaA = getRandom(totalTetangga);
        int tetanggaB = getRandomTetanggaIdx(tetanggaA, totalTetangga);
        int randomPagiSiangMalamA = new Random().nextInt(2);
        int randomPagiSiangMalamB = new Random().nextInt(2);
        DataMakanan tempMakananA = nilaiTetangga.get(tetanggaA).getRandomMakan(randomPagiSiangMalamA, randAttribute);
        DataMakanan tempMakananB = nilaiTetangga.get(tetanggaB).getRandomMakan(randomPagiSiangMalamB, randAttribute);
        nilaiTetangga.get(tetanggaA).setRandomMakanan(randomPagiSiangMalamA, randAttribute, tempMakananB);
        nilaiTetangga.get(tetanggaB).setRandomMakanan(randomPagiSiangMalamB, randAttribute, tempMakananA);

        return nilaiTetangga;
    }

    DataMakananSehari getLocalSearch() {
        DataMakananSehari terbaik = getMakananSehari();
        for (int i = 0; i < iterasi; i++) {
            DataMakananSehari ygSekarang = getMakananSehari();
            if (ygSekarang.fitness > terbaik.fitness) {
                terbaik = ygSekarang;
                Log.e("iterasi","TERBAIK");
            }
            Log.e("iterasi",String.valueOf(ygSekarang.fitness));
        }
        return terbaik;
    }

    DataMakananSehari getMoveOrNot(DataMakananSehari solusiAwal, DataMakananSehari localSearch){
        if (localSearch.fitness > solusiAwal.fitness) {
            return localSearch;
        } else {
            return solusiAwal;
        }
    }

    DataMakananSehari getMakananSehari() {
        DataSekaliMakan makanPagi = new DataSekaliMakan(
                listKarbo.get(getRandom(listKarbo.size())),
                listPH.get(getRandom(listPH.size())),
                listPN.get(getRandom(listPN.size())),
                listS.get(getRandom(listS.size())),
                listL.get(getRandom(listL.size())));

        DataSekaliMakan makanSiang = new DataSekaliMakan(
                listKarbo.get(getRandom(listKarbo.size())),
                listPH.get(getRandom(listPH.size())),
                listPN.get(getRandom(listPN.size())),
                listS.get(getRandom(listS.size())),
                listL.get(getRandom(listL.size())));

        DataSekaliMakan makanMalam = new DataSekaliMakan(
                listKarbo.get(getRandom(listKarbo.size())),
                listPH.get(getRandom(listPH.size())),
                listPN.get(getRandom(listPN.size())),
                listS.get(getRandom(listS.size())),
                listL.get(getRandom(listL.size())));

        DataMakananSehari makananSehari = new DataMakananSehari(makanPagi, makanSiang, makanMalam);
        makananSehari.setFitness(energi,karbohidrat,lemak,protein);

        int totalHarga = makananSehari.getTotalHarga();
        double selisihEnergi = Math.abs(makananSehari.getTotalEnergi() - energi);
        double selisihKarbohidrat = Math.abs(makananSehari.getTotalKarbo() - karbohidrat);
        double selisihLemak = Math.abs(makananSehari.getTotalLemak() - lemak);
        double selisihProtein = Math.abs(makananSehari.getTotalProtein() - protein);
        if (totalHarga < 100000 && (selisihEnergi < 250  && selisihKarbohidrat < 300 && selisihLemak < 50 && selisihProtein < 100)) {
            Log.e ("selisi energi", String.valueOf(selisihEnergi));
            Log.e ("selisi karbo", String.valueOf(selisihKarbohidrat));
            Log.e ("selisi protein", String.valueOf(selisihProtein));
            Log.e ("selisi lemak", String.valueOf(selisihLemak));
            return makananSehari;
        } else {
            return getMakananSehari();
        }
    }

    private void getData(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constant.BASE_URL + "datamakanan.php?diet="+status,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Type listType = new TypeToken<List<DataMakanan>>() {
                        }.getType();
                        List<DataMakanan> data = new Gson().fromJson(response, listType);
                        mappingData(data);
                        start();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(KomposisiActivity.this, String.valueOf(volleyError), Toast.LENGTH_LONG).show();
                    }
                }){
        };
        Volley.newRequestQueue(this).add(stringRequest);
    }
}
