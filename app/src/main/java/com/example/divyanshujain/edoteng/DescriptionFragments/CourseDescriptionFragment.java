package com.example.divyanshujain.edoteng.DescriptionFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.divyanshujain.edoteng.Constants.API;
import com.example.divyanshujain.edoteng.Constants.ApiCodes;
import com.example.divyanshujain.edoteng.Constants.Constants;
import com.example.divyanshujain.edoteng.GlobalClasses.BaseFragment;
import com.example.divyanshujain.edoteng.R;
import com.example.divyanshujain.edoteng.Utils.CallWebService;
import com.neopixl.pixlui.components.textview.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by divyanshu.jain on 12/21/2016.
 */

public class CourseDescriptionFragment extends BaseFragment {
    @InjectView(R.id.physicalVersionPriceTV)
    TextView physicalVersionPriceTV;
    @InjectView(R.id.digitalVersionPriceTV)
    TextView digitalVersionPriceTV;
    @InjectView(R.id.fileTypeTV)
    TextView fileTypeTV;
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

    private String modUrl = "";

    public static CourseDescriptionFragment getInstance(String modUrl) {
        CourseDescriptionFragment courseDescriptionFragment = new CourseDescriptionFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.MOD_URL, modUrl);
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
        modUrl = getArguments().getString(Constants.MOD_URL);
        CallWebService.getInstance(getContext(),true, ApiCodes.GET_PRODUCT_DETAIL).hitJsonObjectRequestAPI(CallWebService.POST, API.GET_PRODUCT_DETAIL,createJsonForGetProductDetail(),this);
    }

    private JSONObject createJsonForGetProductDetail() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(Constants.MOD_URL,modUrl);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    @Override
    public void onJsonObjectSuccess(JSONObject response, int apiType) throws JSONException {
        super.onJsonObjectSuccess(response, apiType);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
