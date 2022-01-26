package org.o7planning.mp3player.Adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

import org.o7planning.mp3player.Activity.DanhsachbaihatActivity;
import org.o7planning.mp3player.Model.Quangcao;
import org.o7planning.mp3player.R;

import java.util.ArrayList;

public class BannerAdapter extends PagerAdapter {

    Context context;
    ArrayList<Quangcao> arrayListBanner;

    public BannerAdapter(Context context, ArrayList<Quangcao> arrayListBanner) {
        this.context = context;
        this.arrayListBanner = arrayListBanner;
    }

    @Override
    public int getCount() {
        return arrayListBanner.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_banner, null);

        ImageView backgroundBanner = view.findViewById(R.id.imageViewBackgroundBanner);
        ImageView songBanner = view.findViewById(R.id.imageViewBanner);
        TextView tvTenBaiHat = view.findViewById(R.id.tv_bannerTitle);
        TextView tvNoiDung = view.findViewById(R.id.tvNoiDung);

        Picasso.with(context).load(arrayListBanner.get(position).getHinhanh()).into(backgroundBanner);
        Picasso.with(context).load(arrayListBanner.get(position).getHinhBaiHat()).into(songBanner);
        tvTenBaiHat.setText(arrayListBanner.get(position).getTenBaiHat());
        tvNoiDung.setText(arrayListBanner.get(position).getNoidung());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                intent.putExtra("banner", arrayListBanner.get(position));
                context.startActivity(intent);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
