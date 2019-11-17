package com.example.kridho.myskripsi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kridho.myskripsi.Utils.Constant;
import com.example.kridho.myskripsi.data.DataUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HasilActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    private TextView tvKalori, tvKarbohidrat, tvLemak, tvProtein;
    private TextView tvUser, tvStatus, pinalty;
    private EditText iterasi, kmax;
    LinearLayout ll_iterasi;
    private Context context;
    Button button;
    Spinner spinner;
    private double protein, lemak, energi, karbohidrat;
    List<DataUser> dataUsers = new ArrayList<>();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);

        context = HasilActivity.this;
        sharedpreferences = getSharedPreferences(Constant.KEY_SHAREDPREFS, Context.MODE_PRIVATE);

        tvKalori = findViewById(R.id.tv_kalori);
        tvKarbohidrat = findViewById(R.id.tv_Karbohidrat);
        tvLemak = findViewById(R.id.tv_lemak);
        tvProtein = findViewById(R.id.tv_protein);
        spinner = findViewById(R.id.sTanggal);
        button = findViewById(R.id.btn_cek);
        iterasi = findViewById(R.id.iterasi);
        pinalty = findViewById(R.id.total_pinalty);
        tvUser = findViewById(R.id.user_name);
        tvStatus = findViewById(R.id.statusDiet);
        kmax = findViewById(R.id.et_kmax);
        ll_iterasi = findViewById(R.id.ll_iterasi);

        getData();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("aldi",dataUsers.get(position).getDiet());
                if (dataUsers.get(position).getDiet().equals("1")) {
                    ll_iterasi.setVisibility(View.GONE);
                } else{
                    ll_iterasi.setVisibility(View.VISIBLE);
                }
                calculateIMT(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvStatus.getText().equals("1")) {
                    Intent intent = new Intent(context, DietJantung1Activity.class);
                    startActivity(intent);
                } else {
                    if (!iterasi.getText().toString().isEmpty() && !kmax.getText().toString().isEmpty()) {
                        Intent intent = new Intent(context, KomposisiActivity.class);
                        intent.putExtra("protein", protein);
                        intent.putExtra("karbohidrat", karbohidrat);
                        intent.putExtra("lemak", lemak);
                        intent.putExtra("energi", energi);
                        intent.putExtra("status", tvStatus.getText());
                        intent.putExtra("iterasi", Integer.parseInt(iterasi.getText().toString()));
                        intent.putExtra("kmax", Integer.parseInt(kmax.getText().toString()));
                        startActivity(intent);
                    }

                }

            }
        });
    }

    void calculateIMT(int index) {
        DataUser dataUser = dataUsers.get(index);
        float tinggiBadan = dataUser.getTinggiBadan() / 100;
        double nilaiIMT = dataUser.getBeratBadan() / Math.pow(tinggiBadan, 2);
        Log.e("login", String.valueOf(nilaiIMT));

        double beratBadanIdeal = dataUser.getBeratBadan();
        if (getIMTIndex(nilaiIMT).equalsIgnoreCase("over weight") ||
                getIMTIndex(nilaiIMT).equalsIgnoreCase("obesitas")) {
            beratBadanIdeal = calculateBBIdeal(dataUser);
        }
        energi = calculateKalori(dataUser, beratBadanIdeal);
        tvKalori.setText(String.valueOf(energi));
        protein = calculateProtein(beratBadanIdeal);
        tvProtein.setText(String.format("%.2f", (protein)));
        lemak = calculateLemak(energi);
        tvLemak.setText(String.format("%.2f", (lemak)));
        karbohidrat = calculateKarbohidrat(energi);
        tvKarbohidrat.setText(String.format("%.2f", (karbohidrat)));
        tvUser.setText(dataUser.getNama());
        tvStatus.setText(dataUser.getDiet());

    }

    String getIMTIndex(double val) {
        if (val < 18.5) return "kurus";
        else if (val < 25) return "normal";
        else if (val < 30) return "over weight";
        else return "obesitas";
    }

    double calculateBBIdeal(DataUser dataUser) {

        if (dataUser.getGender().equalsIgnoreCase("wanita")) {
            return (dataUser.getTinggiBadan() - 100) - (0.15 * (dataUser.getTinggiBadan() - 100));
        } else {
            return (dataUser.getTinggiBadan() - 100) - (0.1 * (dataUser.getTinggiBadan() - 100));
        }
    }

    double calculateKalori(DataUser dataUser, double beratBadanIdeal) {
        double kebutuhanGizi = 0;
        Log.e("login", String.valueOf(kebutuhanGizi));
        if (dataUser.getGender().equalsIgnoreCase("wanita")) {
            kebutuhanGizi = 655 + (1.8 * dataUser.getTinggiBadan()) + (9.6 * beratBadanIdeal) - (4.7 * dataUser.getUmur());

        } else {
            kebutuhanGizi = 66.5 + (13.7 * beratBadanIdeal) + (5 * dataUser.getTinggiBadan()) - (6.8 * dataUser.getUmur());
        }
        Log.e("login", String.valueOf(kebutuhanGizi));
        return kebutuhanGizi * dataUser.getAktifitas();
    }


    double calculateProtein(double beratIdeal) {
        return 0.8 * beratIdeal;
    }

    double calculateLemak(double kalori) {
        return 0.25 * kalori / 9;
    }

    double calculateKarbohidrat(double kalori) {
        return 0.65 * kalori / 4;
    }

    void setItem() {
        String[] items = new String[dataUsers.size()];
        for (int i = 0; i < dataUsers.size(); i++) {
            items[i] = dataUsers.get(i).getNama();
        }

        adapter = new ArrayAdapter<>(this, R.layout.item_spinner, items);
        spinner.setAdapter(adapter);
    }

    private void getData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constant.BASE_URL + "ambil_data.php?id_user="
                + sharedpreferences.getString(Constant.KEY_ID, ""),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("login", response);
                        Type listType = new TypeToken<List<DataUser>>() {
                        }.getType();
                        List<DataUser> data = new Gson().fromJson(response, listType);
                        dataUsers = data;
                        setItem();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(context.getApplicationContext(), String.valueOf(volleyError), Toast.LENGTH_LONG).show();
                    }
                }) {
        };
        Volley.newRequestQueue(this).add(stringRequest);
    }

}


