package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    private TextView tvname,tvuserid,usercuisine,restotype,occtype,lat,longi;
    private Button btnlogout;
    private PreferenceHelper preferenceHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        String s = getIntent().getStringExtra("TextValue");
        String r = getIntent().getStringExtra("TextValue1");
        String o = getIntent().getStringExtra("TextValue2");

        preferenceHelper = new PreferenceHelper(this);
        tvuserid = (TextView) findViewById(R.id.tvuserid);
        tvname = (TextView) findViewById(R.id.tvname);
        usercuisine = (TextView) findViewById(R.id.cuisinetype);
        restotype = (TextView) findViewById(R.id.restotype);
        occtype = (TextView) findViewById(R.id.occtype);
        lat =  findViewById(R.id.lat);
        longi =  findViewById(R.id.longi);
        btnlogout = (Button) findViewById(R.id.btn);
        SharedPreferences sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE);
        String latitude = sharedPreferences.getString("value","");
        String longitude = sharedPreferences.getString("value1","");


        usercuisine.setText("Cusine Type selected : "+preferenceHelper.getCUISINE());
        restotype.setText("Resto type :"+preferenceHelper.getRESTAURANT());
        occtype.setText("Ocassion :"+preferenceHelper.getOCASSION());

        tvname.setText("Username: "+preferenceHelper.getUSERNAME());
        tvuserid.setText("Userid: "+preferenceHelper.getUSERID());
        lat.setText("latitude: "+latitude);
        longi.setText("longitude: "+longitude);
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferenceHelper.putIsLogin(false);
                Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);
                WelcomeActivity.this.finish();
            }
        });

    }
}