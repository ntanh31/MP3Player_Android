package org.o7planning.mp3player.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;



import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;

import org.o7planning.mp3player.Adapter.SearchBaiHatAdapter;
import org.o7planning.mp3player.Model.BaiHat;
import org.o7planning.mp3player.R;
import org.o7planning.mp3player.Service.APIService;
import org.o7planning.mp3player.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Tim_KIem extends Fragment {
    View view;
    Toolbar toolbar;
    AppBarLayout appBarLayout;
    RecyclerView rv_timkiem;
    TextView tv_kotimthay;
    SearchBaiHatAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tim_kiem, container, false);
        toolbar = view.findViewById(R.id.toolbar_timkiem);
        rv_timkiem = view.findViewById(R.id.rv_timkiem);
        tv_kotimthay = view.findViewById(R.id.tv_kotimthay);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("");

        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Searchbaihat(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void Searchbaihat(String tukhoa){
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callback = dataService.Getsearchbaihat(tukhoa);

        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                ArrayList<BaiHat> array_baihat = (ArrayList<BaiHat>) response.body();

                if(array_baihat.size()>0){
                    adapter = new SearchBaiHatAdapter(getActivity(), array_baihat);
                    rv_timkiem.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rv_timkiem.setAdapter(adapter);
                    tv_kotimthay.setVisibility(View.GONE);
                    rv_timkiem.setVisibility(View.VISIBLE);
                }

                else{
                    tv_kotimthay.setVisibility(View.VISIBLE);
                    rv_timkiem.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
}
