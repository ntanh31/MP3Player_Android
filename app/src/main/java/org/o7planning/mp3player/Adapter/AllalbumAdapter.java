package org.o7planning.mp3player.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.o7planning.mp3player.Activity.DanhsachbaihatActivity;
import org.o7planning.mp3player.Model.Album;
import org.o7planning.mp3player.R;

import java.util.ArrayList;

public class AllalbumAdapter extends RecyclerView.Adapter<AllalbumAdapter.ViewHolder>{

    Context context;
    ArrayList<Album> array_album;

    public AllalbumAdapter(Context context, ArrayList<Album> array_album) {
        this.context = context;
        this.array_album = array_album;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_tatcaalbum, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = array_album.get(position);
        holder.tv_tencasi.setText(album.getTenCaSiAlbum());
        holder.tv_tenalbum.setText(album.getTenAlbum());
        Picasso.with(context).load(album.getHinhAlbum()).into(holder.iv_hinhalbum);
    }

    @Override
    public int getItemCount() {
        return array_album.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_hinhalbum;
        TextView tv_tenalbum, tv_tencasi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_hinhalbum = itemView.findViewById(R.id.iv_danhsachalbum);
            tv_tencasi = itemView.findViewById(R.id.tv_tencasitatcaalbum);
            tv_tenalbum = itemView.findViewById(R.id.tv_tentatcaalbum);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("album", array_album.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
