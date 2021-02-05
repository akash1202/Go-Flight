package com.example.goflight;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.DialogPlusBuilder;
import com.orhanobut.dialogplus.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import static com.example.goflight.R.color.colorAccent;

public class Hotel_Retrieve_Adapter extends FirebaseRecyclerAdapter<hotelmodel,Hotel_Retrieve_Adapter.myviewholder> {

    public Hotel_Retrieve_Adapter(@NonNull FirebaseRecyclerOptions<hotelmodel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull hotelmodel model) {
        holder.hotelid.setText("ID:"+" " +model.getHotelid());
        holder.hotelname.setText("Name:"+" " +model.getHotelname());
        holder.hotelcountry.setText("Country:"+" "+model.getHotelcountry());
        holder.hotelprovince.setText("Province:"+" " +model.getHotelprovince());
        holder.hotelcity.setText("City:"+" " +model.getHotelcity());
        holder.hotelpostalcode.setText("PostalCode:"+" " +model.getHotelpostalcode());
        holder.hotelprice.setText("Price:"+" " +model.getHotelprice());
        Picasso.get().load(model.getImageURL()).into(holder.imageURL);


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.hotelname.getContext(),R.style.MyDialogstyle);
                builder.setTitle("Delete Hotel Information ");
                builder.setMessage("  Do you want to delete this Hotel Information ??  ");
                builder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Images")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();

            }
        });
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotelcardview,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{
        TextView hotelid,hotelname,hotelcountry,hotelprovince,hotelcity,hotelpostalcode,hotelprice;
        ImageView imageURL;
        ImageView delete;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            hotelid=(TextView)itemView.findViewById(R.id.hotelid);
            hotelname=(TextView)itemView.findViewById(R.id.hotelName);
            hotelcountry=(TextView)itemView.findViewById(R.id.hotelCountry);
            hotelprovince=(TextView)itemView.findViewById(R.id.hotelProvince);
            hotelcity=(TextView)itemView.findViewById(R.id.hotelCity);
            hotelpostalcode=(TextView)itemView.findViewById(R.id.hotelPostalcode);
            hotelprice=(TextView)itemView.findViewById(R.id.hotelprice);
            imageURL=(ImageView)itemView.findViewById(R.id.img_hotelpic);
            delete=(ImageView)itemView.findViewById(R.id.deleteicon);
            delete.setBackgroundColor(delete.getContext().getResources().getColor(R.color.white));




        }
    }
}
