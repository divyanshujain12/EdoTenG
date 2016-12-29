package com.example.divyanshujain.edoteng.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.divyanshujain.edoteng.Constants.API;
import com.example.divyanshujain.edoteng.Constants.ApiCodes;
import com.example.divyanshujain.edoteng.Constants.Constants;
import com.example.divyanshujain.edoteng.CustomViews.CustomAlertDialogs;
import com.example.divyanshujain.edoteng.GlobalClasses.BaseActivity;
import com.example.divyanshujain.edoteng.Models.ValidationModel;
import com.example.divyanshujain.edoteng.R;
import com.example.divyanshujain.edoteng.Utils.CallWebService;
import com.example.divyanshujain.edoteng.Utils.CommonFunctions;
import com.example.divyanshujain.edoteng.Utils.Validation;
import com.neopixl.pixlui.components.button.Button;
import com.neopixl.pixlui.components.edittext.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ForgotPasswordActivity extends BaseActivity {

    @InjectView(R.id.toolbarView)
    Toolbar toolbarView;
    @InjectView(R.id.emailET)
    EditText emailET;
    @InjectView(R.id.sendPasswordBT)
    Button sendPasswordBT;
    @InjectView(R.id.activity_forgot_password)
    RelativeLayout activityForgotPassword;
    private Validation validation;
    private HashMap<View, String> hashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.inject(this);
        initViews();
    }

    private void initViews() {
        validation = new Validation();
        CommonFunctions.getInstance().configureToolbarWithBackButton(this, toolbarView, getString(R.string.forgot_password));
        validation.addValidationField(new ValidationModel(emailET, Validation.TYPE_EMAIL_VALIDATION, getString(R.string.err_email)));
    }

    @OnClick(R.id.sendPasswordBT)
    public void onClick() {
        hashMap = validation.validate(this);
        if (hashMap != null) {
            CallWebService.getInstance(this, true, ApiCodes.FORGOT_PASSWORD).hitJsonObjectRequestAPI(CallWebService.POST, API.FORGOT_PASSWORD, createJsonForForgotPassword(), this);
        }
    }

    @Override
    public void onJsonObjectSuccess(JSONObject response, int apiType) throws JSONException {
        super.onJsonObjectSuccess(response, apiType);
        CustomAlertDialogs.showAlertDialogWithCallBack(this, getString(R.string.password_reset), response.getString(Constants.MESSAGE), this);
    }

    private JSONObject createJsonForForgotPassword() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(Constants.EMAIl, hashMap.get(emailET));
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
}
