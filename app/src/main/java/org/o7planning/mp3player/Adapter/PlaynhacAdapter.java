package org.o7planning.mp3player.Adapter;

import android.content.Context;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.o7planning.mp3player.Model.BaiHat;
import org.o7planning.mp3player.R;

import java.util.ArrayList;

public class PlaynhacAdapter extends RecyclerView.Adapter<PlaynhacAdapter.ViewHolder>{
    Context context;
    ArrayList<BaiHat> array_baihat;

    public PlaynhacAdapter(Context context, ArrayList<BaiHat> array_baihat) {
        this.context = context;
        this.array_baihat = array_baihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_play_bai_hat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat = array_baihat.get(position);

        holder.tv_index.setText(position+1+"");
        holder.tv_tenbaihat.setText(baiHat.getTenBaiHat());
        holder.tv_tencasi.setText(baiHat.getCaSi());
    }

    @Override
    public int getItemCount() {
        return array_baihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_index, tv_tenbaihat, tv_tencasi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_index = itemView.findViewById(R.id.tv_playnhacindex);
            tv_tenbaihat = itemView.findViewById(R.id.tv_playnhactenbaihat);
            tv_tencasi= itemView.findViewById(R.id.tv_playnhactencasi);
        }
    }
}
