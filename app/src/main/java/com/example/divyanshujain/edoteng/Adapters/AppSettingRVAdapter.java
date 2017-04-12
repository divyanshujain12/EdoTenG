package com.example.divyanshujain.edoteng.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.divyanshujain.edoteng.Interfaces.RecyclerViewClick;
import com.example.divyanshujain.edoteng.R;
import com.neopixl.pixlui.components.textview.TextView;

/**
 * Created by divyanshuPC on 4/10/2017.
 */

public class AppSettingRVAdapter extends RecyclerView.Adapter<AppSettingRVAdapter.MyViewHolder> {

    private Context context;
    private RecyclerViewClick recyclerViewClick;
    private String[] settingsList;

    public AppSettingRVAdapter(Context context, RecyclerViewClick recyclerViewClick) {
        this.recyclerViewClick = recyclerViewClick;
        this.context = context;
        settingsList = context.getResources().getStringArray(R.array.app_setting);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.app_stting_rv_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.settingTypeTV.setText(settingsList[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerViewClick.onClickItem(position, v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return settingsList.length;
    }

    protected class MyViewHolder extends RecyclerView.ViewHolder {
        TextView settingTypeTV;
        private MyViewHolder(View itemView) {
            super(itemView);
            settingTypeTV = (TextView) itemView.findViewById(R.id.settingTypeTV);
        }
    }
}


