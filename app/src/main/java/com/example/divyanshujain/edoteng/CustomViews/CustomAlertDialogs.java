package com.example.divyanshujain.edoteng.CustomViews;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;


import com.example.divyanshujain.edoteng.Constants.Constants;
import com.example.divyanshujain.edoteng.Interfaces.ChangePasswordInterface;
import com.example.divyanshujain.edoteng.Interfaces.SnackBarCallback;
import com.example.divyanshujain.edoteng.Models.ValidationModel;
import com.example.divyanshujain.edoteng.R;
import com.example.divyanshujain.edoteng.Utils.MySharedPereference;
import com.example.divyanshujain.edoteng.Utils.Validation;
import com.neopixl.pixlui.components.edittext.EditText;
import com.neopixl.pixlui.components.textview.TextView;

import java.util.HashMap;


public class CustomAlertDialogs {
    static AlertDialog alertDialog;

    @SuppressWarnings("deprecation")
    public static void showAlertDialog(Context context, String title, String message, final SnackBarCallback snackBarCallback) {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                snackBarCallback.doAction();
            }
        });
        alertDialog.show();
    }


    public static void showAlertDialogWithCallBack(final Context context, String title, String message, final SnackBarCallback snackBarCallback) {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                snackBarCallback.doAction();
                dialog.dismiss();
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((Activity)context).onBackPressed();
                dialog.dismiss();

            }
        });

        alertDialog.show();
    }


    public static void showChangePasswordDialog(final Context context, final ChangePasswordInterface changePasswordInterface) {
        final Validation validation = new Validation();
        alertDialog = new AlertDialog.Builder(context).create();
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.change_password_layout, null);
        setupFullWidthDialog();
        final EditText oldPasswordET = (EditText) layout.findViewById(R.id.oldPasswordET);
        final EditText passwordET = (EditText) layout.findViewById(R.id.passwordET);
        final EditText reEnterPasswordET = (EditText) layout.findViewById(R.id.reEnterPasswordET);
        TextView updateTV = (TextView) layout.findViewById(R.id.updateTV);
        validation.addValidationField(new ValidationModel(oldPasswordET, Validation.TYPE_PASSWORD_VALIDATION, context.getString(R.string.err_pass)));
        validation.addValidationField(new ValidationModel(passwordET, Validation.TYPE_PASSWORD_VALIDATION, context.getString(R.string.err_pass)));
        validation.addValidationField(new ValidationModel(reEnterPasswordET, Validation.TYPE_PASSWORD_VALIDATION, context.getString(R.string.err_pass)));
        updateTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<View, String> hashMap = validation.validate((Activity) context);
                if (hashMap != null) {
                    String savedPassword = MySharedPereference.getInstance().getString(context, Constants.PASSWORD);

                    if (!savedPassword.equals(hashMap.get(oldPasswordET))) {
                        showErrorToast(context, "Old Password Mismatch!");
                        return;
                    } else if (!hashMap.get(passwordET).equals(hashMap.get(reEnterPasswordET))) {
                        showErrorToast(context, "New Password Mismatch!");
                        return;
                    } else {
                        changePasswordInterface.onChangeSuccess(hashMap.get(passwordET));
                        dismissDialog();
                    }
                }


            }
        });
        alertDialog.setView(layout);
        alertDialog.show();
    }

    private static void showErrorToast(Context context, String errorMsg) {
        Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show();
    }

    private static void dismissDialog() {
        if (alertDialog != null)
            alertDialog.dismiss();
    }



    private static void setupFullWidthDialog() {
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = alertDialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

    }
}
