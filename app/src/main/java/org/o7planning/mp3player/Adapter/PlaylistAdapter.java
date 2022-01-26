package org.o7planning.mp3player.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import org.o7planning.mp3player.Model.Playlist;
import org.o7planning.mp3player.R;

import java.util.List;

public class PlaylistAdapter extends ArrayAdapter<Playlist> {
    public PlaylistAdapter(@NonNull Context context, int resource, @NonNull List<Playlist> objects) {
        super(context, resource, objects);
    }

    class ViewHolder{
        TextView tv_tenPlaylist;
        ImageView iv_backgroundPlaylist, iv_iconPlaylist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.item_playlist, null);

            viewHolder = new ViewHolder();
            viewHolder.tv_tenPlaylist = convertView.findViewById(R.id.tv_tenPlaylist);
            viewHolder.iv_backgroundPlaylist = convertView.findViewById(R.id.iv_backgroundPlaylist);
            viewHolder.iv_iconPlaylist = convertView.findViewById(R.id.iv_iconPlaylist);

            convertView.setTag(viewHolder);
        }

        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Playlist playlist = getItem(position);

        Picasso.with(getContext()).load(playlist.getHinhPlaylist()).into(viewHolder.iv_backgroundPlaylist);
        Picasso.with(getContext()).load(playlist.getIcon()).into(viewHolder.iv_iconPlaylist);
        viewHolder.tv_tenPlaylist.setText(playlist.getTen());

        return convertView;
    }
}
