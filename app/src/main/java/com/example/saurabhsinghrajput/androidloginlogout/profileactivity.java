package com.example.saurabhsinghrajput.androidloginlogout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.saurabhsinghrajput.androidloginlogout.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by SAURABH SINGH RAJPUT on 5/14/2017.
 */

public class profileactivity extends AppCompatActivity {
    Button btnres;
    TextView txt_name,txt_em,txt_mob;
    String server_url="http://192.168.0.104/mysite/Android/values.php";
    networkHandler handler;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profileactivity);
        txt_name=(TextView)findViewById(R.id.txt_name_pro);
        txt_em= (TextView)findViewById(R.id.txt_profile_em);
        txt_mob=(TextView)findViewById(R.id.txt_mob_pro);
        btnres=(Button)findViewById(R.id.btnLogout);

        sharedPreferences=getSharedPreferences(loginactivity.Mypref, Context.MODE_PRIVATE);
        txt_name.setText(sharedPreferences.getString(loginactivity.Name,""));
        txt_em.setText(sharedPreferences.getString(loginactivity.Email,""));
        txt_mob.setText(sharedPreferences.getString(loginactivity.Mobile,""));

    }

    public void responce(View view)
    {

    SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.commit();
        startActivity(new Intent(this,loginactivity.class));
        finish();




        //RequestQueue requestQueue= Volley.newRequestQueue(profileactivity.this);
       /* StringRequest stringRequest=new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                txtGret.setText(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               txtGret.setText("Something Error...");
                error.printStackTrace();
            }
        });

        --->json object request
       JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, server_url, null, new Response.Listener<JSONObject>() {
           @Override
           public void onResponse(JSONObject response) {
               try {
                   txtGret.setText(response.getString("Name")+" "+response.getString("Mobile"));
               } catch (JSONException e) {
                   e.printStackTrace();
               }

           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
            txtGret.setText("Something Error...");
           }
       });
        handler=new networkHandler(profileactivity.this);
        handler.addrequestque(jsonObjectRequest);*/

    }

}
