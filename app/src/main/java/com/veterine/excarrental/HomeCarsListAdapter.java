package com.veterine.excarrental;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class HomeCarsListAdapter extends RecyclerView.Adapter<HomeCarsListAdapter.ProductViewHolder> {
    private Context mCtx;
    private List<HomeCarsListData> productList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{

        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){

        mListener =  listener;
    }




    //getting the context and product list with constructor
    HomeCarsListAdapter(Context mCtx, List<HomeCarsListData> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }




    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.homecarslist_row, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        //getting the product of the specified position
        HomeCarsListData product = productList.get(position);

        //binding the data with the viewholder views

        holder.image_name.setText(String.valueOf(product.getImage_name()));
        //holder.pass_no.setText(String.valueOf(product.getPass_no()));
        //holder.price.setText(String.valueOf(product.getPrice()));

        Glide.with(mCtx)
                .load(product.getImage_path())
                .into(holder.image_path);

    }


    @Override
    public int getItemCount() {

        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView image_name, pass_no, price;
        ImageView image_path;

        public ProductViewHolder(final View itemView) {
            super(itemView);

            image_name = itemView.findViewById(R.id.image_name);
            pass_no= itemView.findViewById(R.id.pass_no);
            price = itemView.findViewById(R.id.price);
            image_path = itemView.findViewById(R.id.image_path);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }
}

