package com.example.divyanshujain.edoteng.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.divyanshujain.edoteng.Models.BrandsModel;
import com.example.divyanshujain.edoteng.R;
import com.neopixl.pixlui.components.textview.TextView;

import java.util.ArrayList;

/**
 * Created by divyanshuPC on 3/2/2017.
 */

public class SpinnerAdapter extends ArrayAdapter<BrandsModel> {

    LayoutInflater inflater;
    ArrayList<BrandsModel> brandsModels;

    public SpinnerAdapter(Context context, int resource, ArrayList<BrandsModel> objects) {
        super(context, resource, objects);
        this.brandsModels = objects;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    private View getCustomView(int position, View convertView, ViewGroup parent) {
        TextView row = (TextView) inflater.inflate(R.layout.spinner_rows, parent, false);
        row.setText(brandsModels.get(position).getBrand_title());
        return row;
    }
}
