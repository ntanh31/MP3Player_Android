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

import org.o7planning.mp3player.Activity.PlayNhacActivity;
import org.o7planning.mp3player.Model.BaiHat;
import org.o7planning.mp3player.R;
import org.o7planning.mp3player.Service.APIService;
import org.o7planning.mp3player.Service.DataService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachbaihatAdapter extends RecyclerView.Adapter<DanhsachbaihatAdapter.ViewHolder>{

    Context context;
    ArrayList<BaiHat> array_baihat;

    public DanhsachbaihatAdapter(Context context, ArrayList<BaiHat> array_baihat) {
        this.context = context;
        this.array_baihat = array_baihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_danhsachbaihat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baihat = array_baihat.get(position);

        holder.tv_tencasi.setText(baihat.getCaSi());
        holder.tv_tenbaihat.setText(baihat.getTenBaiHat());
        holder.tv_item.setText(position+1 + "");
    }

    @Override
    public int getItemCount() {
        return array_baihat.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_item, tv_tenbaihat, tv_tencasi;
        ImageView iv_luotthich;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_item = itemView.findViewById(R.id.tv_danhsachindex);
            tv_tenbaihat = itemView.findViewById(R.id.tv_tenbaihat_quangcao);
            tv_tencasi = itemView.findViewById(R.id.tv_tencasi_quangcao);
            iv_luotthich = itemView.findViewById(R.id.iv_luotthich_quangcao);

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
                    intent.putExtra("cakhuc", array_baihat.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
