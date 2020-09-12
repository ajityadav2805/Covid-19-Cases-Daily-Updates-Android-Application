package com.example.coronavirustracker;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class StateClassAdapter extends RecyclerView.Adapter<StateClassAdapter.Holder> {


    private Context mContext;
    private List<StateClass> mList;

    public StateClassAdapter(Context mContext,List<StateClass> mList)
    {
        this.mContext=mContext;
        this.mList=mList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(mContext).inflate(R.layout.state_wise_item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.date.setText(String.valueOf(mList.get(position).getDate()));
        holder.totalCases.setText(String.valueOf(mList.get(position).getTotalCases()));
        holder.totalDeaths.setText(String.valueOf(mList.get(position).getDeaths()));
        holder.discharged.setText(String.valueOf(mList.get(position).getDischarge()));
        holder.confirmedIndian.setText(String.valueOf(mList.get(position).getConfirmedIndian()));
        holder.confirmedForeigner.setText(String.valueOf(mList.get(position).getConfirmedForeign()));
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            long totalDays=totalDays();
            holder.day.setText(new StringBuilder("Day " + (totalDays+1-position)));
        }else {
            holder.day.setText(new StringBuilder("Day " + (mList.size() - (position))));
        }
    }

    private long totalDays() {
        String dateBeforeString = "2020-03-10";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        Date date = new Date(System.currentTimeMillis());

        String dateAfterString = formatter.format(date);

        //Parsing the date
        LocalDate dateBefore = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            dateBefore = LocalDate.parse(dateBeforeString);
        }

        LocalDate dateAfter = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            dateAfter = LocalDate.parse(dateAfterString);
        }

        //calculating number of days in between

        //displaying the number of days
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return (ChronoUnit.DAYS.between(dateBefore, dateAfter));
        }
      return 0;
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        private TextView totalCases;
        private TextView totalDeaths;
        private TextView discharged;
        private TextView confirmedIndian;
        private TextView confirmedForeigner;
        private TextView date;
        private TextView day;



        public Holder(@NonNull View itemView) {
            super(itemView);

            totalCases=itemView.findViewById(R.id.total_cases_state_day);
            totalDeaths=itemView.findViewById(R.id.total_deaths_state_day);
            discharged=itemView.findViewById(R.id.discharged_state_day);
            confirmedIndian=itemView.findViewById(R.id.confirmed_case_indian_state_day);
            confirmedForeigner=itemView.findViewById(R.id.confirmed_case_foreigner_state_day);
            date=itemView.findViewById(R.id.date_state_day);
            day=itemView.findViewById(R.id.days_count_state_day);
        }
    }
}
