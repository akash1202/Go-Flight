package com.flightbooking.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.flightbooking.R;

import java.util.List;

public class CustomerProfileAdapter extends BaseAdapter {
    List<MyProfilePojo> mycardetails;
    Context cnt;

    public CustomerProfileAdapter(List<MyProfilePojo> carDetailsPojo, Context cnt) {
        this.mycardetails = carDetailsPojo;
        this.cnt = cnt;
    }

    @Override
    public int getCount() {
        return mycardetails.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int pos, View view, ViewGroup viewGroup) {
        LayoutInflater obj1 = (LayoutInflater) cnt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View obj2 = obj1.inflate(R.layout.adapter_customer_profile, null);

        TableRow trClick=(TableRow)obj2.findViewById(R.id.trClick);
        trClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDiouge(mycardetails.get(pos).getFirstname()+mycardetails.get(pos).getLastname(),
                        mycardetails.get(pos).getEmail(),mycardetails.get(pos).getPhone(),mycardetails.get(pos).getPass());
            }
        });

        TextView tvFirstnamr = (TextView) obj2.findViewById(R.id.tvFirstnamr);
        tvFirstnamr.setText(mycardetails.get(pos).getFirstname()+mycardetails.get(pos).getLastname());


        TextView tvEmail = (TextView) obj2.findViewById(R.id.tvEmail);
        tvEmail.setText(mycardetails.get(pos).getEmail());


        TextView tvPassword = (TextView) obj2.findViewById(R.id.tvPassword);
        tvPassword.setText(mycardetails.get(pos).getPhone());


        return obj2;
    }
    public void showAlertDiouge(String name,String email,String phoneno,String pass){
        LayoutInflater inflater = LayoutInflater.from(cnt);
        View view = inflater.inflate(R.layout.alert_customer_details, null);

        Button btnOk = (Button) view.findViewById(R.id.btnOk);

        TextView tvName=(TextView)view.findViewById(R.id.tvName);
        TextView tvEmail=(TextView)view.findViewById(R.id.tvEmail);
        TextView tvphoneno=(TextView)view.findViewById(R.id.tvphoneno);
        TextView tvPassword=(TextView)view.findViewById(R.id.tvPassword);


        tvName.setText("Name:  "+name);
        tvEmail.setText("Email:  "+email);
        tvphoneno.setText("Phone:  "+phoneno);
        tvPassword.setText("Password:  "+pass);

        final AlertDialog alertDialog = new AlertDialog.Builder(cnt).setView(view).create();

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                //Toast.makeText(context, "Dismissed..!!", Toast.LENGTH_SHORT).show();
            }
        });
        Window window = alertDialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        alertDialog.show();
    }


}