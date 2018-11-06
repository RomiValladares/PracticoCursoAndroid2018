package com.mycompany.network.ejemplo_marvels.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by romina valladaresromina@gmail.com on 10/17/18.
 */
public class ParcelableExample implements Parcelable {
    public static final Parcelable.Creator<ParcelableExample> CREATOR
            = new Parcelable.Creator<ParcelableExample>() {
        public ParcelableExample createFromParcel(Parcel in) {
            return new ParcelableExample(in);
        }

        public ParcelableExample[] newArray(int size) {
            return new ParcelableExample[size];
        }
    };
    private int mData;

    private ParcelableExample(Parcel in) {
        mData = in.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(mData);
    }
}

