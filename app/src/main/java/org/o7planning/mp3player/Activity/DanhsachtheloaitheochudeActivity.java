package org.o7planning.mp3player.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.o7planning.mp3player.Adapter.DanhsachtheloaitheochudeAdapter;
import org.o7planning.mp3player.Model.ChuDe;
import org.o7planning.mp3player.Model.TheLoai;
import org.o7planning.mp3player.R;
import org.o7planning.mp3player.Service.APIService;
import org.o7planning.mp3player.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachtheloaitheochudeActivity extends AppCompatActivity {

    ChuDe chuDe;
    RecyclerView rv_danhsachtheloai;
    Toolbar toolbar;
    DanhsachtheloaitheochudeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtheloaitheochude);

        GetIntent();
        initView();
        GetData();
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<TheLoai>> callback = dataService.Gettheloaitheochude(chuDe.getIdChuDe());
        callback.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                ArrayList<TheLoai> array_theloai = (ArrayList<TheLoai>) response.body();
                adapter = new DanhsachtheloaitheochudeAdapter(DanhsachtheloaitheochudeActivity.this, array_theloai);
                rv_danhsachtheloai.setLayoutManager(new GridLayoutManager(DanhsachtheloaitheochudeActivity.this, 2));
                rv_danhsachtheloai.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {

            }
        });
    }

    private void initView() {
        rv_danhsachtheloai = findViewById(R.id.rv_Danhsachtheloai);
        toolbar = findViewById(R.id.toolbar_danhsachtheloai);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Thể Loại Theo Chủ Đề");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void GetIntent() {
        Intent intent = getIntent();
        if(intent.hasExtra("chude")){
            chuDe = (ChuDe) intent.getSerializableExtra("chude");
        }
    }
}