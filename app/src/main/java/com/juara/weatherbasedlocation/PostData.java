package com.juara.weatherbasedlocation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.juara.weatherbasedlocation.adapter.AdapterListSimple;
import com.juara.weatherbasedlocation.model.WeatherModel;
import com.juara.weatherbasedlocation.model.geolocation.GeolocationModel;
import com.juara.weatherbasedlocation.model.postresult.PostResult;
import com.juara.weatherbasedlocation.service.APIClient;
import com.juara.weatherbasedlocation.service.APIInterfacesRest;

import org.json.JSONObject;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostData extends AppCompatActivity {

    EditText txtLatitude,txtLongitude;
    Button btnSend;
    RecyclerView rv ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_data);

        rv = findViewById(R.id.lstGeolocation);

        txtLatitude = findViewById(R.id.txtLatitude);
        txtLongitude = findViewById(R.id.txtLongitude);
        btnSend = findViewById(R.id.btnSend);


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postGeolocation(txtLatitude.getText().toString(),txtLongitude.getText().toString(),(new Date()).toString(),"test");
            }
        });


        callGeolocation();

    }


    APIInterfacesRest apiInterface;
    ProgressDialog progressDialog;
    public void callGeolocation(){

        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        progressDialog = new ProgressDialog(PostData.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        Call<GeolocationModel> call3 = apiInterface.getGeolocation();
        call3.enqueue(new Callback<GeolocationModel>() {
            @Override
            public void onResponse(Call<GeolocationModel> call, Response<GeolocationModel> response) {
                progressDialog.dismiss();
                GeolocationModel data = response.body();
                //Toast.makeText(LoginActivity.this,userList.getToken().toString(),Toast.LENGTH_LONG).show();
                if (data !=null) {


                    AdapterListSimple adapter = new AdapterListSimple(PostData.this,data.getData().getGeolocation());

                    rv.setLayoutManager(new LinearLayoutManager(PostData.this));
                    rv.setItemAnimator(new DefaultItemAnimator());
                    rv.setAdapter(adapter);





                }else{

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(PostData.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(PostData.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<GeolocationModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Maaf koneksi bermasalah",Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });




    }



    public void postGeolocation(String lat,String lon,String datetime, String photo){

        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        progressDialog = new ProgressDialog(PostData.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        Call<PostResult> call3 = apiInterface.postDataGeo(lat,lon,datetime,photo);
        call3.enqueue(new Callback<PostResult>() {
            @Override
            public void onResponse(Call<PostResult> call, Response<PostResult> response) {
                progressDialog.dismiss();
                PostResult data = response.body();

                if (data !=null) {


                    Toast.makeText(PostData.this,data.getMessage(),Toast.LENGTH_LONG).show();

                    callGeolocation();




                }else{

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(PostData.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(PostData.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<PostResult> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Maaf koneksi bermasalah",Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });




    }

}
