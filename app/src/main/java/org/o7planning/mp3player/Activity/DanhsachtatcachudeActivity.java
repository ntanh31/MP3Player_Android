package org.o7planning.mp3player.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import org.o7planning.mp3player.Adapter.DanhsachtatcachudeAdapter;
import org.o7planning.mp3player.Model.ChuDe;
import org.o7planning.mp3player.R;
import org.o7planning.mp3player.Service.APIService;
import org.o7planning.mp3player.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachtatcachudeActivity extends AppCompatActivity {

    RecyclerView rv_tatcachude;
    Toolbar toolbar;
    DanhsachtatcachudeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtatcachude);

        initView();
        GetData();
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<ChuDe>> callback = dataService.Gettatcachude();
        callback.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                ArrayList<ChuDe> array_chude = (ArrayList<ChuDe>) response.body();
                adapter = new DanhsachtatcachudeAdapter(DanhsachtatcachudeActivity.this, array_chude);
                rv_tatcachude.setLayoutManager(new LinearLayoutManager(DanhsachtatcachudeActivity.this));
                rv_tatcachude.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        });
    }

    private void initView() {
        rv_tatcachude = findViewById(R.id.rv_tatcachude);
        toolbar = findViewById(R.id.toolbar_tatcachude);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Chủ Đề");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}