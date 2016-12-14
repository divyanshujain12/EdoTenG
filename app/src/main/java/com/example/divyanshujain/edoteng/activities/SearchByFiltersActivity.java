package com.example.divyanshujain.edoteng.activities;

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
import com.neopixl.pixlui.components.textview.TextView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SearchByFiltersActivity extends BaseActivity {

    @InjectView(R.id.toolbarView)
    Toolbar toolbarView;
    @InjectView(R.id.categoryNameTV)
    TextView categoryNameTV;
    @InjectView(R.id.subCategoryNameTV)
    TextView subCategoryNameTV;
    @InjectView(R.id.subSubCategoryNameTV)
    TextView subSubCategoryNameTV;
    @InjectView(R.id.filterSP)
    Spinner filterSP;
    @InjectView(R.id.sortingByFL)
    FrameLayout sortingByFL;
    @InjectView(R.id.searchedKeywordRV)
    RecyclerView searchedKeywordRV;
    @InjectView(R.id.activity_search_keyword)
    LinearLayout activitySearchKeyword;
    SearchAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_filters);
        ButterKnife.inject(this);
        InitViews();
    }

    private void InitViews() {
        CommonFunctions.getInstance().configureToolbarWithBackButton(this, toolbarView, getString(R.string.search_with_filters));

        searchedKeywordRV.setLayoutManager(new LinearLayoutManager(this));
        searchAdapter = new SearchAdapter(this, new ArrayList<String>());
        searchedKeywordRV.setAdapter(searchAdapter);
    }
}
