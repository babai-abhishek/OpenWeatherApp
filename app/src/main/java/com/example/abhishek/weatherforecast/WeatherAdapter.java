package com.example.abhishek.weatherforecast;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherBusiness.CurrentWeatherBusinessModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherBusiness.WeatherListBusinessModel;

import java.util.List;

/**
 * Created by abhishek on 28/7/18.
 */

public class WeatherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final Context mContext;
    private List<IWeatherDetails> iWeatherDetailsList;

    private static final int VIEW_TYPE_TODAY = 0;
    private static final int VIEW_TYPE_FUTURE_DAY = 1;

    private boolean mUseTodayLayout;

    public WeatherAdapter(@NonNull Context context, List<IWeatherDetails> iWeatherDetails) {
        this.mContext = context;
        this.iWeatherDetailsList = iWeatherDetails;
        this.mUseTodayLayout = mContext.getResources().getBoolean(R.bool.use_today_layout);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId;

        LayoutInflater inflater = LayoutInflater.from(mContext);

        switch (viewType) {

            case VIEW_TYPE_TODAY: {
                layoutId = R.layout.current_weather_list_item;
                View view = inflater.inflate(layoutId, parent, false);
                return new CurrentWeatherViewHolder(view);
            }

            case VIEW_TYPE_FUTURE_DAY: {
                layoutId = R.layout.forecast_weather_list_item;
                View view = inflater.inflate(layoutId, parent, false);
                return new ForecastWeatherViewHolder(view);
            }

            default:
                throw new IllegalArgumentException("Invalid view type, value of " + viewType);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        IWeatherDetails item = iWeatherDetailsList.get(position);

        if (holder instanceof CurrentWeatherViewHolder) {
            ((CurrentWeatherViewHolder) holder).bind((CurrentWeatherBusinessModel) item);
        } else {
            ((ForecastWeatherViewHolder) holder).bind((WeatherListBusinessModel) item);
        }
    }

    @Override
    public int getItemCount() {
        return iWeatherDetailsList.size();
    }

    @Override
    public int getItemViewType(int position) {

        //CHECK TYPE OF DATA (CURRNET/FORECAST) FROM LIST
        if ( position == 0 && iWeatherDetailsList.get(position) instanceof CurrentWeatherBusinessModel && mUseTodayLayout) {
            return VIEW_TYPE_TODAY;
        } else {
            return VIEW_TYPE_FUTURE_DAY;
        }
    }

    public void setCurrentWeatherList(List<IWeatherDetails> weatherList){
        this.iWeatherDetailsList = weatherList;
        if(iWeatherDetailsList.get(0) instanceof CurrentWeatherBusinessModel && iWeatherDetailsList.get(0) instanceof WeatherListBusinessModel ){
            notifyItemChanged(0);
        }else if(iWeatherDetailsList.get(0) instanceof CurrentWeatherBusinessModel){
            notifyDataSetChanged();
        }else
            notifyDataSetChanged();
    }

    class ForecastWeatherViewHolder extends RecyclerView.ViewHolder {
        final TextView tv_forecast_weather_date;
        final TextView tv_forecast_weather_description;
        final TextView tv_forecast_weather_high_temperature;
        final TextView tv_forecast_weather_low_temperature;
        final ImageView img_forecast_weather_icon;

        ForecastWeatherViewHolder(View view) {
            super(view);
            img_forecast_weather_icon = view.findViewById(R.id.img_forecast_weather_icon);
            tv_forecast_weather_date = view.findViewById(R.id.tv_forecast_weather_date);
            tv_forecast_weather_description = view.findViewById(R.id.tv_forecast_weather_description);
            tv_forecast_weather_high_temperature = view.findViewById(R.id.tv_forecast_weather_high_temperature);
            tv_forecast_weather_low_temperature = view.findViewById(R.id.tv_forecast_weather_low_temperature);

        }

        void bind(WeatherListBusinessModel weatherListBusinessModel){

            //SET DESC
            String description = WeatherUtils.getStringForWeatherCondition(mContext, weatherListBusinessModel.getWeatherInfoBusinessModel().get(0).getId());
            String descriptionA11y = mContext.getString(R.string.a11y_forecast, description);
            tv_forecast_weather_description.setText(description);
            tv_forecast_weather_description.setContentDescription(descriptionA11y);

            //SET MAX TEMP
            double highInCelsius = weatherListBusinessModel.getMainBusinessModel().getTempMax();
            String highString = WeatherUtils.formatTemperature(mContext, highInCelsius);
            String highA11y = mContext.getString(R.string.a11y_high_temp, highString);
            tv_forecast_weather_high_temperature.setText(highString);
            tv_forecast_weather_high_temperature.setContentDescription(highA11y);


            //SET MIN TEMP
            double lowInCelsius = weatherListBusinessModel.getMainBusinessModel().getTempMin();
            String lowString = WeatherUtils.formatTemperature(mContext, lowInCelsius);
            String lowA11y = mContext.getString(R.string.a11y_low_temp, lowString);
            tv_forecast_weather_low_temperature.setText(lowString);
            tv_forecast_weather_low_temperature.setContentDescription(lowA11y);

            //SET ICON
            int weatherImageId = WeatherUtils
                    .getSmallArtResourceIdForWeatherCondition((weatherListBusinessModel
                            .getWeatherInfoBusinessModel()
                            .get(0)
                            .getId()));
            img_forecast_weather_icon.setImageResource(weatherImageId);

            //SET DATE
            long dateInMillis = weatherListBusinessModel.getDt();
            String dateString = WeatherUtils.getFriendlyDateString(mContext, dateInMillis, false);
            tv_forecast_weather_date.setText(dateString);

        }
    }

    class CurrentWeatherViewHolder extends RecyclerView.ViewHolder{

        TextView tv_current_weather_date, tv_current_weather_description, tv_current_weather_high_temperature,
                 tv_current_weather_low_temperature;
        ImageView img_current_weather_icon;

        public CurrentWeatherViewHolder(View itemView) {
            super(itemView);
            tv_current_weather_date = itemView.findViewById(R.id.tv_current_weather_date);
            tv_current_weather_description = itemView.findViewById(R.id.tv_current_weather_description);
            tv_current_weather_high_temperature = itemView.findViewById(R.id.tv_current_weather_high_temperature);
            tv_current_weather_low_temperature = itemView.findViewById(R.id.tv_current_weather_low_temperature);
            img_current_weather_icon = itemView.findViewById(R.id.img_current_weather_icon);

        }

        void bind(CurrentWeatherBusinessModel weatherListBusinessModel){

            //SET DESC
            String description = WeatherUtils.getStringForWeatherCondition(mContext, weatherListBusinessModel.getCurrentWeatherInfoBusinessModel().get(0).getWeatherId());
            String descriptionA11y = mContext.getString(R.string.a11y_forecast, description);
            tv_current_weather_description.setText(description);
            tv_current_weather_description.setContentDescription(descriptionA11y);

            //SET MAX TEMP
            double highInCelsius = weatherListBusinessModel.getCurrentWeatherMainBusinessModel().getTempMax();
            String highString = WeatherUtils.formatTemperature(mContext, highInCelsius);
            String highA11y = mContext.getString(R.string.a11y_high_temp, highString);
            tv_current_weather_high_temperature.setText(highString);
            tv_current_weather_high_temperature.setContentDescription(highA11y);


            //SET MIN TEMP
            double lowInCelsius = weatherListBusinessModel.getCurrentWeatherMainBusinessModel().getTempMin();
            String lowString = WeatherUtils.formatTemperature(mContext, lowInCelsius);
            String lowA11y = mContext.getString(R.string.a11y_low_temp, lowString);
            tv_current_weather_low_temperature.setText(lowString);
            tv_current_weather_low_temperature.setContentDescription(lowA11y);

            //SET ICON
            int weatherImageId = WeatherUtils
                    .getLargeArtResourceIdForWeatherCondition((weatherListBusinessModel
                            .getCurrentWeatherInfoBusinessModel()
                            .get(0)
                            .getWeatherId()));
            img_current_weather_icon.setImageResource(weatherImageId);

            //SET DATE
            long dateInMillis = weatherListBusinessModel.getDt();
            String dateString = WeatherUtils.getFriendlyDateString(mContext, dateInMillis, false);
            tv_current_weather_date.setText(dateString);

        }
    }
}
