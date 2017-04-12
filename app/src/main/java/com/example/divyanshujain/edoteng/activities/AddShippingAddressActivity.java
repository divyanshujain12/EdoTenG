package com.example.divyanshujain.edoteng.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.example.divyanshujain.edoteng.Adapters.CityAdapter;
import com.example.divyanshujain.edoteng.Adapters.CountryAdapter;
import com.example.divyanshujain.edoteng.Adapters.StateAdapter;
import com.example.divyanshujain.edoteng.Constants.API;
import com.example.divyanshujain.edoteng.Constants.ApiCodes;
import com.example.divyanshujain.edoteng.Constants.Constants;
import com.example.divyanshujain.edoteng.CustomViews.CustomToasts;
import com.example.divyanshujain.edoteng.GlobalClasses.BaseActivity;
import com.example.divyanshujain.edoteng.Models.ValidationModel;
import com.example.divyanshujain.edoteng.R;
import com.example.divyanshujain.edoteng.Utils.CallWebService;
import com.example.divyanshujain.edoteng.Utils.CommonFunctions;
import com.example.divyanshujain.edoteng.Utils.MySharedPereference;
import com.example.divyanshujain.edoteng.Utils.ProductsSingleton;
import com.example.divyanshujain.edoteng.Utils.Validation;
import com.neopixl.pixlui.components.edittext.EditText;
import com.neopixl.pixlui.components.textview.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class AddShippingAddressActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {


    @InjectView(R.id.toolbarView)
    Toolbar toolbarView;
    @InjectView(R.id.totalItemsPriceTV)
    TextView totalItemsPriceTV;
    @InjectView(R.id.shippingPriceTV)
    TextView shippingPriceTV;
    @InjectView(R.id.totalPriceTV)
    TextView totalPriceTV;
    @InjectView(R.id.fullNameET)
    EditText fullNameET;
    @InjectView(R.id.phoneNumberET)
    EditText phoneNumberET;
    @InjectView(R.id.flatNumberET)
    EditText flatNumberET;
    @InjectView(R.id.localityET)
    EditText localityET;
    @InjectView(R.id.pinCodeET)
    EditText pinCodeET;
    @InjectView(R.id.citySP)
    Spinner citySP;
    @InjectView(R.id.stateSP)
    Spinner stateSP;
    @InjectView(R.id.countrySP)
    Spinner countrySP;
    @InjectView(R.id.saveAddressTV)
    TextView saveAddressTV;
    @InjectView(R.id.proceedToPaymentTV)
    TextView proceedToPaymentTV;
    @InjectView(R.id.activity_shipping_address)
    FrameLayout activityShippingAddress;


    CityAdapter cityAdapter;
    StateAdapter stateAdapter;
    CountryAdapter countryAdapter;

    private String selectedCityID, selectedStateID, selectedCountryID;

    private Validation validation;
    private HashMap<View, String> hashMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_shipping_address);

        ButterKnife.inject(this);
        initViews();
    }

    private void initViews() {
        CommonFunctions.getInstance().configureToolbarWithBackButton(this, toolbarView, "Update Address");

        validation = new Validation();
        validation.addValidationField(new ValidationModel(fullNameET, Validation.TYPE_EMPTY_FIELD_VALIDATION, Validation.USERNAME_EMPTY_FIELD_VALIDATION));
        validation.addValidationField(new ValidationModel(phoneNumberET, Validation.TYPE_PHONE_VALIDATION, Validation.PHONE_VALID_FIELD_VALIDATION));
        validation.addValidationField(new ValidationModel(flatNumberET, Validation.TYPE_EMPTY_FIELD_VALIDATION, Validation.ALL_FILED_MANDATORY));
        validation.addValidationField(new ValidationModel(localityET, Validation.TYPE_EMPTY_FIELD_VALIDATION, Validation.ALL_FILED_MANDATORY));
        validation.addValidationField(new ValidationModel(pinCodeET, Validation.TYPE_EMPTY_FIELD_VALIDATION, Validation.ALL_FILED_MANDATORY));

        countryAdapter = new CountryAdapter(this, 0, ProductsSingleton.getInstance().countryModels);
        countrySP.setAdapter(countryAdapter);
        stateAdapter = new StateAdapter(this, 0, ProductsSingleton.getInstance().stateModels);
        stateSP.setAdapter(stateAdapter);
        cityAdapter = new CityAdapter(this, 0, ProductsSingleton.getInstance().cityModels);
        citySP.setAdapter(cityAdapter);

        fullNameET.setText(ProductsSingleton.getInstance().addressModel.getName());
        phoneNumberET.setText(ProductsSingleton.getInstance().addressModel.getPhone());
        pinCodeET.setText(ProductsSingleton.getInstance().addressModel.getPin());
        flatNumberET.setText(ProductsSingleton.getInstance().addressModel.getAddress1());

        citySP.setOnItemSelectedListener(this);
        stateSP.setOnItemSelectedListener(this);
        countrySP.setOnItemSelectedListener(this);

        citySP.setSelection(Integer.parseInt(ProductsSingleton.getInstance().addressModel.getCity()));
        stateSP.setSelection((Integer.parseInt(ProductsSingleton.getInstance().addressModel.getState())));
        countrySP.setSelection((Integer.parseInt(ProductsSingleton.getInstance().addressModel.getCountry())));

    }

    @OnClick({R.id.saveAddressTV, R.id.proceedToPaymentTV})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.saveAddressTV:
                hashMap = validation.validate(this);
                if (hashMap != null) {
                    CallWebService.getInstance(this, true, ApiCodes.UPDATE_SHIPPING_ADDRESS).hitJsonObjectRequestAPI(CallWebService.POST, API.UPDATE_SHIPPING_ADDRESS, createJsonForUpdateAddress(), this);
                }

                break;
            case R.id.proceedToPaymentTV:
                break;
        }
    }

    @Override
    public void onJsonObjectSuccess(JSONObject response, int apiType) throws JSONException {
        super.onJsonObjectSuccess(response, apiType);
        switch (apiType) {
            case ApiCodes.UPDATE_SHIPPING_ADDRESS:
                CustomToasts.getInstance(this).showSuccessToast(response.getString(Constants.MESSAGE));
                startActivity(new Intent(this, ShippingAddressesActivity.class));
                break;
        }
    }

    @Override
    public void onFailure(String str, int apiType) {
        super.onFailure(str, apiType);
        switch (apiType) {
            case ApiCodes.UPDATE_SHIPPING_ADDRESS:
                CustomToasts.getInstance(this).showErrorToast(str);
                break;
        }
    }

    /*@Override
    public void onJsonObjectSuccess(JSONObject response, int apiType) throws JSONException {
        super.onJsonObjectSuccess(response, apiType);
        switch (apiType) {
            case ApiCodes.GET_COUNTRY:
                countryModels = UniversalParser.getInstance().parseJsonArrayWithJsonObject(response.getJSONArray(Constants.DATA), CountryModel.class);
                countryAdapter = new CountryAdapter(this, 0, countryModels);
                countrySP.setAdapter(countryAdapter);
                break;
            case ApiCodes.GET_STATE:
                stateModels = UniversalParser.getInstance().parseJsonArrayWithJsonObject(response.getJSONArray(Constants.DATA), StateModel.class);
                stateAdapter = new StateAdapter(this, 0, stateModels);
                stateSP.setAdapter(stateAdapter);
                break;
            case ApiCodes.GET_CITY:
                cityModels = UniversalParser.getInstance().parseJsonArrayWithJsonObject(response.getJSONArray(Constants.DATA), CityModel.class);
                cityAdapter = new CityAdapter(this, 0, cityModels);
                citySP.setAdapter(cityAdapter);
                break;
        }

    }*/

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.citySP:
                selectedCityID = ProductsSingleton.getInstance().cityModels.get(position).getCity_id();
                break;
            case R.id.stateSP:
                selectedStateID = ProductsSingleton.getInstance().stateModels.get(position).getState_id();
                break;
            case R.id.countrySP:
                selectedCountryID = ProductsSingleton.getInstance().countryModels.get(position).getCountry_id();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private JSONObject createJsonForGetStates(String country_id) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(Constants.COUNTRY_ID, country_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    private JSONObject createJsonForGetCities(String stateID) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(Constants.STATE_ID, stateID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    private JSONObject createJsonForUpdateAddress() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(Constants.USER_ID, MySharedPereference.getInstance().getString(this,Constants.USER_ID));
            jsonObject.put(Constants.NAME, hashMap.get(fullNameET));
            jsonObject.put(Constants.ADDRESS_ONE, hashMap.get(flatNumberET));
            jsonObject.put(Constants.ADDRESS_TWO, hashMap.get(localityET));
            jsonObject.put(Constants.PIN, hashMap.get(pinCodeET));
            jsonObject.put(Constants.PHONE, hashMap.get(phoneNumberET));
            jsonObject.put(Constants.CITY, selectedCityID);
            jsonObject.put(Constants.STATE, selectedStateID);
            jsonObject.put(Constants.COUNTRY, selectedCountryID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
