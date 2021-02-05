package com.example.goflight;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity2 extends AppCompatActivity {
EditText from,to;
Button searchflights;
Spinner place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        from =findViewById(R.id.ETPlaceName);
        to =findViewById(R.id.ETCountryName);
        place  = (Spinner)findViewById(R.id.spPlaceName);
        searchflights =findViewById(R.id.btnsearchflights);


        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/autosuggest/v1.0/UK/GBP/en-GB/?query=Stockholm")
                .get()
                .addHeader("x-rapidapi-key", "ed88dd936bmsh74cb86767571ccfp1738aajsna4d12bb0a878")
                .addHeader("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
                .build();
       /* try (  Response responses = client.newCall(request).execute()){
            String jsonData = null;
            JSONObject Jobject = null;
            JSONArray Jarray = null;
            jsonData = responses.body().string();
            Jobject = new JSONObject(jsonData);
            Jarray = Jobject.getJSONArray("Places");
            for (int i = 0; i < Jarray.length(); i++) {
                try {
                    JSONObject object = Jarray.getJSONObject(i);

                    from.setText(object.getString("PlaceName"));
                    to.setText(object.getString("CountryName"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }*/




        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myresponse =  response.body().string();
                    MainActivity2.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject json = new JSONObject(myresponse);
                                JSONArray placesArray = json.getJSONArray("Places");
                                //JSONObject object = placesArray.getJSONObject(0);

                                List<String> list = new ArrayList<String>();

                                HashMap<String, String> map = new HashMap<String, String>();
                                for (int i = 0; i < placesArray.length(); i++) {
                                    JSONObject object1 = placesArray.getJSONObject(i);
                                    from.setText(object1.getString("PlaceName"));
                                    to.setText(object1.getString("CountryName"));
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }


            }
        });
    }
}