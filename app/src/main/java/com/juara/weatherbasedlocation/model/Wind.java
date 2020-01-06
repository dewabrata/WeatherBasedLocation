
package com.juara.weatherbasedlocation.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wind implements Serializable, Parcelable
{

    @SerializedName("speed")
    @Expose
    private Integer speed;
    public final static Creator<Wind> CREATOR = new Creator<Wind>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Wind createFromParcel(Parcel in) {
            return new Wind(in);
        }

        public Wind[] newArray(int size) {
            return (new Wind[size]);
        }

    }
    ;
    private final static long serialVersionUID = -8323766900447885532L;

    protected Wind(Parcel in) {
        this.speed = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Wind() {
    }

    /**
     * 
     * @param speed
     */
    public Wind(Integer speed) {
        super();
        this.speed = speed;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(speed);
    }

    public int describeContents() {
        return  0;
    }

}
