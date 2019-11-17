package com.example.kridho.myskripsi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kridho.myskripsi.Utils.Constant;

public class MainActivity extends AppCompatActivity {

    ImageView ivCekgizi,ivHasil,ivHistory,ivAbout;
    TextView tvTitle;
    SharedPreferences sharedpreferences;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;

        sharedpreferences = getSharedPreferences(Constant.KEY_SHAREDPREFS, Context.MODE_PRIVATE);

        ivCekgizi = findViewById(R.id.visi);
        ivHasil = findViewById(R.id.iv_hasil);
        tvTitle = findViewById(R.id.tittle_nama);

        tvTitle.setText(sharedpreferences.getString(Constant.KEY_NAME, ""));


        ivCekgizi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(MainActivity.this, CekGiziActivity.class);
                startActivity(pindah);
            }
        });

        ivHasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(MainActivity.this, HasilActivity.class);
                startActivity(pindah);
            }
        });
    }


}
