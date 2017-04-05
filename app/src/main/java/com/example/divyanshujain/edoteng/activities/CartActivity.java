package com.example.divyanshujain.edoteng.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.divyanshujain.edoteng.Adapters.CartAdapter;
import com.example.divyanshujain.edoteng.Constants.Constants;
import com.example.divyanshujain.edoteng.GlobalClasses.BaseActivity;
import com.example.divyanshujain.edoteng.R;
import com.example.divyanshujain.edoteng.Utils.ProductsSingleton;
import com.example.divyanshujain.edoteng.Utils.CommonFunctions;
import com.neopixl.pixlui.components.textview.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class CartActivity extends BaseActivity {

    @InjectView(R.id.toolbarView)
    Toolbar toolbarView;
    @InjectView(R.id.totalItemsPriceTV)
    TextView totalItemsPriceTV;
    @InjectView(R.id.shippingPriceTV)
    TextView shippingPriceTV;
    @InjectView(R.id.totalPriceTV)
    TextView totalPriceTV;
    @InjectView(R.id.productRV)
    RecyclerView productRV;
    @InjectView(R.id.activity_cart)
    FrameLayout activityCart;
    @InjectView(R.id.checkoutFAB)
    FloatingActionButton checkoutFAB;

    private CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.inject(this);
        initViews();
    }

    private void initViews() {
        CommonFunctions.getInstance().configureToolbarWithBackButton(this, toolbarView, getString(R.string.cart));
        productRV.setLayoutManager(new LinearLayoutManager(this));
        cartAdapter = new CartAdapter(this, this);
        productRV.setAdapter(cartAdapter);
    }

    @Override
    public void onClickItem(int position, View view) {
        super.onClickItem(position, view);
        switch (view.getId()) {
            case R.id.detailTV:
                Intent intent = new Intent(this, DescriptionActivity.class);
                //  ProductsSingleton.getInstance().setProductDetailModel(ProductsSingleton.getInstance().getProductDetailModel(position));
                //intent.putExtra(Constants.DATA, ProductsSingleton.getInstance().getProductDetailModel(position));
                intent.putExtra(Constants.MOD_URL, ProductsSingleton.getInstance().getProductDetailModel(position).getMod_url());
                startActivity(intent);
                break;
            case R.id.removeTV:
                ProductsSingleton.getInstance().removeProduct(position);
                cartAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.cart_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
        }
        return true;
    }

    @OnClick({R.id.checkoutFAB})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.checkoutFAB:
                startActivity(new Intent(this, ShippingAddressesActivity.class));
                break;
        }
    }
}
