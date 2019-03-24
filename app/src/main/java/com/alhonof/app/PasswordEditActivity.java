package com.alhonof.app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.alhonof.app.Api.Links;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
public class PasswordEditActivity extends AppCompatActivity {

    String user_id;
    SharedPref sharedPref;
    Button saveBtn,cancelBtn,helpbtn;
    EditText confirmpassword;
    EditText newpassword;
    EditText oldpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_edit);
        sharedPref = new SharedPref(PasswordEditActivity.this);
        user_id = sharedPref.getString("user_id");
        setUiElements();
        setUiElementEvenets();


    }


    @SuppressLint("WrongViewCast")
    private  void  setUiElements(){

        saveBtn = findViewById(R.id.saveBtnID);
        cancelBtn = findViewById(R.id.cancelBtn);
        helpbtn = findViewById(R.id.help);
        oldpassword = findViewById(R.id.oldpassword);
        newpassword = findViewById(R.id.newpassword);
        confirmpassword = findViewById(R.id.confirmpassword);
    }


    private void setUiElementEvenets() {

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!oldpassword.getText().toString().isEmpty() && !newpassword.getText().toString().isEmpty() && !confirmpassword.getText().toString().isEmpty()){
                    checkpassword();
                }else{
                    Toast.makeText(PasswordEditActivity.this,"all field must be not empty",Toast.LENGTH_LONG).show();

                }
            }
        });



        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PasswordEditActivity.this,SettingsActivity.class);
                startActivity(intent);
            }
        });



    }



    private void change(){
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,Links.password,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            if(jsonObject.getBoolean("res")){


                                Intent intent = new Intent(PasswordEditActivity.this,SettingsActivity.class);
                                startActivity(intent);


                            }else{


                                Toast.makeText(PasswordEditActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();

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
                map.put("ob","chnage");
                map.put("password",newpassword.getText().toString());

                return map;
            }
        };

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


    private void checkpassword(){

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,Links.password,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            if(jsonObject.getBoolean("res")){

                                change();


                            }else{


                                Toast.makeText(PasswordEditActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();

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
                map.put("password",oldpassword.getText().toString());
                map.put("ob","check");

                return map;
            }
        };

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


}
