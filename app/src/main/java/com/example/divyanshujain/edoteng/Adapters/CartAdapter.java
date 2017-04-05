package com.example.divyanshujain.edoteng.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.divyanshujain.edoteng.Interfaces.RecyclerViewClick;
import com.example.divyanshujain.edoteng.Models.ProductDetailModel;
import com.example.divyanshujain.edoteng.R;
import com.example.divyanshujain.edoteng.Utils.ProductsSingleton;
import com.example.divyanshujain.edoteng.Utils.CommonFunctions;
import com.neopixl.pixlui.components.textview.TextView;

import java.util.ArrayList;

/**
 * Created by divyanshu.jain on 12/22/2016.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<ProductDetailModel> productDetailModels;
    private RecyclerViewClick recyclerViewClick;

    public CartAdapter(Context context, RecyclerViewClick recyclerViewClick) {
        this.recyclerViewClick = recyclerViewClick;
        this.productDetailModels = ProductsSingleton.getInstance().getProductDetailModels();
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_adapter_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        ProductDetailModel productDetailModel = productDetailModels.get(position);
        holder.itemNameTV.setText(productDetailModel.getTitle());

        CommonFunctions.setItemTypeIV(productDetailModel.getItem_type(), holder.itemIV);

        if (productDetailModel.getPhysicalPriceAdded() == 1)
            holder.itemPriceTV.setText(productDetailModel.getPhysical_price());
        else
            holder.itemPriceTV.setText(productDetailModel.getDownloadable_price());

        holder.detailTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerViewClick.onClickItem(holder.getAdapterPosition(), v);
            }
        });
        holder.removeTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerViewClick.onClickItem(holder.getAdapterPosition(), v);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerViewClick.onClickItem(holder.getAdapterPosition(), view);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productDetailModels.size();
    }

    protected class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView itemIV;
        TextView itemNameTV;
        TextView itemPriceTV;
        TextView detailTV;
        TextView addToWishlistTV;
        TextView removeTV;

        private MyViewHolder(View itemView) {
            super(itemView);
            itemIV = (ImageView) itemView.findViewById(R.id.itemIV);
            itemNameTV = (TextView) itemView.findViewById(R.id.itemNameTV);
            itemPriceTV = (TextView) itemView.findViewById(R.id.itemPriceTV);
            detailTV = (TextView) itemView.findViewById(R.id.detailTV);
            addToWishlistTV = (TextView) itemView.findViewById(R.id.addToWishlistTV);
            removeTV = (TextView) itemView.findViewById(R.id.removeTV);
        }
    }
}

