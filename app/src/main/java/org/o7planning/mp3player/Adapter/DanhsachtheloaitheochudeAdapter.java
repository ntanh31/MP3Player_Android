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
import org.o7planning.mp3player.Model.TheLoai;
import org.o7planning.mp3player.R;

import java.util.ArrayList;

public class DanhsachtheloaitheochudeAdapter extends RecyclerView.Adapter<DanhsachtheloaitheochudeAdapter.ViewHolder> {

    Context context;
    ArrayList<TheLoai> array_theloai;

    public DanhsachtheloaitheochudeAdapter(Context context, ArrayList<TheLoai> array_theloai) {
        this.context = context;
        this.array_theloai = array_theloai;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_theloaitheochude, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TheLoai theLoai = array_theloai.get(position);
        holder.tv_tentheloai.setText(theLoai.getTenTheLoai());
        Picasso.with(context).load(theLoai.getHinhTheLoai()).into(holder.iv_hinhtheloai);
    }

    @Override
    public int getItemCount() {
        return array_theloai.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_hinhtheloai;
        TextView tv_tentheloai;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_hinhtheloai = itemView.findViewById(R.id.iv_theloaitheochude);
            tv_tentheloai = itemView.findViewById(R.id.tv_tentheloaitheochude);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("itemtheloai", array_theloai.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
