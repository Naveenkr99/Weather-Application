package com.example.whatstheweather;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Information extends AppCompatActivity {
    ImageView imageView2;
    TextView textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        imageView2=findViewById(R.id.imageView2);
        textView2=findViewById(R.id.textView2);

        Intent intent=getIntent();
        String d=intent.getStringExtra("des");
        String p =intent.getStringExtra("pres");
        String te=intent.getStringExtra("temp");
        String h=intent.getStringExtra("humid");
        String ws=intent.getStringExtra("windSpeed");
      String city=intent.getStringExtra("city");
       //Log.i("weather:",msgg);
        textView2.setText("City : "+city+"\n"+"Climate : "+d+"\n"+"pressure :"+p+" P"
                +"\n"+"temp :"+te+" C"+"\n"+"Humidity :"+h+" g/m3"+"\n"+"Wind Speed :"+ws +"km/h");


        switch(d){
            case "rain":imageView2.setImageResource(R.drawable.rain);break;
            case "haze":imageView2.setImageResource(R.drawable.hazee);break;
            case "mist":imageView2.setImageResource(R.drawable.mist);break;
            case "clouds":imageView2.setImageResource(R.drawable.cloud);break;
            case "broken clouds":imageView2.setImageResource(R.drawable.cloud);break;
            case "thunderstorm":imageView2.setImageResource(R.drawable.thunderr);break;
            case "clear":imageView2.setImageResource(R.drawable.clear);break;
            case "clear sky":imageView2.setImageResource(R.drawable.clear);break;
            case "smoke":imageView2.setImageResource(R.drawable.mist);break;
        }
    }
    public void askAgainn(View view){
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        finish();

    }
}
