package com.example.abhishek.weatherforecast;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.abhishek.weatherforecast.Model.models.currentWeatherModels.currentWeatherBusiness.CurrentWeatherBusinessModel;
import com.example.abhishek.weatherforecast.Model.models.forecastWeatherModels.forecastWeatherBusiness.MainBusinessModel;
import com.example.abhishek.weatherforecast.Model.models.forecastWeatherModels.forecastWeatherBusiness.WeatherInfoBusinessModel;
import com.example.abhishek.weatherforecast.Model.models.forecastWeatherModels.forecastWeatherBusiness.WeatherListBusinessModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by abhishek on 28/7/18.
 */

public class CurrentWeatherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final Context mContext;
    private List<IWeatherDetails> iWeatherDetailsList;

    private static final int VIEW_TYPE_TODAY = 0;
    private static final int VIEW_TYPE_EMPTY = 2;

    private boolean isLoading = false;

    public CurrentWeatherAdapter(@NonNull Context context, List<IWeatherDetails> iWeatherDetails) {
        this.mContext = context;
        this.iWeatherDetailsList = iWeatherDetails;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId;

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view;

        switch (viewType) {

            case VIEW_TYPE_TODAY: {
                layoutId = R.layout.current_weather_list_item;
                view = inflater.inflate(layoutId, parent, false);
                return new CurrentWeatherViewHolder(view);
            }
            default:
                view = inflater.inflate(R.layout.weather_list_item_empty,
                        parent, false);
                EmptyListViewHolder emptyListViewHolder = new EmptyListViewHolder(view);
                return emptyListViewHolder;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof EmptyListViewHolder){
            ((EmptyListViewHolder) holder).emptyListMessage.setText(
                    isLoading() ?
                    "Loading...." :
                    "No data found!");
        }
        else if (holder instanceof CurrentWeatherViewHolder) {
            IWeatherDetails item = iWeatherDetailsList.get(position);
            ((CurrentWeatherViewHolder) holder).bind((CurrentWeatherBusinessModel) item);
        }
    }

    @Override
    public int getItemCount() {
        int listSize = iWeatherDetailsList.size();
        return listSize < 1 || isLoading() ? 1 : listSize;

    }

    @Override
    public int getItemViewType(int position) {

        if(iWeatherDetailsList.size() < 1 || isLoading()){
            return VIEW_TYPE_EMPTY;
        }else {
            return VIEW_TYPE_TODAY;
        }

    }

    public void setWeatherList(List<IWeatherDetails> weatherList){
        this.iWeatherDetailsList = weatherList;
        notifyDataSetChanged();
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
        notifyDataSetChanged();
    }

    public boolean isLoading() {
        return isLoading;
    }

    class CurrentWeatherViewHolder extends RecyclerView.ViewHolder{

        TextView tv_current_weather_date, tv_current_weather_description, tv_current_weather_high_temperature,
                tv_current_weather_low_temperature, tb_current_updated, tvCurrentLocation;
        ImageView img_current_weather_icon;

        public CurrentWeatherViewHolder(View itemView) {
            super(itemView);
            tv_current_weather_date = itemView.findViewById(R.id.tv_current_weather_date);
            tv_current_weather_description = itemView.findViewById(R.id.tv_current_weather_description);
            tv_current_weather_high_temperature = itemView.findViewById(R.id.tv_current_weather_high_temperature);
            tv_current_weather_low_temperature = itemView.findViewById(R.id.tv_current_weather_low_temperature);
            img_current_weather_icon = itemView.findViewById(R.id.img_current_weather_icon);
            tb_current_updated = itemView.findViewById(R.id.tb_current_updated);
            tvCurrentLocation = itemView.findViewById(R.id.tvCurrentLocation);

        }

        void bind(CurrentWeatherBusinessModel currentWeather){

            //SET LOCATION
            tvCurrentLocation.setText("at, "+ Utils.SettingsUtils.getPreferredLocation(mContext));

            //SET DESC
            String description = Utils.getStringForWeatherCondition(mContext, currentWeather.getCurrentWeatherInfoBusinessModel().get(0).getWeatherId());
            String descriptionA11y = mContext.getString(R.string.a11y_forecast, description);
            tv_current_weather_description.setText(description);
//            tv_current_weather_description.setContentDescription(descriptionA11y);

            //SET MAX TEMP
            double highInCelsius = currentWeather.getCurrentWeatherMainBusinessModel().getTempMax();
            String highString = Utils.formatTemperature(mContext, highInCelsius);
            String highA11y = mContext.getString(R.string.a11y_high_temp, highString);
            tv_current_weather_high_temperature.setText(highString);
//            tv_current_weather_high_temperature.setContentDescription(highA11y);


            //SET MIN TEMP
            double lowInCelsius = currentWeather.getCurrentWeatherMainBusinessModel().getTempMin();
            String lowString = Utils.formatTemperature(mContext, lowInCelsius);
            String lowA11y = mContext.getString(R.string.a11y_low_temp, lowString);
            tv_current_weather_low_temperature.setText(lowString);
//            tv_current_weather_low_temperature.setContentDescription(lowA11y);

            //SET ICON
            int weatherImageId = Utils
                    .getLargeArtResourceIdForWeatherCondition((currentWeather
                            .getCurrentWeatherInfoBusinessModel()
                            .get(0)
                            .getWeatherId()));
            img_current_weather_icon.setImageResource(weatherImageId);

            //SET DATE
            long dateinSec = currentWeather.getDt();
            String dateString = Utils.getDateString(mContext, dateinSec);
            tv_current_weather_date.setText(dateString);

            //SET LAST UPDATED TIME
            SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");

            tb_current_updated.setText(sdf.format(new Date(dateinSec*1000L)));


        }
    }

    class EmptyListViewHolder extends RecyclerView.ViewHolder {

        TextView emptyListMessage;

        public EmptyListViewHolder(View itemView) {
            super(itemView);
            emptyListMessage = (TextView) itemView.findViewById(R.id.author_empty_message);

        }
    }

}
