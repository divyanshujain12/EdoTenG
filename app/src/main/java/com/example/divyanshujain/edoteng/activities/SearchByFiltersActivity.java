package com.example.divyanshujain.edoteng.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.divyanshujain.edoteng.Adapters.SearchAdapter;
import com.example.divyanshujain.edoteng.Adapters.SpinnerAdapter;
import com.example.divyanshujain.edoteng.Constants.API;
import com.example.divyanshujain.edoteng.Constants.ApiCodes;
import com.example.divyanshujain.edoteng.Constants.Constants;
import com.example.divyanshujain.edoteng.GlobalClasses.BaseActivity;
import com.example.divyanshujain.edoteng.Models.BrandsModel;
import com.example.divyanshujain.edoteng.Models.ProductModel;
import com.example.divyanshujain.edoteng.R;
import com.example.divyanshujain.edoteng.Utils.CallWebService;
import com.example.divyanshujain.edoteng.Utils.CommonFunctions;
import com.example.divyanshujain.edoteng.Utils.UniversalParser;
import com.example.divyanshujain.edoteng.Utils.Validation;
import com.neopixl.pixlui.components.button.Button;
import com.neopixl.pixlui.components.textview.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class SearchByFiltersActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {

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
    @InjectView(R.id.typeSP)
    Spinner typeSP;
    @InjectView(R.id.brandSP)
    Spinner brandSP;
    @InjectView(R.id.noDataTV)
    TextView noDataTV;
    @InjectView(R.id.searchBT)
    Button searchBT;

    private TextView selectedTV;
    private ArrayList<ProductModel> productModels;
    private static String ALL = "All";
    private static String MATERIAL = "Materials";
    private static String TEST_SERIES = "Test Series";
    private static String VIDEOS = "Videos";


    private String[] sortingValueArray, typeArray;
    Validation validation;
    HashMap<View, String> hashMap;
    private String selectedBrandID = "", selectedOrderBy = "", selectedSearchType = "";
    SpinnerAdapter spinnerAdapter;
    ArrayList<BrandsModel> brandsModels;

    private String catName, subCatName, subSubCatName, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_filters);
        ButterKnife.inject(this);
        InitViews();
    }

    private void InitViews() {
        CommonFunctions.getInstance().configureToolbarWithBackButton(this, toolbarView, getString(R.string.search_with_filters));

        catName = getIntent().getStringExtra(Constants.CATEGORY);
        subCatName = getIntent().getStringExtra(Constants.SUB_CAT);
        subSubCatName = getIntent().getStringExtra(Constants.SUB_SUB_CAT);
        id = getIntent().getStringExtra(Constants.ID);

        categoryNameTV.setText(catName);
        subCategoryNameTV.setText(subCatName);
        subSubCategoryNameTV.setText(subSubCatName);

        searchedKeywordRV.setLayoutManager(new LinearLayoutManager(this));
        searchAdapter = new SearchAdapter(this, new ArrayList<ProductModel>(), this);
        searchedKeywordRV.setAdapter(searchAdapter);
        sortingValueArray = getResources().getStringArray(R.array.sorting_value_array);
        typeArray = getResources().getStringArray(R.array.type_array);
        CallWebService.getInstance(this, false, ApiCodes.GET_BRANDS).hitJsonObjectRequestAPI(CallWebService.POST, API.GET_BRANDS, null, this);

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
                brandsModels = UniversalParser.getInstance().parseJsonArrayWithJsonObject(response.getJSONArray(Constants.DATA), BrandsModel.class);
                spinnerAdapter = new SpinnerAdapter(this, 0, brandsModels);
                brandSP.setAdapter(spinnerAdapter);
                brandSP.setOnItemSelectedListener(this);
                break;
            case ApiCodes.SEARCH:
                productModels = UniversalParser.getInstance().parseJsonArrayWithJsonObject(response.getJSONObject(Constants.DATA).getJSONArray(Constants.LISTING), ProductModel.class);
                searchAdapter.addItems(productModels);
                noDataTV.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onFailure(String str, int apiType) {
        super.onFailure(str, apiType);
        switch (apiType) {
            case ApiCodes.SEARCH:
                noDataTV.setVisibility(View.VISIBLE);
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
            case R.id.action_user_setting:
                startActivity(new Intent(this, ApplicationSettingActivity.class));
                break;
        }
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int viewId = parent.getId();
        switch (viewId) {
            case R.id.brandSP:
                selectedBrandID = brandsModels.get(position).getId();
                hitSearchApi();
                break;
            case R.id.typeSP:
                selectedSearchType = typeArray[position];
                break;
            case R.id.filterSP:
                selectedOrderBy = sortingValueArray[position];
                break;
        }
    }

    private void hitSearchApi() {
        CallWebService.getInstance(this, true, ApiCodes.SEARCH).hitJsonObjectRequestAPI(CallWebService.POST, API.PRODUCT_LISTING_FILER, createJsonForGettingSearchResult(), this);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private JSONObject createJsonForGettingSearchResult() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(Constants.SEARCH_TYPE, selectedSearchType);
            jsonObject.put(Constants.C_BRAND_ID, selectedBrandID);
            jsonObject.put(Constants.ORDER_BY, selectedOrderBy);
            jsonObject.put(Constants.CAT_ID, id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    @OnClick(R.id.searchBT)
    public void onClick() {
        hitSearchApi();
    }
}
