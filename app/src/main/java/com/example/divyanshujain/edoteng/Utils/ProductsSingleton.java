package com.example.divyanshujain.edoteng.Utils;

import com.example.divyanshujain.edoteng.Models.AddressModel;
import com.example.divyanshujain.edoteng.Models.CityModel;
import com.example.divyanshujain.edoteng.Models.CountryModel;
import com.example.divyanshujain.edoteng.Models.ProductDetailModel;
import com.example.divyanshujain.edoteng.Models.StateModel;

import java.util.ArrayList;

/**
 * Created by divyanshuPC on 3/31/2017.
 */

public class ProductsSingleton {
    private static final ProductsSingleton ourInstance = new ProductsSingleton();

    private ArrayList<ProductDetailModel> productDetailModels = new ArrayList<>();
    private ProductDetailModel productDetailModel = new ProductDetailModel();
    public AddressModel addressModel = new AddressModel();
    public ArrayList<CityModel> cityModels = new ArrayList<>();
    public ArrayList<StateModel> stateModels = new ArrayList<>();
    public ArrayList<CountryModel> countryModels = new ArrayList<>();

    public static ProductsSingleton getInstance() {
        return ourInstance;
    }

    private ProductsSingleton() {
    }


    public void addProductToCart(ProductDetailModel productDetailModel) {
        productDetailModels.add(productDetailModel);
    }

    public ArrayList<ProductDetailModel> getProductDetailModels() {
        return productDetailModels;
    }

    public void setProductDetailModels(ArrayList<ProductDetailModel> productDetailModels) {
        this.productDetailModels = productDetailModels;
    }

    public ProductDetailModel getProductDetailModel(int pos) {
        return productDetailModels.get(pos);
    }

    public void removeProduct(int pos) {
        productDetailModels.remove(pos);
    }

    public ProductDetailModel getProductDetailModel() {
        return productDetailModel;
    }

    public void setProductDetailModel(ProductDetailModel productDetailModel) {
        this.productDetailModel = productDetailModel;
    }

    public AddressModel getAddressModel() {
        return addressModel;
    }

    public void setAddressModel(AddressModel addressModel) {
        this.addressModel = addressModel;
    }

    public ArrayList<CityModel> getCityModels() {
        return cityModels;
    }

    public void setCityModels(ArrayList<CityModel> cityModels) {
        this.cityModels = cityModels;
    }

    public ArrayList<StateModel> getStateModels() {
        return stateModels;
    }

    public void setStateModels(ArrayList<StateModel> stateModels) {
        this.stateModels = stateModels;
    }

    public ArrayList<CountryModel> getCountryModels() {
        return countryModels;
    }

    public void setCountryModels(ArrayList<CountryModel> countryModels) {
        this.countryModels = countryModels;
    }
}
