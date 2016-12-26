package com.example.divyanshujain.edoteng.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.example.divyanshujain.edoteng.Adapters.AddressesAdapter;
import com.example.divyanshujain.edoteng.GlobalClasses.BaseActivity;
import com.example.divyanshujain.edoteng.R;
import com.example.divyanshujain.edoteng.Utils.CommonFunctions;
import com.neopixl.pixlui.components.textview.TextView;

import java.util.ArrayList;

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
    @InjectView(R.id.addressesRV)
    RecyclerView addressesRV;
    @InjectView(R.id.deleteAddressTV)
    TextView deleteAddressTV;
    @InjectView(R.id.proceedToPaymentTV)
    TextView proceedToPaymentTV;
    @InjectView(R.id.activity_shipping_addresses)
    FrameLayout activityShippingAddresses;
    @InjectView(R.id.addAddressTV)
    TextView addAddressTV;

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
        addressesRV.setLayoutManager(new LinearLayoutManager(this));
        addressesAdapter = new AddressesAdapter(this, new ArrayList<String>(), this);
        addressesRV.setAdapter(addressesAdapter);
    }

    @OnClick({R.id.deleteAddressTV, R.id.proceedToPaymentTV, R.id.addAddressTV})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addAddressTV:
                break;
            case R.id.deleteAddressTV:
            break;
            case R.id.proceedToPaymentTV:
            break;
        }
    }


}
