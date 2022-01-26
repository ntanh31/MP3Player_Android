package org.o7planning.mp3player.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import org.o7planning.mp3player.Activity.DanhsachbaihatActivity;
import org.o7planning.mp3player.Activity.DanhsachtatcachudeActivity;
import org.o7planning.mp3player.Activity.DanhsachtheloaitheochudeActivity;
import org.o7planning.mp3player.Model.ChuDe;
import org.o7planning.mp3player.Model.TheLoai;
import org.o7planning.mp3player.Model.TheLoaiTrongNgay;
import org.o7planning.mp3player.R;
import org.o7planning.mp3player.Service.APIService;
import org.o7planning.mp3player.Service.DataService;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentChuDeTheLoaiToday extends Fragment {
    View view;
    HorizontalScrollView horizontalScrollView;
    TextView tv_xemthem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chude_theloai_today,container,false);
        horizontalScrollView = view.findViewById(R.id.horizontalScroll);
        tv_xemthem = view.findViewById(R.id.tv_chudevatheloaiMore);

        tv_xemthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DanhsachtatcachudeActivity.class);
                startActivity(intent);
            }
        });
        GetData();
        return view;
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<TheLoaiTrongNgay> callback = dataService.getCategoryMusic();
        callback.enqueue(new Callback<TheLoaiTrongNgay>() {
            @Override
            public void onResponse(Call<TheLoaiTrongNgay> call, Response<TheLoaiTrongNgay> response) {
                TheLoaiTrongNgay theLoaiTrongNgay = response.body();

                final ArrayList<ChuDe> array_chude = new ArrayList<>();
                array_chude.addAll(theLoaiTrongNgay.getChuDe());

                final ArrayList<TheLoai> array_theloai = new ArrayList<>();
                array_theloai.addAll(theLoaiTrongNgay.getTheLoai());

                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(580, 250);
                layout.setMargins(10,20,10,30);

                for(int i = 0; i<(array_chude.size()); i++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if(array_chude.get(i).getHinhChuDe() != null){
                        Picasso.with(getActivity()).load(array_chude.get(i).getHinhChuDe()).into(imageView);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);

                    final int finalI = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity(), DanhsachtheloaitheochudeActivity.class);
                            intent.putExtra("chude", array_chude.get(finalI));
                            startActivity(intent);
                        }
                    });
                }

                for(int j = 0; j<(array_theloai.size()); j++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if(array_theloai.get(j).getHinhTheLoai() != null){
                        Picasso.with(getActivity()).load(array_theloai.get(j).getHinhTheLoai()).into(imageView);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);

                    final int finalJ = j;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), DanhsachbaihatActivity.class);
                            intent.putExtra("itemtheloai", array_theloai.get(finalJ));
                            Toast.makeText(getActivity(), array_theloai.get(finalJ).getIdTheLoai(),Toast.LENGTH_LONG).show();
                            startActivity(intent);
                        }
                    });
                }

                horizontalScrollView.addView(linearLayout);
            }

            @Override
            public void onFailure(Call<TheLoaiTrongNgay> call, Throwable t) {

            }
        });
    }
}
