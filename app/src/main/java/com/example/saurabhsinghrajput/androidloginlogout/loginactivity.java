package com.example.saurabhsinghrajput.androidloginlogout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SAURABH SINGH RAJPUT on 5/14/2017.
 */

public class loginactivity extends AppCompatActivity {
    Button btnLogin,btnRegisterInit;
    EditText txtEmail,txtPass;
    TextView txt_reg;
    String server_url="http://192.168.0.104/mysite/Android/Login.php";
    AlertDialog.Builder builder;
    SharedPreferences sharedPreferences;
    public static final String Mypref="mypref";
    public static final String Name="name";
    public static final String Email="email";
    public static final String Mobile="mobile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        btnLogin=(Button)findViewById(R.id.btnLogin);
        txt_reg=(TextView)findViewById(R.id.txt_reg_btn);
        txtEmail=(EditText)findViewById(R.id.txtEmail);
        txtPass=(EditText)findViewById(R.id.txtPass);
        builder=new AlertDialog.Builder(this);
        sharedPreferences=getSharedPreferences(Mypref, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(Name) && sharedPreferences.contains(Email) && sharedPreferences.contains(Email))
        {
            startActivity(new Intent(this,profileactivity.class));
        }
    }

    public void login(View view)
    {
        if (validate())
        {
            StringRequest stringRequest=new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray Jsonarray=new JSONArray(response);
                        JSONObject Jsonobject=Jsonarray.getJSONObject(0);
                        String code=Jsonobject.getString("code");
                           if (code.equals("successfull"))
                           {
                               String name=Jsonobject.getString("Name");
                               String email=Jsonobject.getString("Email");
                               String mobile=Jsonobject.getString("Mobile");
                               Intent intent=new Intent(loginactivity.this,profileactivity.class);
                               SharedPreferences.Editor editor=sharedPreferences.edit();
                               editor.putString(Name,name);
                               editor.putString(Email,email);
                               editor.putString(Mobile,mobile);
                               editor.commit();
                               startActivity(intent);
                               finish();

                           }
                           else if(code.equals("unsuccessfull"))
                           {
                               String message=Jsonobject.getString("message");
                               message("Something Went wrong",message);

                           }
                           else if(code.equals("bad_request"))
                           {
                               String message=Jsonobject.getString("message");
                               message("Something went wrong",message);

                           }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(loginactivity.this, "Something Went wrong", Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params=new HashMap<String,String>();
                    params.put("email",txtEmail.getText().toString());
                    params.put("pass",txtPass.getText().toString());
                    return params;
                }
            };

            networkHandler handler=new networkHandler(loginactivity.this);
            handler.addrequestque(stringRequest);
        }
        else{
            Toast.makeText(this,"All fields are mandatory",Toast.LENGTH_SHORT).show();
        }
    }

    public void login_init(View view)
    {
        startActivity(new Intent(loginactivity.this,MainActivity.class));
        finish();
    }

    public boolean validate()
    {    boolean p=false;
        String email,pass;
        email=txtEmail.getText().toString();
        pass=txtPass.getText().toString();
        if ((email.equals("")) | (pass.equals("")))
        {

            p=false;
        }
        else
        {
            p= true;
        }

       return p;

    }
    public void message(String title,String message)
    {
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                txtEmail.setText("");
                txtPass.setText("");
            }
        });
        builder.create();
        builder.show();

    }

}
