package com.example.divyanshujain.edoteng.DescriptionFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.divyanshujain.edoteng.Constants.API;
import com.example.divyanshujain.edoteng.Constants.ApiCodes;
import com.example.divyanshujain.edoteng.Constants.Constants;
import com.example.divyanshujain.edoteng.CustomViews.CustomAlertDialogs;
import com.example.divyanshujain.edoteng.CustomViews.CustomToasts;
import com.example.divyanshujain.edoteng.GlobalClasses.BaseFragment;
import com.example.divyanshujain.edoteng.Interfaces.SnackBarCallback;
import com.example.divyanshujain.edoteng.Models.ProductDetailModel;
import com.example.divyanshujain.edoteng.R;
import com.example.divyanshujain.edoteng.Utils.CallWebService;
import com.example.divyanshujain.edoteng.Utils.MySharedPereference;
import com.example.divyanshujain.edoteng.Utils.ProductsSingleton;
import com.neopixl.pixlui.components.textview.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by divyanshu.jain on 12/21/2016.
 */

public class CourseDescriptionFragment extends BaseFragment {
    @InjectView(R.id.physicalVersionPriceTV)
    TextView physicalVersionPriceTV;
    @InjectView(R.id.digitalVersionPriceTV)
    TextView digitalVersionPriceTV;

    @InjectView(R.id.fileTypeIV)
    ImageView fileTypeIV;
    @InjectView(R.id.productNameTV)
    TextView productNameTV;
    @InjectView(R.id.sellerNameTV)
    TextView sellerNameTV;
    @InjectView(R.id.descriptionTV)
    TextView descriptionTV;
    @InjectView(R.id.downloadDigitalTV)
    TextView downloadDigitalTV;
    @InjectView(R.id.addToCartTV)
    TextView addToCartTV;
    @InjectView(R.id.addToWishListTV)
    TextView addToWishListTV;

    private ProductDetailModel productDetailModel = null;

    public static CourseDescriptionFragment getInstance(ProductDetailModel modUrl) {
        CourseDescriptionFragment courseDescriptionFragment = new CourseDescriptionFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.DATA, modUrl);
        courseDescriptionFragment.setArguments(bundle);
        return courseDescriptionFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.course_description_fragment, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews();

    }

    private void initViews() {
        productDetailModel = getArguments().getParcelable(Constants.DATA);
        if (productDetailModel != null) {
            physicalVersionPriceTV.setText(getString(R.string.rs) + productDetailModel.getPhysical_price());
            digitalVersionPriceTV.setText(getString(R.string.rs) + productDetailModel.getDownloadable_price());
            productNameTV.setText(productDetailModel.getProduct_name());
            sellerNameTV.setText(productDetailModel.getMetaTitle());
            descriptionTV.setText(Html.fromHtml(productDetailModel.getShort_description()));

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.downloadDigitalTV, R.id.addToCartTV})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.downloadDigitalTV:
                if (productDetailModel.getCan_downloadable_purchase().equals("1")) {
                    if (!productDetailModel.getDownloadable_price().equals("0")) {
                        addProductToCart();
                    } else {
                        CallWebService.getInstance(getContext(), true, ApiCodes.DIGITAL_VER_REQUEST).hitJsonObjectRequestAPI(CallWebService.POST, API.DIGITAL_VERSION_MAIL, createJsonForPostDigitalVersionRequest(), this);
                    }
                }
                break;
            case R.id.addToCartTV:
                if (productDetailModel.getCan_physical_purchase().equals("1") && !productDetailModel.getPhysical_price().equals("0"))
                    addProductToCart();
                else
                    CustomToasts.getInstance(getContext()).showErrorToast("No For Purchase!");
                break;
        }
    }

    private void addProductToCart() {
        ProductsSingleton.getInstance().addProductToCart(productDetailModel);
        CustomToasts.getInstance(getContext()).showSuccessToast("Product Added Successfully!");
    }

    @Override
    public void onJsonObjectSuccess(JSONObject response, int apiType) throws JSONException {
        super.onJsonObjectSuccess(response, apiType);
        CustomAlertDialogs.showAlertDialog(getContext(), "Congratulations!", response.getString(Constants.MESSAGE), new SnackBarCallback() {
            @Override
            public void doAction() {

            }
        });

    }

    private JSONObject createJsonForPostDigitalVersionRequest() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(Constants.USER_ID, MySharedPereference.getInstance().getString(getContext(), Constants.USER_ID));
            jsonObject.put(Constants.RANDOM, productDetailModel.getRandom());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }
}
