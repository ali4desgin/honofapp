package com.alhonof.app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alhonof.app.Api.Links;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UsernameEditActivity extends AppCompatActivity {

    String user_id;
    SharedPref sharedPref;
    Button saveBtn,cancelBtn;
    EditText usernameEd;
    TextView usernameTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username_edit);
        sharedPref = new SharedPref(UsernameEditActivity.this);
        user_id = sharedPref.getString("user_id");
        setUiElements();
        setUiElementEvenets();
        loadData();

    }


    @SuppressLint("WrongViewCast")
    private  void  setUiElements(){

        saveBtn = findViewById(R.id.saveBtnID);
        cancelBtn = findViewById(R.id.cancelBtn);
        usernameEd = findViewById(R.id.usernameEditTxt);
        usernameTxtView = findViewById(R.id.usernameTxt);
    }


    private void setUiElementEvenets() {

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!usernameEd.getText().toString().isEmpty()){
                    change();
                }
            }
        });



        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UsernameEditActivity.this,SettingsActivity.class);
                startActivity(intent);
            }
        });



    }



    private void change(){
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,Links.change_info,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            if(jsonObject.getBoolean("res")){


                                Intent intent = new Intent(UsernameEditActivity.this,SettingsActivity.class);
                                startActivity(intent);


                            }else{


                                Toast.makeText(UsernameEditActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> map = new HashMap();
                map.put("user_id",user_id);
                map.put("key","username");
                map.put("value",usernameEd.getText().toString());

                return map;
            }
        };

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


    private void loadData(){

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,Links.get_info,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            if(jsonObject.getBoolean("res")){


                                String userstr = jsonObject.getString("user");
                                JSONObject user = new JSONObject(userstr);

                                usernameTxtView.setText(user.getString("username"));



                            }else{


                                Toast.makeText(UsernameEditActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> map = new HashMap();
                map.put("user_id",user_id);

                return map;
            }
        };

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


}
