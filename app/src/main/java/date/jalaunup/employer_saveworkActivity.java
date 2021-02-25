package date.jalaunup;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class employer_saveworkActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences_emp;
    public static final String MY_PREFERENCES_EMP = "MyPrefsEmp";
    public static final String EMAIL_EMP = "email";
    public static final String USERNAME_EMP = "username";
    EditText ed_project,ed_project_add;
    String str_project,str_project_add,str_mobile,str_sdate,str_edate;
    String url_changeP = "http://10.135.217.19:8080/date/employer_savework.php";
    TextView username,email,sdate,edate;
    Button logout,back,startdate,enddate;
    Calendar start,end;
    DatePickerDialog dstart,dend;
    ArrayList<String> arrayList_tehsil,arrayList_field;
    ArrayAdapter<String> arrayAdapter_tehsil,arrayAdapter_field;
    String str_tehsil,str_field;
    Spinner sp_tehsil,sp_field;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employer_savework);
        sharedPreferences_emp = getSharedPreferences(MY_PREFERENCES_EMP, Context.MODE_PRIVATE);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        username.setText("Welcome " + sharedPreferences_emp.getString(USERNAME_EMP, ""));
        email.setText("Your Mobile No. " + sharedPreferences_emp.getString(EMAIL_EMP, ""));
        ed_project = findViewById(R.id.txtProjectName);
        ed_project_add = findViewById(R.id.txtSideAdd);

        sp_field = (Spinner) findViewById(R.id.ProjectField);
        arrayList_field = new ArrayList<>();
        arrayList_field.add("Civil");
        arrayList_field.add("Electrical");
        arrayList_field.add("Jal Sansaadan");
        arrayList_field.add("Computer");
        arrayList_field.add("Education");
        arrayList_field.add("Official Work");
        arrayAdapter_field = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList_field);
        sp_field.setAdapter(arrayAdapter_field);
        sp_field.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> ProjectField, View view, int position, long id) {
                str_field =  ProjectField.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        sp_tehsil = (Spinner) findViewById(R.id.tehsil);
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

        startdate = findViewById(R.id.txtStartDate);
        sdate = findViewById(R.id.tv_sdate);
        startdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start = Calendar.getInstance();
                start.set(Calendar.YEAR, 2021);
                int day = start.get(Calendar.DAY_OF_MONTH);
                int month = start.get(Calendar.MONTH);
                int year = start.get(Calendar.YEAR);
                dstart = new DatePickerDialog(employer_saveworkActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int nYear, int nMonth, int nDay) {
                        Integer month1 = nMonth +1;
                        sdate.setText(nYear + "-" + month1 + "-" + nDay );
                    }
                }, day, month, year);
                dstart.show();
            }
        });

        enddate = findViewById(R.id.txtEndDate);
        edate = findViewById(R.id.tv_edate);
        enddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                end = Calendar.getInstance();
                end.set(Calendar.YEAR, 2021);
                int day = end.get(Calendar.DAY_OF_MONTH);
                int month = end.get(Calendar.MONTH);
                int year = end.get(Calendar.YEAR);
                dend = new DatePickerDialog(employer_saveworkActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                SharedPreferences.Editor editor = sharedPreferences_emp.edit();
                Intent intent = new Intent(employer_saveworkActivity.this, WelcomeeActivity.class);
                startActivity(intent);
            }
        });
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences_emp.edit();
                editor.clear();
                editor.apply();
                finish();
                Intent intent = new Intent(employer_saveworkActivity.this, logineActivity.class);
                startActivity(intent);
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
            str_mobile = sharedPreferences_emp.getString(EMAIL_EMP, "");
            str_project = ed_project.getText().toString().trim();
            str_project_add = ed_project_add.getText().toString().trim();
            str_sdate=sdate.getText().toString().trim();
            str_edate=edate.getText().toString().trim();
            StringRequest request = new StringRequest(Request.Method.POST, url_changeP, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    SharedPreferences.Editor editor = sharedPreferences_emp.edit();
                    Toast.makeText(employer_saveworkActivity.this, response, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(employer_saveworkActivity.this, WelcomeeActivity.class);
                    startActivity(intent);
                }
            },new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(employer_saveworkActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("mobile",str_mobile);
                    params.put("project",str_project);
                    params.put("project_add",str_project_add);
                    params.put("sdate",str_sdate);
                    params.put("edate",str_edate);
                    params.put("field",str_field);
                    params.put("tehsil",str_tehsil);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(employer_saveworkActivity.this);
            requestQueue.add(request);
        }
    }
}