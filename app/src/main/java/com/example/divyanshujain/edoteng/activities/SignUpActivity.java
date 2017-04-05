package com.example.divyanshujain.edoteng.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.divyanshujain.edoteng.Adapters.CityAdapter;
import com.example.divyanshujain.edoteng.Adapters.CountryAdapter;
import com.example.divyanshujain.edoteng.Adapters.StateAdapter;
import com.example.divyanshujain.edoteng.Constants.API;
import com.example.divyanshujain.edoteng.Constants.ApiCodes;
import com.example.divyanshujain.edoteng.Constants.Constants;
import com.example.divyanshujain.edoteng.CustomViews.CustomAlertDialogs;
import com.example.divyanshujain.edoteng.CustomViews.CustomTopSnackbars;
import com.example.divyanshujain.edoteng.GlobalClasses.BaseActivity;
import com.example.divyanshujain.edoteng.Models.CityModel;
import com.example.divyanshujain.edoteng.Models.CountryModel;
import com.example.divyanshujain.edoteng.Models.StateModel;
import com.example.divyanshujain.edoteng.Models.ValidationModel;
import com.example.divyanshujain.edoteng.R;
import com.example.divyanshujain.edoteng.Utils.CallWebService;
import com.example.divyanshujain.edoteng.Utils.CommonFunctions;
import com.example.divyanshujain.edoteng.Utils.UniversalParser;
import com.example.divyanshujain.edoteng.Utils.Validation;
import com.neopixl.pixlui.components.button.Button;
import com.neopixl.pixlui.components.edittext.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class SignUpActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {
    @InjectView(R.id.toolbarView)
    Toolbar toolbarView;
    @InjectView(R.id.usernameET)
    EditText usernameET;
    @InjectView(R.id.emailET)
    EditText emailET;
    @InjectView(R.id.phoneET)
    EditText phoneET;
    @InjectView(R.id.passwordET)
    EditText passwordET;
    @InjectView(R.id.confPasswordET)
    EditText confPasswordET;
    @InjectView(R.id.activity_sign_up)
    LinearLayout activitySignUp;
    @InjectView(R.id.signUpBT)
    Button signUpBT;
    @InjectView(R.id.fullNameET)
    EditText fullNameET;
    @InjectView(R.id.phoneNumberET)
    EditText phoneNumberET;
    @InjectView(R.id.pinCodeET)
    EditText pinCodeET;
    @InjectView(R.id.flatNumberET)
    EditText flatNumberET;
    @InjectView(R.id.localityET)
    EditText localityET;
    @InjectView(R.id.citySP)
    Spinner citySP;
    @InjectView(R.id.stateSP)
    Spinner stateSP;
    @InjectView(R.id.countrySP)
    Spinner countrySP;
    /*@InjectView(R.id.landMarkET)
    EditText landMarkET;
    @InjectView(R.id.cityET)
    EditText cityET;*/
    private Validation validation;
    private HashMap<View, String> formValues;
    private String genderStr;


    CityAdapter cityAdapter;
    StateAdapter stateAdapter;
    CountryAdapter countryAdapter;

    private ArrayList<CityModel> cityModels = new ArrayList<>();
    private ArrayList<StateModel> stateModels = new ArrayList<>();
    private ArrayList<CountryModel> countryModels = new ArrayList<>();

    private String selectedCityName, selectedStateName, selectedCountryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.inject(this);
        initViews();

    }

    private void initViews() {
        CommonFunctions.getInstance().configureToolbarWithBackButton(this, toolbarView, getString(R.string.sign_up));
        validation = new Validation();
        addValidation();
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

    private void addValidation() {
        validation = new Validation();
        validation.addValidationField(new ValidationModel(usernameET, Validation.TYPE_NAME_VALIDATION, getString(R.string.err_user_name)));
        validation.addValidationField(new ValidationModel(phoneET, Validation.TYPE_PHONE_VALIDATION, getString(R.string.err_phone_number)));
        validation.addValidationField(new ValidationModel(emailET, Validation.TYPE_EMAIL_VALIDATION, getString(R.string.err_email)));
        validation.addValidationField(new ValidationModel(passwordET, Validation.TYPE_PASSWORD_VALIDATION, getString(R.string.err_pass)));
        validation.addValidationField(new ValidationModel(confPasswordET, Validation.TYPE_PASSWORD_VALIDATION, getString(R.string.err_re_enter_pass)));
    }


    @OnClick(R.id.signUpBT)
    public void onClick() {
        hitWebService();
    }

    private void hitWebService() {
        formValues = validation.validate(this);
        if (formValues != null) {
            if (!formValues.get(passwordET).equals(formValues.get(confPasswordET))) {
                CustomTopSnackbars.getInstance().getErrorSnackbar(this).setText(R.string.err_password_mismatch).show();
                return;
            }

            CallWebService.getInstance(this, true, ApiCodes.REGISTRATION).hitJsonObjectRequestAPI(CallWebService.POST, API.REGISTRATION, createJsonForSignUp(), this);
        }
    }

    @Override
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
            case ApiCodes.REGISTRATION:
                CustomAlertDialogs.showAlertDialog(this, getString(R.string.congratulation), response.getString(Constants.MESSAGE), this);
                break;
        }

    }

    private JSONObject createJsonForSignUp() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(Constants.NAME, formValues.get(usernameET));
            jsonObject.put(Constants.PHONE_NUMBER, formValues.get(phoneET));
            jsonObject.put(Constants.EMAIl, formValues.get(emailET));
            jsonObject.put(Constants.PASSWORD, formValues.get(passwordET));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    @Override
    public void doAction() {
        super.doAction();
        onBackPressed();
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
}
