package com.jsw.manageproductrecycler.presenter;


import com.jsw.manageproductrecycler.Model.Product;
import com.jsw.manageproductrecycler.ProductRepository;

public class ProductPresenterImpl implements ProductPresenter{

    private ProductPresenter.View view;
    private ProductRepository repository;

    public ProductPresenterImpl(ProductPresenter.View view) {
        this.view=view;
        this.repository=ProductRepository.getInstance();
    }

    @Override
    public void loadProducts() {
        if(repository.getProducts().isEmpty())
            view.showEmptyText(true);
        else
            view.showProducts(repository.getProducts());

    }

    @Override
    public Product getProduct(int id) {
       // return repository.getProduct(id);
        return  null;
    }

    @Override
    public void deleteProduct(Product product) {
        repository.deleteProduct(product);

        //DEPENDE de la implementacion
        //opcion 1
       loadProducts();

        //opcion 2
        /*view.getAdapter().deleteProduct();
        if(view.getAdapter().isEmty){
            view.showEmptyText(true);
        }*/
    }

    @Override
    public void onDestroy() {
        this.view=null;
    }
}
