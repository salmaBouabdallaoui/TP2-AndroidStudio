package com.salma.estonews.helpers;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.salma.estonews.ui.HomeActivity;

public class VolleyHelper {
    private static VolleyHelper instance;
    private RequestQueue requestQueue;
    private static Context ctx;
    private VolleyHelper(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();
    }
    public static synchronized VolleyHelper getInstance(HomeActivity context) { if (instance == null) {
        instance = new VolleyHelper(context);
    }
        return instance;
    }
    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext()); }
        return requestQueue;
    }
    public <T> void addToRequestQueue(StringRequest req) {
        getRequestQueue().add(req);
    }
}
