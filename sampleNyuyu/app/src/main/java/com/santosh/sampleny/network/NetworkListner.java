package com.santosh.sampleny.network;


import com.santosh.sampleny.starships.StarshipData;

/**
 * Created by santoshrd on 6/10/16.
 */

public interface NetworkListner {

    public void onSuccess(StarshipData starshipData, int pages);

    public void onError(String error);
}
