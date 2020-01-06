
package com.juara.weatherbasedlocation.model.geolocation;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Geolocation implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("datetime")
    @Expose
    private String datetime;
    @SerializedName("photo")
    @Expose
    private String photo;
    public final static Creator<Geolocation> CREATOR = new Creator<Geolocation>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Geolocation createFromParcel(Parcel in) {
            return new Geolocation(in);
        }

        public Geolocation[] newArray(int size) {
            return (new Geolocation[size]);
        }

    }
    ;
    private final static long serialVersionUID = 2251430230537308006L;

    protected Geolocation(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.latitude = ((String) in.readValue((String.class.getClassLoader())));
        this.longitude = ((String) in.readValue((String.class.getClassLoader())));
        this.datetime = ((String) in.readValue((String.class.getClassLoader())));
        this.photo = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Geolocation() {
    }

    /**
     * 
     * @param datetime
     * @param latitude
     * @param photo
     * @param id
     * @param longitude
     */
    public Geolocation(String id, String latitude, String longitude, String datetime, String photo) {
        super();
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.datetime = datetime;
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(latitude);
        dest.writeValue(longitude);
        dest.writeValue(datetime);
        dest.writeValue(photo);
    }

    public int describeContents() {
        return  0;
    }

}
