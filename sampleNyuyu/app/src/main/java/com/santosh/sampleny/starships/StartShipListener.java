package com.santosh.sampleny.starships;

import java.util.List;

/**
 * Created by santoshrd on 6/10/16.
 */

public interface StartShipListener {

    public void onSuccess(List<Results> list);
    public void onError(String error);


}
