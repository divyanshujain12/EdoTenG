package com.example.divyanshujain.edoteng.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.divyanshujain.edoteng.Interfaces.RecyclerViewClick;
import com.example.divyanshujain.edoteng.R;
import com.neopixl.pixlui.components.textview.TextView;

import java.util.ArrayList;

/**
 * Created by divyanshu.jain on 12/22/2016.
 */

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<String> arrayList;
    private RecyclerViewClick recyclerViewClick;

    public WishlistAdapter(Context context, ArrayList<String> arrayList, RecyclerViewClick recyclerViewClick) {
        this.recyclerViewClick = recyclerViewClick;
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public WishlistAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_adapter_view, parent, false);

        return new WishlistAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final WishlistAdapter.MyViewHolder holder, int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerViewClick.onClickItem(holder.getAdapterPosition(), view);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    protected class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView itemIV;
        TextView itemNameTV;
        TextView itemPriceTV;
        TextView detailTV;
        TextView addToCartTV;
        TextView removeTV;

        private MyViewHolder(View itemView) {
            super(itemView);
            itemIV = (ImageView) itemView.findViewById(R.id.itemIV);
            itemNameTV = (TextView) itemView.findViewById(R.id.itemNameTV);
            itemPriceTV = (TextView) itemView.findViewById(R.id.itemPriceTV);
            detailTV = (TextView) itemView.findViewById(R.id.detailTV);
            addToCartTV = (TextView) itemView.findViewById(R.id.addToCartTV);
            removeTV = (TextView) itemView.findViewById(R.id.removeTV);
        }
    }
}


