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
 * Created by divyanshu.jain on 12/13/2016.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<String> arrayList;
    private RecyclerViewClick recyclerViewClick;

    public SearchAdapter(Context context, ArrayList<String> arrayList,RecyclerViewClick recyclerViewClick) {
        this.recyclerViewClick = recyclerViewClick;
        this.arrayList = arrayList;
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerViewClick.onClickItem(holder.getAdapterPosition(),view);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    protected class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView categoryIconIV;
        TextView titleTV, descTV, productPurchaseTypeTV, detailTV;

        private MyViewHolder(View itemView) {
            super(itemView);
            categoryIconIV = (ImageView) itemView.findViewById(R.id.categoryIconIV);
            titleTV = (TextView) itemView.findViewById(R.id.titleTV);
            descTV = (TextView) itemView.findViewById(R.id.descTV);
            productPurchaseTypeTV = (TextView) itemView.findViewById(R.id.productPurchaseTypeTV);
            detailTV = (TextView) itemView.findViewById(R.id.detailTV);
        }
    }
}
