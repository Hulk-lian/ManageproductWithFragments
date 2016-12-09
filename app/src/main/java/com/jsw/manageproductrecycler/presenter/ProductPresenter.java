package com.jsw.manageproductrecycler.presenter;

import com.jsw.manageproductrecycler.Model.Product;

import java.util.List;

/**
 * Created by usuario on 9/12/16.
 */

public interface ProductPresenter {
    interface View{
        void showProducts(List<Product> products);
        void showEmptyText(boolean show);
        void showMessage(String message);
    }
    void loadProducts();
    Product getProduct(int id);
    void  deleteProduct(Product product);
    void onDestroy();
}
