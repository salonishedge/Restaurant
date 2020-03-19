package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Ocassion extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView textView,uid;
    private PreferenceHelper preferenceHelper;
    private String registerURL = "http://192.168.0.117/Tennis/simpleinputs.php";
    private RequestQueue rQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocassion);
        preferenceHelper = new PreferenceHelper(this);


        final String s = getIntent().getStringExtra("TextValue");
        final String r = getIntent().getStringExtra("TextValue1");
        radioGroup = findViewById(R.id.radioGroup);
        uid = (TextView) findViewById(R.id.uid);
        textView = findViewById(R.id.text_view_selected);


        Button buttonApply = findViewById(R.id.button_apply);

        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();

                radioButton = findViewById(radioId);

                uid.setText("Your id: " + preferenceHelper.getUSERID());

                textView.setText("Your choice: " + radioButton.getText());
                String occType= (String) radioButton.getText();
                SharedPreferences sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE);
                final String latitude = sharedPreferences.getString("value","");
                final String longitude = sharedPreferences.getString("value1","");


                final String cuisine = s;
                final String restaurant= r;
                final String ocassion= occType;





                StringRequest stringRequest = new StringRequest(Request.Method.POST, registerURL,
                        new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {

                                rQueue.getCache().clear();



                                Toast.makeText(Ocassion.this, response, Toast.LENGTH_LONG).show();

                                try {

                                    parseData(response);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        },

                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(Ocassion.this, error.toString(), Toast.LENGTH_LONG).show();

                            }
                        })
                {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("userid",preferenceHelper.getUSERID());
                        params.put("cuisine", cuisine);
                        params.put("restaurant", restaurant);
                        params.put("ocassion", ocassion);
                        params.put("latitude",latitude);
                        params.put("longitude",longitude);

                        return params;
                    }

                };


                rQueue = Volley.newRequestQueue(Ocassion.this);
                rQueue.add(stringRequest);

            }

            private void parseData(String response) throws JSONException {

                JSONObject jsonObject = new JSONObject(response);
                if (jsonObject.optString("status").equals("true")) {


                    saveInfo(response);


                    Toast.makeText(Ocassion.this, "Input!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Ocassion.this, WelcomeActivity.class);
                    intent.putExtra("TextValue1",r);
                    intent.putExtra("TextValue",s);
                    startActivity(intent);

                } else {

                    Toast.makeText(Ocassion.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                }
            }

            private void saveInfo(String response) {

                preferenceHelper.putIsLogin(true);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("status").equals("true")) {
                        JSONArray dataArray = jsonObject.getJSONArray("data");
                        for (int i = 0; i < dataArray.length(); i++) {

                            JSONObject dataobj = dataArray.getJSONObject(i);

                            preferenceHelper.putUSERID(dataobj.getString("userid"));
                            preferenceHelper.putCUISINE(dataobj.getString("cuisine"));
                            preferenceHelper.putRESTAURANT(dataobj.getString("restaurant"));
                            preferenceHelper.putOCASSION(dataobj.getString("ocassion"));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });

    }

    public void checkButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);

        Toast.makeText(this, "Selected Radio Button: " + radioButton.getText(),
                Toast.LENGTH_SHORT).show();
    }
}
