package com.flightbooking.activies;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.flightbooking.R;

public class MyTicketDetailsActivity extends AppCompatActivity {

    TextView tvbid,tvname,tvTimings,tvSourceDest,tvoneway,tvAirways,tvstops,tvlayour,tvextrag,tvamount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ticket_details);


        getSupportActionBar().setTitle("Trip Details");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        tvbid=(TextView)findViewById(R.id.tvbid);
        tvname=(TextView)findViewById(R.id.tvname);
        tvTimings=(TextView)findViewById(R.id.tvTimings);

        tvSourceDest=(TextView)findViewById(R.id.tvSourceDest);
        tvoneway=(TextView)findViewById(R.id.tvoneway);
        tvAirways=(TextView)findViewById(R.id.tvAirways);

        tvstops=(TextView)findViewById(R.id.tvstops);
        tvlayour=(TextView)findViewById(R.id.tvlayour);
        tvextrag=(TextView)findViewById(R.id.tvextrag);
        tvamount=(TextView)findViewById(R.id.tvamount);
//        tvname=(TextView)findViewById(R.id.tvname);
//        tvTimings=(TextView)findViewById(R.id.tvTimings);





        tvbid.setText("Booking Id : #FB"+getIntent().getStringExtra("bid"));
        tvname.setText(getIntent().getStringExtra("name"));
        tvTimings.setText(getIntent().getStringExtra("time"));

        tvSourceDest.setText(getIntent().getStringExtra("place"));
        tvoneway.setText(getIntent().getStringExtra("type"));
        tvAirways.setText(getIntent().getStringExtra("air"));

        tvstops.setText("No of Stops : "+getIntent().getStringExtra("stops"));
        tvlayour.setText("Total Hours : "+getIntent().getStringExtra("lay"));
        tvextrag.setText(getIntent().getStringExtra("extra"));

        tvamount.setText("CAD "+"$"+getIntent().getStringExtra("total"));
//        tvname.setText(getIntent().getStringExtra("city"));
//        tvTimings.setText(getIntent().getStringExtra("price")+"$");

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