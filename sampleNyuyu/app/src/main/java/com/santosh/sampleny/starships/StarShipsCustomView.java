package com.santosh.sampleny.starships;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import com.santosh.sampleny.R;


/**
 * Created by santoshrd on 6/10/16.
 */

public class StarShipsCustomView extends FrameLayout implements StartShipListener {

    RecyclerView recyclerView;
    TextView mTitle;
    TextView mErrorTextView;
    ProgressBar mProgressBar;
    RelativeLayout progressContainer;
    FrameLayout layout;
    StartshipAdapter startshipAdapter;
    StartShipsImpl startShips;


    public StarShipsCustomView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layout = (FrameLayout) mInflater.inflate(R.layout.starship, null);
        recyclerView = (RecyclerView) layout.findViewById(R.id.storeslist);
        progressContainer = (RelativeLayout) layout.findViewById(R.id.progressContainer);
        mTitle = (TextView) layout.findViewById(R.id.title);
        mErrorTextView = (TextView) layout.findViewById(R.id.errorText);
        mProgressBar = (ProgressBar) layout.findViewById(R.id.progressBar);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layout.setLayoutParams(params);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        startShips = new StartShipsImpl();
        addView(layout);
    }

    public void setTitle(int res) {
        mTitle.setText(getContext().getString(res));
    }

    public void loadData(int page) {
        startShips.getStarShipsFromServer(this, getContext(), page);
    }


    private void setUpAdapter(List<Results> list) {
        startshipAdapter = new StartshipAdapter(getContext(), list);
        recyclerView.setAdapter(startshipAdapter);
    }


    @Override
    public void onSuccess(List<Results> list) {
     setUpAdapter(list);
     hideProgressContainer();
    }

    @Override
    public void onError(String error) {
        showErrorMessage(error);
    }

    protected void hideProgressContainer() {
        progressContainer.setVisibility(GONE);
    }

    protected void showErrorMessage(String reason) {
        progressContainer.setVisibility(VISIBLE);
        mProgressBar.setVisibility(GONE);
        mErrorTextView.setVisibility(VISIBLE);
        mErrorTextView.setText(reason);
    }



}
