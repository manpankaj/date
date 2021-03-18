package date.jalaunup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import date.jalaunup.Config.RequestHandler;
import date.jalaunup.Config.SessionManager;
import date.jalaunup.Config.url_add;

public class ViewRegisteredEmployerDetailActivity extends AppCompatActivity {
    SessionManager session;
    Button logout,back,activate_Employer;
    String currentEmployerId;
    TextView tv_Employerid,tv_Employer_name,tv_Employer_mobile,tv_Employer_email,tv_Employer_address,tv_Employer_tehsil;
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
        tv_Employer_address=findViewById(R.id.tv_Employer_address);
        tv_Employer_tehsil=findViewById(R.id.tv_Employer_tehsil);

        Intent intent = getIntent();
        currentEmployerId = intent.getStringExtra("EmployerId");
        DisplayEmployerDetail(currentEmployerId);

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
                StringRequest request = new StringRequest(Request.Method.POST, url_add.activate_employer_by_id, new Response.Listener<String>() {
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
                        params.put("id", currentEmployerId);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(ViewRegisteredEmployerDetailActivity.this);
                requestQueue.add(request);
            }
        });
    }
    /************************************************************************************************************************************************************************/
    public void DisplayEmployerDetail(String currentEmployerId)
    {
        class BindEmployerMaster extends AsyncTask<Void, Void, String> {
            ProgressDialog pdLoading = new ProgressDialog(ViewRegisteredEmployerDetailActivity.this);

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                pdLoading.setMessage("\tLoading...");
                pdLoading.setCancelable(false);
                pdLoading.show();
            }

            @Override
            protected String doInBackground(Void... voids) {
                RequestHandler requestHandler = new RequestHandler();
                HashMap<String, String> params = new HashMap<>();
                params.put("id", currentEmployerId);
                return requestHandler.sendPostRequest(url_add.employer_detail_by_id, params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                pdLoading.dismiss();
                try {
                    JSONObject obj = new JSONObject(s);
                    tv_Employerid.setText(obj.getString("EmployerId"));
                    tv_Employer_name.setText(obj.getString("EmployerName"));
                    tv_Employer_mobile.setText(obj.getString("EmployerMobileNo"));
                    tv_Employer_email.setText(obj.getString("EmployerEmail"));
                    tv_Employer_address.setText(obj.getString("EmployerAddress"));
                    tv_Employer_tehsil.setText(obj.getString("EmployerTehsil"));

                } catch (Exception e) {
                }
            }
        }
        BindEmployerMaster obj = new BindEmployerMaster();
        obj.execute();
    }
    /************************************************************************************************************************************************************************/
}