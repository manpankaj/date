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

import date.jalaunup.Adapter.EmployerWorkAdapter;
import date.jalaunup.Config.RequestHandler;
import date.jalaunup.Config.SessionManager;
import date.jalaunup.Config.url_add;
import date.jalaunup.Objects.Work;

public class EmployerRegisteredWorkActivity extends AppCompatActivity {

    SessionManager session;
    List<Work> myListData;
    RecyclerView recyclerView;
    String str_username,str_email,str_role;
    public static final String URL_EMPLOYER_REGISTERED_WORK_LIST =url_add.employer_registered_work_act ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_registered_work);
        session = new SessionManager(getApplicationContext());
        session.checkLogin();
        String roleNew =  session.checkEmployerNew(session);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(EmployerRegisteredWorkActivity.this));
        myListData = new ArrayList<>();
        HashMap<String, String> user = session.getUserDetails();
        str_username = user.get(SessionManager.KEY_NAME);
        str_email = user.get(SessionManager.KEY_EMAIL);

        GetRegisteredWork();
    }

    public void GetRegisteredWork()
    {
        class GetRegisteredWork extends AsyncTask<Void, Void, String>
        {
            ProgressDialog pdLoading = new ProgressDialog(EmployerRegisteredWorkActivity.this);

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
                params.put("EmployerMobile", str_email);
                return requestHandler.sendPostRequest(URL_EMPLOYER_REGISTERED_WORK_LIST, params);
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
                        JSONArray cmArray = jsonObj.getJSONArray("WorkList");
                        for (int count = 0; count < cmArray.length(); count++)
                        {
                            JSONObject registeredWork = cmArray.getJSONObject(count);

                            Work tempOBJ = new Work();
                            tempOBJ.setProjectId(registeredWork.getString("ProjectId"));
                            tempOBJ.setProjectField(registeredWork.getString("ProjectField"));
                            tempOBJ.setProjectName(registeredWork.getString("ProjectName"));
                            tempOBJ.setProjectAddress(registeredWork.getString("ProjectAddress"));
                            myListData.add(tempOBJ);
                        }
                    }
                    Work[] myArrayData = new Work[myListData.size()];
                    myArrayData = myListData.toArray(myArrayData);

                    Log.e("Work List :",myListData.toString());

                    EmployerWorkAdapter finalAdapter = new EmployerWorkAdapter(EmployerRegisteredWorkActivity.this,myArrayData);
                    recyclerView.setAdapter(finalAdapter);

                }
                catch (Exception e)
                {
                    Log.e("Result",e.getMessage());
                }
            }
        }
        GetRegisteredWork show = new GetRegisteredWork();
        show.execute();
    }

    @Override
    public void onBackPressed()
    {
        Intent i = new Intent(EmployerRegisteredWorkActivity.this,WelcomeeActivity.class);
       startActivity(i);
      finish();
    }
}