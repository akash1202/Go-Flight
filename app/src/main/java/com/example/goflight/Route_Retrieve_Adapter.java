package com.example.goflight;

import android.app.AlertDialog;
import android.content.Context;
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
import java.util.List;
import java.util.Map;

import static com.example.goflight.R.color.colorAccent;

public class Route_Retrieve_Adapter extends RecyclerView.Adapter<Route_Retrieve_Adapter.myviewholder> {
    public Route_Retrieve_Adapter(Context context, List<RouteListRetrieve> list) {
        this.context = context;
        this.list = list;
    }

    private Context context;
private List<RouteListRetrieve> list;


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.threerow,parent,false);
        return new Route_Retrieve_Adapter.myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.routeid_tv.setText(list.get(position).getRouteid());
        holder.departure_tv.setText(list.get(position).getDeparturecity());
        holder.arrival_tv.setText(list.get(position).getArrivalcity());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class myviewholder extends RecyclerView.ViewHolder{
        TextView routeid_tv,departure_tv,arrival_tv;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            routeid_tv=(TextView)itemView.findViewById(R.id.routeid_tv);
            departure_tv=(TextView)itemView.findViewById(R.id.departure_tv);
            arrival_tv=(TextView)itemView.findViewById(R.id.arrival_tv);
        }
    }
}
