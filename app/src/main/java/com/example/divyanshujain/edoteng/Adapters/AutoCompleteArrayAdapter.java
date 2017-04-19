package com.example.divyanshujain.edoteng.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.divyanshujain.edoteng.Interfaces.RecyclerViewClick;
import com.example.divyanshujain.edoteng.Models.AutoSearchKeywordModel;
import com.example.divyanshujain.edoteng.R;

import java.util.ArrayList;
import java.util.Collection;

public class AutoCompleteArrayAdapter extends BaseAdapter {
    private final Context mContext;
    private final ArrayList<AutoSearchKeywordModel> autoSearchKewordModels;
    private final int mLayoutResourceId;
    private RecyclerViewClick recyclerViewClick;


    public AutoCompleteArrayAdapter(Context context, int resource, ArrayList<AutoSearchKeywordModel> autoSearchKewordModels, RecyclerViewClick recyclerViewClick) {
        this.mContext = context;
        this.mLayoutResourceId = resource;
        this.recyclerViewClick = recyclerViewClick;
        this.autoSearchKewordModels = new ArrayList<>(autoSearchKewordModels);

    }

    @Override
    public int getCount() {
        return autoSearchKewordModels.size();
    }

    public AutoSearchKeywordModel getItem(int position) {
        return autoSearchKewordModels.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        try {
            if (convertView == null) {
                LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
                convertView = inflater.inflate(R.layout.auto_complete_array_adapter, parent, false);
            }
            AutoSearchKeywordModel autoSearchKeywordModel = getItem(position);
            ((TextView) convertView).setText(autoSearchKeywordModel.getKeyword_title());
        } catch (Exception e) {
            e.printStackTrace();
        }

        assert convertView != null;
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerViewClick.onClickItem(position, v);
            }
        });
        return convertView;
    }
    
    public void addAll(Collection<? extends AutoSearchKeywordModel> collection) {
        autoSearchKewordModels.clear();
        autoSearchKewordModels.addAll(collection);
        notifyDataSetChanged();
    }
}