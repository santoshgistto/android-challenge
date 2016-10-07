package com.santosh.sampleny.network;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.santosh.sampleny.AppUtils;
import com.santosh.sampleny.starships.StarshipData;


/**
 * Created by santoshrd on 6/10/16.
 */

public class NetworkOperations {
    public static final String TAG = "NetworkOperations";
    public static NetworkOperations networkOperations;

    public static NetworkOperations getInstance() {
        if (networkOperations == null) {
            networkOperations = new NetworkOperations();
        }
        return networkOperations;
    }

    public void loadStarshipsFromServer(final Context context, final NetworkListner listener, final int page) {

        final String url = "http://swapi.co/api/starships/?page=" + page;
        if (!AppUtils.isNetworkAvialable(context)) {
            listener.onError("No Network :(");
            return;
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        StarshipData data = new Gson().fromJson(response.toString(), StarshipData.class);
                        Log.v(TAG, "url-->" + url);
                        Log.v(TAG, "page-->" + page);
                        int pages = 0;
                        if (data.getNext() != null && page <= 3) {
                            Uri uri = Uri.parse(data.getNext());
                            pages = Integer.parseInt(uri.getQueryParameter("page"));
                        }
                        listener.onSuccess(data, pages);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                String errorStr = error.getMessage();
                if (errorStr == null)
                    errorStr = "Something Went Wrong Please try again !";
                listener.onError(errorStr);
            }
        });
        AppNetworkController.getInstance(context).addRequestQueue(jsonObjectRequest);


    }

}
