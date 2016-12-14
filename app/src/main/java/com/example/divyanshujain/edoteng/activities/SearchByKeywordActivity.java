package com.example.divyanshujain.edoteng.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.divyanshujain.edoteng.Adapters.SearchAdapter;
import com.example.divyanshujain.edoteng.GlobalClasses.BaseActivity;
import com.example.divyanshujain.edoteng.R;
import com.example.divyanshujain.edoteng.Utils.CommonFunctions;
import com.neopixl.pixlui.components.edittext.EditText;
import com.neopixl.pixlui.components.textview.TextView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SearchByKeywordActivity extends BaseActivity {

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

    SearchAdapter searchAdapter;
    @InjectView(R.id.toolbarView)
    Toolbar toolbarView;
    @InjectView(R.id.sortingByFL)
    FrameLayout sortingByFL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_keyword);
        ButterKnife.inject(this);
        InitViews();

    }

    private void InitViews() {
        CommonFunctions.getInstance().configureToolbarWithBackButton(this,toolbarView,getString(R.string.search_with_keyword));
        searchKeyTV.setTextColor(Color.WHITE);
        searchedKeywordRV.setLayoutManager(new LinearLayoutManager(this));
        searchAdapter = new SearchAdapter(this, new ArrayList<String>());
        searchedKeywordRV.setAdapter(searchAdapter);
    }
}
