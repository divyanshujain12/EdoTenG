package com.example.divyanshujain.edoteng.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.divyanshujain.edoteng.Models.HomeSpinnerModel;
import com.example.divyanshujain.edoteng.R;
import com.neopixl.pixlui.components.textview.TextView;

import java.util.ArrayList;

public class HomeSpinnerAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<HomeSpinnerModel> categoryModels;
    private LayoutInflater inflater;

    public HomeSpinnerAdapter(Context applicationContext, ArrayList<HomeSpinnerModel> categoryModels) {
        this.context = applicationContext;
        this.categoryModels = categoryModels;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return categoryModels.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.custom_spinner_items, null);
        TextView names = (TextView) view;
        names.setText(categoryModels.get(i).getName());
        return view;
    }
}
