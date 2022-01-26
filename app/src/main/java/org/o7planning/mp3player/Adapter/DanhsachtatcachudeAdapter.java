package org.o7planning.mp3player.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.o7planning.mp3player.Activity.DanhsachtheloaitheochudeActivity;
import org.o7planning.mp3player.Model.ChuDe;
import org.o7planning.mp3player.R;

import java.util.ArrayList;

public class DanhsachtatcachudeAdapter extends  RecyclerView.Adapter<DanhsachtatcachudeAdapter.ViewHolder> {

    Context context;
    ArrayList<ChuDe> array_chude;

    public DanhsachtatcachudeAdapter(Context context, ArrayList<ChuDe> array_chude) {
        this.context = context;
        this.array_chude = array_chude;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_tatcachude, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChuDe chuDe = array_chude.get(position);
        Picasso.with(context).load(chuDe.getHinhChuDe()).into(holder.iv_hinhchude);
    }

    @Override
    public int getItemCount() {
        return array_chude.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_hinhchude;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_hinhchude = itemView.findViewById(R.id.iv_hinhchude);

            iv_hinhchude.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DanhsachtheloaitheochudeActivity.class);
                    intent.putExtra("chude", array_chude.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
