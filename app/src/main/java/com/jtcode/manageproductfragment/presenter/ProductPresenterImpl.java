package com.jtcode.manageproductfragment.presenter;


import com.jtcode.manageproductfragment.Model.Product;
import com.jtcode.manageproductfragment.ProductRepository;

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
       // repository.deleteProduct(product);

        //DEPENDE de la implementacion
        //opcion 1
       //loadProducts();
        view.showMessageDelete(product);

        //opcion 2
        /*view.getAdapter().deleteProduct();
        if(view.getAdapter().isEmty){
            view.showEmptyText(true);
        }*/
    }


    //metodo que elimina definitivamente el elemento cuando se cierra el snackbar
    @Override
    public void deleteFinallyProduct(Product product) {
        repository.deleteProduct(product);
        loadProducts();
    }

    @Override
    public void addProduct(Product p){
        repository.add(p);
        view.showProducts(repository.getProducts());
    }

    @Override
    public void onDestroy() {
        this.view=null;
    }
}
