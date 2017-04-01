package com.example.divyanshujain.edoteng.Utils;

import com.example.divyanshujain.edoteng.Models.ProductDetailModel;

import java.util.ArrayList;

/**
 * Created by divyanshuPC on 3/31/2017.
 */

public class AddToCartProducts {
    private static final AddToCartProducts ourInstance = new AddToCartProducts();

    private ArrayList<ProductDetailModel> productDetailModels = new ArrayList<>();

    public static AddToCartProducts getInstance() {
        return ourInstance;
    }

    private AddToCartProducts() {
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
}
