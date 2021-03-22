package date.jalaunup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;
import date.jalaunup.Config.RequestHandler;
import date.jalaunup.Config.SessionManager;
import date.jalaunup.Config.url_add;

public class ViewWorkerDetailByempActivity extends AppCompatActivity {
    SessionManager session;
    Button logout, back;
    String currentWorkerId;
    TextView workerid, worker_name, worker_mobile, worker_email, worker_sex, worker_age,worker_add,worker_teh, worker_fa, worker_wa, worker_we;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_worker_detail_byemp);
        session = new SessionManager(getApplicationContext());
        session.checkLogin();
        String roleNew = session.checkAdminNew(session);
        workerid = findViewById(R.id.tv_workerid);
        worker_name = findViewById(R.id.tv_worker_name);
        worker_mobile = findViewById(R.id.tv_worker_mobile);
        worker_email = findViewById(R.id.tv_worker_email);
        worker_sex = findViewById(R.id.tv_worker_sex);
        worker_age = findViewById(R.id.tv_worker_age);
        worker_add = findViewById(R.id.tv_worker_add);
        worker_teh = findViewById(R.id.tv_worker_teh);
        worker_fa = findViewById(R.id.tv_worker_fa);
        worker_wa = findViewById(R.id.tv_worker_wa);
        worker_we = findViewById(R.id.tv_worker_we);

        Intent intent = getIntent();
        currentWorkerId = intent.getStringExtra("WorkerId");
        DisplayWorkerDetail(currentWorkerId);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewWorkerDetailByempActivity.this, EmployerSearchManBycatActivity.class);
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
    }

    /************************************************************************************************************************************************************************/
    public void DisplayWorkerDetail(String currentWorkerId) {
        class BindWorkerMaster extends AsyncTask<Void, Void, String> {
            ProgressDialog pdLoading = new ProgressDialog(ViewWorkerDetailByempActivity.this);

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
                params.put("id", currentWorkerId);
                return requestHandler.sendPostRequest(url_add.worker_detail_by_id, params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                pdLoading.dismiss();
                try {
                    JSONObject obj = new JSONObject(s);
                    workerid.setText(obj.getString("WorkerId"));
                    worker_name.setText(obj.getString("WorkerName"));
                    worker_mobile.setText(obj.getString("WorkerMobileNo"));
                    worker_email.setText(obj.getString("WorkerEmail"));
                    worker_sex.setText(obj.getString("WorkerSex"));
                    worker_age.setText(obj.getString("WorkerAge"));
                    worker_add.setText(obj.getString("WorkerAdd"));
                    worker_teh.setText(obj.getString("WorkerTeh"));
                    worker_fa.setText(obj.getString("WorkerField"));
                    worker_wa.setText(obj.getString("WorkerWork"));
                    worker_we.setText(obj.getString("WorkerExp"));

                } catch (Exception e) {
                }
            }
        }
        BindWorkerMaster obj = new BindWorkerMaster();
        obj.execute();
    }
    /************************************************************************************************************************************************************************/
}