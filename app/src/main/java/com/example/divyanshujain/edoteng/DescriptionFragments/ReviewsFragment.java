package com.example.divyanshujain.edoteng.DescriptionFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.divyanshujain.edoteng.Adapters.ReviewsAdapter;
import com.example.divyanshujain.edoteng.Constants.Constants;
import com.example.divyanshujain.edoteng.GlobalClasses.BaseFragment;
import com.example.divyanshujain.edoteng.Models.ProductDetailModel;
import com.example.divyanshujain.edoteng.Models.ReviewModel;
import com.example.divyanshujain.edoteng.R;
import com.neopixl.pixlui.components.textview.TextView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by divyanshuPC on 4/1/2017.
 */

public class ReviewsFragment extends BaseFragment {

    @InjectView(R.id.fiveStarSB)
    AppCompatSeekBar fiveStarSB;
    @InjectView(R.id.fourStarSB)
    AppCompatSeekBar fourStarSB;
    @InjectView(R.id.threeStarSB)
    AppCompatSeekBar threeStarSB;
    @InjectView(R.id.twoStarSB)
    AppCompatSeekBar twoStarSB;
    @InjectView(R.id.oneStarSB)
    AppCompatSeekBar oneStarSB;
    @InjectView(R.id.fiveStartReviewCountTV)
    TextView fiveStartReviewCountTV;
    @InjectView(R.id.fourStartReviewCountTV)
    TextView fourStartReviewCountTV;
    @InjectView(R.id.threeStartReviewCountTV)
    TextView threeStartReviewCountTV;
    @InjectView(R.id.twoStartReviewCountTV)
    TextView twoStartReviewCountTV;
    @InjectView(R.id.oneStartReviewCountTV)
    TextView oneStartReviewCountTV;
    @InjectView(R.id.reviewsRV)
    RecyclerView reviewsRV;
    ReviewsAdapter reviewsAdapter;

    private ProductDetailModel productDetailModel;

    public static ReviewsFragment getInstance(ProductDetailModel modUrl) {
        ReviewsFragment reviewsFragment = new ReviewsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.DATA, modUrl);
        reviewsFragment.setArguments(bundle);
        return reviewsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reviews, container, false);
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
        if (productDetailModel != null)
            setUI();
    }

    private void setUI() {
        setUpReviewsRating();
        reviewsRV.setLayoutManager(new LinearLayoutManager(getContext()));
        reviewsAdapter = new ReviewsAdapter(getContext(), productDetailModel.getProductReview(), this);
        reviewsRV.setAdapter(reviewsAdapter);

    }

    private void setUpReviewsRating() {
        ArrayList<ReviewModel> reviewModels = productDetailModel.getrArray();
        int fiveStarRating = reviewModels.get(0).getTotla();
        int fourStarRating = reviewModels.get(1).getTotla();
        int threeStarRating = reviewModels.get(2).getTotla();
        int twoStarRating = reviewModels.get(3).getTotla();
        int oneStarRating = reviewModels.get(4).getTotla();
        int total = fiveStarRating + fourStarRating + threeStarRating + twoStarRating + oneStarRating;

        fiveStarSB.setMax(total);
        fourStarSB.setMax(total);
        threeStarSB.setMax(total);
        twoStarSB.setMax(total);
        oneStarSB.setMax(total);

        fiveStarSB.setProgress(fiveStarRating);
        fourStarSB.setProgress(fourStarRating);
        threeStarSB.setProgress(threeStarRating);
        twoStarSB.setProgress(twoStarRating);
        oneStarSB.setProgress(oneStarRating);

        fiveStartReviewCountTV.setText("" + reviewModels.get(0).getTotla());
        fourStartReviewCountTV.setText("" + reviewModels.get(1).getTotla());
        threeStartReviewCountTV.setText("" + reviewModels.get(2).getTotla());
        twoStartReviewCountTV.setText("" + reviewModels.get(3).getTotla());
        oneStartReviewCountTV.setText("" + reviewModels.get(4).getTotla());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
