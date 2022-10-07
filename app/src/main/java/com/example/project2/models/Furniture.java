package com.example.project2.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Furniture implements Parcelable {

    public String getProductimage() {
        return productimage;
    }

    public void setProductimage(String productimage) {
        this.productimage = productimage;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public Double getProductrating() {
        return productrating;
    }

    public void setProductrating(Double productrating) {
        this.productrating = productrating;
    }

    public Integer getProductprice() {
        return productprice;
    }

    public void setProductprice(Integer productprice) {
        this.productprice = productprice;
    }

    public String getProductdescription() {
        return productdescription;
    }

    public void setProductdescription(String productdescription) {
        this.productdescription = productdescription;
    }

    private String productimage;
    private String productname;
    private Double productrating;
    private Integer productprice;
    private String productdescription;

    public Furniture(String productimage, String productname, Double productrating, Integer productprice, String productdescription){
        this.productimage = productimage;
        this.productname = productname;
        this.productrating = productrating;
        this.productprice = productprice;
        this.productdescription = productdescription;
    }

    public Furniture(){
    }

    protected Furniture (Parcel in){
        productimage = in.readString();
        productname = in.readString();
        productrating = in.readDouble();
        productprice = in.readInt();
        productdescription = in.readString();
    }

    public static final Creator<Furniture> CREATOR = new Creator<Furniture>() {
        @Override
        public Furniture createFromParcel(Parcel in) {
            return new Furniture(in);
        }

        @Override
        public Furniture[] newArray(int size) {
            return new Furniture[size];
        }
    };



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(productimage);
        parcel.writeString(productname);
        parcel.writeDouble(productrating);
        parcel.writeInt(productprice);
        parcel.writeString(productdescription);
    }
}
