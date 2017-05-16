package com.example.saurabhsinghrajput.androidloginlogout;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by SAURABH SINGH RAJPUT on 5/15/2017.
 */

public class networkHandler {
    private static RequestQueue requestQueue;
    private Context mcontaxt;


    public networkHandler(Context context)
    {
        mcontaxt=context;
        requestQueue=requestHandler();

    }
    public RequestQueue requestHandler()
    {
        if (requestQueue==null)
        {
            requestQueue=Volley.newRequestQueue(mcontaxt.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addrequestque(Request<T> request)
    {
        requestQueue.add(request);
    }




}
