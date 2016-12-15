package com.jtcode.manageproductfragment.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jtcode.manageproductfragment.Model.Product;
import com.jtcode.manageproductfragment.Product_Application;
import com.jtcode.manageproductfragment.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ProductAdapter extends ArrayAdapter<Product>{

    private boolean ASC=true;
    private Context context;
    /**
     * se pasa como tercer parameto en la llamada super un ArrayList con los elementos del Repositorio. se tiene una copia local
     * diferente al repositorio.
     * @param
     */
    public ProductAdapter(Context context) {
        super(context, R.layout.item_product,
                new ArrayList<Product>(((Product_Application)context.getApplicationContext()).getProducts()));
        //cuando en el super inicializa el objeto interno al objeto indicado como application
        //todos los objetos del application son los mismos del array adapter
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item= convertView;
        ProductHolder productHolder=null;
        if(item==null){
            LayoutInflater inflater=(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            item=inflater.inflate(R.layout.item_product,null);

            productHolder= new ProductHolder();
            productHolder.imgProd=(ImageView)item.findViewById(R.id.iv_img);
            productHolder.txvName=(TextView)item.findViewById(R.id.tv_nombre);
            productHolder.txvPrice=(TextView)item.findViewById(R.id.tv_precio);
            productHolder.txvStock=(TextView)item.findViewById(R.id.tv_stock);

            item.setTag(productHolder);
        }
        else {
            productHolder = (ProductHolder) item.getTag();
        }
        productHolder.imgProd.setBackgroundResource(getItem(position).getmImage());
        productHolder.txvName.setText(getItem(position).getmName());
        productHolder.txvPrice.setText(getItem(position).getFormattedPrice());
        productHolder.txvStock.setText(getItem(position).getmStock());

        return item;
    }

    class ProductHolder{
        ImageView imgProd;
        TextView txvName,txvStock,txvPrice;
    }

    public void sortByAlph(){
        if(ASC)
            sort(Product.NAME_COMPARATOR);
        else
            sort(Collections.reverseOrder());
        ASC=!ASC;
        notifyDataSetChanged();
    }

    public boolean addProduct(Product product){
        add(product);
        return true;
    }

    public boolean addAtPos(Product pold,Product pnew,int pos){
        remove(pold);
        insert(pnew,pos);
        return true;
    }
    public boolean removeProduct(Product p){
        remove(p);
        return  true;
    }
    public void updateProduct(List<Product> products){
        clear();
        addAll(products);
    }

}
