package com.flightbooking.activies;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.flightbooking.R;

public class VacationDetailsActivity extends AppCompatActivity {
    ImageView imageView;
    TextView tvname,tvCityName,tvPrice,tvWeb;
    WebView webview;
    CardView cdWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacation_details);


        getSupportActionBar().setTitle("Hotel Details");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final String url=getIntent().getStringExtra("web");

        webview=(WebView)findViewById(R.id.webview);
        cdWebview=(CardView)findViewById(R.id.cdWebview);
        cdWebview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
                /* webview.getSettings().setJavaScriptEnabled(true);
                webview.getSettings().setLoadWithOverviewMode(true);
                webview.getSettings().setUseWideViewPort(true);
                webview.setWebViewClient(new WebViewClient(){
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                        view.loadUrl(url);
                        return true;
                    }
                });
                webview.loadUrl(url);*/
            }
        });

        imageView=(ImageView)findViewById(R.id.imageView);

        Glide.with(VacationDetailsActivity.this).load(getIntent().getStringExtra("photo")).into(imageView);

        tvname=(TextView)findViewById(R.id.tvname);
        tvCityName=(TextView)findViewById(R.id.tvCityName);
        tvPrice=(TextView)findViewById(R.id.tvPrice);
        tvWeb=(TextView) findViewById(R.id.hotelLink);
        tvname.setText(getIntent().getStringExtra("hotelname"));
        tvCityName.setText(getIntent().getStringExtra("city"));
        tvPrice.setText(getIntent().getStringExtra("price")+"$");
        tvWeb.setText(getIntent().getStringExtra("web"));

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