package Activity;

import java.util.Date;

import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;


public class NasaService {
    public static void(Date date, Boolean hd, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.Nasa_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.Nasa_date_QUERY_PARAMETER, String.valueOf(date));
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", Constants.Nasa_Token)
                .build();

    }
}