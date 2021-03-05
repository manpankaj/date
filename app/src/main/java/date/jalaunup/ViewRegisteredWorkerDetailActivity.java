package date.jalaunup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import date.jalaunup.Config.SessionManager;

public class ViewRegisteredWorkerDetailActivity extends AppCompatActivity {
    SessionManager session;
    String currentWorkerId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_registered_worker_detail);
        session = new SessionManager(getApplicationContext());
        session.checkLogin();
        String roleNew =  session.checkAdminNew(session);

        Intent intent = getIntent();
        currentWorkerId = intent.getStringExtra("WorkerId");

        Toast.makeText(ViewRegisteredWorkerDetailActivity.this,"Worker Id : "+ currentWorkerId, Toast.LENGTH_LONG).show();
    }
}