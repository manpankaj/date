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
import date.jalaunup.Adapter.WorkerAdapter;
import date.jalaunup.Config.RequestHandler;
import date.jalaunup.Config.SessionManager;
import date.jalaunup.Config.url_add;
import date.jalaunup.Objects.Worker;

public class RegisteredWorkerActivity extends AppCompatActivity {

    SessionManager session;
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

        GetPendingWorker();
    }

    public void GetPendingWorker()
    {
        class GetPendingWorkerList extends AsyncTask<Void, Void, String>
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
                            JSONObject currentWorker = cmArray.getJSONObject(count);

                            Worker tempOBJ = new Worker();
                            tempOBJ.setWorkerId(currentWorker.getString("WorkerId"));
                            tempOBJ.setWorkerName(currentWorker.getString("WorkerName"));
                            tempOBJ.setWorkerMobileNo(currentWorker.getString("WorkerMobileNo"));
                            tempOBJ.setWorkerEmail(currentWorker.getString("WorkerEmail"));
                            tempOBJ.setworkerAge(currentWorker.getString("WorkerAge"));
                            tempOBJ.setWorkerSex(currentWorker.getString("WorkerSex"));
                            tempOBJ.setWorkerField(currentWorker.getString("WorkerField"));
                            tempOBJ.setWorkerWork(currentWorker.getString("WorkerWork"));
                            tempOBJ.setWorkerExp(currentWorker.getString("WorkerExp"));
                            myListData.add(tempOBJ);
                        }
                    }
                    Worker[] myArrayData = new Worker[myListData.size()];
                    myArrayData = myListData.toArray(myArrayData);

                    Log.e("Worker List :",myListData.toString());

                    WorkerAdapter finalAdapter = new WorkerAdapter(RegisteredWorkerActivity.this,myArrayData);
                    recyclerView.setAdapter(finalAdapter);

                }
                catch (Exception e)
                {
                    Log.e("Result",e.getMessage());
                }
            }
        }
        GetPendingWorkerList show = new GetPendingWorkerList();
        show.execute();
    }

    @Override
    public void onBackPressed()
    {
        Intent i = new Intent(RegisteredWorkerActivity.this,WelcomeaActivity.class);
       startActivity(i);
      finish();
    }
}