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
import org.o7planning.mp3player.Model.Playlist;
import org.o7planning.mp3player.R;

import java.util.ArrayList;

public class DanhsachplaylistAdapter extends RecyclerView.Adapter<DanhsachplaylistAdapter.ViewHolder>{

    Context context;
    ArrayList<Playlist> array_playlist;

    public DanhsachplaylistAdapter(Context context, ArrayList<Playlist> array_playlist) {
        this.context = context;
        this.array_playlist = array_playlist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_danhsachplaylist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Playlist playlist = array_playlist.get(position);

        holder.tv_tenplaylist.setText(playlist.getTen());
        Picasso.with(context).load(playlist.getIcon()).into(holder.iv_hinhplaylist);
    }

    @Override
    public int getItemCount() {
        return array_playlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_hinhplaylist;
        TextView tv_tenplaylist;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_hinhplaylist = itemView.findViewById(R.id.iv_danhsachplaylist);
            tv_tenplaylist = itemView.findViewById(R.id.tv_tenPlaylist);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("itemplaylist", array_playlist.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
