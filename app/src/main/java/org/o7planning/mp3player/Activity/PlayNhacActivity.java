package org.o7planning.mp3player.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import org.o7planning.mp3player.Adapter.ViewPagerPlaylistnhac;
import org.o7planning.mp3player.Fragment.Fragment_Dia_Nhac;
import org.o7planning.mp3player.Fragment.Fragment_Play_Danh_Sach_Bai_Hat;
import org.o7planning.mp3player.Model.BaiHat;
import org.o7planning.mp3player.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlayNhacActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView tv_timesong, tv_totaltimesong;
    SeekBar sktime;
    ImageButton ib_play, ib_repeat, ib_pre, ib_next, ib_random;
    ViewPager viewPager;
    public static ArrayList<BaiHat> array_baihat = new ArrayList<>();
    public static ViewPagerPlaylistnhac adapternhac;
    Fragment_Dia_Nhac fragment_dia_nhac;
    Fragment_Play_Danh_Sach_Bai_Hat fragment_play_danh_sach_bai_hat;
    MediaPlayer mediaPlayer;
    int position = 0;
    boolean repeat = false;
    boolean check_random = false;
    boolean next = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        GetIntent();
        initView();
        eventClick();

    }

    private void eventClick() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(adapternhac.getItem(1) != null){
                    if(array_baihat.size()>0){
                        fragment_dia_nhac.playNhac(array_baihat.get(0).getHinhBaiHat());
                        handler.removeCallbacks(this);
                    }
                    else{
                        handler.postDelayed(this, 500);
                    }
                }
            }
        },500);

        ib_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    ib_play.setImageResource(R.drawable.iconplay);
                }
                else{
                    mediaPlayer.start();
                    ib_play.setImageResource(R.drawable.iconpause);
                }
            }
        });

        ib_repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(repeat == false){
                    if(check_random == true){
                        check_random = false;
                        ib_repeat.setImageResource(R.drawable.iconsyned);
                        ib_random.setImageResource(R.drawable.iconsuffle);
                    }

                    ib_repeat.setImageResource(R.drawable.iconsyned);
                    repeat = true;
                }

                else{
                    ib_repeat.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                }
            }
        });

        ib_random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check_random == false){
                    if(repeat == true){
                        repeat = false;
                        ib_random.setImageResource(R.drawable.iconshuffled);
                        ib_repeat.setImageResource(R.drawable.iconrepeat);
                    }

                    ib_random.setImageResource(R.drawable.iconshuffled);
                    check_random = true;
                }

                else{
                    ib_random.setImageResource(R.drawable.iconsuffle);
                    check_random = false;
                }
            }
        });

        sktime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        ib_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(array_baihat.size() > 0){
                    if(mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }

                    if(position < array_baihat.size()){
                        ib_play.setImageResource(R.drawable.iconpause);
                        position++;

                        if(repeat == true){
                            if(position == 0){
                                position = array_baihat.size();
                            }
                            position-=1;
                        }

                        if(check_random == true){
                            Random random = new Random();
                            int index = random.nextInt(array_baihat.size());
                            if(index == position){
                                position = index - 1;
                            }
                            position = index;
                        }

                        if(position > array_baihat.size() - 1){
                            position = 0;
                        }
                        new PlayMP3().execute(array_baihat.get(position).getLinkBaiHat());
                        fragment_dia_nhac.playNhac(array_baihat.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(array_baihat.get(position).getTenBaiHat());
                        updateTime();
                    }
                }
                ib_next.setClickable(false);
                ib_pre.setClickable(false);

                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ib_next.setClickable(true);
                        ib_pre.setClickable(true);
                    }
                }, 5000);
            }
        });

        ib_pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(array_baihat.size() > 0){
                    if(mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }

                    if(position < array_baihat.size()){
                        ib_play.setImageResource(R.drawable.iconpause);
                        position--;

                        if(position < 0){
                            position = array_baihat.size()-1;
                        }

                        if(repeat == true){
                            position += 1;
                        }

                        if(check_random == true){
                            Random random = new Random();
                            int index = random.nextInt(array_baihat.size());
                            if(index == position){
                                position = index - 1;
                            }
                            position = index;
                        }

                        new PlayMP3().execute(array_baihat.get(position).getLinkBaiHat());
                        fragment_dia_nhac.playNhac(array_baihat.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(array_baihat.get(position).getTenBaiHat());
                        updateTime();
                    }
                }
                ib_next.setClickable(false);
                ib_pre.setClickable(false);

                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ib_next.setClickable(true);
                        ib_pre.setClickable(true);
                    }
                }, 5000);
            }
        });
    }

    private void GetIntent() {
        Intent intent = getIntent();
        array_baihat.clear();

        if(intent != null){
            if(intent.hasExtra("cakhuc")){
                BaiHat baihat = intent.getParcelableExtra("cakhuc");
                array_baihat.add(baihat);
            }

            if(intent.hasExtra("cacbaihat")){
                ArrayList<BaiHat> array_baihat_fromIntent = intent.getParcelableArrayListExtra("cacbaihat");
                array_baihat = array_baihat_fromIntent;
            }
        }

    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar_playnhac);
        tv_timesong = findViewById(R.id.tv_timesong);
        tv_totaltimesong = findViewById(R.id.tv_totaltimesong);
        ib_play = findViewById(R.id.ib_play);
        ib_repeat = findViewById(R.id.ib_repeat);
        ib_pre = findViewById(R.id.ib_preview);
        ib_random = findViewById(R.id.ib_shuffle);
        ib_next = findViewById(R.id.ib_next);
        sktime = findViewById(R.id.seekbar_song);
        viewPager = findViewById(R.id.viewpager_playnhac);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                mediaPlayer.stop();
                array_baihat.clear();
            }
        });

        fragment_dia_nhac = new Fragment_Dia_Nhac();
        fragment_play_danh_sach_bai_hat = new Fragment_Play_Danh_Sach_Bai_Hat();

        adapternhac = new ViewPagerPlaylistnhac(getSupportFragmentManager());
        adapternhac.AddFragment(fragment_play_danh_sach_bai_hat);
        adapternhac.AddFragment(fragment_dia_nhac);


        viewPager.setAdapter(adapternhac);

        fragment_dia_nhac = (Fragment_Dia_Nhac) adapternhac.getItem(1);

        if(array_baihat.size()>0){
            getSupportActionBar().setTitle(array_baihat.get(0).getTenBaiHat());
            new PlayMP3().execute(array_baihat.get(0).getLinkBaiHat());
            ib_play.setImageResource(R.drawable.iconpause);
        }
    }

    class PlayMP3 extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                }
            });

            try {
                mediaPlayer.setDataSource(baihat);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }

            mediaPlayer.start();
            TimeSong();
            updateTime();
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        tv_totaltimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        sktime.setMax(mediaPlayer.getDuration());
    }

    private void updateTime(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer != null){
                    sktime.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    tv_timesong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }
            }
        }, 300);

        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(next == true){
                    if(array_baihat.size() > 0){
                        if(mediaPlayer.isPlaying() || mediaPlayer != null){
                            mediaPlayer.stop();
                            mediaPlayer.release();
                            mediaPlayer = null;
                        }

                        if(position < array_baihat.size()){
                            ib_play.setImageResource(R.drawable.iconpause);
                            position++;

                            if(repeat == true){
                                if(position == 0){
                                    position = array_baihat.size();
                                }
                                position-=1;
                            }

                            if(check_random == true){
                                Random random = new Random();
                                int index = random.nextInt(array_baihat.size());
                                if(index == position){
                                    position = index - 1;
                                }
                                position = index;
                            }

                            if(position > array_baihat.size() - 1){
                                position = 0;
                            }
                            new PlayMP3().execute(array_baihat.get(position).getLinkBaiHat());
                            fragment_dia_nhac.playNhac(array_baihat.get(position).getHinhBaiHat());
                            getSupportActionBar().setTitle(array_baihat.get(position).getTenBaiHat());
                        }
                    }
                    ib_next.setClickable(false);
                    ib_pre.setClickable(false);

                    Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ib_next.setClickable(true);
                            ib_pre.setClickable(true);
                        }
                    }, 5000);
                    next = false;
                    handler1.removeCallbacks(this);
                }
                else{
                    handler1.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }
}