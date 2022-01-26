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

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
    Context context;
    ArrayList<Album> arraylist_album;

    public AlbumAdapter(Context context, ArrayList<Album> arraylist_album) {
        this.context = context;
        this.arraylist_album = arraylist_album;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_album, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = arraylist_album.get(position);

        holder.tv_tenAlbum.setText(album.getTenAlbum());
        holder.tv_tenCasialbum.setText(album.getTenCaSiAlbum());
        Picasso.with(context).load(album.getHinhAlbum()).into(holder.iv_hinhAlbum);
    }

    @Override
    public int getItemCount() {
        return arraylist_album.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_hinhAlbum;
        TextView tv_tenAlbum, tv_tenCasialbum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_hinhAlbum = itemView.findViewById(R.id.iv_album);
            tv_tenAlbum = itemView.findViewById(R.id.tv_tenAlbum);
            tv_tenCasialbum = itemView.findViewById(R.id.tv_tenCaSiAlbum);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("album", arraylist_album.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
