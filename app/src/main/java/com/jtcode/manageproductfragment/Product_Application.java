package com.jtcode.manageproductfragment;

import android.app.Application;

import com.jtcode.manageproductfragment.Model.Product;

import java.util.ArrayList;
import java.util.List;

public class Product_Application extends Application {
    private ArrayList<Product> products;

    @Override
    public void onCreate() {
        products = new ArrayList<Product>();
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

   public void OrderAlph(boolean order){
       /* if(order)
            Collections.sort(products, (p1, p2) -> p1.getmName().compareTo(p2.getmName()));
        else
            Collections.sort(products, (p1, p2) -> p2.getmName().compareTo(p1.getmName()));*/
    }
}
