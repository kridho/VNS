package com.example.kridho.myskripsi;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kridho.myskripsi.Utils.Constant;

import org.json.JSONException;
import org.json.JSONObject;

public class CekGiziActivity extends AppCompatActivity {

    Dialog dialog;
    ImageView popUp;

    private EditText etName, etUmur, etTinggibadan, etBeratbadan;
    private Spinner sAktifitas, sDiet, sGender;
    private Context context;
    private Button btnCek;

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cek_gizi);
        dialog = new Dialog(this);
        context = CekGiziActivity.this;
        sharedpreferences = getSharedPreferences(Constant.KEY_SHAREDPREFS, Context.MODE_PRIVATE);


        popUp = findViewById(R.id.tv_info);
        popUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowPopup();
            }
        });



        etName = findViewById(R.id.et_name);
        etUmur = findViewById(R.id.et_years);
        etTinggibadan = findViewById(R.id.et_hight);
        etBeratbadan = findViewById(R.id.et_wight);
        sAktifitas = findViewById(R.id.list_aktifitas);
        sGender = findViewById(R.id.list_gender);
        sDiet = findViewById(R.id.list_diet);
        btnCek = findViewById(R.id.btn_cek);

        etName.setText(sharedpreferences.getString(Constant.KEY_NAME, ""));
        btnCek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });

    }

    private void insert(){
        String vAktifitas = "";
        final String name = etName.getText().toString();
        final String umur = etUmur.getText().toString();
        final String tinggibadan = etTinggibadan.getText().toString();
        final String beratbadan = etBeratbadan.getText().toString();
        final String aktifitas = sAktifitas.getSelectedItem().toString();
        final String gender = sGender.getSelectedItem().toString();
        final String diet = sDiet.getSelectedItem().toString();

        if (aktifitas.equals("Ringan")){

            if (gender.equals("Pria")){
                vAktifitas = "1.56";
            } else {
                vAktifitas = "1.55";
            }
        } else if (aktifitas.equals("Sedang")) {

            if (gender.equals("Pria")) {
                vAktifitas = "1.76";
            } else {
                vAktifitas = "1.70";
            }
        } else if (aktifitas.equals("Berat")) {

            if (gender.equals("Pria")) {
                vAktifitas = "2.10";
            } else {
                vAktifitas = "2.00";
            }
        } else if (aktifitas.equals("Sangat Ringan")){
            vAktifitas= "1.30";
        }

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constant.BASE_URL + "insert.php?id_user="
                +sharedpreferences.getString(Constant.KEY_ID,"")+"&nama=" +name+ "&umur=" +umur+
                "&tinggi_badan=" +tinggibadan+ "&berat_badan=" +beratbadan+ "&aktifitas=" +vAktifitas+ "&gender=" +gender+ "&diet=" +diet,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("login", response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getString("status").equals(Constant.LOGIN_SUCCESS)){
                                Toast.makeText(context.getApplicationContext(), String.valueOf("SUKSES"), Toast.LENGTH_LONG).show();
                                goToActivity();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(context.getApplicationContext(), String.valueOf(volleyError), Toast.LENGTH_LONG).show();
                    }
                }){
        };
        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void ShowPopup() {
        dialog.setContentView(R.layout.popup);
        dialog.show();
    }

    private void goToActivity() {
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);


    }
}
