package com.santosh.sampleny.starships;

import android.content.Context;

/**
 * Created by santoshrd on 6/10/16.
 */

public interface StartShipInteractor {

    public void getStarShipsFromServer(StartShipListener listener,Context context, int page);
}
