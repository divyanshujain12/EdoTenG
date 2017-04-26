package com.example.divyanshujain.edoteng.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.divyanshujain.edoteng.Adapters.HomeSpinnerAdapter;
import com.example.divyanshujain.edoteng.Constants.API;
import com.example.divyanshujain.edoteng.Constants.ApiCodes;
import com.example.divyanshujain.edoteng.Constants.Constants;
import com.example.divyanshujain.edoteng.GlobalClasses.BaseActivity;
import com.example.divyanshujain.edoteng.Models.HomeSpinnerModel;
import com.example.divyanshujain.edoteng.R;
import com.example.divyanshujain.edoteng.Utils.CallWebService;
import com.example.divyanshujain.edoteng.Utils.CommonFunctions;
import com.example.divyanshujain.edoteng.Utils.UniversalParser;
import com.neopixl.pixlui.components.button.Button;
import com.neopixl.pixlui.components.edittext.EditText;
import com.neopixl.pixlui.components.textview.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {

    @InjectView(R.id.categorySP)
    Spinner categorySP;
    @InjectView(R.id.subCategorySP)
    Spinner subCategorySP;
    @InjectView(R.id.subSubCategorySP)
    Spinner subSubCategorySP;
    @InjectView(R.id.goBT)
    Button goBT;
    @InjectView(R.id.searchET)
    EditText searchET;
    @InjectView(R.id.activity_home)
    LinearLayout activityHome;
    @InjectView(R.id.toolbarView)
    Toolbar toolbarView;
    @InjectView(R.id.searchKeyTV)
    TextView searchKeyTV;
    @InjectView(R.id.categoryLL)
    LinearLayout categoryLL;
    @InjectView(R.id.subCategoryLL)
    LinearLayout subCategoryLL;
    @InjectView(R.id.subSubCategoryLL)
    LinearLayout subSubCategoryLL;
    @InjectView(R.id.searchIV)
    ImageView searchIV;

    private ArrayList<HomeSpinnerModel> categoryArray, subCategoryArray, subSubCategoryArray;
    private HomeSpinnerAdapter categoryAdapter, subCategoryAdapter, subSubCategoryAdapter;
    private String categoryName, subCategoryName, subSubCategoryName;
    private String ID = "";
    private HomeSpinnerModel homeSpinnerModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.inject(this);
        initViews();
    }

    private void initViews() {
        CommonFunctions.getInstance().configureToolbarWithOutBackButton(this, toolbarView, getString(R.string.home));
        categorySP.setOnItemSelectedListener(this);
        subCategorySP.setOnItemSelectedListener(this);
        subSubCategorySP.setOnItemSelectedListener(this);
        homeSpinnerModel = new HomeSpinnerModel();
        homeSpinnerModel.setId("0");
        homeSpinnerModel.setName("Please Select");
        showHideSpinnerLayout(subCategoryLL, false);
        showHideSpinnerLayout(subSubCategoryLL, false);

        CallWebService.getInstance(this, true, ApiCodes.CATEGORIES).hitJsonObjectRequestAPI(CallWebService.POST, API.GET_ALL_CATEGORIES, null, this);
    }

    @OnClick({R.id.goBT, R.id.searchET})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.goBT:
                Intent intent = new Intent(this, SearchByFiltersActivity.class);
                intent.putExtra(Constants.CATEGORY, categoryName);
                intent.putExtra(Constants.SUB_CAT, subCategoryName);
                intent.putExtra(Constants.SUB_SUB_CAT, subSubCategoryName);
                intent.putExtra(Constants.ID, ID);
                startActivity(intent);
                break;
            case R.id.searchET:
                startActivity(new Intent(this, SearchByKeywordActivity.class));
                break;
        }
    }

    @Override
    public void onJsonObjectSuccess(JSONObject response, int apiType) throws JSONException {
        super.onJsonObjectSuccess(response, apiType);

        switch (apiType) {
            case ApiCodes.CATEGORIES:
                categoryArray = UniversalParser.getInstance().parseJsonArrayWithJsonObject(response.getJSONArray(Constants.DATA), HomeSpinnerModel.class);
                categoryArray.add(0, homeSpinnerModel);
                categoryAdapter = new HomeSpinnerAdapter(this, categoryArray);
                categorySP.setAdapter(categoryAdapter);
                break;
            case ApiCodes.SUB_CATEGORIES:
                subCategoryArray = UniversalParser.getInstance().parseJsonArrayWithJsonObject(response.getJSONArray(Constants.DATA), HomeSpinnerModel.class);
                if (subCategoryArray.size() > 0) {
                    showHideSpinnerLayout(subCategoryLL, true);
                    subCategoryArray.add(0, homeSpinnerModel);
                    subCategoryAdapter = new HomeSpinnerAdapter(this, subCategoryArray);
                    subCategorySP.setAdapter(subCategoryAdapter);
                }
                break;
            case ApiCodes.SUB_SUB_CATEGORIES:
                subSubCategoryArray = UniversalParser.getInstance().parseJsonArrayWithJsonObject(response.getJSONArray(Constants.DATA), HomeSpinnerModel.class);
                if (subSubCategoryArray.size() > 0) {
                    showHideSpinnerLayout(subSubCategoryLL, true);
                    subSubCategoryAdapter = new HomeSpinnerAdapter(this, subSubCategoryArray);
                    subSubCategorySP.setAdapter(subSubCategoryAdapter);
                }
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
    protected void onResume() {
        super.onResume();
        searchET.setFocusable(false);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.categorySP:
                if (position > 0) {
                    CallWebService.getInstance(this, true, ApiCodes.SUB_CATEGORIES).hitJsonObjectRequestAPI(CallWebService.POST, API.GET_SUB_AND_SUB_SUB_CAT, createJsonForGetData(categoryArray.get(position).getId()), this);
                    categoryName = categoryArray.get(position).getName();
                    ID = categoryArray.get(position).getId();
                } else {
                    categoryName = "";
                }
                break;
            case R.id.subCategorySP:
                if (position > 0) {
                    CallWebService.getInstance(this, true, ApiCodes.SUB_SUB_CATEGORIES).hitJsonObjectRequestAPI(CallWebService.POST, API.GET_SUB_AND_SUB_SUB_CAT, createJsonForGetData(subCategoryArray.get(position).getId()), this);
                    subCategoryName = subCategoryArray.get(position).getName();
                    ID = subCategoryArray.get(position).getId();
                } else {
                    subSubCategoryName = "";
                }
                break;
            case R.id.subSubCategorySP:
                if (position > 0) {
                    subSubCategoryName = subSubCategoryArray.get(position).getName();
                    ID = subSubCategoryArray.get(position).getId();
                } else {
                    subSubCategoryName = "";
                }
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private JSONObject createJsonForGetData(String id) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(Constants.ID, id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    private void showHideSpinnerLayout(LinearLayout linearLayout, boolean show) {
        if (show)
            linearLayout.setVisibility(View.VISIBLE);
        else
            linearLayout.setVisibility(View.GONE);
    }
}
