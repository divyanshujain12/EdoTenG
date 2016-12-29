package com.example.divyanshujain.edoteng.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.divyanshujain.edoteng.Constants.API;
import com.example.divyanshujain.edoteng.Constants.ApiCodes;
import com.example.divyanshujain.edoteng.Constants.Constants;
import com.example.divyanshujain.edoteng.GlobalClasses.BaseActivity;
import com.example.divyanshujain.edoteng.Models.UserModel;
import com.example.divyanshujain.edoteng.Models.ValidationModel;
import com.example.divyanshujain.edoteng.R;
import com.example.divyanshujain.edoteng.Utils.CallWebService;
import com.example.divyanshujain.edoteng.Utils.MySharedPereference;
import com.example.divyanshujain.edoteng.Utils.UniversalParser;
import com.example.divyanshujain.edoteng.Utils.Validation;
import com.neopixl.pixlui.components.button.Button;
import com.neopixl.pixlui.components.edittext.EditText;
import com.neopixl.pixlui.components.textview.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @InjectView(R.id.emailET)
    EditText emailET;
    @InjectView(R.id.passwordET)
    EditText passwordET;
    @InjectView(R.id.signInBT)
    Button signInBT;
    @InjectView(R.id.signUpBT)
    Button signUpBT;
    @InjectView(R.id.activity_main)
    LinearLayout activityMain;
    @InjectView(R.id.forgotPassTV)
    TextView forgotPassTV;
    private Validation validation;
    private HashMap<View, String> validationMap = new HashMap<>();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.inject(this);
        addValidation();

    }

    private void addValidation() {
        validation = new Validation();
        validation.addValidationField(new ValidationModel(emailET, Validation.TYPE_NAME_VALIDATION, getString(R.string.err_user_name)));
        validation.addValidationField(new ValidationModel(passwordET, Validation.TYPE_PASSWORD_VALIDATION, getString(R.string.err_pass)));
        //emailET.setText("divyanshujain12@hotmail.com");
        //passwordET.setText("divyanshu");
    }


    @OnClick({R.id.signInBT, R.id.signUpBT, R.id.forgotPassTV})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signInBT:
               // validateFields();
                goToHome();
                break;
            case R.id.signUpBT:
                Intent intent = new Intent(this, SignUpActivity.class);
                startActivity(intent);
                break;
            case R.id.forgotPassTV:
                /*Intent forgotPasswordIntent = new Intent(this, ForgotPassword.class);
                startActivity(forgotPasswordIntent);*/
                break;
        }
    }

    private void validateFields() {

        validationMap = validation.validate(this);
        if (validationMap != null) {
            CallWebService.getInstance(this, true, ApiCodes.LOGIN).hitJsonObjectRequestAPI(CallWebService.POST, API.LOGIN, createJsonForSignIn(), this);
        }
    }

    private JSONObject createJsonForSignIn() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(Constants.EMAIl, validationMap.get(emailET));
            jsonObject.put(Constants.PASSWORD, validationMap.get(passwordET));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    @Override
    public void onJsonObjectSuccess(JSONObject response, int apiType) throws JSONException {
        super.onJsonObjectSuccess(response, apiType);

        UserModel userModel = UniversalParser.getInstance().parseJsonObject(response.getJSONObject(Constants.DATA), UserModel.class);
        MySharedPereference.getInstance().setString(this, Constants.NAME, userModel.getName());
        MySharedPereference.getInstance().setString(this, Constants.PHONE_NUMBER, userModel.getPhone());
        MySharedPereference.getInstance().setString(this, Constants.EMAIl, userModel.getEmail());
        MySharedPereference.getInstance().setString(this, Constants.DATE_OF_BIRTH, userModel.getDate_of_birth());
        MySharedPereference.getInstance().setString(this, Constants.PASSWORD, validationMap.get(passwordET));
        MySharedPereference.getInstance().setString(this, Constants.USER_ID, userModel.getUser_id());
        MySharedPereference.getInstance().setBoolean(this, Constants.IS_LOGGED_IN, true);

        goToHome();
    }

    private void goToHome() {
        Intent categoryIntent = new Intent(this, HomeActivity.class);
        startActivity(categoryIntent);
        finish();
    }

}
