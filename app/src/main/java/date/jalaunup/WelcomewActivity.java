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

public class WelcomewActivity extends AppCompatActivity {
    SessionManager session;
    TextView username,email;
    Button worker_profile,search_employment,change_password,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_w);
        session = new SessionManager(getApplicationContext());
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
        session.checkLogin();
        //session.checkWorker();
        HashMap<String, String> user = session.getUserDetails();
        String str_username = user.get(SessionManager.KEY_NAME);
        String str_email = user.get(SessionManager.KEY_EMAIL);
        String str_role = user.get(SessionManager.KEY_ROLE);
        username.setText("Welcome " + str_username + str_role);
        email.setText("Your Mobile No. " + str_email );

        worker_profile = findViewById(R.id.profileupdate);
        worker_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomewActivity.this, worker_profileActivity.class);
                startActivity(intent);
            }
        });
        search_employment = findViewById(R.id.searchemployment);
        search_employment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomewActivity.this, worker_employmentActivity.class);
                startActivity(intent);
            }
        });
        change_password = findViewById(R.id.changepassword);
        change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomewActivity.this, worker_changePActivity.class);
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
}