package date.jalaunup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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

public class ViewRegisteredWorkerDetailActivity extends AppCompatActivity {
    SessionManager session;
    Button logout,back,activate_worker;
    String currentWorkerId;
    TextView workerid,worker_name,worker_mobile,worker_email,worker_sex,worker_age,worker_fa,worker_wa,worker_we;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_registered_worker_detail);
        session = new SessionManager(getApplicationContext());
        session.checkLogin();
        String roleNew =  session.checkAdminNew(session);
        workerid=findViewById(R.id.tv_workerid);
        worker_name=findViewById(R.id.tv_worker_name);
        worker_mobile=findViewById(R.id.tv_worker_mobile);
        worker_email=findViewById(R.id.tv_worker_email);
        worker_sex=findViewById(R.id.tv_worker_sex);
        worker_age=findViewById(R.id.tv_worker_age);
        worker_fa=findViewById(R.id.tv_worker_fa);
        worker_wa=findViewById(R.id.tv_worker_wa);
        worker_we=findViewById(R.id.tv_worker_we);

        Intent intent = getIntent();
        currentWorkerId = intent.getStringExtra("WorkerId");
        DisplayWorkerDetail(currentWorkerId);

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
        activate_worker.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                ActivateWorkerDetail(currentWorkerId);
            }
        });
    }

    /************************************************************************************************************************************************************************/
    public void DisplayWorkerDetail(String currentWorkerId)
    {
        class BindWorkerMaster extends AsyncTask<Void, Void, String>
        {
            ProgressDialog pdLoading = new ProgressDialog(ViewRegisteredWorkerDetailActivity.this);

            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();
                pdLoading.setMessage("\tLoading...");
                pdLoading.setCancelable(false);
                pdLoading.show();
            }

            @Override
            protected String doInBackground(Void... voids)
            {
                RequestHandler requestHandler = new RequestHandler();
                HashMap<String, String> params = new HashMap<>();
                params.put("id", currentWorkerId);
                return requestHandler.sendPostRequest(url_add.worker_detail_by_id, params);
            }

            @Override
            protected void onPostExecute(String s)
            {
                super.onPostExecute(s);
                pdLoading.dismiss();
                //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                try
                {
                    JSONObject obj = new JSONObject(s);
                    //Toast.makeText(getApplicationContext(), obj.toString(), Toast.LENGTH_LONG).show();
                    String wore=obj.getString("WorkerId");
                    Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                        workerid.setText(wore);
                        worker_name.setText(obj.getString("WorkerName"));
                        worker_mobile.setText(obj.getString("WorkerMobileNo"));
                        worker_email.setText(obj.getString("WorkerEmail"));
                        worker_sex.setText(obj.getString("WorkerSex"));
                        worker_age.setText(obj.getString("WorkerAge"));
                        worker_fa.setText(obj.getString("WorkerField"));
                        worker_wa.setText(obj.getString("WorkerWork"));
                        worker_we.setText(obj.getString("WorkerExp"));

                }
                catch (Exception e )
                {
                    //Toast.makeText(ViewRegisteredWorkerDetailActivity.this, "Exception: "+e, Toast.LENGTH_SHORT).show();
                }
            }
        }
        BindWorkerMaster obj = new BindWorkerMaster();
        obj.execute();
    }
    /************************************************************************************************************************************************************************/
    public void ActivateWorkerDetail(String currentWorkerId)
    {
        class ActivateWorker extends AsyncTask<Void, Void, String>
        {
            ProgressDialog pdLoading = new ProgressDialog(ViewRegisteredWorkerDetailActivity.this);

            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();
                pdLoading.setMessage("\tLoading...");
                pdLoading.setCancelable(false);
                pdLoading.show();
            }

            @Override
            protected String doInBackground(Void... voids)
            {
                RequestHandler requestHandler = new RequestHandler();
                HashMap<String, String> params = new HashMap<>();
                params.put("id", currentWorkerId);
                return requestHandler.sendPostRequest(url_add.activate_worker_by_id, params);
            }

            @Override
            protected void onPostExecute(String s)
            {
                super.onPostExecute(s);
                pdLoading.dismiss();

                try
                {
                    JSONObject obj = new JSONObject(s);
                    if (!obj.getBoolean("error"))
                    {
                        Toast.makeText(ViewRegisteredWorkerDetailActivity.this, "Worker Activated Successfully", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e )
                {
                    Toast.makeText(ViewRegisteredWorkerDetailActivity.this, "Exception: "+e, Toast.LENGTH_SHORT).show();
                }
            }
        }
        ActivateWorker obj = new ActivateWorker();
        obj.execute();
    }
}