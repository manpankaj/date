package date.jalaunup;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import date.jalaunup.Config.RequestHandler;
import date.jalaunup.Config.SessionManager;
import date.jalaunup.Config.url_add;

public class employer_updateworkActivity extends AppCompatActivity {
    SessionManager session;
    EditText ed_project,ed_project_add;
    String str_project,str_project_add,str_sdate,str_edate,str_email,str_username;
    TextView username,email,sdate,edate;
    Button logout,back,startdate,enddate;
    Calendar start,end;
    DatePickerDialog dstart,dend;
    ArrayList<String> arrayList_tehsil;
    ArrayAdapter<String> arrayAdapter_tehsil;
    String str_tehsil;
    TextView tehsil,field,id;
    Spinner sp_tehsil;
    String currentProjectId;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employer_updatework);
        session = new SessionManager(getApplicationContext());
        session.checkLogin();
        String roleNew =  session.checkEmployerNew(session);
        HashMap<String, String> user = session.getUserDetails();
        str_username = user.get(SessionManager.KEY_NAME);
        str_email = user.get(SessionManager.KEY_EMAIL);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        username.setText("Welcome " +  str_username);
        email.setText("Your Mobile No. " + str_email);
        id = findViewById(R.id.ProjectId);
        ed_project = findViewById(R.id.txtProjectName);
        ed_project_add = findViewById(R.id.txtSideAdd);
        field =  findViewById(R.id.ProjectField);
        tehsil = findViewById(R.id.tehsil);
        startdate = findViewById(R.id.txtStartDate);
        sdate = findViewById(R.id.tv_sdate);
        enddate = findViewById(R.id.txtEndDate);
        edate = findViewById(R.id.tv_edate);
        sp_tehsil = (Spinner) findViewById(R.id.tehsilSP);

        Intent intent = getIntent();
        currentProjectId = intent.getStringExtra("ProjectId");
        DisplayEmployerWorkDetail(currentProjectId);

        arrayList_tehsil = new ArrayList<>();
        arrayList_tehsil.add("Jalaun");
        arrayList_tehsil.add("Orai");
        arrayList_tehsil.add("Kalpi");
        arrayList_tehsil.add("Konch");
        arrayList_tehsil.add("Madhogarh");
        arrayAdapter_tehsil = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList_tehsil);
        sp_tehsil.setAdapter(arrayAdapter_tehsil);
        sp_tehsil.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> tehsil, View view, int position, long id) {
                str_tehsil =  tehsil.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> tehsil) {
            }
        });

        startdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start = Calendar.getInstance();
                start.set(Calendar.YEAR, 2021);
                int day = start.get(Calendar.DAY_OF_MONTH);
                int month = start.get(Calendar.MONTH);
                int year = start.get(Calendar.YEAR);
                dstart = new DatePickerDialog(employer_updateworkActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int nYear, int nMonth, int nDay) {
                        Integer month1 = nMonth +1;
                        sdate.setText(nYear + "-" + month1 + "-" + nDay );
                    }
                }, day, month, year);
                dstart.show();
            }
        });


        enddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                end = Calendar.getInstance();
                end.set(Calendar.YEAR, 2021);
                int day = end.get(Calendar.DAY_OF_MONTH);
                int month = end.get(Calendar.MONTH);
                int year = end.get(Calendar.YEAR);
                dend = new DatePickerDialog(employer_updateworkActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int nYear, int nMonth, int nDay) {
                        Integer month1 = nMonth +1;
                        edate.setText(nYear + "-" + month1 + "-" + nDay );
                    }
                }, day, month, year);
                dend.show();
            }
        });

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(employer_updateworkActivity.this, WelcomeeActivity.class);
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
                    Toast.makeText(employer_updateworkActivity.this, response, Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    Intent intent = new Intent(employer_updateworkActivity.this, WelcomeeActivity.class);
                    startActivity(intent);
                }
            },new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(employer_updateworkActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
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
                    params.put("tehsil",str_tehsil);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(employer_updateworkActivity.this);
            requestQueue.add(request);
        }
    }
/****************************************************************************************************/
    public void DisplayEmployerWorkDetail(String currentEmployerId)
    {
        class BindEmployerMaster extends AsyncTask<Void, Void, String> {
            ProgressDialog pdLoading = new ProgressDialog(employer_updateworkActivity.this);

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
                return requestHandler.sendPostRequest(url_add.employer_work_detail_by_id, params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                pdLoading.dismiss();
                try {
                    JSONObject obj = new JSONObject(s);
                    id.setText(obj.getString("ProjectId"));
                    field.setText(obj.getString("ProjectField"));
                    tehsil.setText(obj.getString("Tehsil"));
                    sdate.setText(obj.getString("ProjectStart"));
                    edate.setText(obj.getString("ProjectEnd"));
                    ed_project.setText(obj.getString("ProjectName"));
                    ed_project_add.setText(obj.getString("ProjectAddress"));

                } catch (Exception e) {
                }
            }
        }
        BindEmployerMaster obj = new BindEmployerMaster();
        obj.execute();
    }
    /************************************************************************************************/
}