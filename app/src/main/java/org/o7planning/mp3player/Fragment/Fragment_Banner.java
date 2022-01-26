package org.o7planning.mp3player.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import org.o7planning.mp3player.Adapter.BannerAdapter;
import org.o7planning.mp3player.Model.Quangcao;
import org.o7planning.mp3player.R;
import org.o7planning.mp3player.Service.APIService;
import org.o7planning.mp3player.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Banner extends Fragment {

    View view;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    Runnable runnable;
    Handler handler;
    int currentItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_banner, container, false);
        initView();
        GetData();
        return view;
    }

    public void initView(){
        viewPager = view.findViewById(R.id.viewPagerBanner);
        circleIndicator = view.findViewById(R.id.indicatorDefault);
    }

    private void GetData(){
        DataService dataService = APIService.getService();
        Call<List<Quangcao>> callback = dataService.GetDataBanner();
        callback.enqueue(new Callback<List<Quangcao>>() {
            @Override
            public void onResponse(Call<List<Quangcao>> call, Response<List<Quangcao>> response) {
                ArrayList<Quangcao> banner = (ArrayList<Quangcao>) response.body();
                BannerAdapter bannerAdapter = new BannerAdapter(getActivity(), banner);
                viewPager.setAdapter(bannerAdapter);
                circleIndicator.setViewPager(viewPager);

                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        currentItem = viewPager.getCurrentItem();
                        currentItem++;

                        if(currentItem >= viewPager.getAdapter().getCount()){
                            currentItem = 0;
                        }

                        viewPager.setCurrentItem(currentItem, true);
                        handler.postDelayed(runnable, 5000);
                    }
                };
                handler.postDelayed(runnable, 5000);
            }

            @Override
            public void onFailure(Call<List<Quangcao>> call, Throwable t) {

            }
        });
    }
}
