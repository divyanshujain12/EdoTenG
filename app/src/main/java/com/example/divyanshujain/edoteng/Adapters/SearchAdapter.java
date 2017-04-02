package com.example.divyanshujain.edoteng.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.divyanshujain.edoteng.Interfaces.RecyclerViewClick;
import com.example.divyanshujain.edoteng.Models.ProductModel;
import com.example.divyanshujain.edoteng.R;
import com.example.divyanshujain.edoteng.Utils.CommonFunctions;
import com.neopixl.pixlui.components.textview.TextView;

import java.util.ArrayList;

/**
 * Created by divyanshu.jain on 12/13/2016.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<ProductModel> productModels;
    private RecyclerViewClick recyclerViewClick;

    public SearchAdapter(Context context, ArrayList<ProductModel> productModels, RecyclerViewClick recyclerViewClick) {
        this.recyclerViewClick = recyclerViewClick;
        this.productModels = productModels;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_keyword_rv_adapter_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        ProductModel productModel = productModels.get(position);
        holder.titleTV.setText(productModel.getProduct_name());
        holder.itemTypeTV.setText(productModel.getItem_type());
        holder.descTV.setText(Html.fromHtml(productModel.getShort_description()));
        CommonFunctions.setItemTypeIV(productModel.getItem_type(), holder.categoryIconIV);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerViewClick.onClickItem(holder.getAdapterPosition(), view);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productModels.size();
    }

    protected class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView categoryIconIV;
        TextView titleTV, descTV, itemTypeTV, detailTV;

        private MyViewHolder(View itemView) {
            super(itemView);
            categoryIconIV = (ImageView) itemView.findViewById(R.id.categoryIconIV);
            titleTV = (TextView) itemView.findViewById(R.id.titleTV);
            descTV = (TextView) itemView.findViewById(R.id.descTV);
            itemTypeTV = (TextView) itemView.findViewById(R.id.itemTypeTV);
            detailTV = (TextView) itemView.findViewById(R.id.detailTV);
        }
    }

    public void addItems(ArrayList<ProductModel> productModels) {
        this.productModels = productModels;
        notifyDataSetChanged();
    }


}
