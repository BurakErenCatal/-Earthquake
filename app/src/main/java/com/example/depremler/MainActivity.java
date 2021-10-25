package com.example.depremler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.StrictMode;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy gfgPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(gfgPolicy);
        setContentView(R.layout.activity_main);

        DepremDataAdapter adapter = new DepremDataAdapter(DepremModel.GetData(),getApplicationContext());
        RecyclerView depremler = findViewById(R.id.rv_depremler);
        depremler.setAdapter(adapter);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        depremler.setLayoutManager(lm);
    }
}