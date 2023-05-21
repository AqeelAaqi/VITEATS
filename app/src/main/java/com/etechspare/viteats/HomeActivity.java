package com.etechspare.viteats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    DBHelper dbHelper =new DBHelper(this);
    HotelsAdapter hotelsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        RecyclerView recyclerView = findViewById(R.id.rv_peace_point);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(false);

        Restaurant[] restaurantData = new Gson().fromJson(readJSON(), Restaurant[].class);
        List<Restaurant> list = Arrays.asList(restaurantData);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        hotelsAdapter = (new HotelsAdapter(list, this, dbHelper));
        recyclerView.setAdapter(hotelsAdapter);
        hotelsAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        hotelsAdapter.notifyDataSetChanged();
    }

    public String readJSON() {
        String json = null;
        try {
            // Opening data.json file
            InputStream inputStream = this.getAssets().open("restaurant_data.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            // read values in the byte array
            inputStream.read(buffer);
            inputStream.close();
            // convert byte to string
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return json;
        }
        return json;
    }
}