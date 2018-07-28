package com.example.abhishek.weatherforecast;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by abhishek on 28/7/18.
 */

public class WeatherForecastWithCurrentInfoAdapter extends RecyclerView.Adapter<WeatherForecastWithCurrentInfoAdapter.WeatherForecastWithCurrentInfoViewHolder>{

    private final Context mContext;

    private static final int VIEW_TYPE_TODAY = 0;
    private static final int VIEW_TYPE_FUTURE_DAY = 1;

    private boolean mUseTodayLayout;

    public WeatherForecastWithCurrentInfoAdapter(@NonNull Context context) {
        mContext = context;
        mUseTodayLayout = mContext.getResources().getBoolean(R.bool.use_today_layout);
    }

    @Override
    public WeatherForecastWithCurrentInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId;

        switch (viewType) {

            case VIEW_TYPE_TODAY: {
                layoutId = R.layout.current_weather_list_item;
                break;
            }

            case VIEW_TYPE_FUTURE_DAY: {
                layoutId = R.layout.forecast_weather_list_item;
                break;
            }

            default:
                throw new IllegalArgumentException("Invalid view type, value of " + viewType);
        }

        View view = LayoutInflater.from(mContext).inflate(layoutId, parent, false);
        view.setFocusable(true);

        return new WeatherForecastWithCurrentInfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeatherForecastWithCurrentInfoViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class WeatherForecastWithCurrentInfoViewHolder extends RecyclerView.ViewHolder {
        // final TextView weatherSummary;
        final TextView dateView;
        final TextView descriptionView;
        final TextView highTempView;
        final TextView lowTempView;
        final ImageView iconView;

        WeatherForecastWithCurrentInfoViewHolder(View view) {
            super(view);
            iconView = (ImageView) view.findViewById(R.id.weather_icon);
            dateView = (TextView) view.findViewById(R.id.date);
            descriptionView = (TextView) view.findViewById(R.id.weather_description);
            highTempView = (TextView) view.findViewById(R.id.high_temperature);
            lowTempView = (TextView) view.findViewById(R.id.low_temperature);

        }
    }
}
