package com.example.abhishek.weatherforecast;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abhishek.weatherforecast.Model.models.forecastWeatherModels.forecastWeatherBusiness.WeatherListBusinessModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by abhishek on 26/12/18.
 */

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastVH> {

    LinkedHashMap<String,List<WeatherListBusinessModel>> map ;
    private final Context mContext;
    List<ForecastInnerAdapter> forecastInnerAdapters;
    private RecyclerView.RecycledViewPool recyclerViewPool;
    List<String> days = new ArrayList<>();

    public ForecastAdapter(LinkedHashMap<String, List<WeatherListBusinessModel>> map, Context mContext) {
        this.map = map;
        this.mContext = mContext;
        recyclerViewPool = new RecyclerView.RecycledViewPool();

        this.forecastInnerAdapters = new ArrayList<>(map.size());
        for (Map.Entry<String, List<WeatherListBusinessModel>> entry : map.entrySet()){
            forecastInnerAdapters.add(new ForecastInnerAdapter(entry.getValue(), mContext));
            days.add(Utils.getDateString(mContext,entry.getValue().get(0).getDt()));

    }
}

    @Override
    public ForecastVH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.forecast_weather_list, parent, false);
        ForecastVH mForecastVH = new ForecastVH(view);
        mForecastVH.inner_recyclerview_forecast.setNestedScrollingEnabled(false);
        mForecastVH.inner_recyclerview_forecast.setRecycledViewPool(recyclerViewPool);
        return mForecastVH;
    }

    @Override
    public void onBindViewHolder(ForecastVH holder, int position) {

        holder.tv_weather_update_day.setText(days.get(position));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext,
                LinearLayoutManager.HORIZONTAL, false);
        holder.inner_recyclerview_forecast.setLayoutManager(linearLayoutManager);
        holder.inner_recyclerview_forecast.setAdapter(forecastInnerAdapters.get(position));
    }

    @Override
    public int getItemCount() {
        return map.size();
    }

    class ForecastVH extends RecyclerView.ViewHolder {

        TextView tv_weather_update_day;
        RecyclerView inner_recyclerview_forecast;

        public ForecastVH(View itemView) {
            super(itemView);
            tv_weather_update_day = (TextView) itemView.findViewById(R.id.tv_weather_update_day);
            inner_recyclerview_forecast = itemView.findViewById(R.id.inner_recyclerview_forecast);

        }
    }
}
