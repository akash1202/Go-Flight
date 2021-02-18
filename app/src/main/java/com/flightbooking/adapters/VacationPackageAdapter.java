package com.flightbooking.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.flightbooking.R;
import com.flightbooking.model.VacationPackagePojo;

import java.util.List;

public class VacationPackageAdapter extends BaseAdapter {
    List<VacationPackagePojo> vacationPackagePojos;
    Context cnt;

    public VacationPackageAdapter(List<VacationPackagePojo> vacationPackagePojos, Context cnt) {
        this.vacationPackagePojos = vacationPackagePojos;
        this.cnt = cnt;
    }

    @Override
    public int getCount() {
        return vacationPackagePojos.size();
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
        View obj2 = obj1.inflate(R.layout.adapter_vacation_packages, null);

        ImageView imageView=(ImageView)obj2.findViewById(R.id.imageView);
        Glide.with(cnt).load(vacationPackagePojos.get(pos).getImage()).into(imageView);

        TextView tvname = (TextView) obj2.findViewById(R.id.tvname);
        tvname.setText(vacationPackagePojos.get(pos).getAgencyname());

        TextView tvCityName = (TextView) obj2.findViewById(R.id.tvCityName);
        tvCityName.setText(vacationPackagePojos.get(pos).getCityname());

        TextView tvPrice = (TextView) obj2.findViewById(R.id.tvPrice);
        tvPrice.setText(vacationPackagePojos.get(pos).getCost());

        CardView cdVacationOffers=(CardView)obj2.findViewById(R.id.cdVacationOffers);
        cdVacationOffers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cnt.startActivity(new Intent(cnt, VacationDetailsActivity.class));
            }
        });


        return obj2;
    }


}