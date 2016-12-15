package com.jtcode.manageproductfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.jtcode.manageproductfragment.Model.Product;
import com.jtcode.manageproductfragment.interfaces.IProducto;

public class ManageProductFragment extends Fragment implements IProducto {

    TextInputLayout mName, mTrademark, mDosage, mStock, mPrice, mDescription;
    ImageView mImage;
    FloatingActionButton fab;
    Product product;
    int img;
    private String PRODUCTKEY=PRODUCT_KEY;

    //objecto que  comunica la activity con el listproductListener
    private ManageProductListener mCallback;

    interface ManageProductListener{
        void showListProduct(Bundle bundle);//lanza el fragment manageproduct
    }
    @Override
    public void onCreateView(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_product);
        mName = (TextInputLayout) findViewById(R.id.til_nombre);
        mTrademark = (TextInputLayout) findViewById(R.id.til_marca);
        mDosage = (TextInputLayout) findViewById(R.id.til_dosage);
        mStock = (TextInputLayout) findViewById(R.id.til_stock);
        mPrice = (TextInputLayout) findViewById(R.id.til_price);
        mDescription = (TextInputLayout) findViewById(R.id.til_descripcion);
        fab=(FloatingActionButton)findViewById(R.id.fab_guardar);
        mImage=(ImageView)findViewById(R.id.ib_imagen);

        try {
            product = getIntent().getExtras().getParcelable(PRODUCTKEY);
        }
        catch (Exception e){
            product=null;
        }

        if(product!=null){
            img=product.getmImage();
            mImage.setImageResource(product.getmImage());
            mName.getEditText().setText(product.getmName());
            mTrademark.getEditText().setText(product.getmBrandM());
            mDosage.getEditText().setText(product.getmDosage());
            mStock.getEditText().setText(product.getmStock());
            mPrice.getEditText().setText(String.valueOf(product.getmPrice()));
            mDescription.getEditText().setText(product.getmDescription());
        }
        else{
            img=R.drawable.vaporu;
            //mImage.set (R.drawable.new_d);
        }

    }
    private boolean comprdatos(){
        if(TextUtils.isEmpty(mName.getEditText().getText().toString()) ||
        TextUtils.isEmpty(mTrademark.getEditText().getText().toString()) ||
        TextUtils.isEmpty(mDosage.getEditText().getText().toString() )||
        TextUtils.isEmpty(mStock.getEditText().getText().toString() )||
        TextUtils.isEmpty(mPrice.getEditText().getText().toString()) ||
        TextUtils.isEmpty(mDescription.getEditText().getText().toString())){
            return false;
        }
        return true;
    }

    public void guardar(View v){

        if(comprdatos()) {

            Bundle bundle = new Bundle();
            Intent intent = getIntent();

            Product p = new Product(img,
                    mName.getEditText().getText().toString(),
                    mTrademark.getEditText().getText().toString(),
                    mDosage.getEditText().getText().toString(),
                    mStock.getEditText().getText().toString(),
                    Double.parseDouble(mPrice.getEditText().getText().toString()),
                    mDescription.getEditText().getText().toString());

            bundle.putParcelable(ListProductFragment.PRODUCKKEY, p);

            intent.putExtras(bundle);

            setResult(RESULT_OK, intent);
            finish();
        }
    }



}
