package com.example.divyanshujain.edoteng.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.divyanshujain.edoteng.Adapters.AutoCompleteArrayAdapter;
import com.example.divyanshujain.edoteng.Adapters.SearchAdapter;
import com.example.divyanshujain.edoteng.Adapters.SpinnerAdapter;
import com.example.divyanshujain.edoteng.Constants.API;
import com.example.divyanshujain.edoteng.Constants.ApiCodes;
import com.example.divyanshujain.edoteng.Constants.Constants;
import com.example.divyanshujain.edoteng.CustomViews.CustomAutoCompleteView;
import com.example.divyanshujain.edoteng.GlobalClasses.BaseActivity;
import com.example.divyanshujain.edoteng.Models.AutoSearchKeywordModel;
import com.example.divyanshujain.edoteng.Models.BrandsModel;
import com.example.divyanshujain.edoteng.Models.ProductModel;
import com.example.divyanshujain.edoteng.R;
import com.example.divyanshujain.edoteng.Utils.CallWebService;
import com.example.divyanshujain.edoteng.Utils.CommonFunctions;
import com.example.divyanshujain.edoteng.Utils.UniversalParser;
import com.neopixl.pixlui.components.button.Button;
import com.neopixl.pixlui.components.textview.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class SearchByKeywordActivity extends BaseActivity implements AdapterView.OnItemSelectedListener, TextWatcher {

    @InjectView(R.id.searchKeyTV)
    TextView searchKeyTV;

    @InjectView(R.id.allTV)
    TextView allTV;
    @InjectView(R.id.materialTV)
    TextView materialTV;
    @InjectView(R.id.testSeriesTV)
    TextView testSeriesTV;
    @InjectView(R.id.videosTV)
    TextView videosTV;
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
    @InjectView(R.id.brandSP)
    Spinner brandSP;
    @InjectView(R.id.searchBT)
    Button searchBT;

    SpinnerAdapter spinnerAdapter;
    @InjectView(R.id.searchAC)
    CustomAutoCompleteView searchAC;
    @InjectView(R.id.noDataTV)
    TextView noDataTV;
    private TextView selectedTV;
    private ArrayList<ProductModel> productModels;
    private static String ALL = "All";
    private static String MATERIAL = "Materials";
    private static String TEST_SERIES = "Test Series";
    private static String VIDEOS = "Videos";

    private String selectedTextFromAC = "";
    private String selectedSearchType;
    private String[] sortingKeyArray, sortingValueArray;
    //Validation validation;
    // HashMap<View, String> hashMap;
    private String selectedBrandID = "", selectedOrderBy = "";
    ArrayList<BrandsModel> brandsModels;
    ArrayList<AutoSearchKeywordModel> autoSearchKeywordModels = new ArrayList<>();
    private AutoCompleteArrayAdapter autoCompleteArrayAdapter;
    private ArrayList<String> autoSearchResult = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_keyword);
        ButterKnife.inject(this);
        InitViews();

    }

    private void InitViews() {
        CommonFunctions.getInstance().configureToolbarWithBackButton(this, toolbarView, getString(R.string.search_with_keyword));
        setInitialValues();
        sortingKeyArray = getResources().getStringArray(R.array.sorting_array);
        sortingValueArray = getResources().getStringArray(R.array.sorting_value_array);
        //validation.addValidationField(new ValidationModel(searchAC, Validation.TYPE_EMPTY_FIELD_VALIDATION, getString(R.string.err_msg_search)));
        searchKeyTV.setTextColor(Color.WHITE);
        searchedKeywordRV.setLayoutManager(new LinearLayoutManager(this));

        searchAdapter = new SearchAdapter(this, new ArrayList<ProductModel>(), this);
        searchedKeywordRV.setAdapter(searchAdapter);

        //CallWebService.getInstance(this, false, ApiCodes.GET_BRANDS).hitJsonObjectRequestAPI(CallWebService.POST, API.GET_BRANDS, null, this);
        filterSP.setOnItemSelectedListener(this);

        searchAC.addTextChangedListener(this);


    }

    private void setInitialValues() {
        selectedTV = allTV;
        setTextSizeToTextView(allTV, false);
        selectedSearchType = ALL;
    }

    @Override
    public void onClickItem(int position, View view) {
        super.onClickItem(position, view);
        switch (view.getId()) {
            case R.id.singleTextviewLL:
                hitGetDataApi();
                break;
            default:
                Intent intent = new Intent(this, DescriptionActivity.class);
                intent.putExtra(Constants.MOD_URL, productModels.get(position).getMod_url());
                startActivity(intent);
                break;
        }

    }

    @Override
    public void onJsonObjectSuccess(JSONObject response, int apiType) throws JSONException {
        super.onJsonObjectSuccess(response, apiType);
        switch (apiType) {
            case ApiCodes.GET_AUTO_SEARCH:
                autoSearchKeywordModels = UniversalParser.getInstance().parseJsonArrayWithJsonObject(response.getJSONArray(Constants.DATA), AutoSearchKeywordModel.class);
                autoCompleteArrayAdapter = new AutoCompleteArrayAdapter(this, R.layout.auto_complete_array_adapter, autoSearchKeywordModels, this);
                searchAC.setAdapter(autoCompleteArrayAdapter);
                searchAC.setThreshold(1);
                searchAC.showDropDown();
                break;
            case ApiCodes.GET_BRANDS:
                brandsModels = UniversalParser.getInstance().parseJsonArrayWithJsonObject(response.getJSONArray(Constants.DATA), BrandsModel.class);
                spinnerAdapter = new SpinnerAdapter(this, 0, brandsModels);
                brandSP.setAdapter(spinnerAdapter);
                brandSP.setOnItemSelectedListener(this);
                break;
            case ApiCodes.SEARCH:
                productModels = UniversalParser.getInstance().parseJsonArrayWithJsonObject(response.getJSONObject(Constants.DATA).getJSONArray(Constants.LISTING), ProductModel.class);
                searchAdapter.addItems(productModels);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.descriptipn_menu, menu);
        return true;
    }


    private void createSortByMap() {

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

    @OnClick({R.id.allTV, R.id.materialTV, R.id.testSeriesTV, R.id.videosTV})
    public void onClick(View view) {
        if (selectedTV == ((TextView) view)) {
            return;
        } else {
            switch (view.getId()) {
                case R.id.allTV:
                    selectedSearchType = ALL;
                    break;
                case R.id.materialTV:
                    selectedSearchType = MATERIAL;
                    break;
                case R.id.testSeriesTV:
                    selectedSearchType = TEST_SERIES;
                    break;
                case R.id.videosTV:
                    selectedSearchType = VIDEOS;
                    break;
            }
            setTextSizeToTextView(selectedTV, true);
            setTextSizeToTextView((TextView) view, false);
            selectedTV = ((TextView) view);
        }
    }

    private void setTextSizeToTextView(TextView textView, boolean resetTV) {
        if (resetTV)
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.fourteen_sp));
        else
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.sixteen_sp));
    }

    @OnClick(R.id.searchBT)
    public void onClick() {
        hitGetDataApi();
    }

    private void hitGetDataApi() {
        selectedTextFromAC = searchAC.getText().toString();
        if (selectedTextFromAC.length() > 0) {
            CallWebService.getInstance(this, true, ApiCodes.SEARCH).hitJsonObjectRequestAPI(CallWebService.POST, API.PRODUCT_LISTING_FILER, createJsonForGettingSearchResult(), this);
        }
    }

    private JSONObject createJsonForGettingSearchResult() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(Constants.SEARCH_KEYWORDS, selectedTextFromAC);
            jsonObject.put(Constants.SEARCH_TYPE, selectedSearchType);
            jsonObject.put(Constants.C_BRAND_ID, selectedBrandID);
            jsonObject.put(Constants.ORDER_BY, selectedOrderBy);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (count > 0) {
            CallWebService.getInstance(this, true, ApiCodes.GET_AUTO_SEARCH).hitJsonObjectRequestAPI(CallWebService.POST, API.GET_AUTO_SEARCH, createJsonForGetData(s.toString()), this);

        }
    }

    private JSONObject createJsonForGetData(String s) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(Constants.TERM, s);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int viewId = view.getId();
        if (viewId == R.id.spinnerItemTV) {
            selectedBrandID = brandsModels.get(position).getId();
        } else {
            selectedOrderBy = sortingValueArray[position];
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
