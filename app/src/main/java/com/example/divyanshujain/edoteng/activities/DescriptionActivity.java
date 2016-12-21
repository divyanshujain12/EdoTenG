package com.example.divyanshujain.edoteng.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.divyanshujain.edoteng.DescriptionFragments.CourseDescriptionFragment;
import com.example.divyanshujain.edoteng.GlobalClasses.BaseActivity;
import com.example.divyanshujain.edoteng.R;
import com.example.divyanshujain.edoteng.Utils.CommonFunctions;
import com.neopixl.pixlui.components.textview.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DescriptionActivity extends BaseActivity {

    @InjectView(R.id.toolbarView)
    Toolbar toolbarView;
    @InjectView(R.id.descTV)
    TextView descTV;
    @InjectView(R.id.reviewTV)
    TextView reviewTV;
    @InjectView(R.id.fragmentsFL)
    FrameLayout fragmentsFL;
    @InjectView(R.id.activity_description)
    LinearLayout activityDescription;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        ButterKnife.inject(this);
        initViews();
    }

    private void initViews() {
        fragmentManager = getSupportFragmentManager();
        CommonFunctions.getInstance().configureToolbarWithBackButton(this, toolbarView, getString(R.string.desc));
        updateFragment(new CourseDescriptionFragment());
    }

    public void updateFragment(Fragment fragment) {
        String name = fragment.getClass().getName();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        boolean isPopped = fragmentManager.popBackStackImmediate(name, 0);
        if (!isPopped && fragmentManager.findFragmentByTag(name) == null) {
            fragmentTransaction.replace(R.id.fragmentsFL, fragment);
        }
        fragmentTransaction.commit();
    }

}

