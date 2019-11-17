package com.example.kridho.myskripsi;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kridho.myskripsi.Utils.Constant;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistrasiActivity extends AppCompatActivity {

    private EditText etUsername, etName, etPassword;
    private Button btnSimpan;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        context = RegistrasiActivity.this;

        etName = findViewById(R.id.namalengkap);
        etUsername = findViewById(R.id.nama);
        etPassword = findViewById(R.id.password);
        btnSimpan = findViewById(R.id.daftar);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daftar();
            }
        });



    }

    private void daftar(){
        final String nama = etName.getText().toString();
        final String username = etUsername.getText().toString();
        final String password = etPassword.getText().toString();

        Log.e("DATA", "isi nama " + nama + " username " + username + " pw " + password);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constant.BASE_URL + "regis.php?username=" +username+ "&nama=" +nama+ "&password=" +password,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("login", response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getString("status").equals(Constant.LOGIN_SUCCESS)){
                                Toast.makeText(context.getApplicationContext(), "SUKSES", Toast.LENGTH_LONG).show();
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
    private void goToActivity() {
        Intent intent = new Intent(context, Login.class);
        startActivity(intent);
        finish();
}
}
