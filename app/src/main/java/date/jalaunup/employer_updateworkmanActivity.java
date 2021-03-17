package date.jalaunup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class employer_updateworkmanActivity extends AppCompatActivity {
    SessionManager session;
    EditText ed_project,ed_project_add;
    String str_project,str_project_add,str_sdate,str_edate,str_email,str_username;
    TextView username,email,sdate,edate;
    Button logout,back;
    String str_tehsil,str_field;
    TextView tehsil,field,id;
    EditText edcl,edcm,edcs,edcj,edcp,edcpl,edce,edcc,edctm,edcjm;
    TextView txtcl,txtcm,txtcs,txtcj,txtcp,txtcpl,txtce,txtcc,txtctm,txtcjm;
    String currentProjectId,currentField,currentProject;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        currentProjectId = intent.getStringExtra("ProjectId");
        currentField = intent.getStringExtra("ProjectField");
        currentProject = intent.getStringExtra("ProjectName");
       /* arrayList_field.add("Civil");
        arrayList_field.add("Electrical");
        arrayList_field.add("Jal Sansaadan");
        arrayList_field.add("Computer");
        arrayList_field.add("Education");
        arrayList_field.add("Official Work");*/



        if(currentField.equals("Civil")){
        setContentView(R.layout.employer_manwork_civil);

            edcl = findViewById(R.id.edcl);
            edcm = findViewById(R.id.edcm);
            edcs = findViewById(R.id.edcs);
            edcj = findViewById(R.id.edcj);
            edcp = findViewById(R.id.edcp);
            edcpl = findViewById(R.id.edcpl);
            edce = findViewById(R.id.edce);
            edcc = findViewById(R.id.edcc);
            edctm = findViewById(R.id.edctm);
            edcjm = findViewById(R.id.edcjm);
            txtcl = findViewById(R.id.txtcl);
            txtcm = findViewById(R.id.txtcm);
            txtcs = findViewById(R.id.txtcs);
            txtcj = findViewById(R.id.txtcj);
            txtcp = findViewById(R.id.txtcp);
            txtcpl = findViewById(R.id.txtcpl);
            txtce = findViewById(R.id.txtce);
            txtcc = findViewById(R.id.txtcc);
            txtctm = findViewById(R.id.txtctm);
            txtcjm = findViewById(R.id.txtcjm);
            session = new SessionManager(getApplicationContext());
            session.checkLogin();
            String roleNew =  session.checkEmployerNew(session);
            HashMap<String, String> user = session.getUserDetails();
            str_username = user.get(SessionManager.KEY_NAME);
            str_email = user.get(SessionManager.KEY_EMAIL);
            username = findViewById(R.id.username);
            email = findViewById(R.id.email);
        username.setText("Id: " +  currentProjectId + " Field: " + currentField);
        email.setText("Name: " + currentProject);
            DisplayrWorkmanCivilDetail(currentProjectId);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(employer_updateworkmanActivity.this, WelcomeeActivity.class);
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

    } /****************************************************************************************************/
    public void saveProject(View v) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");
        if (ed_project.getText().toString().equals("")){
            Toast.makeText(this, "Enter Project Name", Toast.LENGTH_SHORT).show();
        }
        else if(ed_project_add.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Project Side Address", Toast.LENGTH_SHORT).show();
        }
        else{
            progressDialog.show();
            str_project = ed_project.getText().toString().trim();
            str_project_add = ed_project_add.getText().toString().trim();
            str_sdate=sdate.getText().toString().trim();
            str_edate=edate.getText().toString().trim();
            StringRequest request = new StringRequest(Request.Method.POST, url_add.employer_update_work, new Response.Listener<String>() {


                @Override
                public void onResponse(String response) {
                    Toast.makeText(employer_updateworkmanActivity.this, response, Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    Intent intent = new Intent(employer_updateworkmanActivity.this, WelcomeeActivity.class);
                    startActivity(intent);
                }
            },new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(employer_updateworkmanActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("id", currentProjectId);
                    params.put("mobile", str_email);
                    params.put("project",str_project);
                    params.put("project_add",str_project_add);
                    params.put("sdate",str_sdate);
                    params.put("edate",str_edate);
                    params.put("field",str_field);
                    params.put("tehsil",str_tehsil);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(employer_updateworkmanActivity.this);
            requestQueue.add(request);
        }
    }
/*****************************************************************************************************/
    public void DisplayrWorkmanCivilDetail(String currentEmployerId){
        class BindEmployerMaster extends AsyncTask<Void, Void, String> {
            ProgressDialog pdLoading = new ProgressDialog(employer_updateworkmanActivity.this);

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
                return requestHandler.sendPostRequest(url_add.employer_work_man_civil, params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                pdLoading.dismiss();
                try {
                    JSONObject obj = new JSONObject(s);
                    txtcl.setText(obj.getString("labour"));
                    txtcm.setText(obj.getString("mistree"));
                    txtcs.setText(obj.getString("supply"));
                    txtcj.setText(obj.getString("jcb"));
                    txtcp.setText(obj.getString("paint"));
                    txtcpl.setText(obj.getString("plumber"));
                    txtce.setText(obj.getString("elect"));
                    txtcc.setText(obj.getString("carp"));
                    txtctm.setText(obj.getString("tile"));
                    txtcjm.setText(obj.getString("jaal"));

                } catch (Exception e) {
                }
            }
        }
        BindEmployerMaster obj = new BindEmployerMaster();
        obj.execute();
    }
/*****************************************************************************************************/
}