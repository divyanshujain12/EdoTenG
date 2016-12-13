package com.example.divyanshujain.edoteng.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.divyanshujain.edoteng.Adapters.SearchByKeywordAdapter;
import com.example.divyanshujain.edoteng.R;
import com.neopixl.pixlui.components.edittext.EditText;
import com.neopixl.pixlui.components.textview.TextView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SearchKeywordActivity extends AppCompatActivity {

    @InjectView(R.id.searchKeyTV)
    TextView searchKeyTV;
    @InjectView(R.id.searchET)
    EditText searchET;
    @InjectView(R.id.allTV)
    TextView allTV;
    @InjectView(R.id.materialTV)
    TextView materialTV;
    @InjectView(R.id.testSeriesTV)
    TextView testSeriesTV;
    @InjectView(R.id.videosLiveTV)
    TextView videosLiveTV;
    @InjectView(R.id.classesTV)
    TextView classesTV;
    @InjectView(R.id.filterSP)
    Spinner filterSP;
    @InjectView(R.id.searchedKeywordRV)
    RecyclerView searchedKeywordRV;
    @InjectView(R.id.activity_search_keyword)
    LinearLayout activitySearchKeyword;

    SearchByKeywordAdapter searchByKeywordAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_keyword);
        ButterKnife.inject(this);
        InitViews();

    }

    private void InitViews() {
        searchKeyTV.setTextColor(Color.WHITE);
        searchedKeywordRV.setLayoutManager(new LinearLayoutManager(this));
        searchByKeywordAdapter = new SearchByKeywordAdapter(this, new ArrayList<String>());
        searchedKeywordRV.setAdapter(searchByKeywordAdapter);
    }
}
