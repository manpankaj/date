package date.jalaunup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import date.jalaunup.Config.SessionManager;
import date.jalaunup.Config.url_add;

public class ViewRegisteredEmployerDetailActivity extends AppCompatActivity {
    SessionManager session;
    Button logout,back,activate_Employer;
    String url = url_add.activate_employer;
    String currentEmployerId,currentEmployerName,currentEmployerMobileNo,currentEmployerEmail,currentEmployerAge,currentEmployerSex,
            currentEmployerField,currentEmployerEmploy,currentEmployerExp;
    TextView tv_Employerid,tv_Employer_name,tv_Employer_mobile,tv_Employer_email,tv_Employer_sex,tv_Employer_age,
            tv_Employer_fa,tv_Employer_wa,tv_Employer_we;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_registered_employer_detail);
        session = new SessionManager(getApplicationContext());
        session.checkLogin();
        String roleNew =  session.checkAdminNew(session);
        tv_Employerid=findViewById(R.id.tv_employerid);
        tv_Employer_name=findViewById(R.id.tv_employer_name);
        tv_Employer_mobile=findViewById(R.id.tv_Employer_mobile);
        tv_Employer_email=findViewById(R.id.tv_Employer_email);

        Intent intent = getIntent();
        currentEmployerId = intent.getStringExtra("EmployerId");
        currentEmployerName = intent.getStringExtra("EmployerName");
        currentEmployerMobileNo = intent.getStringExtra("EmployerMobileNo");
        currentEmployerEmail = intent.getStringExtra("EmployerEmail");

        tv_Employerid.setText(currentEmployerId);
        tv_Employer_name.setText(currentEmployerName);
        tv_Employer_mobile.setText(currentEmployerMobileNo);
        tv_Employer_email.setText(currentEmployerEmail);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewRegisteredEmployerDetailActivity.this, WelcomeaActivity.class);
                startActivity(intent);
            }
        });
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.logoutUser();
            }
        });
        activate_Employer = findViewById(R.id.activate_Employer);
        activate_Employer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ProgressDialog progressDialog = new ProgressDialog(ViewRegisteredEmployerDetailActivity.this);
                progressDialog.setMessage("Please Wait..");
                progressDialog.show();
                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Toast.makeText(ViewRegisteredEmployerDetailActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(ViewRegisteredEmployerDetailActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                ) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("Employer_mobile", currentEmployerMobileNo);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(ViewRegisteredEmployerDetailActivity.this);
                requestQueue.add(request);
            }
        });


    }
}