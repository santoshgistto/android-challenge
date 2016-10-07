package com.santosh.sampleny.starships;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import com.santosh.sampleny.R;


/**
 * Created by santoshrd on 6/10/16.
 */

public class StartshipAdapter extends RecyclerView.Adapter<StartshipAdapter.ViewHolder> {

    Context mContext;
    LayoutInflater inflater;
    List<Results> starshipDataList;

    public StartshipAdapter(Context context, List<Results> starshipDataList) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        this.starshipDataList = starshipDataList;
    }


    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {

        View view = inflater.inflate(R.layout.starship_row, parent, false);
        ViewHolder holder = new ViewHolder(view, mContext, starshipDataList);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.startShipName.setText("Name: "+starshipDataList.get(position).getName());
        holder.startShipsCost.setText("Cost: "+starshipDataList.get(position).getCost_in_credits());
        holder.starShipFilmLink.setText("Film: "+starshipDataList.get(position).getFilms()[0]); // currently setting lint of first film


    }

    @Override
    public int getItemCount() {
        return starshipDataList.size() == 15 ?starshipDataList.size() : 15;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView startShipName;
        TextView startShipsCost;
        TextView starShipFilmLink;
        Context context;
        List<Results> list = Collections.emptyList();


        public ViewHolder(View itemView, Context context, List<Results> list) {
            super(itemView);
            this.context = context;
            this.list = list;
            startShipName = (TextView) itemView.findViewById(R.id.title);
            startShipsCost = (TextView) itemView.findViewById(R.id.cost);
            starShipFilmLink = (TextView) itemView.findViewById(R.id.link);

        }


    }
}
