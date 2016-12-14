package com.example.divyanshujain.edoteng.GlobalClasses;


import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;


import com.example.divyanshujain.edoteng.Interfaces.RecyclerViewClick;
import com.example.divyanshujain.edoteng.Interfaces.SnackBarCallback;
import com.example.divyanshujain.edoteng.Interfaces.UpdateUiCallback;
import com.example.divyanshujain.edoteng.Utils.CallWebService;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by divyanshu on 5/29/2016.
 */
public class BaseFragment extends Fragment implements CallWebService.ObjectResponseCallBack, SnackBarCallback, UpdateUiCallback, RecyclerViewClick {

    @Override
    public void doAction() {

    }

    @Override
    public void updateUi(String string) {

    }

    @Override
    public void onClickItem(int position, View view) {

    }

    public void showDialogFragment(DialogFragment fragment) {
        FragmentManager fragmentManager = getChildFragmentManager();
        fragment.show(fragmentManager, fragment.getClass().getName());

    }

    @Override
    public void onJsonObjectSuccess(JSONObject response, int apiType) throws JSONException {

    }

    @Override
    public void onFailure(String str, int apiType) {

    }
}
