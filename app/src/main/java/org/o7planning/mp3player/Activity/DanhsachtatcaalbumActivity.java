package org.o7planning.mp3player.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.o7planning.mp3player.Adapter.AllalbumAdapter;
import org.o7planning.mp3player.Model.Album;
import org.o7planning.mp3player.R;
import org.o7planning.mp3player.Service.APIService;
import org.o7planning.mp3player.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachtatcaalbumActivity extends AppCompatActivity {

    RecyclerView rv_tatcaalbum;
    Toolbar toolbar;
    AllalbumAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtatcaalbum);
        
        initView();
        GetData();
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<Album>> callback = dataService.Gettatcaalbum();

        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> array_album = (ArrayList<Album>) response.body();
                adapter = new AllalbumAdapter(DanhsachtatcaalbumActivity.this, array_album);
                rv_tatcaalbum.setLayoutManager(new GridLayoutManager(DanhsachtatcaalbumActivity.this, 2));
                rv_tatcaalbum.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }

    private void initView() {
        rv_tatcaalbum = findViewById(R.id.rv_tatcaalbum);
        toolbar = findViewById(R.id.toolbar_tatcaalbum);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Album");
    }


}