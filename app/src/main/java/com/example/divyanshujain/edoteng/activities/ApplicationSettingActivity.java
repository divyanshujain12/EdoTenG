package com.example.divyanshujain.edoteng.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.divyanshujain.edoteng.Adapters.AppSettingRVAdapter;
import com.example.divyanshujain.edoteng.GlobalClasses.BaseActivity;
import com.example.divyanshujain.edoteng.R;
import com.example.divyanshujain.edoteng.Utils.CommonFunctions;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ApplicationSettingActivity extends BaseActivity {

    @InjectView(R.id.toolbarView)
    Toolbar toolbarView;
    @InjectView(R.id.appSettingRV)
    RecyclerView appSettingRV;
    AppSettingRVAdapter appSettingRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_setting);
        ButterKnife.inject(this);

        initViews();
    }

    private void initViews() {
        CommonFunctions.getInstance().configureToolbarWithBackButton(this, toolbarView, getString(R.string.settings));

        appSettingRV.setLayoutManager(new LinearLayoutManager(this));
        appSettingRVAdapter = new AppSettingRVAdapter(this, this);
        appSettingRV.setAdapter(appSettingRVAdapter);

    }

    @Override
    public void onClickItem(int position, View view) {
        super.onClickItem(position, view);
        switch (position) {
            case 0:
              /*  Intent intent = new Intent(this, AddShippingAddressActivity.class);
                startActivity(intent);*/
                break;
            case 1:
                break;
            case 2:
                break;

        }
    }
}
