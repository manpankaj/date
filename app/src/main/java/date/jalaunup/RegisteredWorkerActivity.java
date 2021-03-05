package date.jalaunup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import date.jalaunup.Adapter.WorkerAdapter;
import date.jalaunup.Objects.Worker;
import date.jalaunup.Config.RequestHandler;
import date.jalaunup.Config.SessionManager;
import date.jalaunup.Config.url_add;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RegisteredWorkerActivity extends AppCompatActivity {

    SessionManager session;
    String userId , roleId="a", areaId;

    List<Worker> myListData;
    RecyclerView recyclerView;

    public static final String URL_PENDING_WORKER_LIST =url_add.pending_worker_act ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_worker);
        session = new SessionManager(getApplicationContext());
        session.checkLogin();
        String roleNew =  session.checkAdminNew(session);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(RegisteredWorkerActivity.this));

        myListData = new ArrayList<>();

        GetPendingComplain(areaId);
    }

    public void GetPendingComplain(String areaId)
    {
        class GetPendingComplainList extends AsyncTask<Void, Void, String>
        {
            ProgressDialog pdLoading = new ProgressDialog(RegisteredWorkerActivity.this);

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
                params.put("id", "1");
                return requestHandler.sendPostRequest(URL_PENDING_WORKER_LIST, params);
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
                            JSONObject currentComplain = cmArray.getJSONObject(count);

                            Worker tempOBJ = new Worker();
                            tempOBJ.setWorkerId(currentComplain.getString("WorkerId"));
                            tempOBJ.setWorkerName(currentComplain.getString("WorkerName"));
                            tempOBJ.setWorkerEmail(currentComplain.getString("WorkerEmail"));
                            tempOBJ.setWorkerMobileNo(currentComplain.getString("WorkerMobileNo"));
                            myListData.add(tempOBJ);
                        }
                    }
                    Worker[] myArrayData = new Worker[myListData.size()];
                    myArrayData = myListData.toArray(myArrayData);

                    Log.e("Worker List :",myListData.toString());

                    WorkerAdapter finalAdapter = new WorkerAdapter(RegisteredWorkerActivity.this,myArrayData,"PENDING",roleId);
                    recyclerView.setAdapter(finalAdapter);

                }
                catch (Exception e)
                {
                    Log.e("Result",e.getMessage());
                }
            }
        }
        GetPendingComplainList show = new GetPendingComplainList();
        show.execute();
    }

    @Override
    public void onBackPressed()
    {
       // Intent i = new Intent(RegisteredWorkerActivity.this,.class);
       // startActivity(i);
        //finish();
    }
}