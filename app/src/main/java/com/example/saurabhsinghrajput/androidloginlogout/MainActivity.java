package com.example.saurabhsinghrajput.androidloginlogout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText txtemail,txtmob,txtpass,txtname;
    Button btnRegister;
    TextView txt;
    AlertDialog.Builder builder;
    String server_url="http://192.168.0.104/mysite/Android/Registration.php";
    networkHandler handler;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtemail=(EditText)findViewById(R.id.txtEmail_reg);
        txtmob=(EditText)findViewById(R.id.txtMob_reg);
        txtpass=(EditText)findViewById(R.id.txtPass_reg);
        txtname=(EditText)findViewById(R.id.txtName_reg);
        txt=(TextView)findViewById(R.id.txt_login_init);
        btnRegister=(Button)findViewById(R.id.btnRegistration_reg);
        builder=new AlertDialog.Builder(MainActivity.this);
        sharedPreferences=getSharedPreferences(loginactivity.Mypref, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(loginactivity.Name) && sharedPreferences.contains(loginactivity.Email) && sharedPreferences.contains(loginactivity.Email))
        {
            startActivity(new Intent(this,profileactivity.class));
        }
    }

    public void registration(View view)
    {
      if (validate())
      {
          StringRequest stringRequest=new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
              @Override
              public void onResponse(String response) {
                  builder.setTitle("Message");
                  builder.setMessage(response);
                  builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {
                         txtemail.setText("");
                          txtmob.setText("");
                          txtpass.setText("");
                          txtname.setText("");
                      }
                  });
                  builder.create();
                  builder.show();

              }
          }, new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {

              }
          }){
              @Override
              protected Map<String, String> getParams() throws AuthFailureError {

                  Map<String,String> params= new HashMap<String,String>();
                  params.put("name",txtname.getText().toString());
                  params.put("email",txtemail.getText().toString());
                  params.put("mobile",txtmob.getText().toString());
                  params.put("pass",txtpass.getText().toString());
                  return  params;
              }
          };

          handler=new networkHandler(MainActivity.this);
          handler.addrequestque(stringRequest);
      }
      else{
          Toast.makeText(this,"All fields are mandatory",Toast.LENGTH_SHORT).show();
      }
    }
  public void registerinit(View view)
  {
   startActivity(new Intent(this,loginactivity.class));
      finish();
  }

    public boolean validate()
    {
        boolean t=true;
        if ((txtemail.getText().toString()).equals("") | (txtmob.getText().toString()).equals("") | (txtpass.getText().toString()).equals("")  )
        {
           t=false;
        }



        return  t;

    }
}
