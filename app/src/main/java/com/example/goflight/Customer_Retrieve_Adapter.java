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

import java.util.HashMap;
import java.util.Map;

import static com.example.goflight.R.color.colorAccent;

public class Customer_Retrieve_Adapter extends FirebaseRecyclerAdapter<CustomerListRetrieve,Customer_Retrieve_Adapter.myviewholder> {

    public Customer_Retrieve_Adapter(@NonNull FirebaseRecyclerOptions<CustomerListRetrieve> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull CustomerListRetrieve model) {

        holder.firstname.setText("Name:"+" " +model.getFirstname());
        holder.lastname.setText(model.getLastname());
        holder.email.setText("Email:"+" " +model.getEmail());
        holder.password.setText("Password:"+" " +model.getPassword());


        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.firstname.getContext())
                        .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                        .setExpanded(true,1200)
                        .create();

                View myview=dialogPlus.getHolderView();
                final EditText firstname=myview.findViewById(R.id.firstname);
                final EditText lastname=myview.findViewById(R.id.lastname);
                final EditText email=myview.findViewById(R.id.email);
                final EditText password=myview.findViewById(R.id.password);
                Button update=myview.findViewById(R.id.update);

                firstname.setText(model.getFirstname());
                lastname.setText(model.getLastname());
                email.setText(model.getEmail());
                password.setText(model.getPassword());

                dialogPlus.show();

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map=new HashMap<>();
                        map.put("firstname",firstname.getText().toString());
                        map.put("lastname",lastname.getText().toString());
                        map.put("email",email.getText().toString());
                        map.put("password",password.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("users")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });
                holder.delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder=new AlertDialog.Builder(holder.firstname.getContext(),R.style.MyDialogstyle);
                        builder.setTitle("Delete Customer ");
                        builder.setMessage("  Do you want to delete this Customer ?  ");
                        builder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                FirebaseDatabase.getInstance().getReference().child("users")
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
        });
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{
        TextView firstname,lastname,email,password;
        ImageView edit,delete;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            firstname=(TextView)itemView.findViewById(R.id.firstname);
            lastname=(TextView)itemView.findViewById(R.id.lastname);
            email=(TextView)itemView.findViewById(R.id.email);
            password=(TextView)itemView.findViewById(R.id.password);
            edit=(ImageView) itemView.findViewById(R.id.editicon);
            edit.setBackgroundColor(edit.getContext().getResources().getColor(R.color.white));
            delete=(ImageView)itemView.findViewById(R.id.deleteicon);
            delete.setBackgroundColor(delete.getContext().getResources().getColor(R.color.white));




        }
    }
}
