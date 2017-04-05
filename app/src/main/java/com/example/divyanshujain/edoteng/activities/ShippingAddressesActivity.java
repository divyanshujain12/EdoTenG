package com.example.divyanshujain.edoteng.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.example.divyanshujain.edoteng.Adapters.AddressesAdapter;
import com.example.divyanshujain.edoteng.Constants.API;
import com.example.divyanshujain.edoteng.Constants.ApiCodes;
import com.example.divyanshujain.edoteng.Constants.Constants;
import com.example.divyanshujain.edoteng.GlobalClasses.BaseActivity;
import com.example.divyanshujain.edoteng.Models.AddressModel;
import com.example.divyanshujain.edoteng.Models.CityModel;
import com.example.divyanshujain.edoteng.Models.CountryModel;
import com.example.divyanshujain.edoteng.Models.StateModel;
import com.example.divyanshujain.edoteng.R;
import com.example.divyanshujain.edoteng.Utils.CallWebService;
import com.example.divyanshujain.edoteng.Utils.CommonFunctions;
import com.example.divyanshujain.edoteng.Utils.MySharedPereference;
import com.example.divyanshujain.edoteng.Utils.ProductsSingleton;
import com.example.divyanshujain.edoteng.Utils.UniversalParser;
import com.neopixl.pixlui.components.textview.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ShippingAddressesActivity extends BaseActivity {

    @InjectView(R.id.toolbarView)
    Toolbar toolbarView;
    @InjectView(R.id.totalItemsPriceTV)
    TextView totalItemsPriceTV;
    @InjectView(R.id.shippingPriceTV)
    TextView shippingPriceTV;
    @InjectView(R.id.totalPriceTV)
    TextView totalPriceTV;
    @InjectView(R.id.proceedToPaymentTV)
    TextView proceedToPaymentTV;
    @InjectView(R.id.activity_shipping_addresses)
    FrameLayout activityShippingAddresses;
    @InjectView(R.id.updateAddressTV)
    TextView addAddressTV;
    @InjectView(R.id.fullNameTV)
    TextView fullNameTV;
    @InjectView(R.id.phoneNumberTV)
    TextView phoneNumberTV;
    @InjectView(R.id.pincodeTV)
    TextView pincodeTV;
    @InjectView(R.id.addressTV)
    TextView addressTV;
    @InjectView(R.id.cityTV)
    TextView cityTV;
    @InjectView(R.id.deleteAddressTV)
    TextView deleteAddressTV;
    @InjectView(R.id.stateTV)
    TextView stateTV;
    @InjectView(R.id.countryTV)
    TextView countryTV;

    private AddressesAdapter addressesAdapter;

    private CountryModel countryModel = new CountryModel();
    private StateModel stateModel = new StateModel();
    private CityModel cityModel = new CityModel();
    private String selectedCityName, selectedStateName, selectedCountryName;
    int selectedCountryPos, selectedStatePos, selectedCityPos;
    private String completeAddress = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping_addresses);
        ButterKnife.inject(this);
        initViews();
    }

    private void initViews() {
        CommonFunctions.getInstance().configureToolbarWithBackButton(this, toolbarView, getString(R.string.ship_addresses));
        stateTV.setVisibility(View.GONE);
        countryTV.setVisibility(View.GONE);

    }

    @Override
    public void onJsonObjectSuccess(JSONObject response, int apiType) throws JSONException {
        super.onJsonObjectSuccess(response, apiType);
        switch (apiType) {
            case ApiCodes.GET_ADDRESS:
                ProductsSingleton.getInstance().addressModel = UniversalParser.getInstance().parseJsonObject(response.getJSONObject(Constants.DATA), AddressModel.class);
                changeUi(ProductsSingleton.getInstance().addressModel);
                break;
            case ApiCodes.GET_COUNTRY:
                parseCountryData(response);
                break;
            case ApiCodes.GET_STATE:
                parseStateData(response);
                break;
            case ApiCodes.GET_CITY:
                parserCityData(response);
                break;
        }

    }

    private void parserCityData(JSONObject response) throws JSONException {
        ProductsSingleton.getInstance().cityModels = UniversalParser.getInstance().parseJsonArrayWithJsonObject(response.getJSONArray(Constants.DATA), CityModel.class);
        selectedCityPos = ProductsSingleton.getInstance().cityModels.indexOf(cityModel);
        selectedCityName = ProductsSingleton.getInstance().cityModels.get(selectedCityPos).getName();
        ProductsSingleton.getInstance().addressModel.setCity(String.valueOf(selectedCityPos));


        completeAddress = selectedCityName + "," + selectedStateName + "," + selectedCountryName;
        cityTV.setText(getString(R.string.address) + completeAddress);
    }

    private void parseStateData(JSONObject response) throws JSONException {
        ProductsSingleton.getInstance().stateModels = UniversalParser.getInstance().parseJsonArrayWithJsonObject(response.getJSONArray(Constants.DATA), StateModel.class);
        selectedStatePos = ProductsSingleton.getInstance().stateModels.indexOf(stateModel);
        selectedStateName = ProductsSingleton.getInstance().stateModels.get(selectedStatePos).getName();
        stateTV.setText("");
        ProductsSingleton.getInstance().addressModel.setState(String.valueOf(selectedStatePos));
        CallWebService.getInstance(this, true, ApiCodes.GET_CITY).hitJsonObjectRequestAPI(CallWebService.POST, API.GET_ALL_CITY, createJsonForGetCities(stateModel.getState_id()), this);
    }

    private void parseCountryData(JSONObject response) throws JSONException {
        ProductsSingleton.getInstance().countryModels = UniversalParser.getInstance().parseJsonArrayWithJsonObject(response.getJSONArray(Constants.DATA), CountryModel.class);
        selectedCountryPos = ProductsSingleton.getInstance().countryModels.indexOf(countryModel);
        selectedCountryName = ProductsSingleton.getInstance().countryModels.get(selectedCountryPos).getName();
        countryTV.setText("");
        CallWebService.getInstance(this, true, ApiCodes.GET_STATE).hitJsonObjectRequestAPI(CallWebService.POST, API.GET_ALL_STATE, createJsonForGetStates(countryModel.getCountry_id()), this);
        ProductsSingleton.getInstance().addressModel.setCountry(String.valueOf(selectedCountryPos));
    }

    private void changeUi(AddressModel addressModel) {
        fullNameTV.setText(getString(R.string.name) + "-" + addressModel.getName());
        phoneNumberTV.setText(getString(R.string.phone_number) + "-" + addressModel.getPhone());
        pincodeTV.setText(getString(R.string.pin_code) + "-" + addressModel.getPin());
        addressTV.setText(getString(R.string.flat_house_number_floor_building) + "-" + addressModel.getAddress1());
        countryModel.setCountry_id(addressModel.getCountry());
        stateModel.setState_id(addressModel.getState());
        cityModel.setCity_id(addressModel.getCity());
        CallWebService.getInstance(this, true, ApiCodes.GET_COUNTRY).hitJsonObjectRequestAPI(CallWebService.POST, API.GET_ALL_COUNTRIES, null, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        CallWebService.getInstance(this, true, ApiCodes.GET_ADDRESS).hitJsonObjectRequestAPI(CallWebService.POST, API.GET_ADDRESS, createJsonForGetAddress(), this);
    }

    private JSONObject createJsonForGetAddress() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(Constants.USER_ID, MySharedPereference.getInstance().getString(this, Constants.USER_ID));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    @OnClick({R.id.deleteAddressTV, R.id.proceedToPaymentTV, R.id.updateAddressTV})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.updateAddressTV:
                startActivity(new Intent(this, AddShippingAddressActivity.class));
                break;
            case R.id.deleteAddressTV:
                break;
            case R.id.proceedToPaymentTV:
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

}
