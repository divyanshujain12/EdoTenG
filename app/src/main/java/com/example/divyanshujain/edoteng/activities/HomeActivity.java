package com.example.divyanshujain.edoteng.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.divyanshujain.edoteng.GlobalClasses.BaseActivity;
import com.example.divyanshujain.edoteng.R;
import com.example.divyanshujain.edoteng.Utils.CommonFunctions;
import com.neopixl.pixlui.components.button.Button;
import com.neopixl.pixlui.components.edittext.EditText;
import com.neopixl.pixlui.components.textview.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity {

    @InjectView(R.id.categorySP)
    Spinner categorySP;
    @InjectView(R.id.subCategorySP)
    Spinner subCategorySP;
    @InjectView(R.id.subSubCategorySP)
    Spinner subSubCategorySP;
    @InjectView(R.id.goBT)
    Button goBT;
    @InjectView(R.id.searchET)
    EditText searchET;
    @InjectView(R.id.activity_home)
    LinearLayout activityHome;
    @InjectView(R.id.toolbarView)
    Toolbar toolbarView;
    @InjectView(R.id.searchKeyTV)
    TextView searchKeyTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.inject(this);
        initViews();
    }

    private void initViews() {
        CommonFunctions.getInstance().configureToolbarWithOutBackButton(this, toolbarView, getString(R.string.home));
    }

    @OnClick({R.id.goBT, R.id.searchET})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.goBT:
                startActivity(new Intent(this, SearchByFiltersActivity.class));
                break;
            case R.id.searchET:
                startActivity(new Intent(this, SearchByKeywordActivity.class));
                break;
        }
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
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        searchET.setFocusable(false);
    }
}
