
package com.juara.weatherbasedlocation.model.postresult;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostResult implements Serializable, Parcelable
{

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Creator<PostResult> CREATOR = new Creator<PostResult>() {


        @SuppressWarnings({
            "unchecked"
        })
        public PostResult createFromParcel(Parcel in) {
            return new PostResult(in);
        }

        public PostResult[] newArray(int size) {
            return (new PostResult[size]);
        }

    }
    ;
    private final static long serialVersionUID = -1863110438958520722L;

    protected PostResult(Parcel in) {
        this.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public PostResult() {
    }

    /**
     * 
     * @param message
     * @param status
     */
    public PostResult(Boolean status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(message);
    }

    public int describeContents() {
        return  0;
    }

}
