package com.example.divyanshujain.edoteng.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.divyanshujain.edoteng.Adapters.ViewPagerAdapter;
import com.example.divyanshujain.edoteng.Constants.API;
import com.example.divyanshujain.edoteng.Constants.ApiCodes;
import com.example.divyanshujain.edoteng.Constants.Constants;
import com.example.divyanshujain.edoteng.DescriptionFragments.CourseDescriptionFragment;
import com.example.divyanshujain.edoteng.DescriptionFragments.ReviewsFragment;
import com.example.divyanshujain.edoteng.GlobalClasses.BaseActivity;
import com.example.divyanshujain.edoteng.Models.ProductDetailModel;
import com.example.divyanshujain.edoteng.Models.ProductReviewUserModel;
import com.example.divyanshujain.edoteng.Models.ReviewModel;
import com.example.divyanshujain.edoteng.R;
import com.example.divyanshujain.edoteng.Utils.CallWebService;
import com.example.divyanshujain.edoteng.Utils.CommonFunctions;
import com.example.divyanshujain.edoteng.Utils.NonSwipeableViewPager;
import com.example.divyanshujain.edoteng.Utils.UniversalParser;
import com.neopixl.pixlui.components.textview.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class DescriptionActivity extends BaseActivity {

    @InjectView(R.id.toolbarView)
    Toolbar toolbarView;
    @InjectView(R.id.descTV)
    TextView descTV;
    @InjectView(R.id.reviewTV)
    TextView reviewTV;


    @InjectView(R.id.productViewsVp)
    NonSwipeableViewPager productViewsVp;

    ViewPagerAdapter viewPagerAdapter;
    @InjectView(R.id.activity_description)
    LinearLayout activityDescription;
    private View lastSelectedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        ButterKnife.inject(this);
        initViews();
    }

    private void initViews() {

        CommonFunctions.getInstance().configureToolbarWithBackButton(this, toolbarView, getString(R.string.desc));

        lastSelectedView = descTV;

    }

    @Override
    public void onJsonObjectSuccess(JSONObject response, int apiType) throws JSONException {
        super.onJsonObjectSuccess(response, apiType);
        ProductDetailModel productDetailModel = UniversalParser.getInstance().parseJsonObject(response.getJSONObject(Constants.DATA).getJSONObject(Constants.PRODUCT_DETAIL), ProductDetailModel.class);
        ArrayList<ReviewModel> reviewModels = UniversalParser.getInstance().parseJsonArrayWithJsonObject(response.getJSONObject(Constants.DATA).getJSONArray(Constants.R_ARRAY), ReviewModel.class);
        ArrayList<ProductReviewUserModel> productReviewUserModels = UniversalParser.getInstance().parseJsonArrayWithJsonObject(response.getJSONObject(Constants.DATA).getJSONArray(Constants.PRODUCT_REVIEW), ProductReviewUserModel.class);

        productDetailModel.setrArray(reviewModels);
        productDetailModel.setProductReview(productReviewUserModels);
        setUpViewPager(productDetailModel);

    }

    private void setUpViewPager(ProductDetailModel productDetailModel) {
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(CourseDescriptionFragment.getInstance(productDetailModel), "");
        viewPagerAdapter.addFragment(ReviewsFragment.getInstance(productDetailModel), "");

        productViewsVp.setAdapter(viewPagerAdapter);
    }


    private JSONObject createJsonForGetProductDetail() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(Constants.MOD_URL, getIntent().getStringExtra(Constants.MOD_URL));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.descriptipn_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.action_cart:
                startActivity(new Intent(this, CartActivity.class));
                break;
            case R.id.action_user_setting:
                startActivity(new Intent(this, ApplicationSettingActivity.class));
                break;
        }
        return true;
    }

    @OnClick({R.id.descTV, R.id.reviewTV})
    public void onClick(View view) {
        if (lastSelectedView == view)
            return;
        switch (view.getId()) {
            case R.id.descTV:
                productViewsVp.setCurrentItem(0);
                break;
            case R.id.reviewTV:
                productViewsVp.setCurrentItem(1);
                break;
        }
        view.setBackgroundResource(R.drawable.rounded_top_corners_white);
        lastSelectedView.setBackgroundResource(R.drawable.rounded_top_corners_light_grey);
        lastSelectedView = view;
    }

    @Override
    protected void onResume() {
        super.onResume();
       /* if (getIntent().getIntExtra(Constants.FROM_CART, 0) == 1) {
            setUpViewPager((ProductDetailModel) getIntent().getParcelableExtra(Constants.DATA));
        } else {*/
            CallWebService.getInstance(this, true, ApiCodes.GET_PRODUCT_DETAIL).hitJsonObjectRequestAPI(CallWebService.POST, API.GET_PRODUCT_DETAIL, createJsonForGetProductDetail(), this);
//        }
    }
}

