package com.example.abhishek.weatherforecast;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abhishek.weatherforecast.Model.models.forecastWeatherModels.forecastWeatherBusiness.WeatherListBusinessModel;

import java.util.List;

/**
 * Created by abhishek on 26/12/18.
 */

public class ForecastInnerAdapter extends RecyclerView.Adapter<ForecastInnerAdapter.ForecastInnerVH> {

    List<WeatherListBusinessModel> list;
    Context context;

    public ForecastInnerAdapter(List<WeatherListBusinessModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ForecastInnerVH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.forecast_list_item, parent, false);
        ForecastInnerVH mForecastInnerVH = new ForecastInnerVH(view);
        return mForecastInnerVH;
    }

    @Override
    public void onBindViewHolder(ForecastInnerVH holder, int position) {

        double highInCelsius = list.get(position).getMainBusinessModel().getTempMax();
        String highString = Utils.formatTemperature(context, highInCelsius);
        String highA11y = context.getString(R.string.a11y_high_temp, highString);

        holder.tv_forecast_update_temperature.setText(highString);
        holder.tv_forecast_update_temperature.setText(highA11y);

        holder.tv_forecast_update_weather_description.setText(list.get(position).getWeatherInfoBusinessModel().get(0).getDescription());
        holder.tv_weather_update_time.setText(Utils.getTime(context,list.get(position).getDt()));

        int weatherImageId = Utils
                .getSmallArtResourceIdForWeatherCondition((list
                        .get(position).getWeatherInfoBusinessModel().get(0).getId()));
        holder.iv_forecast_update_weather_icon.setImageResource(weatherImageId);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ForecastInnerVH extends RecyclerView.ViewHolder {

        TextView tv_forecast_update_temperature, tv_forecast_update_weather_description, tv_weather_update_time;
        ImageView iv_forecast_update_weather_icon;

        public ForecastInnerVH(View itemView) {
            super(itemView);
            tv_forecast_update_temperature = (TextView) itemView.findViewById(R.id.tv_forecast_update_temerature);
            tv_forecast_update_weather_description = (TextView) itemView.findViewById(R.id.tv_forecast_update_weather_description);
            tv_weather_update_time = (TextView) itemView.findViewById(R.id.tv_weather_update_time);

            iv_forecast_update_weather_icon = itemView.findViewById(R.id.iv_forecast_update_weather_icon);

        }
    }

}
