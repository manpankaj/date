package date.jalaunup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import date.jalaunup.Adapter.EmployerAdapter;
import date.jalaunup.Config.RequestHandler;
import date.jalaunup.Config.SessionManager;
import date.jalaunup.Config.url_add;
import date.jalaunup.Objects.Employer;

public class RegisteredEmployerActivity extends AppCompatActivity {

    SessionManager session;
    List<Employer> myListData;
    RecyclerView recyclerView;
    public static final String URL_PENDING_Employer_LIST =url_add.pending_employer_act ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_employer);
        session = new SessionManager(getApplicationContext());
        session.checkLogin();
        String roleNew =  session.checkAdminNew(session);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(RegisteredEmployerActivity.this));
        myListData = new ArrayList<>();

        GetPendingEmployer();
    }

    public void GetPendingEmployer()
    {
        class GetPendingEmployerList extends AsyncTask<Void, Void, String>
        {
            ProgressDialog pdLoading = new ProgressDialog(RegisteredEmployerActivity.this);

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
                return requestHandler.sendPostRequest(URL_PENDING_Employer_LIST, params);
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
                        JSONArray cmArray = jsonObj.getJSONArray("EmployerList");
                        for (int count = 0; count < cmArray.length(); count++)
                        {
                            JSONObject currentEmployer = cmArray.getJSONObject(count);

                            Employer tempOBJ = new Employer();
                            tempOBJ.setEmployerId(currentEmployer.getString("EmployerId"));
                            tempOBJ.setEmployerName(currentEmployer.getString("EmployerName"));
                            tempOBJ.setEmployerMobileNo(currentEmployer.getString("EmployerMobileNo"));
                            tempOBJ.setEmployerAddress(currentEmployer.getString("EmployerAddress"));
                            myListData.add(tempOBJ);
                        }
                    }
                    Employer[] myArrayData = new Employer[myListData.size()];
                    myArrayData = myListData.toArray(myArrayData);

                    Log.e("Employer List :",myListData.toString());

                    EmployerAdapter finalAdapter = new EmployerAdapter(RegisteredEmployerActivity.this,myArrayData);
                    recyclerView.setAdapter(finalAdapter);

                }
                catch (Exception e)
                {
                    Log.e("Result",e.getMessage());
                }
            }
        }
        GetPendingEmployerList show = new GetPendingEmployerList();
        show.execute();
    }

    @Override
    public void onBackPressed()
    {
        Intent i = new Intent(RegisteredEmployerActivity.this,WelcomeaActivity.class);
        startActivity(i);
        finish();
    }
}