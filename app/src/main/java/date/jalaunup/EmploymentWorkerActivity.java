package date.jalaunup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import date.jalaunup.Adapter.EmploymentAdapter;
import date.jalaunup.Adapter.WorkerAdapter;
import date.jalaunup.Config.RequestHandler;
import date.jalaunup.Config.SessionManager;
import date.jalaunup.Config.url_add;
import date.jalaunup.Objects.Employment;
import date.jalaunup.Objects.Worker;

public class EmploymentWorkerActivity extends AppCompatActivity {

    SessionManager session;
    List<Employment> myListData;
    RecyclerView recyclerView;
    String str_category,str_subcategory;
    public static final String URL_WORKER_EMPLOYMENT_LIST =url_add.worker_employment_act ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employment_worker);
        session = new SessionManager(getApplicationContext());
        session.checkLogin();
        String roleNew =  session.checkWorkerNew(session);
        HashMap<String, String> user = session.getUserDetails();
        str_category = user.get(SessionManager.KEY_CATEGORY);
        str_subcategory = user.get(SessionManager.KEY_SUBCATEGORY);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(EmploymentWorkerActivity.this));
        myListData = new ArrayList<>();

        GetWorkerEmployment();
    }

    public void GetWorkerEmployment()
    {
        class GetWorkerEmployment extends AsyncTask<Void, Void, String>
        {
            ProgressDialog pdLoading = new ProgressDialog(EmploymentWorkerActivity.this);

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
                params.put("category",str_category);
                params.put("subcategory",str_subcategory);
                return requestHandler.sendPostRequest(URL_WORKER_EMPLOYMENT_LIST, params);
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
                        JSONArray cmArray = jsonObj.getJSONArray("EmploymentList");
                        for (int count = 0; count < cmArray.length(); count++)
                        {
                            JSONObject currentEmployment = cmArray.getJSONObject(count);

                            Employment tempOBJ = new Employment();
                            tempOBJ.setEmployerId(currentEmployment.getString("EmployerId"));
                            tempOBJ.setEmployerName(currentEmployment.getString("EmployerName"));
                            tempOBJ.setEmployerMobileNo(currentEmployment.getString("EmployerMobileNo"));
                            tempOBJ.setEmployerAddress(currentEmployment.getString("EmployerAddress"));
                            myListData.add(tempOBJ);
                        }
                    }
                    Employment[] myArrayData = new Employment[myListData.size()];
                    myArrayData = myListData.toArray(myArrayData);

                    Log.e("Employment List :",myListData.toString());

                    EmploymentAdapter finalAdapter = new EmploymentAdapter(EmploymentWorkerActivity.this,myArrayData);
                    recyclerView.setAdapter(finalAdapter);

                }
                catch (Exception e)
                {
                    Log.e("Result",e.getMessage());
                }
            }
        }
        GetWorkerEmployment show = new GetWorkerEmployment();
        show.execute();
    }

    @Override
    public void onBackPressed()
    {
        Intent i = new Intent(EmploymentWorkerActivity.this,WelcomewActivity.class);
       startActivity(i);
      finish();
    }
}