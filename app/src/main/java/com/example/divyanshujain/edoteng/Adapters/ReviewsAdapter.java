package com.example.divyanshujain.edoteng.Adapters;

import android.content.Context;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.divyanshujain.edoteng.Interfaces.RecyclerViewClick;
import com.example.divyanshujain.edoteng.Models.ProductReviewUserModel;
import com.example.divyanshujain.edoteng.R;
import com.neopixl.pixlui.components.textview.TextView;

import java.util.ArrayList;

/**
 * Created by divyanshuPC on 4/1/2017.
 */

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<ProductReviewUserModel> productReviewUserModels;
    private RecyclerViewClick recyclerViewClick;
    private AppCompatRadioButton lastSelectedRadioButton = null;

    public ReviewsAdapter(Context context, ArrayList<ProductReviewUserModel> productReviewUserModels, RecyclerViewClick recyclerViewClick) {
        this.recyclerViewClick = recyclerViewClick;
        this.productReviewUserModels = productReviewUserModels;
        this.context = context;
    }

    @Override
    public ReviewsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reviews_rv_adapter, parent, false);

        return new ReviewsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ReviewsAdapter.MyViewHolder holder, int position) {
        ProductReviewUserModel productReviewUserModel = productReviewUserModels.get(position);
        holder.userNameTV.setText(productReviewUserModel.getName());
        holder.timeTV.setText(productReviewUserModel.getReview_date());
        holder.descTV.setText(productReviewUserModel.getReview());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return productReviewUserModels.size();
    }

    protected class MyViewHolder extends RecyclerView.ViewHolder {

        TextView userNameTV;
        TextView timeTV;
        TextView descTV;

        private MyViewHolder(View itemView) {
            super(itemView);

            userNameTV = (TextView) itemView.findViewById(R.id.userNameTV);
            timeTV = (TextView) itemView.findViewById(R.id.timeTV);
            descTV = (TextView) itemView.findViewById(R.id.descTV);

        }
    }
}


