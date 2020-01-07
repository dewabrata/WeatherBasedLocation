package com.juara.weatherbasedlocation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.juara.weatherbasedlocation.adapter.AdapterListSimple;
import com.juara.weatherbasedlocation.model.WeatherModel;
import com.juara.weatherbasedlocation.model.geolocation.GeolocationModel;
import com.juara.weatherbasedlocation.model.postresult.PostResult;
import com.juara.weatherbasedlocation.service.APIClient;
import com.juara.weatherbasedlocation.service.APIInterfacesRest;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostData extends AppCompatActivity {

    ImageView imgFoto;
    ImageButton btnCapture;
    EditText txtLatitude,txtLongitude;
    Button btnSend;
    RecyclerView rv ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_data);

        rv = findViewById(R.id.lstGeolocation);

        btnCapture = findViewById(R.id.btnCapture);
        imgFoto = findViewById(R.id.imgCamera);


        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
            }
        });


        txtLatitude = findViewById(R.id.txtLatitude);
        txtLongitude = findViewById(R.id.txtLongitude);
        btnSend = findViewById(R.id.btnSend);


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pattern = "yyyy-MM-dd";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

                String date = simpleDateFormat.format(new Date());
                postGeolocation(txtLatitude.getText().toString(),txtLongitude.getText().toString(),date,"test");
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


    public RequestBody toRequestBody(String value) {
        if (value==null){
            value="";
        }
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), value);
        return body;
    }

    public void postGeolocation(String lat,String lon,String datetime, String photo){



        RequestBody requestFile1 = RequestBody.create(MediaType.parse("image/jpeg"),byteArray);

        MultipartBody.Part bodyImg1 = MultipartBody.Part.createFormData("photo", "dewa.png", requestFile1);



        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        progressDialog = new ProgressDialog(PostData.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        Call<PostResult> call3 = apiInterface.postDataGeoWithPhoto(toRequestBody(lat),toRequestBody(lon),toRequestBody(datetime),bodyImg1);
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


    private int CAMERA_REQUEST = 100;
    void openCamera() {



            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

            startActivityForResult(cameraIntent, CAMERA_REQUEST);

    }

    Bitmap bitmap;
    byte[] byteArray;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            bitmap = (Bitmap) data.getExtras().get("data");



            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, baos);
            byteArray = baos.toByteArray();
            imgFoto.setImageBitmap(bitmap);


        }
    }





}
