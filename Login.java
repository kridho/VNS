package com.example.kridho.myskripsi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kridho.myskripsi.Utils.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

  private EditText etUsername,etPassword;
  private Context context;
  private Button btnLogin,btnRegis;

    private SharedPreferences.Editor editor;


    public final static String TAG_USERNAME = "name";
    public final static String TAG_ID = "id";

    String tag_json_obj = "json_obj_req";

    SharedPreferences sharedpreferences;
    Boolean session = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = Login.this;

        sharedpreferences = getSharedPreferences(Constant.KEY_SHAREDPREFS, Context.MODE_PRIVATE);

        etUsername = findViewById(R.id.user);
        etPassword = findViewById(R.id.pass);
        btnLogin = findViewById(R.id.masuk);
        btnRegis = findViewById(R.id.regis);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(Login.this, RegistrasiActivity.class);
                startActivity(pindah);

            }
        });
    }

    private void login(){
        final String username = etUsername.getText().toString();
        final String password = etPassword.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constant.BASE_URL + "login.php?&username=" +username+ "&password=" +password,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("login", response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getString("status").equals(Constant.LOGIN_SUCCESS)) {

                                JSONObject data = new JSONObject(jsonObject.getString("data"));

                                String name = data.getString("nama");
                                String id = data.getString("id_user");

                                editor = sharedpreferences.edit();
                                editor.putBoolean(Constant.KEY_SHAREDPREFS_LOGIN_STATUS, true);
                                editor.putString(Constant.KEY_ID, id);
                                editor.putString(Constant.KEY_NAME, name);

                                editor.apply();
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
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
        finish();

    }
}
