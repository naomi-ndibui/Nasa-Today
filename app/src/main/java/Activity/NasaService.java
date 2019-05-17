package Activity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class NasaService {
    public static void findSpace(String date, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.Nasa_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.Nasa_date_QUERY_PARAMETER, String.valueOf(date));
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", Constants.Nasa_Token)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Space> processResults(Response response) {
        ArrayList<Space> space = new ArrayList<>();
        try {
            String jsonData = response.body().string();
            JSONObject NasaJSON = new JSONObject(jsonData);
            JSONObject view = NasaJSON.getJSONObject("view");
            String image = view.getString("image");
            String credits = view.getString("credits");
            String title = view.getString("title");
            String explanation = view.getString("explanation");
            String date = view.getString("date");

            Space space1 = new Space(image, credits, title, explanation, date);
            space.add(space1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return space;
    }

    }