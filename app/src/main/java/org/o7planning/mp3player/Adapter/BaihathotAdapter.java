package org.o7planning.mp3player.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.o7planning.mp3player.Activity.PlayNhacActivity;
import org.o7planning.mp3player.Model.BaiHat;
import org.o7planning.mp3player.R;
import org.o7planning.mp3player.Service.APIService;
import org.o7planning.mp3player.Service.DataService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaihathotAdapter extends RecyclerView.Adapter<BaihathotAdapter.ViewHolder> {

    Context context;
    ArrayList<BaiHat> arraylist_baihat;

    public BaihathotAdapter(Context context, ArrayList<BaiHat> arraylist_baihat) {
        this.context = context;
        this.arraylist_baihat = arraylist_baihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_bai_hat_hot, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat = arraylist_baihat.get(position);

        holder.tv_tenbaihat.setText(baiHat.getTenBaiHat());
        holder.tv_tencasi.setText(baiHat.getCaSi());

        Picasso.with(context).load(baiHat.getHinhBaiHat()).into(holder.iv_hinhbaihat);
    }

    @Override
    public int getItemCount() {
        return arraylist_baihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_tenbaihat, tv_tencasi;
        ImageView iv_hinhbaihat, iv_luotthich;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_tenbaihat = itemView.findViewById(R.id.tv_tenbaihathot);
            tv_tencasi = itemView.findViewById(R.id.tv_tencasibaihat);
            iv_hinhbaihat = itemView.findViewById(R.id.iv_baihathot);
            iv_luotthich = itemView.findViewById(R.id.iv_luotthich);

            iv_luotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iv_luotthich.setImageResource(R.drawable.iconloved);
                    DataService dataService = APIService.getService();
                    Call<String> callback = dataService.Updateluotthich("1", arraylist_baihat.get(getPosition()).getIdBaiHat());

                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if(ketqua.equals("Success")){
                                Toast.makeText(context, "Đã Thích", Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(context, "Lỗi", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc", arraylist_baihat.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
