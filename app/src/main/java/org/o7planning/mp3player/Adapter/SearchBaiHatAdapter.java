package org.o7planning.mp3player.Adapter;

import android.content.Context;
import android.content.Intent;
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

public class SearchBaiHatAdapter extends RecyclerView.Adapter<SearchBaiHatAdapter.ViewHolder>{

    Context context;
    ArrayList<BaiHat> array_baihat;

    public SearchBaiHatAdapter(Context context, ArrayList<BaiHat> array_baihat) {
        this.context = context;
        this.array_baihat = array_baihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat = array_baihat.get(position);
        holder.tv_tenbaihat.setText(baiHat.getTenBaiHat());
        holder.tv_casi.setText(baiHat.getCaSi());
        Picasso.with(context).load(baiHat.getHinhBaiHat()).into(holder.iv_hinhbaihat);
    }

    @Override
    public int getItemCount() {
        return array_baihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_hinhbaihat, iv_luotthich;
        TextView tv_tenbaihat, tv_casi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_hinhbaihat = itemView.findViewById(R.id.iv_timkiemhinhanh);
            iv_luotthich = itemView.findViewById(R.id.iv_searchluotthich);
            tv_tenbaihat = itemView.findViewById(R.id.tv_searchtenbai);
            tv_casi = itemView.findViewById(R.id.tv_searchtencasi);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc", array_baihat.get(getPosition()));
                    context.startActivity(intent);
                }
            });

            iv_luotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iv_luotthich.setImageResource(R.drawable.iconloved);
                    DataService dataService = APIService.getService();
                    Call<String> callback = dataService.Updateluotthich("1", array_baihat.get(getPosition()).getIdBaiHat());

                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if(ketqua.equals("Success")){
                                Toast.makeText(context, "Đã Thích", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });

                    iv_luotthich.setEnabled(false);
                }
            });
        }
    }
}
