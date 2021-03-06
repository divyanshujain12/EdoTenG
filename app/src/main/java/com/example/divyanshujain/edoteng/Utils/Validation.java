package com.example.divyanshujain.edoteng.Utils;


import android.app.Activity;
import android.support.annotation.Nullable;
import android.util.Patterns;
import android.view.View;

import com.androidadvance.topsnackbar.TSnackbar;
import com.example.divyanshujain.edoteng.CustomViews.CustomTopSnackbars;
import com.example.divyanshujain.edoteng.Models.ValidationModel;
import com.neopixl.pixlui.components.edittext.EditText;
import com.neopixl.pixlui.components.textview.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by divyanshu.jain on 9/16/2016.
 */
public class Validation {
    public final static int TYPE_NAME_VALIDATION = 0;
    public final static int TYPE_EMAIL_VALIDATION = 1;
    public final static int TYPE_PHONE_VALIDATION = 2;
    public final static int TYPE_PASSWORD_VALIDATION = 3;
    public final static int TYPE_EMPTY_FIELD_VALIDATION = 4;
    private EditText editText = null;
    private TextView textView = null;
    private TSnackbar snackbar;
    private ArrayList<ValidationModel> validationModels = new ArrayList<>();

    public final static String ALL_FILED_MANDATORY = "All fields are mandatory";
    public final static String USERNAME_EMPTY_FIELD_VALIDATION = "Name cant left blank";
    public final static String EMAILNOT_VALID_FIELD_VALIDATION = "Email is not valid";
    public final static String PHONE_VALID_FIELD_VALIDATION = "Phone Number is not valid";

    public void addValidationField(ValidationModel validationModel) {
        validationModels.add(validationModel);
    }

    public void removeValidationField(ValidationModel validationModel) {
        validationModels.remove(validationModel);
    }

    public HashMap<View, String> validate(View view) {
        snackbar = CustomTopSnackbars.getInstance().getErrorSnackbar(view);
        HashMap<View, String> valueMap = getHashMap();
        if (valueMap == null) return null;
        return valueMap;
    }

    public HashMap<View, String> validate(Activity activity) {
        snackbar = CustomTopSnackbars.getInstance().getErrorSnackbar(activity);
        HashMap<View, String> valueMap = getHashMap();
        if (valueMap == null) return null;
        return valueMap;
    }

    @Nullable
    private HashMap<View, String> getHashMap() {
        HashMap<View, String> valueMap = new HashMap<>();

        for (ValidationModel validationModel : validationModels) {
            if (validationModel.getView() instanceof EditText) {
                editText = (EditText) validationModel.getView();
                textView = null;
            } else if (validationModel.getView() instanceof TextView) {
                textView = (TextView) validationModel.getView();
                editText = null;
            }


            String editTextValue = (editText == null ? textView : editText).getText().toString();
            boolean status = false;
            switch (validationModel.validationType) {
                case TYPE_NAME_VALIDATION:
                    status = validateName(editTextValue);
                    break;
                case TYPE_EMAIL_VALIDATION:
                    status = validateEmail(editTextValue);
                    break;
                case TYPE_PHONE_VALIDATION:
                    status = validatePhone(editTextValue);
                    break;
                case TYPE_PASSWORD_VALIDATION:
                    status = validateName(editTextValue);
                    break;
                case TYPE_EMPTY_FIELD_VALIDATION:
                    status = validateName(editTextValue);
                    break;
            }
            if (!status) {
                snackbar.setText(validationModel.errorMessage).show();
                return null;

            }
            valueMap.put((editText == null ? textView : editText), editTextValue);
        }
        return valueMap;
    }

    private boolean validateName(String editTextValue) {
        if (editTextValue.length() == 0)
            return false;
        else
            return true;
    }

    private boolean validateEmail(String editTextValue) {
        if (editTextValue.length() == 0 || !Patterns.EMAIL_ADDRESS.matcher(editTextValue).matches())
            return false;
        else
            return true;
    }

    private boolean validatePhone(String editTextValue) {
        if (editTextValue.length() == 0 || !Patterns.PHONE.matcher(editTextValue).matches())
            return false;
        else
            return true;
    }

}
