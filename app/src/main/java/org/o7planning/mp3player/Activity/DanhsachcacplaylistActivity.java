package org.o7planning.mp3player.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import org.o7planning.mp3player.Adapter.DanhsachplaylistAdapter;
import org.o7planning.mp3player.Model.Playlist;
import org.o7planning.mp3player.R;
import org.o7planning.mp3player.Service.APIService;
import org.o7planning.mp3player.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachcacplaylistActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView rv_danhsachplaylist;
    DanhsachplaylistAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachcacplaylist);

        initView();
        init();
        GetData();
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<Playlist>> callback = dataService.getDanhsachcacplaylist();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                ArrayList<Playlist> array_playlist = (ArrayList<Playlist>) response.body();
                adapter = new DanhsachplaylistAdapter(DanhsachcacplaylistActivity.this, array_playlist);
                rv_danhsachplaylist.setLayoutManager(new GridLayoutManager(DanhsachcacplaylistActivity.this, 2));
                rv_danhsachplaylist.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }

    private void init(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Danh Sách Playlist");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbarplaylist);
        rv_danhsachplaylist = findViewById(R.id.rv_danhsachplaylist);
    }


}