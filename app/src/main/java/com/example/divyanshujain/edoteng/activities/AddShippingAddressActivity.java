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
import com.example.divyanshujain.edoteng.GlobalClasses.BaseActivity;
import com.example.divyanshujain.edoteng.Models.CityModel;
import com.example.divyanshujain.edoteng.Models.CountryModel;
import com.example.divyanshujain.edoteng.Models.StateModel;
import com.example.divyanshujain.edoteng.Models.ValidationModel;
import com.example.divyanshujain.edoteng.R;
import com.example.divyanshujain.edoteng.Utils.CallWebService;
import com.example.divyanshujain.edoteng.Utils.CommonFunctions;
import com.example.divyanshujain.edoteng.Utils.Validation;
import com.neopixl.pixlui.components.edittext.EditText;
import com.neopixl.pixlui.components.textview.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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

    private ArrayList<CityModel> cityModels = new ArrayList<>();
    private ArrayList<StateModel> stateModels = new ArrayList<>();
    private ArrayList<CountryModel> countryModels = new ArrayList<>();

    private Validation validation;

    private String selectedCityName, selectedStateName, selectedCountryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_shipping_address);

        ButterKnife.inject(this);
        initViews();
    }

    private void initViews() {
        CommonFunctions.getInstance().configureToolbarWithBackButton(this, toolbarView, "Add Address");

        validation = new Validation();
        validation.addValidationField(new ValidationModel(fullNameET, Validation.TYPE_EMPTY_FIELD_VALIDATION, Validation.USERNAME_EMPTY_FIELD_VALIDATION));
        validation.addValidationField(new ValidationModel(phoneNumberET, Validation.TYPE_PHONE_VALIDATION, Validation.PHONE_VALID_FIELD_VALIDATION));
        validation.addValidationField(new ValidationModel(flatNumberET, Validation.TYPE_EMPTY_FIELD_VALIDATION, Validation.ALL_FILED_MANDATORY));
        validation.addValidationField(new ValidationModel(localityET, Validation.TYPE_EMPTY_FIELD_VALIDATION, Validation.ALL_FILED_MANDATORY));
        validation.addValidationField(new ValidationModel(pinCodeET, Validation.TYPE_EMPTY_FIELD_VALIDATION, Validation.ALL_FILED_MANDATORY));

        citySP.setOnItemSelectedListener(this);
        stateSP.setOnItemSelectedListener(this);
        countrySP.setOnItemSelectedListener(this);

        cityAdapter = new CityAdapter(this, 0, cityModels);
        stateAdapter = new StateAdapter(this, 0, stateModels);
        countryAdapter = new CountryAdapter(this, 0, countryModels);

        citySP.setAdapter(cityAdapter);
        stateSP.setAdapter(stateAdapter);
        countrySP.setAdapter(countryAdapter);

        CallWebService.getInstance(this, true, ApiCodes.GET_COUNTRY).hitJsonObjectRequestAPI(CallWebService.POST, API.GET_ALL_COUNTRIES, null, this);
    }

    @OnClick({R.id.saveAddressTV, R.id.proceedToPaymentTV})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.saveAddressTV:
                startActivity(new Intent(this, ShippingAddressesActivity.class));
                break;
            case R.id.proceedToPaymentTV:
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.citySP:
                selectedCityName = cityModels.get(position).getName();
                break;
            case R.id.stateSP:
                selectedStateName = stateModels.get(position).getName();
                CallWebService.getInstance(this, true, ApiCodes.GET_CITY).hitJsonObjectRequestAPI(CallWebService.POST, API.GET_ALL_CITY, createJsonForGetCities(stateModels.get(position).getState_id()), this);
                break;
            case R.id.countrySP:
                selectedCountryName = countryModels.get(position).getName();
                CallWebService.getInstance(this, true, ApiCodes.GET_STATE).hitJsonObjectRequestAPI(CallWebService.POST, API.GET_ALL_STATE, createJsonForGetStates(countryModels.get(position).getCountry_id()), this);
                break;
        }
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

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
