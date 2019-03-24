package com.alhonof.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class SettingsActivity extends AppCompatActivity {

    ListView listView,listView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        listView = findViewById(R.id.accountListView);
        listView2 = findViewById(R.id.accountListView2);

        AccountListViewAdapter accountListViewAdapter = new AccountListViewAdapter(SettingsActivity.this);
        listView.setAdapter(accountListViewAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0){

                    Intent intent = new Intent(SettingsActivity.this,UsernameEditActivity.class);
                    startActivity(intent);
                }else if (i==1){

                    Intent intent = new Intent(SettingsActivity.this,PhoneEditActivity.class);
                    startActivity(intent);
                }else if (i==2){

                    Intent intent = new Intent(SettingsActivity.this,PasswordEditActivity.class);
                    startActivity(intent);
                }else if (i==3){

                    Intent intent = new Intent(SettingsActivity.this,CountryEditActivity.class);
                    startActivity(intent);
                }
            }
        });

        GeneralAdapter generalAdapter = new GeneralAdapter(SettingsActivity.this);
        listView2.setAdapter(generalAdapter);
    }

}
