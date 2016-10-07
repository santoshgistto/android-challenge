package com.santosh.sampleny.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class AppNetworkController {
    public static final String TAG = AppNetworkController.class.getSimpleName();
    private static AppNetworkController appNetworkController;
    private RequestQueue requestQueue;
    private Context context;

    public AppNetworkController(Context context) {
        this.context = context;

    }

    public static synchronized AppNetworkController getInstance(Context context) {
        if (appNetworkController == null) {
            appNetworkController = new AppNetworkController(context);
        }
        return appNetworkController;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context);
        }
        return requestQueue;
    }

    public <T> void addRequestQueue(Request<T> request) {
        request.setTag(TAG);
        getRequestQueue().add(request);
    }


}
