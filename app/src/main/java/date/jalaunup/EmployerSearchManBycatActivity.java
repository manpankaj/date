package date.jalaunup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import date.jalaunup.Adapter.WorkerAdapterByEmp;
import date.jalaunup.Config.RequestHandler;
import date.jalaunup.Config.SessionManager;
import date.jalaunup.Config.url_add;
import date.jalaunup.Objects.WorkerBycat;

public class EmployerSearchManBycatActivity extends AppCompatActivity {

    SessionManager session;
    List<WorkerBycat> myListData;
    RecyclerView recyclerView;
    String str_username,str_email;
    String currentProjectId,currentProjectField;
    TextView headertext;
    public static final String URL_EMPLOYER_REGISTERED_WORKER_LIST =url_add.employer_search_worker_bycat ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employer_searchworker_bycat);
        session = new SessionManager(getApplicationContext());
        session.checkLogin();
        String roleNew =  session.checkEmployerNew(session);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(EmployerSearchManBycatActivity.this));
        recyclerView.setHasFixedSize(true);
        headertext=findViewById(R.id.head_text);

        myListData = new ArrayList<>();
        HashMap<String, String> user = session.getUserDetails();
        str_username = user.get(SessionManager.KEY_NAME);
        str_email = user.get(SessionManager.KEY_EMAIL);

        Intent intent = getIntent();
        currentProjectId = intent.getStringExtra("ProjectId");
        currentProjectField = intent.getStringExtra("ProjectField");
        headertext.setText("Current Project Field " + currentProjectId +" "+ currentProjectField);

       GetRegisteredWorker();
    }

    public void GetRegisteredWorker()
    {
        class GetRegisteredWorker extends AsyncTask<Void, Void, String>
        {
            ProgressDialog pdLoading = new ProgressDialog(EmployerSearchManBycatActivity.this);

            @Override
            protected void onPreExecute() {
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
                params.put("WorkCat", currentProjectField);
                return requestHandler.sendPostRequest(URL_EMPLOYER_REGISTERED_WORKER_LIST, params);
            }

            @Override
            protected void onPostExecute(String s)
            {
                super.onPostExecute(s);
                pdLoading.dismiss();
                try
                {
                    JSONObject jsonObj = new JSONObject(s);
                    if (jsonObj != null)
                    {
                        JSONArray cmArray = jsonObj.getJSONArray("WorkerList");
                        for (int count = 0; count < cmArray.length(); count++)
                        {
                            JSONObject registeredWorker = cmArray.getJSONObject(count);

                            WorkerBycat tempOBJ = new WorkerBycat();
                            tempOBJ.setWorkerId(registeredWorker.getString("WorkerId"));
                            tempOBJ.setWorkerName(registeredWorker.getString("WorkerName"));
                            tempOBJ.setWorkerMobileNo(registeredWorker.getString("WorkerMobileNo"));
                            tempOBJ.setWorkerSubcat(registeredWorker.getString("WorkerSubcat"));
                            myListData.add(tempOBJ);
                        }
                    }
                    WorkerBycat[] myArrayData = new WorkerBycat[myListData.size()];
                    myArrayData = myListData.toArray(myArrayData);

                    Log.e("Worker List :",myListData.toString());

                    WorkerAdapterByEmp finalAdapter = new WorkerAdapterByEmp(EmployerSearchManBycatActivity.this,myArrayData);
                    recyclerView.setAdapter(finalAdapter);

                }
                catch (Exception e)
                {
                    Log.e("Result",e.getMessage());
                }
            }
        }
        GetRegisteredWorker show = new GetRegisteredWorker();
        show.execute();
    }

    @Override
    public void onBackPressed()
    {
        Intent i = new Intent(EmployerSearchManBycatActivity.this,WelcomeeActivity.class);
       startActivity(i);
      finish();
    }
}