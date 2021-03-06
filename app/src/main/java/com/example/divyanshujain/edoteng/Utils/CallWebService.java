package com.example.divyanshujain.edoteng.Utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.divyanshujain.edoteng.Constants.Constants;
import com.example.divyanshujain.edoteng.CustomViews.CustomToasts;
import com.example.divyanshujain.edoteng.R;
import com.example.divyanshujain.edoteng.activities.MyApp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by Divyanshu jain on 30-10-2015.
 */
public class CallWebService implements Response.ErrorListener, Response.Listener {

    private Context context = null;

    public static int GET = Request.Method.GET;
    public static int POST = Request.Method.POST;
    public static int PUT = Request.Method.PUT;
    public static int DELETE = Request.Method.DELETE;
    private ObjectResponseCallBack objectCallBackInterface;
    private ArrayResponseCallback arrayCallBackInterface;
    private int apiCode = 0;
    private String url = "";
    private static ProgressDialog progressDialog;

    public static CallWebService getInstance(Context context, boolean showProgressBar, int apiCode) {
        CallWebService instance = new CallWebService();
        instance.context = context;
        instance.apiCode = apiCode;

        if (context != null && showProgressBar) {
            configProgressDialog(context);
        }
        else
            instance.progressDialog = null;
        return instance;
    }



    public void hitJsonObjectRequestAPI(int requestType, final String url, JSONObject json, final ObjectResponseCallBack callBackInterface) {
        if (InternetCheck.isInternetOn(context)) {
            objectCallBackInterface = callBackInterface;
            cancelRequest(url);
            this.url = url;
            if (progressDialog != null)
                showDialog();

            JsonObjectRequest request = new JsonObjectRequest(requestType, url, json == null ? null : (json), this, this);
            addRequestToVolleyQueue(url, request);
        } else {
            CustomToasts.getInstance(context).showErrorToast(context.getString(R.string.no_internet_connection));
        }
    }

    public void hitJsonArrayRequestAPI(int requestType, final String url, JSONArray json, final ArrayResponseCallback callBackinerface) {
        arrayCallBackInterface = callBackinerface;
        cancelRequest(url);

        this.url = url;

        if (progressDialog != null)
            showDialog();

        JsonArrayRequest request = new JsonArrayRequest(requestType, url, json == null ? null : (json), this, this);
        addRequestToVolleyQueue(url, request);
    }

    private void addRequestToVolleyQueue(String url, Request request) {
        RetryPolicy policy = new DefaultRetryPolicy(Constants.REQUEST_TIMEOUT_TIME, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        request.setRetryPolicy(policy);
        MyApp.getInstance().addToRequestQueue(request, url);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        MyApp.getInstance().getRequestQueue().getCache().invalidate(url, true);
        error = configureErrorMessage(error);
        onError(error.getMessage());
    }

    @Override
    public void onResponse(Object response) {
        MyApp.getInstance().getRequestQueue().getCache().invalidate(url, true);
        if (progressDialog != null)
            hideDialog();

        if (response instanceof JSONObject) {
            onJsonObjectResponse((JSONObject) response);
        } else if (response instanceof JSONArray) {
            onJsonArrayResponse((JSONArray) response);
        }

    }

    private void onJsonObjectResponse(JSONObject response) {
        try {
            if (response.getBoolean(Constants.STATUS_CODE)) {
                if (objectCallBackInterface != null)
                    objectCallBackInterface.onJsonObjectSuccess(response, apiCode);

            }  else
                onError(response.getString(Constants.MESSAGE));
        } catch (final JSONException e) {
            onError(e.getMessage());
            e.printStackTrace();
        }
    }


    private void onJsonArrayResponse(JSONArray response) {
        try {
            arrayCallBackInterface.onJsonArraySuccess(response, apiCode);
        } catch (final JSONException e) {
            onError(e.getMessage());
            e.printStackTrace();
        }
    }


    private void showDialog() {
        progressDialog.show();
    }
    private void hideDialog() {
        if(progressDialog!=null)
        progressDialog.hide();
    }


    public interface ObjectResponseCallBack {

        void onJsonObjectSuccess(JSONObject response, int apiType) throws JSONException;

        void onFailure(String str, int apiType);

    }

    public interface ArrayResponseCallback {

        void onJsonArraySuccess(JSONArray array, int apiType) throws JSONException;

        void onFailure(String str, int apiType);

    }

    private void onError(String error) {

        if (progressDialog != null)
            hideDialog();
        if (objectCallBackInterface != null)
            objectCallBackInterface.onFailure(error, apiCode);
    }

    private VolleyError configureErrorMessage(VolleyError volleyError) {
        if (volleyError.networkResponse != null && volleyError.networkResponse.data != null) {
            VolleyError error = new VolleyError(new String(volleyError.networkResponse.data));
            volleyError = error;
        }
        return volleyError;
    }

    private void cancelRequest(String url) {
        if (this.url.equals(url))
            MyApp.getInstance().cancelPendingRequests(url);
    }
    private static void configProgressDialog(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(context.getString(R.string.working_please_wait));
    }
}