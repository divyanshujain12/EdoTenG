package com.example.divyanshujain.edoteng;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

import com.example.divyanshujain.edoteng.Utils.CommonFunctions;
import com.neopixl.pixlui.components.button.Button;
import com.neopixl.pixlui.components.edittext.EditText;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SignUpActivity extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.inject(this);
        CommonFunctions.getInstance().configureToolbarWithBackButton(this, toolbarView, getString(R.string.sign_up));
    }
}
