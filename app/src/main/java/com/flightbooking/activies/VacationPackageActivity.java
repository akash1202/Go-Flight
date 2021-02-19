package com.flightbooking.activies;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.flightbooking.R;
import com.flightbooking.model.VacationPackagePojo;

import java.util.ArrayList;
import java.util.List;

public class VacationPackageActivity extends AppCompatActivity {
    List<VacationPackagePojo> vacationPackagePojo;
    ListView list_view;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacation_package);

        getSupportActionBar().setTitle("Vacation Package");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list_view = (ListView) findViewById(R.id.list_view);

        vacationPackagePojo = new ArrayList<>();
        vacationPackagePojo.add(new VacationPackagePojo("https://www.traveldailymedia.com/assets/2019/07/Singapore-.jpg","Hyarr Regency Ahemedabad","Ahemedabad","CAD $515"));
        vacationPackagePojo.add(new VacationPackagePojo("https://www.traveldailymedia.com/assets/2019/07/Singapore-.jpg","Hyarr Regency Ahemedabad","Ahemedabad","CAD $515"));
        vacationPackagePojo.add(new VacationPackagePojo("https://www.traveldailymedia.com/assets/2019/07/Singapore-.jpg","Hyarr Regency Ahemedabad","Ahemedabad","CAD $515"));
        vacationPackagePojo.add(new VacationPackagePojo("https://www.traveldailymedia.com/assets/2019/07/Singapore-.jpg","Hyarr Regency Ahemedabad","Ahemedabad","CAD $515"));
        vacationPackagePojo.add(new VacationPackagePojo("https://www.traveldailymedia.com/assets/2019/07/Singapore-.jpg","Hyarr Regency Ahemedabad","Ahemedabad","CAD $515"));
        vacationPackagePojo.add(new VacationPackagePojo("https://www.traveldailymedia.com/assets/2019/07/Singapore-.jpg","Hyarr Regency Ahemedabad","Ahemedabad","CAD $515"));
        vacationPackagePojo.add(new VacationPackagePojo("https://www.traveldailymedia.com/assets/2019/07/Singapore-.jpg","Hyarr Regency Ahemedabad","Ahemedabad","CAD $515"));
        vacationPackagePojo.add(new VacationPackagePojo("https://www.traveldailymedia.com/assets/2019/07/Singapore-.jpg","Hyarr Regency Ahemedabad","Ahemedabad","CAD $515"));

        list_view.setAdapter(new VacationPackageAdapter(vacationPackagePojo, VacationPackageActivity.this));
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