package org.o7planning.mp3player.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.o7planning.mp3player.Activity.DanhsachtatcaalbumActivity;
import org.o7planning.mp3player.Adapter.AlbumAdapter;
import org.o7planning.mp3player.Model.Album;
import org.o7planning.mp3player.R;
import org.o7planning.mp3player.Service.APIService;
import org.o7planning.mp3player.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragmen_Album_Hot extends Fragment {
    View view;
    RecyclerView recyclerView;
    TextView tv_albumMore;
    AlbumAdapter albumAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_album_hot, container, false);
        recyclerView = view.findViewById(R.id.rv_albumhot);
        tv_albumMore = view.findViewById(R.id.tv_albumMore);

        tv_albumMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DanhsachtatcaalbumActivity.class);
                startActivity(intent);
            }
        });
        GetData();
        return view;
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<Album>> callback = dataService.getAlbumHot();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> array_album = (ArrayList<Album>) response.body();
                albumAdapter = new AlbumAdapter(getActivity(), array_album);
                LinearLayoutManager linearLayout = new LinearLayoutManager(getActivity());
                linearLayout.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(linearLayout);
                recyclerView.setAdapter(albumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }
}
