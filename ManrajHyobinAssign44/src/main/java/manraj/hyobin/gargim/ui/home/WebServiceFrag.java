package manraj.hyobin.gargim.ui.home;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

import manraj.hyobin.gargim.R;


public class WebServiceFrag extends Fragment {

    private Button webServiceButton;
    private TextView tvLong;
    private TextView tvLat;
    private TextView tvTemp;
    private TextView tvName;
    private TextView tvHumidity;
    private TextView tvZip;
    private EditText etZip;

    public WebServiceFrag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web_service, container, false);

        tvLong = (TextView)view.findViewById(R.id.manrajHyobinLong);
        tvLat = (TextView)view.findViewById(R.id.manrajHyobinLat);
        tvTemp = (TextView)view.findViewById(R.id.manrajHyobinTemp);
        tvName = (TextView)view.findViewById(R.id.manrajHyobinName);
        tvHumidity = (TextView)view.findViewById(R.id.manrajHyobinHumidity);
        tvZip = (TextView)view.findViewById(R.id.manrajHyobinZipcode);
        etZip = (EditText)view.findViewById(R.id.manrajHyobinZip);

        webServiceButton = view.findViewById(R.id.manrajHyobinWebServiceBtn);
        webServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWeather();
            }
        });



        return view;
    }


    public void getWeather()
    {
        //read geocoordinates from edit texts
        //get weather information using geo coordinates
        //this method calls OpenWeatherMap API
        //
        //create the URL to call JSON service
        //"http://api.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=13f04464b7119837cf1dc4fa8b39caa3");

        String url = "https://api.openweathermap.org/data/2.5/weather?";
        if (etZip.getText().length() == 0){
            etZip.setError("Zip cannot be empty");
        } else if (etZip.getText().length() > 0 && etZip.getText().length() < 5) {
            etZip.setError("Zip too short");
        } else {
            url += "zip=" + etZip.getText().toString();
            url += ",us&appid=23f04464b7119837cf1dc4fa8b39caa3"; //from OpenWeatherMap website
            Log.d("URL", url);
            new ReadJSONFeedTask().execute(url);
            //new ReadJSONFeedTask().execute(
            //        "https://api.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=13f04464b7119837cf1dc4fa8b39caa3");
        }
    }



    public String readJSONFeed(String address) {
        URL url = null;
        try {
            url = new URL(address);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        };
        StringBuilder stringBuilder = new StringBuilder();
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            InputStream content = new BufferedInputStream(
                    urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(content));
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return stringBuilder.toString();
    }
    private class ReadJSONFeedTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {
            return readJSONFeed(urls[0]);
        }

        protected void onPostExecute(String result) {
            try {
                JSONObject weatherJson = new JSONObject(result);
                DecimalFormat df = new DecimalFormat("0.00");
                JSONObject dataObject = weatherJson.getJSONObject("main");
                double tempC, tempDouble;
                String temp;
                temp = dataObject.getString("temp");
                tempDouble = Double.parseDouble(temp);
                tempC = tempDouble - 273.15;


                tvTemp.setText("temp: " + df.format(tempC) + "C");
                //tvTemp.setText("temp: " + dataObject.getString("temp"));
                tvHumidity.setText("humidity: " + dataObject.getString("humidity"));

                JSONObject coordObject = weatherJson.getJSONObject("coord");
                tvLat.setText("Lat: " + coordObject.getString("lat"));
                tvLong.setText("Long: " + coordObject.getString("lon"));

                tvZip.setText("Zip Code: " + etZip.getText().toString());
                tvName.setText("Name: " + weatherJson.getString("name"));



            } catch (Exception e) {
                if (e.toString().contains("org.json.JSONException: End of input at character 0 of")){
                    etZip.setError("That zip could not be found");
                } else {
                    etZip.setError(e.toString());
                }
            }
        }
    }
}