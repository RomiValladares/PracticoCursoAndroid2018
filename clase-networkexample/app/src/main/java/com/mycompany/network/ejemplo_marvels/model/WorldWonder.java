package com.mycompany.network.ejemplo_marvels.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by romina valladaresromina@gmail.com on 10/16/18.
 */
public class WorldWonder implements Parcelable {
    private int id;
    private String nombre;
    private String pais;
    private String epoca;
    private String extraInfo;
    private ArrayList<String> imagenes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public ArrayList<String> getImagenes() {
        return imagenes;
    }

    public void setImagenes(ArrayList<String> imagenes) {
        this.imagenes = imagenes;
    }

    public String getEpoca() {
        return epoca;
    }

    public void setEpoca(String epoca) {
        this.epoca = epoca;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    protected WorldWonder(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        pais = in.readString();
        epoca = in.readString();
        extraInfo = in.readString();
        if (in.readByte() == 0x01) {
            imagenes = new ArrayList<String>();
            in.readList(imagenes, String.class.getClassLoader());
        } else {
            imagenes = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nombre);
        dest.writeString(pais);
        dest.writeString(epoca);
        dest.writeString(extraInfo);
        if (imagenes == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(imagenes);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<WorldWonder> CREATOR = new Parcelable.Creator<WorldWonder>() {
        @Override
        public WorldWonder createFromParcel(Parcel in) {
            return new WorldWonder(in);
        }

        @Override
        public WorldWonder[] newArray(int size) {
            return new WorldWonder[size];
        }
    };
}