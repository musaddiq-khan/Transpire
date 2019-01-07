package com.example.junaid.transpire;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import com.example.junaid.transpire.SaveSharedPreference;

public class loginActivity extends AppCompatActivity {

    private EditText email_l,password_l;
    private Button btn_login;
    private TextView link_regist;
    private ProgressBar loading;
    private RelativeLayout loginPage;
    //private static String URL_LOGIN="http://192.168.43.7/android_register_login/login.php";
    private static String URL_LOGIN="http://transpires.000webhostapp.com/login.php";

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setTitle("Login");

        sessionManager=new SessionManager(this);

        //back button code
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }



        loading = findViewById(R.id.loading);
        email_l = findViewById(R.id.email);
        password_l = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);
        link_regist = findViewById(R.id.link_regist);
        loginPage =findViewById(R.id.login_page);

        if(SaveSharedPreference.getLoggedStatus(getApplicationContext())) {
            Intent intent=new Intent(loginActivity.this,home_activity.class);
            startActivity(intent);
        } else {
            loginPage.setVisibility(View.VISIBLE);
        }

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail = email_l.getText().toString().trim();
                String mPass = password_l.getText().toString().trim();

                if(!mEmail.isEmpty() || !mPass.isEmpty())
                {
                    login(mEmail,mPass);

                }
                else{
                    email_l.setError("please enter email");
                    password_l.setError("please enter password");
                }
            }
        });

        link_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(loginActivity.this,activity_register.class));
            }
        });




    }

    private void login(final String email, final String password) {
        loading.setVisibility(View.VISIBLE);
        btn_login.setVisibility(View.GONE);

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                    try
                    {

                    JSONObject jsonObject= new JSONObject(response);
                    String success= jsonObject.getString("success");
                    JSONArray jsonArray=jsonObject.getJSONArray("login");

                    if(success.equals("1"))
                    {
                        for (int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject object=jsonArray.getJSONObject(i);

                            String name=object.getString("name").trim();
                            String email=object.getString("email").trim();



                            email_l.setText("");
                            password_l.setText("");

                            loading.setVisibility(View.GONE);
                            btn_login.setVisibility(View.VISIBLE);

                            sessionManager.createSession(name,email);

                            SaveSharedPreference.setLoggedIn(getApplicationContext(), true);
                            Intent intent=new Intent(loginActivity.this,home_activity.class);
                            intent.putExtra("name",name);
                            intent.putExtra("email",email);


                            startActivity(intent);
                                finish();

                        }
                    }else
                    {
                        loading.setVisibility(View.GONE);
                        btn_login.setVisibility(View.VISIBLE);
                        Toast.makeText(loginActivity.this,"Invalid login credentials",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e)
                    {
                    e.printStackTrace();
                    loading.setVisibility(View.GONE);
                    btn_login.setVisibility(View.VISIBLE);
                    Toast.makeText(loginActivity.this,"Invalid login credentials",Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.setVisibility(View.GONE);
                        btn_login.setVisibility(View.VISIBLE);
                        Toast.makeText(loginActivity.this,"Error"+error.toString(),Toast.LENGTH_SHORT).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("email",email);
                params.put("password",password);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    //back button code
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }






}
