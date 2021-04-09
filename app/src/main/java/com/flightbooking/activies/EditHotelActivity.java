package com.flightbooking.activies;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.flightbooking.R;
import com.flightbooking.api.ApiService;
import com.flightbooking.model.ResponseData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditHotelActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{

    EditText etHotelName,etCountry,etProvince,etCity,etPostalCode,etPrice;
    Button btnAddHotel,btn_upload;
    ImageView hotelImageView;
    ProgressDialog pd;
    private static final String TAG = AddHotelActivity.class.getSimpleName();
    private static final int REQUEST_GALLERY_CODE = 200;
    private static final int READ_REQUEST_CODE = 300;
    //private static final String SERVER_PATH = "http://bookingflight.info/";
    private static final String SERVER_PATH = "https://goflightinfo.000webhostapp.com/";
    String imgUrl="https://goflightinfo.000webhostapp.com/flight";
    private Uri uri=null;
    String hid="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hotel);

        getSupportActionBar().setTitle("Edit Hotel");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etHotelName=(EditText)findViewById(R.id.etHotelName);
        etCountry=(EditText)findViewById(R.id.etCountry);
        etProvince=(EditText)findViewById(R.id.etProvince);
        etCity=(EditText)findViewById(R.id.etCity);
        etPostalCode=(EditText)findViewById(R.id.etPostalCode);
        etPrice=(EditText)findViewById(R.id.etPrice);
        hotelImageView= findViewById(R.id.hotelImageView);
        hid=getIntent().getStringExtra("hid");
        etHotelName.setText(getIntent().getStringExtra("name"));
        etCity.setText(getIntent().getStringExtra("city"));
        etProvince.setText(getIntent().getStringExtra("province"));
        etCountry.setText(getIntent().getStringExtra("country"));
        etPostalCode.setText(getIntent().getStringExtra("pcode"));
        etPrice.setText(getIntent().getStringExtra("price"));
        hotelImageView.setVisibility(View.VISIBLE);
        Glide.with(EditHotelActivity.this).load(imgUrl + getIntent().getStringExtra("photo")).into(hotelImageView);
        hotelImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK);
                openGalleryIntent.setType("image/*");
                startActivityForResult(openGalleryIntent, REQUEST_GALLERY_CODE);
            }
        });

        btnAddHotel=(Button)findViewById(R.id.btnAddHotel);
        btnAddHotel.setText("Update Hotel");
        btnAddHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImageToServer();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, EditHotelActivity.this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_GALLERY_CODE && resultCode == Activity.RESULT_OK) {
            uri = data.getData();
            if (EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                String filePath = getRealPathFromURIPath(uri, EditHotelActivity.this);
                file = new File(filePath);
                Uri uri =Uri.fromFile(file);
                hotelImageView.setImageURI(uri);
            } else {
                EasyPermissions.requestPermissions(this, getString(R.string.read_file), READ_REQUEST_CODE, Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        }else{
            uri=null;
        }
    }
    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }
    File file;
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if(uri != null){
            String filePath = getRealPathFromURIPath(uri, EditHotelActivity.this);
            file = new File(filePath);
            // uploadImageToServer();
        }
    }
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d(TAG, "Permission has been denied");

    }

    private void uploadImageToServer() {
        pd = new ProgressDialog(EditHotelActivity.this);
        pd.setTitle("Loading");
        pd.show();
        Map<String, String> map = new HashMap<>();
        map.put("hid",hid);
        map.put("name", etHotelName.getText().toString());
        map.put("country", etCountry.getText().toString());
        map.put("city", etCity.getText().toString());
        map.put("province", etProvince.getText().toString());
        map.put("pcode", etPostalCode.getText().toString());
        map.put("price", etPrice.getText().toString());

        MultipartBody.Part fileToUpload=null;
        ApiService uploadImage=null;
        if(file!=null) {
            RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
            fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), mFile);
            RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());
        }
        Gson gson=new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_PATH)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
            uploadImage = retrofit.create(ApiService.class);
            Call<ResponseData> fileUpload;
            if (fileToUpload != null) {
               // Toast.makeText(this, ""+map.get("hid"), Toast.LENGTH_SHORT).show();
                fileUpload = uploadImage.addhotel(fileToUpload, map);
            } else {
                fileUpload = uploadImage.addHotel(map.get("hid"),map.get("name"),map.get("country"),map.get("province"),map.get("city"),map.get("pcode"),map.get("price"));
            }

            fileUpload.enqueue(new Callback<ResponseData>() {
                @Override
                public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                    pd.dismiss();
                    Log.e("error in upload: ", response.toString()+ response.body().message+" "+ response.body().status);
                    Toast.makeText(EditHotelActivity.this, "Hotel Updated successfully. ", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(EditHotelActivity.this, HotelInfoActivity.class));
                    finish();
                }

                @Override
                public void onFailure(Call<ResponseData> call, Throwable t) {
                    pd.dismiss();
                    Log.e("error in upload1: ", t.getMessage()+ call.clone().toString());
                    Toast.makeText(EditHotelActivity.this, "Error" + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}