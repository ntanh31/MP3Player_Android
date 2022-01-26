package org.o7planning.mp3player.Service;

import org.o7planning.mp3player.Model.Album;
import org.o7planning.mp3player.Model.BaiHat;
import org.o7planning.mp3player.Model.ChuDe;
import org.o7planning.mp3player.Model.Playlist;
import org.o7planning.mp3player.Model.Quangcao;
import org.o7planning.mp3player.Model.TheLoai;
import org.o7planning.mp3player.Model.TheLoaiTrongNgay;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {

    @GET("songbanner.php")
    Call<List<Quangcao>> GetDataBanner();

    @GET("playlistforcurrentday.php")
    Call<List<Playlist>> GetPlaylistCurrentDay();

    @GET("chudevatheloaitrongngay.php")
    Call<TheLoaiTrongNgay> getCategoryMusic();

    @GET("albumhot.php")
    Call<List<Album>> getAlbumHot();

    @GET("baihatduocthich.php")
    Call<List<BaiHat>> getBaiHatHot();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> Getdanhsachbaihattheoquangcao(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> Getdanhsachbaihattheoplaylist(@Field("idplaylist") String idplaylist);

    @GET("danhsachcacplaylist.php")
    Call<List<Playlist>> getDanhsachcacplaylist();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> Getdanhsachbaihattheotheloai(@Field("idtheloai") String idtheloai);

    @GET("tatcachude.php")
    Call<List<ChuDe>> Gettatcachude();

    @FormUrlEncoded
    @POST("theloaitheochude.php")
    Call<List<TheLoai>> Gettheloaitheochude(@Field("idchude") String idchude);

    @GET("tatcaalbum.php")
    Call<List<Album>> Gettatcaalbum();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> Getdanhsachbaihattheoalbum(@Field("idalbum") String idalbum);

    @FormUrlEncoded
    @POST("updateluotthich.php")
    Call<String> Updateluotthich(@Field("luotthich") String luotthich, @Field("idbaihat") String idbaihat);

    @FormUrlEncoded
    @POST("search.php")
    Call<List<BaiHat>> Getsearchbaihat(@Field("tukhoa") String tukhoa);
}
