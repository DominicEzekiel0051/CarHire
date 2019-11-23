package com.veterine.excarrental;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.veterine.excarrental.R;

import java.util.List;


public class CarsDetailsAdapter extends RecyclerView.Adapter<CarsDetailsAdapter.ViewHolder> {

    Context context;
    List<CarsDetailsData> carsDetailsData;

    public CarsDetailsAdapter(List<CarsDetailsData> carsDetailsData, Context context){

        super();
        this.carsDetailsData = carsDetailsData;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardetails_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        CarsDetailsData bicycle_details_getDataAdapter1 =  carsDetailsData.get(position);

        holder.NameTextView.setText(bicycle_details_getDataAdapter1.getImage_name());

    }

    @Override
    public int getItemCount() {

        return carsDetailsData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView NameTextView;



        public ViewHolder(View itemView) {

            super(itemView);

            NameTextView = itemView.findViewById(R.id.textView4) ;


        }
    }
}


