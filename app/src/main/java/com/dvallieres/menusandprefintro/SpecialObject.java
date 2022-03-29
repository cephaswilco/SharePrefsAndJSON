package com.dvallieres.menusandprefintro;

import android.os.Parcel;
import android.os.Parcelable;

// Parcelable is a special serialization interface that allows the Class to be sent through activities.
public class SpecialObject implements Parcelable {
    private int quantityOfFruit;
    private String fruit;

    public SpecialObject(int intIn, String fruit) {
        quantityOfFruit = intIn;
        this.fruit = fruit;
    }

    public SpecialObject(int intIn) {
        quantityOfFruit = intIn;
        this.fruit = "papaya";
    }


    public SpecialObject() {
        quantityOfFruit = 0;
        this.fruit = "papaya";
    }



    public int GetQuantity(){
        return quantityOfFruit;
    }

    public String GetName(){
        return fruit;
    }



    // Usually you can ignore this
    @Override
    public int describeContents() {
        return 0;
    }

    // write your object's data to the passed-in Parcel
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(quantityOfFruit);
        out.writeString(fruit);
    }

    public static final Parcelable.Creator<SpecialObject> CREATOR = new Parcelable.Creator<SpecialObject>() {
        public SpecialObject createFromParcel(Parcel in) {
            return new SpecialObject(in);
        }
        public SpecialObject[] newArray(int size) {
            return new SpecialObject[size];
        }
    };

    // used by the creator above
    private SpecialObject(Parcel in) {
        quantityOfFruit = in.readInt();
        fruit = in.readString();
    }




}