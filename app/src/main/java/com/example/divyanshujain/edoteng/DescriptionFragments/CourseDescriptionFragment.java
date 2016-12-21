package com.example.divyanshujain.edoteng.DescriptionFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.divyanshujain.edoteng.GlobalClasses.BaseFragment;
import com.example.divyanshujain.edoteng.R;
import com.neopixl.pixlui.components.textview.TextView;

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
    @InjectView(R.id.addToWishListTV)
    TextView addToWishListTV;
    @InjectView(R.id.addToCartTV)
    TextView addToCartTV;

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

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
