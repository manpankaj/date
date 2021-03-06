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

public class ViewRegisteredWorkerDetailActivity extends AppCompatActivity {
    SessionManager session;
    Button logout,back,activate_worker;
    String url = url_add.activate_worker;
    String currentWorkerId,currentWorkerName,currentWorkerMobileNo,currentWorkerEmail,currentWorkerAge,currentWorkerSex,
            currentWorkerField,currentWorkerWork,currentWorkerExp;
    TextView tv_workerid,tv_worker_name,tv_worker_mobile,tv_worker_email,tv_worker_sex,tv_worker_age,
            tv_worker_fa,tv_worker_wa,tv_worker_we;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_registered_worker_detail);
        session = new SessionManager(getApplicationContext());
        session.checkLogin();
        String roleNew =  session.checkAdminNew(session);
        tv_workerid=findViewById(R.id.tv_workerid);
        tv_worker_name=findViewById(R.id.tv_worker_name);
        tv_worker_mobile=findViewById(R.id.tv_worker_mobile);
        tv_worker_email=findViewById(R.id.tv_worker_email);
        tv_worker_sex=findViewById(R.id.tv_worker_sex);
        tv_worker_age=findViewById(R.id.tv_worker_age);
        tv_worker_fa=findViewById(R.id.tv_worker_fa);
        tv_worker_wa=findViewById(R.id.tv_worker_wa);
        tv_worker_we=findViewById(R.id.tv_worker_we);

        Intent intent = getIntent();
        currentWorkerId = intent.getStringExtra("WorkerId");
        currentWorkerName = intent.getStringExtra("WorkerName");
        currentWorkerMobileNo = intent.getStringExtra("WorkerMobileNo");
        currentWorkerEmail = intent.getStringExtra("WorkerEmail");
        currentWorkerAge = intent.getStringExtra("WorkerAge");
        currentWorkerSex = intent.getStringExtra("WorkerSex");
        currentWorkerField = intent.getStringExtra("WorkerField");
        currentWorkerWork = intent.getStringExtra("WorkerWork");
        currentWorkerExp = intent.getStringExtra("WorkerExp");

        tv_workerid.setText(currentWorkerId);
        tv_worker_name.setText(currentWorkerName);
        tv_worker_mobile.setText(currentWorkerMobileNo);
        tv_worker_email.setText(currentWorkerEmail);
        tv_worker_sex.setText(currentWorkerSex);
        tv_worker_age.setText(currentWorkerAge);
        tv_worker_fa.setText(currentWorkerField);
        tv_worker_wa.setText(currentWorkerWork);
        tv_worker_we.setText(currentWorkerExp);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewRegisteredWorkerDetailActivity.this, WelcomeaActivity.class);
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
        activate_worker = findViewById(R.id.activate_worker);
        activate_worker.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ProgressDialog progressDialog = new ProgressDialog(ViewRegisteredWorkerDetailActivity.this);
                progressDialog.setMessage("Please Wait..");
                progressDialog.show();
                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Toast.makeText(ViewRegisteredWorkerDetailActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(ViewRegisteredWorkerDetailActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                ) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("worker_mobile", currentWorkerMobileNo);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(ViewRegisteredWorkerDetailActivity.this);
                requestQueue.add(request);
            }
        });


    }
}