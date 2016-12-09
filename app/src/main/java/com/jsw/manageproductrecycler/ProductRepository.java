package com.jsw.manageproductrecycler;

    //to do: complete

import com.jsw.manageproductrecycler.Model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    ArrayList<Product> products;

    private static ProductRepository ourInstance = new ProductRepository();

    public static ProductRepository getInstance() {
        return ourInstance;
    }

    private ProductRepository() {
        products=new ArrayList<>();
        products.add(new Product(R.drawable.pastilla, "Ibuprofeno", "Generico", "1gr", "2", 45.70, "Ibuprofeno Generico 1gr"));
        products.add(new Product(R.drawable.pastilla, "Ibuprofeno", "Generico", "700mg", "65", 15.70, "Ibuprofeno Generico 700mg"));
        products.add(new Product(R.drawable.vaporu, "Vaporu", "Vicks", "1gr", "6", 65.70, "Gel Utopico"));
        products.add(new Product(R.drawable.hemoal, "Hemoal", "Hemoal SA", "3gr", "2", 55.70, "Crema Almorranas"));
        products.add(new Product(R.drawable.juanola, "Juanolas", "Juanolas", "5gr", "2", 45.70, "Pastillas"));
        products.add(new Product(R.drawable.avril, "Avril", "Avril", "1gr", "9", 7.70, "Crema Quemaduras"));
        products.add(new Product(R.drawable.avril, "Avril", "Avril", "5gr", "12", 15.70, "Crema Quemaduras"));
        products.add(new Product(R.drawable.diazepam, "Diazepam", "Bayern", "1gr", "200", 45.70, "Pastillas Tranquilizantes"));
        products.add(new Product(R.drawable.pastilla, "Ibuprofeno", "Generico", "1gr", "2", 45.70, "Ibuprofeno Generico 1gr"));
        products.add(new Product(R.drawable.pastilla, "Ibuprofeno", "Generico", "700mg", "65", 15.70, "Ibuprofeno Generico 700mg"));
        products.add(new Product(R.drawable.vaporu, "Vaporu", "Vicks", "1gr", "6", 65.70, "Gel Utopico"));
        products.add(new Product(R.drawable.hemoal, "Hemoal", "Hemoal SA", "3gr", "2", 55.70, "Crema Almorranas"));
        products.add(new Product(R.drawable.juanola, "Juanolas", "Juanolas", "5gr", "2", 45.70, "Pastillas"));
        products.add(new Product(R.drawable.avril, "Avril", "Avril", "1gr", "9", 7.70, "Crema Quemaduras"));
        products.add(new Product(R.drawable.avril, "Avril", "Avril", "5gr", "12", 15.70, "Crema Quemaduras"));
        products.add(new Product(R.drawable.diazepam, "Diazepam", "Bayern", "1gr", "200", 45.70, "Pastillas Tranquilizantes"));

    }
    public void add(Product p){
        this.products.add(p);
    }

    public List<Product> getProducts(){
        return products;
    }

    public void deleteProduct(Product product) {
        this.products.remove(product);
    }
    public Product getProduct(int id){
        for (Product p :products  ) {
            if(p.getmId()==id){
                return p;
            }
        }
        return null;
    }
}
