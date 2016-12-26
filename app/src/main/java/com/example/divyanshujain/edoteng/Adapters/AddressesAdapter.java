package com.example.divyanshujain.edoteng.Adapters;

import android.content.Context;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.divyanshujain.edoteng.Interfaces.RecyclerViewClick;
import com.example.divyanshujain.edoteng.R;
import com.neopixl.pixlui.components.textview.TextView;

import java.util.ArrayList;

/**
 * Created by divyanshu.jain on 12/22/2016.
 */

public class AddressesAdapter extends RecyclerView.Adapter<AddressesAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<String> arrayList;
    private RecyclerViewClick recyclerViewClick;
    private AppCompatRadioButton lastSelectedRadioButton = null;

    public AddressesAdapter(Context context, ArrayList<String> arrayList, RecyclerViewClick recyclerViewClick) {
        this.recyclerViewClick = recyclerViewClick;
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public AddressesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.address_adapter_view, parent, false);

        return new AddressesAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final AddressesAdapter.MyViewHolder holder, int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lastSelectedRadioButton != null && lastSelectedRadioButton.isChecked())
                    lastSelectedRadioButton.setChecked(false);
                holder.selectAddressRB.setChecked(true);
                lastSelectedRadioButton = holder.selectAddressRB;

            }
        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    protected class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatRadioButton selectAddressRB;
        TextView fullNameTV;
        TextView addressDetailTV;
        TextView editAddressTV;
        TextView shipToThisAddressTV;
        TextView removeTV;

        private MyViewHolder(View itemView) {
            super(itemView);
            selectAddressRB = (AppCompatRadioButton) itemView.findViewById(R.id.selectAddressRB);
            fullNameTV = (TextView) itemView.findViewById(R.id.fullNameTV);
            addressDetailTV = (TextView) itemView.findViewById(R.id.addressDetailTV);
            editAddressTV = (TextView) itemView.findViewById(R.id.editAddressTV);
            shipToThisAddressTV = (TextView) itemView.findViewById(R.id.shipToThisAddressTV);
        }
    }
}

