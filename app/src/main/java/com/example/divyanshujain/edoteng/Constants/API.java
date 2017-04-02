package com.example.divyanshujain.edoteng.Constants;

/**
 * Created by divyanshu.jain on 12/29/2016.
 */

public interface API {
    String BASE = "http://edu10g.com/beta/api/";
    String REGISTRATION = BASE + "registration";
    String LOGIN = BASE + "login";
    String CONTACT_US = BASE + "contactus";
    String FORGOT_PASSWORD = BASE + "forgetpassword";
    String CHANGE_PASSWORD = BASE + "changepassword";
    String GET_BRANDS = BASE + "getAllBrands";
    String PRODUCT_LISTING_FILER = BASE + "categoryWiseFilter";
    String GET_PRODUCT_DETAIL = BASE + "getProductDetail";
    String DIGITAL_VERSION_MAIL = BASE + "digitalVersionEmail";
    String GET_ALL_CATEGORIES = BASE + "getAllCategoryDropdown";
    String GET_SUB_AND_SUB_SUB_CAT = BASE + "getAllSubcategories";
    String GET_ALL_COUNTRIES = BASE + "getAllCountries";
    String GET_ALL_STATE = BASE + "getStateByCountry";
    String GET_ALL_CITY = BASE + "getCityStateWise";
}
