package date.jalaunup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;
import date.jalaunup.Config.RequestHandler;
import date.jalaunup.Config.SessionManager;
import date.jalaunup.Config.url_add;

public class ViewWorkerEmploymentDetailActivity extends AppCompatActivity {
    SessionManager session;
    String currentProjectId,currentProjectMobile,currentProjectrName,currentProjectrAddress,currentProjectrTehsil,str_category ,str_subcategory;
    TextView username,email;
    TextView tv_Employerid,tv_Employer_name,tv_Employer_mobile,tv_Employer_email,tv_Employer_address,tv_Employer_tehsil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_worker_employment_detail);
        session = new SessionManager(getApplicationContext());
        session.checkLogin();
        String roleNew =  session.checkWorkerNew(session);
        HashMap<String, String> user = session.getUserDetails();
        str_category = user.get(SessionManager.KEY_CATEGORY);
        str_subcategory = user.get(SessionManager.KEY_SUBCATEGORY);
        tv_Employerid=findViewById(R.id.tv_employerid);
        tv_Employer_name=findViewById(R.id.tv_employer_name);
        tv_Employer_mobile=findViewById(R.id.tv_Employer_mobile);
        tv_Employer_email=findViewById(R.id.tv_Employer_email);
        tv_Employer_address=findViewById(R.id.tv_Employer_address);
        tv_Employer_tehsil=findViewById(R.id.tv_Employer_tehsil);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);

        Intent intent = getIntent();
        currentProjectId = intent.getStringExtra("ProjectId");
        currentProjectMobile = intent.getStringExtra("ProjectMobile");
        currentProjectrName = intent.getStringExtra("ProjectName");
        currentProjectrAddress= intent.getStringExtra("ProjectAddress");
        currentProjectrTehsil = intent.getStringExtra("ProjectTehsil");
        username.setText("Project Id: " + currentProjectId + "  Name: " + currentProjectrName);
        email.setText(currentProjectrAddress +" "+ currentProjectrTehsil );
        DisplayEmployerDetail(currentProjectId);


    }
    /************************************************************************************************************************************************************************/
    public void DisplayEmployerDetail(String currentProjectrId)
    {
        class BindEmployerMaster extends AsyncTask<Void, Void, String> {
            ProgressDialog pdLoading = new ProgressDialog(ViewWorkerEmploymentDetailActivity.this);

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
                params.put("id", currentProjectrId);
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
    @Override
    public void onBackPressed()
    {
        Intent i = new Intent(ViewWorkerEmploymentDetailActivity.this,EmploymentWorkerActivity.class);
        startActivity(i);
        finish();
    }
}