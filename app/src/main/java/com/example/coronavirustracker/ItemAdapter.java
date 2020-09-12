package com.example.coronavirustracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<ItemClass> list;

    public ItemAdapter(Context mContext,ArrayList<ItemClass> list)
    {
        this.mContext=mContext;
        this.list=list;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.totalRecoveredCases.setText(String.valueOf(list.get(position).getTotalRecoveredCases()));
        holder.totalActiveCases.setText(String.valueOf(list.get(position).getTotalActiveCases()));
        holder.totalDeathsCases.setText(String.valueOf(list.get(position).getTotalDeathsCases()));
        holder.totalConfirmedCases.setText(String.valueOf(list.get(position).getTotalConfirmedCases()));
        holder.state.setText(list.get(position).getState());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView totalActiveCases;
        private TextView totalRecoveredCases;
        private TextView totalDeathsCases;
        private TextView totalConfirmedCases;
        private TextView state;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            totalActiveCases=itemView.findViewById(R.id.active_case);
            totalRecoveredCases=itemView.findViewById(R.id.recovered_case);
            totalDeathsCases=itemView.findViewById(R.id.deaths_case);
            totalConfirmedCases=itemView.findViewById(R.id.confirmed_case);
            state=itemView.findViewById(R.id.state);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    mOnItemClick.onClick(position);
                }
            });
        }
    }

    private OnItemClick mOnItemClick;

    public interface OnItemClick{
        void onClick(int position);
    }

    public void setOnItemClick(OnItemClick mOnItemClick)
    {
        this.mOnItemClick=mOnItemClick;
    }
}
