package com.example.divyanshujain.edoteng.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.divyanshujain.edoteng.Interfaces.RecyclerViewClick;
import com.example.divyanshujain.edoteng.Models.AutoSearchKeywordModel;
import com.example.divyanshujain.edoteng.R;
import com.neopixl.pixlui.components.textview.TextView;

import java.util.ArrayList;
import java.util.Collection;

public class AutoCompleteArrayAdapter extends ArrayAdapter<AutoSearchKeywordModel> {
    private final Context mContext;
    private final ArrayList<AutoSearchKeywordModel> autoSearchKeywordModels;
    private final int mLayoutResourceId;
    private RecyclerViewClick recyclerViewClick;


    public AutoCompleteArrayAdapter(Context context, int resource, ArrayList<AutoSearchKeywordModel> autoSearchKeywordModels, RecyclerViewClick recyclerViewClick) {
        super(context, resource, autoSearchKeywordModels);
        this.mContext = context;
        this.mLayoutResourceId = resource;
        this.recyclerViewClick = recyclerViewClick;
        this.autoSearchKeywordModels = new ArrayList<>(autoSearchKeywordModels);
    }


    public int getCount() {
        return autoSearchKeywordModels.size();
    }

    public AutoSearchKeywordModel getItem(int position) {
        return autoSearchKeywordModels.get(position);
    }

    public long getItemId(int position) {
        return position;
    }


    public View getView(final int position, View convertView, ViewGroup parent) {
        try {
            if (convertView == null) {
                getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                LayoutInflater inflater = LayoutInflater.from(mContext);
                convertView = inflater.inflate(mLayoutResourceId, null);
            }
            AutoSearchKeywordModel autoSearchKeywordModel = autoSearchKeywordModels.get(position);
            TextView autoSearchTV = (TextView) convertView.findViewById(R.id.autoSearchTV);
            autoSearchTV.setText(autoSearchKeywordModel.getKeyword_title());
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

    @Override
    public void addAll(Collection<? extends AutoSearchKeywordModel> collection) {
        autoSearchKeywordModels.clear();
        autoSearchKeywordModels.addAll(collection);
        notifyDataSetChanged();
    }
}