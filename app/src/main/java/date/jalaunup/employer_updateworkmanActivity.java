package date.jalaunup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputFilter;
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
import date.jalaunup.Config.integerMinMax;
import date.jalaunup.Config.url_add;

public class employer_updateworkmanActivity extends AppCompatActivity {
    SessionManager session;
    String str_email,str_username;
    TextView username,email;
    Button logout,back;
    EditText edcl,edcm,edcs,edcj,edcp,edcpl,edce,edcc,edctm,edcjm,edel,edele,edes,edil,edim,edij,edis,edcdeo,edcpr,edcne,edche;
    EditText edces,edcem,edept,edejt,edehst,edest,edegt,edepgt,edoc,edom,edop,edor;
    TextView txtcl,txtcm,txtcs,txtcj,txtcp,txtcpl,txtce,txtcc,txtctm,txtcjm,txtel,txtele,txtes,txtil,txtim,txtij,txtis;
    TextView txtcdeo,txtcpr,txtcne,txtche,txtces,txtcem,txtept,txtejt,txtehst,txtest,txtegt,txtepgt,txtoc,txtom,txtop,txtor;
    String currentProjectId,currentField,currentProject;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        currentProjectId = intent.getStringExtra("ProjectId");
        currentField = intent.getStringExtra("ProjectField");
        currentProject = intent.getStringExtra("ProjectName");
        /*arrayList_field.add("Official Work");*/


   /***************************************************************************/
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
            edcl.setFilters(new InputFilter[]{new integerMinMax.InputFilterMinMax("1", "999")});
            edcm.setFilters(new InputFilter[]{new integerMinMax.InputFilterMinMax("1", "999")});
            edcs.setFilters(new InputFilter[]{new integerMinMax.InputFilterMinMax("1", "999")});
            edcj.setFilters(new InputFilter[]{new integerMinMax.InputFilterMinMax("1", "999")});
            edcp.setFilters(new InputFilter[]{new integerMinMax.InputFilterMinMax("1", "999")});
            edcpl.setFilters(new InputFilter[]{new integerMinMax.InputFilterMinMax("1", "999")});
            edce.setFilters(new InputFilter[]{new integerMinMax.InputFilterMinMax("1", "999")});
            edcc.setFilters(new InputFilter[]{new integerMinMax.InputFilterMinMax("1", "999")});
            edctm.setFilters(new InputFilter[]{new integerMinMax.InputFilterMinMax("1", "999")});
            edcjm.setFilters(new InputFilter[]{new integerMinMax.InputFilterMinMax("1", "999")});
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
/******************************************************************************************/
        if(currentField.equals("Electrical")){
            setContentView(R.layout.employer_manwork_electrical);

            edel = findViewById(R.id.edel);
            edele = findViewById(R.id.edele);
            edes = findViewById(R.id.edes);
            txtel = findViewById(R.id.txtel);
            txtele = findViewById(R.id.txtele);
            txtes = findViewById(R.id.txtes);
            edel.setFilters(new InputFilter[]{new integerMinMax.InputFilterMinMax("1", "999")});
            edele.setFilters(new InputFilter[]{new integerMinMax.InputFilterMinMax("1", "999")});
            edes.setFilters(new InputFilter[]{new integerMinMax.InputFilterMinMax("1", "999")});
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
            DisplayrWorkmanElectricalDetail(currentProjectId);

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
        /****************************************************************************************************/
        if(currentField.equals("Jal Sansaadan")){
            setContentView(R.layout.employer_manwork_irrigation);

            edil = findViewById(R.id.edil);
            edim = findViewById(R.id.edim);
            edij = findViewById(R.id.edij);
            edis = findViewById(R.id.edis);
            txtil = findViewById(R.id.txtil);
            txtim = findViewById(R.id.txtim);
            txtij = findViewById(R.id.txtij);
            txtis = findViewById(R.id.txtis);
            edil.setFilters(new InputFilter[]{new integerMinMax.InputFilterMinMax("1", "999")});
            edim.setFilters(new InputFilter[]{new integerMinMax.InputFilterMinMax("1", "999")});
            edij.setFilters(new InputFilter[]{new integerMinMax.InputFilterMinMax("1", "999")});
            edis.setFilters(new InputFilter[]{new integerMinMax.InputFilterMinMax("1", "999")});
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
            DisplayrWorkmanIrrigationDetail(currentProjectId);

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
  /****************************************************************************************************/
        if(currentField.equals("Computer")){
            setContentView(R.layout.employer_manwork_computer);

            edcdeo = findViewById(R.id.edcdeo);
            edcpr = findViewById(R.id.edcpr);
            edcne = findViewById(R.id.edcne);
            edche = findViewById(R.id.edche);
            edces = findViewById(R.id.edces);
            edcem = findViewById(R.id.edcem);
            txtcdeo = findViewById(R.id.txtcdeo);
            txtcpr = findViewById(R.id.txtcpr);
            txtcne = findViewById(R.id.txtcne);
            txtche = findViewById(R.id.txtche);
            txtces = findViewById(R.id.txtces);
            txtcem = findViewById(R.id.txtcem);
            edcdeo.setFilters(new InputFilter[]{new integerMinMax.InputFilterMinMax("1", "999")});
            edcpr.setFilters(new InputFilter[]{new integerMinMax.InputFilterMinMax("1", "999")});
            edcne.setFilters(new InputFilter[]{new integerMinMax.InputFilterMinMax("1", "999")});
            edche.setFilters(new InputFilter[]{new integerMinMax.InputFilterMinMax("1", "999")});
            edces.setFilters(new InputFilter[]{new integerMinMax.InputFilterMinMax("1", "999")});
            edcem.setFilters(new InputFilter[]{new integerMinMax.InputFilterMinMax("1", "999")});
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
            DisplayrWorkmanComputerDetail(currentProjectId);

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
        /****************************************************************************************************/
        if(currentField.equals("Education")){
            setContentView(R.layout.employer_manwork_education);

            edept = findViewById(R.id.edept);
            edejt = findViewById(R.id.edejt);
            edehst = findViewById(R.id.edehst);
            edest = findViewById(R.id.edest);
            edegt = findViewById(R.id.edegt);
            edepgt = findViewById(R.id.edepgt);
            txtept = findViewById(R.id.txtept);
            txtejt = findViewById(R.id.txtejt);
            txtehst = findViewById(R.id.txtehst);
            txtest = findViewById(R.id.txtest);
            txtegt = findViewById(R.id.txtegt);
            txtepgt = findViewById(R.id.txtepgt);
            edept.setFilters(new InputFilter[]{new integerMinMax.InputFilterMinMax("1", "999")});
            edejt.setFilters(new InputFilter[]{new integerMinMax.InputFilterMinMax("1", "999")});
            edehst.setFilters(new InputFilter[]{new integerMinMax.InputFilterMinMax("1", "999")});
            edest.setFilters(new InputFilter[]{new integerMinMax.InputFilterMinMax("1", "999")});
            edegt.setFilters(new InputFilter[]{new integerMinMax.InputFilterMinMax("1", "999")});
            edepgt.setFilters(new InputFilter[]{new integerMinMax.InputFilterMinMax("1", "999")});
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
            DisplayrWorkmanEducationDetail(currentProjectId);

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
        /****************************************************************************************************/
        if(currentField.equals("Official Work")){
            setContentView(R.layout.employer_manwork_official);

            edoc = findViewById(R.id.edoc);
            edom = findViewById(R.id.edom);
            edop = findViewById(R.id.edop);
            edor = findViewById(R.id.edor);
            txtoc = findViewById(R.id.txtoc);
            txtom = findViewById(R.id.txtom);
            txtop = findViewById(R.id.txtop);
            txtor = findViewById(R.id.txtor);
            edoc.setFilters(new InputFilter[]{new integerMinMax.InputFilterMinMax("1", "999")});
            edom.setFilters(new InputFilter[]{new integerMinMax.InputFilterMinMax("1", "999")});
            edop.setFilters(new InputFilter[]{new integerMinMax.InputFilterMinMax("1", "999")});
            edor.setFilters(new InputFilter[]{new integerMinMax.InputFilterMinMax("1", "999")});
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
            DisplayrWorkmanOfficialDetail(currentProjectId);

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
        /****************************************************************************************************/
    }
  /****************************************************************************************************/
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
    /****************************************************************************************************/
    public void DisplayrWorkmanElectricalDetail(String currentEmployerId){
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
                return requestHandler.sendPostRequest(url_add.employer_work_man_electrical, params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                pdLoading.dismiss();
                try {
                    JSONObject obj = new JSONObject(s);
                    txtel.setText(obj.getString("labour"));
                    txtele.setText(obj.getString("mistree"));
                    txtes.setText(obj.getString("supply"));

                } catch (Exception e) {
                }
            }
        }
        BindEmployerMaster obj = new BindEmployerMaster();
        obj.execute();
    }
    /*****************************************************************************************************/
    public void DisplayrWorkmanIrrigationDetail(String currentEmployerId){
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
                return requestHandler.sendPostRequest(url_add.employer_work_man_irrigation, params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                pdLoading.dismiss();
                try {
                    JSONObject obj = new JSONObject(s);
                    txtil.setText(obj.getString("labour"));
                    txtim.setText(obj.getString("mistree"));
                    txtij.setText(obj.getString("jcb"));
                    txtis.setText(obj.getString("supply"));

                } catch (Exception e) {
                }
            }
        }
        BindEmployerMaster obj = new BindEmployerMaster();
        obj.execute();
    }
    /*****************************************************************************************************/
    public void DisplayrWorkmanComputerDetail(String currentEmployerId){
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
                return requestHandler.sendPostRequest(url_add.employer_work_man_computer, params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                pdLoading.dismiss();
                try {
                    JSONObject obj = new JSONObject(s);
                    txtcdeo.setText(obj.getString("deo"));
                    txtcpr.setText(obj.getString("prog"));
                    txtcne.setText(obj.getString("cne"));
                    txtche.setText(obj.getString("che"));
                    txtces.setText(obj.getString("ces"));
                    txtcem.setText(obj.getString("cem"));

                } catch (Exception e) {
                }
            }
        }
        BindEmployerMaster obj = new BindEmployerMaster();
        obj.execute();
    }
    /*****************************************************************************************************/
    public void DisplayrWorkmanEducationDetail(String currentEmployerId){
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
                return requestHandler.sendPostRequest(url_add.employer_work_man_education, params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                pdLoading.dismiss();
                try {
                    JSONObject obj = new JSONObject(s);
                    txtept.setText(obj.getString("ept"));
                    txtejt.setText(obj.getString("ejt"));
                    txtehst.setText(obj.getString("ehst"));
                    txtest.setText(obj.getString("est"));
                    txtegt.setText(obj.getString("egt"));
                    txtepgt.setText(obj.getString("epgt"));

                } catch (Exception e) {
                }
            }
        }
        BindEmployerMaster obj = new BindEmployerMaster();
        obj.execute();
    }
    /*****************************************************************************************************/
    public void DisplayrWorkmanOfficialDetail(String currentEmployerId){
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
                return requestHandler.sendPostRequest(url_add.employer_work_man_official, params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                pdLoading.dismiss();
                try {
                    JSONObject obj = new JSONObject(s);
                    txtoc.setText(obj.getString("oc"));
                    txtom.setText(obj.getString("om"));
                    txtop.setText(obj.getString("op"));
                    txtor.setText(obj.getString("or"));

                } catch (Exception e) {
                }
            }
        }
        BindEmployerMaster obj = new BindEmployerMaster();
        obj.execute();
    }
    /*****************************************************************************************************/
    public void saveCivilMan(View v) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");
            progressDialog.show();
        String str_edcl = edcl.getText().toString().trim();
        String str_edcm = edcm.getText().toString().trim();
        String str_edcs = edcs.getText().toString().trim();
        String str_edcj = edcj.getText().toString().trim();
        String str_edcp = edcp.getText().toString().trim();
        String str_edcpl = edcpl.getText().toString().trim();
        String str_edce = edce.getText().toString().trim();
        String str_edcc = edcc.getText().toString().trim();
        String str_edctm = edctm.getText().toString().trim();
        String str_edcjm = edcjm.getText().toString().trim();
        StringRequest request = new StringRequest(Request.Method.POST, url_add.update_work_man_civil, new Response.Listener<String>() {

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
                    params.put("edcl",str_edcl);
                    params.put("edcm",str_edcm);
                    params.put("edcs",str_edcs);
                    params.put("edcj",str_edcj);
                    params.put("edcp",str_edcp);
                    params.put("edcpl",str_edcpl);
                    params.put("edce",str_edce);
                    params.put("edcc",str_edcc);
                    params.put("edctm",str_edctm);
                    params.put("edcjm",str_edcjm);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(employer_updateworkmanActivity.this);
            requestQueue.add(request);
        }
/*****************************************************************************************************/
public void saveElectricalMan(View v) {
    final ProgressDialog progressDialog = new ProgressDialog(this);
    progressDialog.setMessage("Please Wait..");
    progressDialog.show();
    String str_edel = edel.getText().toString().trim();
    String str_edele = edele.getText().toString().trim();
    String str_edes = edes.getText().toString().trim();
    StringRequest request = new StringRequest(Request.Method.POST, url_add.update_work_man_electrical, new Response.Listener<String>() {

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
            params.put("edcl",str_edel);
            params.put("edcm",str_edele);
            params.put("edcs",str_edes);
            return params;
        }
    };
    RequestQueue requestQueue = Volley.newRequestQueue(employer_updateworkmanActivity.this);
    requestQueue.add(request);
}
/*****************************************************************************************************/
public void saveIrrigationMan(View v) {
    final ProgressDialog progressDialog = new ProgressDialog(this);
    progressDialog.setMessage("Please Wait..");
    progressDialog.show();
    String str_edil = edil.getText().toString().trim();
    String str_edim = edim.getText().toString().trim();
    String str_edij = edij.getText().toString().trim();
    String str_edis = edis.getText().toString().trim();
    StringRequest request = new StringRequest(Request.Method.POST, url_add.update_work_man_irrigation, new Response.Listener<String>() {

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
            params.put("edcl",str_edil);
            params.put("edcm",str_edim);
            params.put("edcs",str_edij);
            params.put("edcs",str_edis);
            return params;
        }
    };
    RequestQueue requestQueue = Volley.newRequestQueue(employer_updateworkmanActivity.this);
    requestQueue.add(request);
}
/*****************************************************************************************************/
public void saveComputerMan(View v) {
    final ProgressDialog progressDialog = new ProgressDialog(this);
    progressDialog.setMessage("Please Wait..");
    progressDialog.show();
    String str_edcdeo = edcdeo.getText().toString().trim();
    String str_edcpr = edcpr.getText().toString().trim();
    String str_edcne = edcne.getText().toString().trim();
    String str_edche = edche.getText().toString().trim();
    String str_edces = edces.getText().toString().trim();
    String str_edcem = edcem.getText().toString().trim();
    StringRequest request = new StringRequest(Request.Method.POST, url_add.update_work_man_computer, new Response.Listener<String>() {

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
            params.put("edcdeo",str_edcdeo);
            params.put("edcpr",str_edcpr);
            params.put("edcne",str_edcne);
            params.put("edche",str_edche);
            params.put("edces",str_edces);
            params.put("edcem",str_edcem);
            return params;
        }
    };
    RequestQueue requestQueue = Volley.newRequestQueue(employer_updateworkmanActivity.this);
    requestQueue.add(request);
}
/*****************************************************************************************************/
public void saveEducationMan(View v) {
    final ProgressDialog progressDialog = new ProgressDialog(this);
    progressDialog.setMessage("Please Wait..");
    progressDialog.show();
    String str_edept = edept.getText().toString().trim();
    String str_edejt = edejt.getText().toString().trim();
    String str_edehst = edehst.getText().toString().trim();
    String str_edest = edest.getText().toString().trim();
    String str_edegt = edegt.getText().toString().trim();
    String str_edepgt = edepgt.getText().toString().trim();
    StringRequest request = new StringRequest(Request.Method.POST, url_add.update_work_man_education, new Response.Listener<String>() {

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
            params.put("edept",str_edept);
            params.put("edejt",str_edejt);
            params.put("edehst",str_edehst);
            params.put("edest",str_edest);
            params.put("edegt",str_edegt);
            params.put("edepgt",str_edepgt);
            return params;
        }
    };
    RequestQueue requestQueue = Volley.newRequestQueue(employer_updateworkmanActivity.this);
    requestQueue.add(request);
}
/*****************************************************************************************************/
public void saveOfficialMan(View v) {
    final ProgressDialog progressDialog = new ProgressDialog(this);
    progressDialog.setMessage("Please Wait..");
    progressDialog.show();
    String str_edoc = edoc.getText().toString().trim();
    String str_edom = edom.getText().toString().trim();
    String str_edop = edop.getText().toString().trim();
    String str_edor = edor.getText().toString().trim();
    StringRequest request = new StringRequest(Request.Method.POST, url_add.update_work_man_official, new Response.Listener<String>() {

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
            params.put("edoc",str_edoc);
            params.put("edom",str_edom);
            params.put("edop",str_edop);
            params.put("edor",str_edor);
            return params;
        }
    };
    RequestQueue requestQueue = Volley.newRequestQueue(employer_updateworkmanActivity.this);
    requestQueue.add(request);
}
/*****************************************************************************************************/

}