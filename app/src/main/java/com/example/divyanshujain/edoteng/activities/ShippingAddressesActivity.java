package com.example.divyanshujain.edoteng.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.example.divyanshujain.edoteng.Adapters.AddressesAdapter;
import com.example.divyanshujain.edoteng.GlobalClasses.BaseActivity;
import com.example.divyanshujain.edoteng.R;
import com.example.divyanshujain.edoteng.Utils.CommonFunctions;
import com.neopixl.pixlui.components.textview.TextView;

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
    @InjectView(R.id.flatNumberTV)
    TextView flatNumberTV;
    @InjectView(R.id.landmarkTV)
    TextView landmarkTV;
    @InjectView(R.id.cityTV)
    TextView cityTV;
    @InjectView(R.id.deleteAddressTV)
    TextView deleteAddressTV;

    private AddressesAdapter addressesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping_addresses);
        ButterKnife.inject(this);
        initViews();
    }

    private void initViews() {
        CommonFunctions.getInstance().configureToolbarWithBackButton(this, toolbarView, getString(R.string.ship_addresses));

    }

    @OnClick({R.id.deleteAddressTV, R.id.proceedToPaymentTV, R.id.updateAddressTV})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.updateAddressTV:
                startActivity(new Intent(this,AddShippingAddressActivity.class));
                break;
            case R.id.deleteAddressTV:
                break;
            case R.id.proceedToPaymentTV:
                break;
        }
    }


}
