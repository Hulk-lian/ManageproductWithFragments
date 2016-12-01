package com.jsw.manageproductrecycler.Model;

import android.media.MediaCrypto;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by usuario on 19/10/16.
 */

public class Product implements Comparable<Product>, Serializable, Parcelable{
    private int mId;
    private String mStock;
    private int mImage;
    private String mName;
    private String mDescription;
    private String mDosage;
    private String mBrandM;
    private double mPrice;

    public static final Comparator<Product> NAME_COMPARATOR = new Comparator<Product>() {
        @Override
        public int compare(Product p1, Product p2) {
            return p1.mName.compareTo(p2.mName);
        }
    };
    public static final Comparator<Product> PRICE_COMPARATOR = new Comparator<Product>() {
        @Override
        public int compare(Product p1, Product p2) {
            return Double.compare(p1.getmPrice(), p2.getmPrice());
        }
    };
    public static final Comparator<Product> STOCK_COMPARATOR = new Comparator<Product>() {
        @Override
        public int compare(Product p1, Product p2) {
            return (p1.getmStock().compareTo(p2.getmStock()));
        }
    };

    public Product(int Image, String Name, String Trademark , String Dosage, String Stock, Double Price, String Description) {
        this.mStock = Stock;
        this.mImage = Image;
        this.mName = Name;
        this.setmDosage(Dosage);
        this.mDescription = Description;
        this.mBrandM = Trademark;
        this.mPrice = Price;
    }

    //parcelable

    protected Product(Parcel in) {
        mId = in.readInt();
        mStock = in.readString();
        mImage = in.readInt();
        mName = in.readString();
        mDescription = in.readString();
        mDosage = in.readString();
        mBrandM = in.readString();
        mPrice = in.readDouble();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mId);
        parcel.writeString(mStock);
        parcel.writeInt(mImage);
        parcel.writeString(mName);
        parcel.writeString(mDescription);
        parcel.writeString(mDosage);
        parcel.writeString(mBrandM);
        parcel.writeDouble(mPrice);
    }

    public int getmId() {
        return mId;
    }

    public String getmStock() {
        return mStock;
    }

    public void setmStock(String mStock) {
        this.mStock = mStock;
    }

    public int getmImage() {
        return mImage;
    }

    public void setmImage(int mImage) {
        this.mImage = mImage;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmBrandM() {
        return mBrandM;
    }

    public void setmBrandM(String mBrandM) {
        this.mBrandM = mBrandM;
    }

    public double getmPrice() {
        return mPrice;
    }

    public void setmPrice(double mPrice) {
        this.mPrice = mPrice;
    }

    public String getFormattedPrice(){
        return String.format("$%s", mPrice);
    }


    public String getmDosage() {
        return mDosage;
    }

    public void setmDosage(String mDosage) {
        this.mDosage = mDosage;
    }


    @Override
    public String toString() {
        return mName + "     " + mStock + "     " + mPrice;

                /*"mId=" + mId +
                ", mStock=" + mStock +
                //", mImage=" + mImage +
                ", mName='" + mName + '\'' +
                //", mDescription='" + mDescription + '\'' +
                //", mBrandM='" + mBrandM + '\'' +
                ", mPrice=" + mPrice +
                '}';*/
    }

    /*Dos prodcutos son iguales cuando tienen:
    -El mismo nombre
    -La misma marca
    -La misma concentración
     */

    /**
     * Equals Method refactored by me.
     * @param  o Object we want to compare.
     * @return Boolean. True if they are the same, false if not.
     */
    @Override
    public boolean equals(Object o) {
        boolean res = false;

        //Upcast
        if(this != o || o == null || getClass() != o.getClass())
            res = false;
        else{
            //Downcast
            Product product = (Product) o;

            if (mName.equals(product.mName) && mDosage.equals(product.mDosage) && mBrandM.equals(product.mBrandM))
                res = true;
        }

        return res;
    }

    @Override
    public int compareTo(Product product) {
       if(this.getmName().compareTo(product.getmName()) == 0)
           return this.getmBrandM().compareTo(product.getmBrandM());
        else
           return this.getmName().compareTo(product.getmName());
    }


}