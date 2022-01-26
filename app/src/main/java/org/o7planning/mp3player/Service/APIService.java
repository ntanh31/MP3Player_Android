package org.o7planning.mp3player.Service;

public class APIService {
    private static String base_url = "https://anhnguyendb.000webhostapp.com/public_html/Server/";

    public static DataService getService(){
        return APIRetrofitClient.getClient(base_url).create(DataService.class);
    }
}
