package com.example.divyanshujain.edoteng.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.example.divyanshujain.edoteng.GlobalClasses.BaseActivity;
import com.example.divyanshujain.edoteng.R;
import com.example.divyanshujain.edoteng.Utils.CommonFunctions;
import com.neopixl.pixlui.components.edittext.EditText;
import com.neopixl.pixlui.components.textview.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class AddShippingAddressActivity extends BaseActivity {

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
    @InjectView(R.id.pinCodeET)
    EditText pinCodeET;
    @InjectView(R.id.flatNumberET)
    EditText flatNumberET;
    @InjectView(R.id.landMarkET)
    EditText landMarkET;
    @InjectView(R.id.cityET)
    EditText cityET;
    @InjectView(R.id.saveAddressTV)
    TextView saveAddressTV;
    @InjectView(R.id.proceedToPaymentTV)
    TextView proceedToPaymentTV;
    @InjectView(R.id.activity_shipping_address)
    FrameLayout activityShippingAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_shipping_address);

        ButterKnife.inject(this);
        initViews();
    }

    private void initViews() {
        CommonFunctions.getInstance().configureToolbarWithBackButton(this,toolbarView,"Add Address");
    }

    @OnClick({R.id.saveAddressTV, R.id.proceedToPaymentTV})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.saveAddressTV:
                startActivity(new Intent(this,ShippingAddressesActivity.class));
                break;
            case R.id.proceedToPaymentTV:
                break;
        }
    }
}
