package org.o7planning.mp3player.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import org.o7planning.mp3player.Adapter.DanhsachbaihatAdapter;
import org.o7planning.mp3player.Model.Album;
import org.o7planning.mp3player.Model.BaiHat;
import org.o7planning.mp3player.Model.Playlist;
import org.o7planning.mp3player.Model.Quangcao;
import org.o7planning.mp3player.Model.TheLoai;
import org.o7planning.mp3player.R;
import org.o7planning.mp3player.Service.APIService;
import org.o7planning.mp3player.Service.DataService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Url;

public class DanhsachbaihatActivity extends AppCompatActivity {

    Quangcao quangcao;
    Playlist playlist;
    TheLoai theLoai;
    Album album;
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView rv_danhsachbaihat;
    FloatingActionButton floatingActionButton;
    ImageView iv_danhsachbaihat;
    ArrayList<BaiHat> array_baihat;
    DanhsachbaihatAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachbaihat);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        DataIntent();
        initView();
        init();

        if(quangcao != null && !quangcao.getTenBaiHat().equals("")){
            setValueView(quangcao.getTenBaiHat(), quangcao.getHinhBaiHat());
            GetDataQuangcao(quangcao.getIdQuangCao());
        }

        if(playlist != null && !playlist.getTen().equals("")){
            setValueView(playlist.getTen(), playlist.getIcon());
            GetDataPlaylist(playlist.getIdPlaylist());
        }

        if(theLoai != null && !theLoai.getTenTheLoai().equals("")){
            setValueView(theLoai.getTenTheLoai(), theLoai.getHinhTheLoai());
            GetDataTheLoai(theLoai.getIdTheLoai());
        }

        if(album != null && !album.getTenAlbum().equals("")){
            setValueView(album.getTenAlbum(), album.getHinhAlbum());
            GetDataAlbum(album.getIdAlbum());
        }
    }

    private void GetDataAlbum(String idAlbum) {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callback = dataService.Getdanhsachbaihattheoalbum(idAlbum);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                array_baihat = (ArrayList<BaiHat>) response.body();
                adapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, array_baihat);
                rv_danhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                rv_danhsachbaihat.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void GetDataTheLoai(String idTheLoai) {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callback = dataService.Getdanhsachbaihattheotheloai(idTheLoai);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                array_baihat = (ArrayList<BaiHat>) response.body();
                adapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, array_baihat);
                rv_danhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                rv_danhsachbaihat.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void GetDataPlaylist(String idPlaylist) {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callback = dataService.Getdanhsachbaihattheoplaylist(idPlaylist);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                array_baihat = (ArrayList<BaiHat>) response.body();
                adapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, array_baihat);
                rv_danhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                rv_danhsachbaihat.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void GetDataQuangcao(String idquangcao) {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callback = dataService.Getdanhsachbaihattheoquangcao(idquangcao);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                array_baihat = (ArrayList<BaiHat>) response.body();
                adapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, array_baihat);
                rv_danhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                rv_danhsachbaihat.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void setValueView(String ten, String hinh) {
        collapsingToolbarLayout.setTitle(ten);
        try {
            URL url = new URL(hinh);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
            collapsingToolbarLayout.setBackground(bitmapDrawable);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Picasso.with(this).load(hinh).into(iv_danhsachbaihat);
    }

    private void init(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
    }

    private void initView() {
        coordinatorLayout = findViewById(R.id.cl_danhsachbaihat);
        collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar);
        toolbar = findViewById(R.id.toolbardanhsach);
        rv_danhsachbaihat = findViewById(R.id.rv_danhsachbaihat);
        iv_danhsachbaihat = findViewById(R.id.iv_danhsachbaihat);
        floatingActionButton = findViewById(R.id.floatingactionbutton);
        floatingActionButton.setEnabled(false);
        eventClick();
    }

    private void DataIntent() {
        Intent intent = getIntent();

        if(intent != null){
            if(intent.hasExtra("banner")){
                quangcao = (Quangcao) intent.getSerializableExtra("banner");
            }

            if(intent.hasExtra("itemplaylist")){
                playlist = (Playlist) intent.getSerializableExtra("itemplaylist");
            }

            if(intent.hasExtra("itemtheloai")){
                theLoai = (TheLoai) intent.getSerializableExtra("itemtheloai");
            }

            if(intent.hasExtra("album")){
                album = (Album) intent.getSerializableExtra("album");
            }
        }
    }

    private void eventClick(){
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DanhsachbaihatActivity.this, PlayNhacActivity.class);
                intent.putExtra("cacbaihat", array_baihat);
                startActivity(intent);
            }
        });
    }
}