package com.alhonof.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
public class CountryEditActivity extends AppCompatActivity {

    ListView countryList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_edit);
        countryList = findViewById(R.id.countryList);
        CountryAdapter countryAdapter = new CountryAdapter(CountryEditActivity.this);
        countryList.setAdapter(countryAdapter);
    }
}
