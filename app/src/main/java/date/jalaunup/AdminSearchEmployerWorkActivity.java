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

import date.jalaunup.Adapter.AdminSearchEmployerWorkAdapter;
import date.jalaunup.Adapter.EmployerSaveWorkAdapter;
import date.jalaunup.Config.RequestHandler;
import date.jalaunup.Config.SessionManager;
import date.jalaunup.Config.url_add;
import date.jalaunup.Objects.AdminSearchEmployerWork;
import date.jalaunup.Objects.SearchWorkMan;

public class AdminSearchEmployerWorkActivity extends AppCompatActivity {

    SessionManager session;
    List<AdminSearchEmployerWork> myListData;
    RecyclerView recyclerView;
    String str_username,str_email;
    public static final String URL_ADMIN_EMPLOYER_WORK_LIST =url_add.admin_employer_registered_work_list ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_search_employer_work);
        session = new SessionManager(getApplicationContext());
        session.checkLogin();
        String roleNew =  session.checkAdminNew(session);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(AdminSearchEmployerWorkActivity.this));
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
            ProgressDialog pdLoading = new ProgressDialog(AdminSearchEmployerWorkActivity.this);

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
                return requestHandler.sendPostRequest(URL_ADMIN_EMPLOYER_WORK_LIST, params);
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

                            AdminSearchEmployerWork tempOBJ = new AdminSearchEmployerWork();
                            tempOBJ.setProjectId(registeredWork.getString("ProjectId"));
                            tempOBJ.setProjectField(registeredWork.getString("ProjectField"));
                            tempOBJ.setProjectName(registeredWork.getString("ProjectName"));
                            tempOBJ.setProjectAddress(registeredWork.getString("ProjectAddress"));
                            myListData.add(tempOBJ);
                        }
                    }
                    AdminSearchEmployerWork[] myArrayData = new AdminSearchEmployerWork[myListData.size()];
                    myArrayData = myListData.toArray(myArrayData);

                    Log.e("Work List :",myListData.toString());

                    AdminSearchEmployerWorkAdapter finalAdapter = new AdminSearchEmployerWorkAdapter(AdminSearchEmployerWorkActivity.this,myArrayData);
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
        Intent i = new Intent(AdminSearchEmployerWorkActivity.this,WelcomeeActivity.class);
       startActivity(i);
      finish();
    }
}