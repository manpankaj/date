package date.jalaunup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
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

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import date.jalaunup.Config.RequestHandler;
import date.jalaunup.Config.SessionManager;
import date.jalaunup.Config.url_add;
import date.jalaunup.Objects.AdminSearchEmployerWork;

public class ViewRegisteredEmployerWorkDetailActivity extends AppCompatActivity {
    SessionManager session;
    Button logout, back, activate_work;
    String currentProjectId,currentProjectField,currentProjectName,currentProjectAddress;
    TextView username, email, projectteh, projectsd, projected, empname,empemail,empmob, empadd, empteh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_employer_work_detail);
        session = new SessionManager(getApplicationContext());
        session.checkLogin();
        String roleNew = session.checkAdminNew(session);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        projectteh = findViewById(R.id.projectteh);
        projectsd = findViewById(R.id.projectsd);
        projected = findViewById(R.id.projected);
        empname = findViewById(R.id.empname);
        empemail = findViewById(R.id.empemail);
        empmob = findViewById(R.id.empmob);
        empadd = findViewById(R.id.empadd);
        empteh = findViewById(R.id.empteh);

        Intent intent = getIntent();
        currentProjectId = intent.getStringExtra("ProjectId");
        currentProjectField = intent.getStringExtra("ProjectField");
        currentProjectName = intent.getStringExtra("ProjectName");
        currentProjectAddress = intent.getStringExtra("ProjectAddress");
        DisplayProjectDetail(currentProjectId);

        username.setText("Project ID " + currentProjectId + " Project Name " + currentProjectName);
        email.setText("Field " + currentProjectField + " Add. " +  currentProjectAddress);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewRegisteredEmployerWorkDetailActivity.this, AdminSearchEmployerWork.class);
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

        activate_work = findViewById(R.id.activateWork);
        activate_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog progressDialog = new ProgressDialog(ViewRegisteredEmployerWorkDetailActivity.this);
                progressDialog.setMessage("Please Wait..");
                progressDialog.show();
                StringRequest request = new StringRequest(Request.Method.POST, url_add.activate_employer_work_id, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Toast.makeText(ViewRegisteredEmployerWorkDetailActivity.this, response, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ViewRegisteredEmployerWorkDetailActivity.this, AdminSearchEmployerWork.class);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(ViewRegisteredEmployerWorkDetailActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                ) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("id", currentProjectId);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(ViewRegisteredEmployerWorkDetailActivity.this);
                requestQueue.add(request);
            }
        });
    }

    /********************************************************************************************/
    public void DisplayProjectDetail(String currentProjectId) {
        class BindProjectMaster extends AsyncTask<Void, Void, String> {
            ProgressDialog pdLoading = new ProgressDialog(ViewRegisteredEmployerWorkDetailActivity.this);

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
                params.put("id", currentProjectId);
                return requestHandler.sendPostRequest(url_add.admin_work_employer_detail, params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                pdLoading.dismiss();
                try {
                    JSONObject obj = new JSONObject(s);
                    projectteh.setText(obj.getString("ProjectTeh"));
                    projectsd.setText(obj.getString("ProjectSD"));
                    projected.setText(obj.getString("ProjectED"));
                    empmob.setText(obj.getString("EmployerMob"));
                    empname.setText(obj.getString("EmployerName"));
                    empemail.setText(obj.getString("EmployerEmail"));
                    empadd.setText(obj.getString("EmployerAdd"));
                    empteh.setText(obj.getString("EmployerTeh"));

                } catch (Exception e) {
                }
            }
        }
        BindProjectMaster obj = new BindProjectMaster();
        obj.execute();
    }
    /*******************************************************************************************************/
    @Override
    public void onBackPressed()
    {
        Intent i = new Intent(ViewRegisteredEmployerWorkDetailActivity.this,WelcomeaActivity.class);
        startActivity(i);
        finish();
    }
}