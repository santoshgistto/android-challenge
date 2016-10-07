package com.santosh.sampleny.starships;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.santosh.sampleny.network.NetworkListner;
import com.santosh.sampleny.network.NetworkOperations;


/**
 * Created by santoshrd on 6/10/16.
 */

public class StartShipsImpl implements StartShipInteractor , NetworkListner {

    StartShipListener startShipListener;
    List<Results> resultsList = new ArrayList<>();
    Context context;
    @Override
    public void getStarShipsFromServer(StartShipListener listener, final Context context,int page) {
        startShipListener = listener;
        this.context = context;
        getAllStarships(context,page,this);
    }

    private void getAllStarships(final Context context,int page,NetworkListner networkListner){
        NetworkOperations.getInstance().loadStarshipsFromServer(context,this,page);
    }
    @Override
    public void onSuccess(final StarshipData starshipData, final int page) {
        // making 4 request one by one here bcz api doesn't support the startships limit count and filter.
        mergeStarships(starshipData.getResults());
        if (page == 0) {
            sortStarShips();
            startShipListener.onSuccess(resultsList);
        } else {
            getAllStarships(context,page,this);
        }
    }

    @Override
    public void onError(final String error) {

        startShipListener.onError(error);

    }

    private void mergeStarships(Results[] results) {
        resultsList.addAll(Arrays.asList(results));
    }


    private List<Results> removeUnknowns() {
        List<Results> templist = new ArrayList<>(resultsList);
        for (Results results : resultsList) {
            if (results.cost_in_credits.equals("unknown"))
                templist.remove(results);
        }
        return templist;
    }

    private void sortStarShips() {
        List<Results> tempList = removeUnknowns(); // need to remove unkown cost
        resultsList = tempList;
        Collections.sort(resultsList, new Comparator<Results>() {
            @Override
            public int compare(Results result1, Results result2) {

                return Long.compare(Long.parseLong(result2.cost_in_credits), Long.parseLong(result1.cost_in_credits));
            }
        });
    }
}
