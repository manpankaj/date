package date.jalaunup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;
import date.jalaunup.Config.SessionManager;

public class WelcomeeActivity extends AppCompatActivity {
    SessionManager session;
    TextView username,email;
    Button save_work,update_work,save_manpower,update_manpower,search_manpower,change_password,logout;
    String str_username,str_email,str_role;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_e);
        session = new SessionManager(getApplicationContext());
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        session.checkLogin();
        String roleNew =  session.checkEmployerNew(session);
        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
        HashMap<String, String> user = session.getUserDetails();
        str_username = user.get(SessionManager.KEY_NAME);
        str_email = user.get(SessionManager.KEY_EMAIL);
        username.setText("Welcome " + str_username);
        email.setText("Your Mobile No. " + str_email );


        save_work = findViewById(R.id.saveWork);
        save_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeeActivity.this, employer_saveworkActivity.class);
                startActivity(intent);
            }
        });
        update_work = findViewById(R.id.Update_Projectwork);
        update_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeeActivity.this, employer_profileActivity.class);
                startActivity(intent);
            }
        });
        save_manpower= findViewById(R.id.saveManpowerReq);
        save_manpower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeeActivity.this, employer_profileActivity.class);
                startActivity(intent);
            }
        });
        update_manpower= findViewById(R.id.updateManpowerReq);
        update_manpower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeeActivity.this, employer_profileActivity.class);
                startActivity(intent);
            }
        });
        search_manpower= findViewById(R.id.searchWorker);
        search_manpower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeeActivity.this, employer_profileActivity.class);
                startActivity(intent);
            }
        });
        change_password = findViewById(R.id.changePassword);
        change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeeActivity.this, employer_changePActivity.class);
                startActivity(intent);
            }
        });
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  session.logoutUser(); }
        });
    }
}