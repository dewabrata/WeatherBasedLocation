
package com.juara.weatherbasedlocation.model.geolocation;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Serializable, Parcelable
{

    @SerializedName("geolocation")
    @Expose
    private List<Geolocation> geolocation = null;
    public final static Creator<Data> CREATOR = new Creator<Data>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        public Data[] newArray(int size) {
            return (new Data[size]);
        }

    }
    ;
    private final static long serialVersionUID = 3677292120128682106L;

    protected Data(Parcel in) {
        in.readList(this.geolocation, (com.juara.weatherbasedlocation.model.geolocation.Geolocation.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Data() {
    }

    /**
     * 
     * @param geolocation
     */
    public Data(List<Geolocation> geolocation) {
        super();
        this.geolocation = geolocation;
    }

    public List<Geolocation> getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(List<Geolocation> geolocation) {
        this.geolocation = geolocation;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(geolocation);
    }

    public int describeContents() {
        return  0;
    }

}
