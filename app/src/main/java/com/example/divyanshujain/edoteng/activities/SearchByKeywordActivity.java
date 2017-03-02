package com.example.divyanshujain.edoteng.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.divyanshujain.edoteng.Adapters.SearchAdapter;
import com.example.divyanshujain.edoteng.Adapters.SpinnerAdapter;
import com.example.divyanshujain.edoteng.Constants.API;
import com.example.divyanshujain.edoteng.Constants.ApiCodes;
import com.example.divyanshujain.edoteng.Constants.Constants;
import com.example.divyanshujain.edoteng.GlobalClasses.BaseActivity;
import com.example.divyanshujain.edoteng.Models.BrandsModel;
import com.example.divyanshujain.edoteng.R;
import com.example.divyanshujain.edoteng.Utils.CallWebService;
import com.example.divyanshujain.edoteng.Utils.CommonFunctions;
import com.example.divyanshujain.edoteng.Utils.UniversalParser;
import com.neopixl.pixlui.components.edittext.EditText;
import com.neopixl.pixlui.components.textview.TextView;

import org.json.JSONException;
import org.json.JSONObject;

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
    @InjectView(R.id.searchIV)
    ImageView searchIV;
    @InjectView(R.id.brandSP)
    Spinner brandSP;

    SpinnerAdapter spinnerAdapter;
    private static String ADD_DATE_DESC = "add_date___DESC";
    private static String VIEWS_DESC = "views___DESC";
    private static String RATING_DESC = "rating___DESC";
    private static String ADD_DATE_ASC = "add_date___ASC";
    private static String PRODUCT_NAME_ASC = "product_name___ASC";
    private static String PRODUCT_NAME_DESC = "product_name___DESC";
    private static String DOWNLOADABLE_PRICE_ASC = "downloadable_price___ASC";
    private static String DOWNLOADABLE_PRICE_DESC = "downloadable_price___DESC";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_keyword);
        ButterKnife.inject(this);
        InitViews();

    }

    private void InitViews() {
        CommonFunctions.getInstance().configureToolbarWithBackButton(this, toolbarView, getString(R.string.search_with_keyword));
        searchKeyTV.setTextColor(Color.WHITE);
        searchedKeywordRV.setLayoutManager(new LinearLayoutManager(this));
        searchAdapter = new SearchAdapter(this, new ArrayList<String>(), this);
        searchedKeywordRV.setAdapter(searchAdapter);
        CallWebService.getInstance(this, true, ApiCodes.GET_BRANDS).hitJsonObjectRequestAPI(CallWebService.POST, API.GET_BRANDS, null, this);
    }

    @Override
    public void onClickItem(int position, View view) {
        super.onClickItem(position, view);
        Intent intent = new Intent(this, DescriptionActivity.class);
        startActivity(intent);
    }

    @Override
    public void onJsonObjectSuccess(JSONObject response, int apiType) throws JSONException {
        super.onJsonObjectSuccess(response, apiType);
        switch (apiType) {
            case ApiCodes.GET_BRANDS:
                ArrayList<BrandsModel> brandsModels = UniversalParser.getInstance().parseJsonArrayWithJsonObject(response.getJSONArray(Constants.DATA), BrandsModel.class);
                spinnerAdapter = new SpinnerAdapter(this, 0, brandsModels);
                brandSP.setAdapter(spinnerAdapter);
                break;
            case ApiCodes.SEARCH:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.descriptipn_menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.action_cart:
                startActivity(new Intent(this, CartActivity.class));
                break;
        }
        return true;
    }
}
