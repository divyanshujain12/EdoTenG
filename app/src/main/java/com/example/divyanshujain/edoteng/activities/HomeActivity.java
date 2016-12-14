package com.example.divyanshujain.edoteng.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.divyanshujain.edoteng.GlobalClasses.BaseActivity;
import com.example.divyanshujain.edoteng.R;
import com.neopixl.pixlui.components.button.Button;
import com.neopixl.pixlui.components.edittext.EditText;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.inject(this);

        //searchET.setEnabled(false);
    }

    @OnClick({R.id.goBT, R.id.searchET})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.goBT:
                break;
            case R.id.searchET:
                startActivity(new Intent(this, SearchByKeywordActivity.class));
                break;
        }
    }
}
