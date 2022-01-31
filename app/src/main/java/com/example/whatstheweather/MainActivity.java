package com.example.whatstheweather;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    String encodedCityName="";
    TextView textView2;
    String message =" ";
    String mainn=" ";
     String id="";
    ImageView imageView;
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.editText);
       // textView2=findViewById(R.id.textView2);
         textView=findViewById(R.id.textView);
         imageView=findViewById(R.id.imageView);
         button =findViewById(R.id.button);


    }
    public void getWeather(View view){
        //       // textView.setVisibility(View.GONE);
       // editText.setVisibility(View.GONE);
      //  button.setVisibility(View.GONE);
        askAgain();

    }
  //  boolean flag=false;
    public void askAgain(){

        try {
            DownloadTask task = new DownloadTask();
            encodedCityName = URLEncoder.encode(editText.getText().toString(), "UTF-8");

            task.execute("https://openweathermap.org/data/2.5/weather?q=" + encodedCityName + "&appid=439d4b804bc8187953eb36d2a8c26a02");
            InputMethodManager mngr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            mngr.hideSoftInputFromWindow(editText.getWindowToken(), 0);

        }catch(Exception e){
            //Toast.makeText(getApplicationContext(),"could not find weather", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }


    }
        public class DownloadTask extends AsyncTask<String,Void,String> {

            @Override
            protected String doInBackground(String... urls) {
                String result = "";
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    url = new URL(urls[0]);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream in = urlConnection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(in);
                    int data = reader.read();
                    while (data != -1) {
                        char current = (char) data;
                        result += current;
                        data = reader.read();
                    }
                    return result;

                } catch (Exception e) {
                  //  e.printStackTrace();
                    //textView.setText("wrong");
                   //Toast.makeText(getApplicationContext(),"could not find weather", Toast.LENGTH_SHORT).show();
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    JSONObject jsonObject1 = new JSONObject(s);
                    String weatherInfo = jsonObject1.getString("weather");
                    Log.i("weather info", weatherInfo);
                    JSONArray jsonArray = jsonObject1.getJSONArray("weather");
                    JSONObject jsonObjectWeather=jsonArray.getJSONObject(0);
                    String description=jsonObjectWeather.getString("description");
                    JSONObject jsonObjectMain=jsonObject1.getJSONObject("main");
                    double temp=jsonObjectMain.getDouble("temp");
                    double feelslike=jsonObjectMain.getDouble("feels_like");
                    float pressure=jsonObjectMain.getInt("pressure");
                    int humidity=jsonObjectMain.getInt("humidity");
                    JSONObject jsonObjectWind=jsonObject1.getJSONObject("wind");
                    float windSpeed=jsonObjectWind.getInt("speed");
                    String humid=Integer.toString(humidity);
                    String p=Float.toString(pressure);
                    String tempe=Double.toString(temp);
                    String speed=Float.toString(windSpeed);
                    Log.i("des",description);
                    Log.i("pressure",p+" p");
                    Log.i("temp",tempe+" C");
                    Log.i("humid",humid+" g/m3");
                    Log.i("wind speed",speed);
                    Intent intent=new Intent(getApplicationContext(),Information.class);
                    intent.putExtra("des",description);
                    intent.putExtra("pres",p);
                    intent.putExtra("temp",tempe);
                    intent.putExtra("humid",humid);
                    intent.putExtra("windSpeed",speed);
                    intent.putExtra("city",encodedCityName);

                   startActivity(intent);


                }

                catch(Exception e){
                        //textView.setText("wrong");
                        Toast.makeText(getApplicationContext(),"could not find weather", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                }
            }
        }


