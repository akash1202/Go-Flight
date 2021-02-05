package com.example.goflight;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.IOException;

import java.util.UUID;


public class admin_add_hotel extends AppCompatActivity {
    Button btn_Addhotel;
    EditText hotelid,hotelname,hotelcountry,hotelprovince,hotelcity,hotelpostalcode,hotelprice;
ImageView hotelpic;
public Uri imageUri;
StorageReference storage;
DatabaseReference reference;
int Image_Request_Code=7;
ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_hotel);
        hotelid = findViewById(R.id.edt_hotelID);
        hotelname = findViewById(R.id.edt_hotelName);
        hotelcountry = findViewById(R.id.edt_hotelCountry);
        hotelprovince = findViewById(R.id.edt_hotelProvince);
        hotelcity = findViewById(R.id.edt_hotelCity);
        hotelpostalcode = findViewById(R.id.edt_hotelPostalcode);
        hotelprice = findViewById(R.id.edt_hotelPrice);
        btn_Addhotel = findViewById(R.id.hotel_btn);
        progressDialog= new ProgressDialog(admin_add_hotel.this);
        hotelpic = findViewById(R.id.img_hotelpics);
        storage = FirebaseStorage.getInstance().getReference("Images");
        reference = FirebaseDatabase.getInstance().getReference("Images");
        hotelpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosepicture();
            }
        });
        btn_Addhotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadImage();
            }
        });

    }
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Image_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {

            imageUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                hotelpic.setImageBitmap(bitmap);
            }
            catch (IOException e) {

                e.printStackTrace();
            }
        }
    }
*/


    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }


    public void UploadImage() {

        if (imageUri != null) {

            progressDialog.setTitle("Data is Uploading...");
            progressDialog.show();
            StorageReference storageReference2 = storage.child(System.currentTimeMillis() + "." + GetFileExtension(imageUri));
            storageReference2.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            String hotelID = hotelid.getText().toString();
                            String hotelNameID = hotelname.getText().toString();
                            String hotelCountryID = hotelcountry.getText().toString();
                            String hotelProvinceID = hotelprovince.getText().toString();
                            String hotelCityID = hotelcity.getText().toString().trim();
                            String hotelPostalcodeID = hotelpostalcode.getText().toString();
                            String hotelPriceID = hotelprice.getText().toString().trim();
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Data Added Successfully ", Toast.LENGTH_LONG).show();
                            @SuppressWarnings("VisibleForTests")
                            hotelmodel imageUploadInfo = new hotelmodel(hotelID,hotelNameID,hotelCountryID,hotelProvinceID,hotelCityID,hotelPostalcodeID,hotelPriceID, taskSnapshot.getUploadSessionUri().toString());
                            String hotelmodelKey = reference.push().getKey();
                            reference.child(hotelmodelKey).setValue(imageUploadInfo);
                        }
                    });
        }
        else {

            Toast.makeText(admin_add_hotel.this, "Please Select Image or Add Image Name", Toast.LENGTH_LONG).show();

        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK && data!=null && data.getData()!=null)
        {
            imageUri =data.getData();
            hotelpic.setImageURI(imageUri);
            uploadpicture();

        }
    }

    private void uploadpicture() {
        final ProgressDialog pdg = new ProgressDialog(this);
        pdg.setTitle("uploading image...");
        pdg.show();
// Create a reference to "mountains.jpg"
        final  String randomKey = UUID.randomUUID().toString();
        StorageReference mountainsRef = storage.child("images/"+randomKey);
mountainsRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
    @Override
    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
        pdg.dismiss();
        Snackbar.make(findViewById(android.R.id.content),"Hotel Image uploaded",Snackbar.LENGTH_LONG).show();
    }
}).addOnFailureListener(new OnFailureListener() {
    @Override
    public void onFailure(@NonNull Exception e) {
        pdg.dismiss();
        Toast.makeText(getApplicationContext(),"Failed to upload hotel image",Toast.LENGTH_LONG).show();
    }
}).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
    @Override
    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
double progressPercent = (100.00 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
pdg.setMessage("Percentage"+(int)progressPercent+"*");
    }
});
    }




    private void choosepicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);



    }
}