package org.o7planning.mp3player.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.o7planning.mp3player.Activity.PlayNhacActivity;
import org.o7planning.mp3player.Adapter.PlaynhacAdapter;
import org.o7planning.mp3player.R;

public class Fragment_Play_Danh_Sach_Bai_Hat extends Fragment {
    View view;
    RecyclerView rv_playbaihat;
    PlaynhacAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_play_danh_sach_bai_hat, container, false);
        rv_playbaihat = view.findViewById(R.id.rv_playbaihat);

        if(PlayNhacActivity.array_baihat.size() > 0){
            adapter = new PlaynhacAdapter(getActivity(), PlayNhacActivity.array_baihat);
            rv_playbaihat.setLayoutManager(new LinearLayoutManager(getActivity()));
            rv_playbaihat.setAdapter(adapter);
        }
        return view;
    }
}
