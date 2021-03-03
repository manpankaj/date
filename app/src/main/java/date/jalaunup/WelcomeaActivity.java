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

public class WelcomeaActivity extends AppCompatActivity {
    SessionManager session;
    TextView username,email;
    Button active_Worker,active_Employer,approve_EW,reset_WP,reset_EP,change_P,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_a);
        session = new SessionManager(getApplicationContext());
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
        session.checkLogin();
        //session.checkAdmin();
        HashMap<String, String> user = session.getUserDetails();
        String str_username = user.get(SessionManager.KEY_NAME);
        String str_email = user.get(SessionManager.KEY_EMAIL);
        String str_role = user.get(SessionManager.KEY_ROLE);
        username.setText("Welcome " + str_username + str_role);
        email.setText("Your Mobile No. " + str_email );

        active_Worker = findViewById(R.id.activeWorker);
        active_Worker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeaActivity.this, admin_profileActivity.class);
                startActivity(intent);
            }
        });
        active_Employer= findViewById(R.id.activeEmployer);
        active_Employer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeaActivity.this, admin_profileActivity.class);
                startActivity(intent);
            }
        });
        approve_EW= findViewById(R.id.approveEW);
        approve_EW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeaActivity.this, admin_profileActivity.class);
                startActivity(intent);
            }
        });
        reset_WP= findViewById(R.id.resetWP);
        reset_WP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeaActivity.this, admin_profileActivity.class);
                startActivity(intent);
            }
        });
        reset_EP= findViewById(R.id.resetEP);
        reset_EP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeaActivity.this, admin_profileActivity.class);
                startActivity(intent);
            }
        });
        change_P = findViewById(R.id.changeP);
        change_P.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeaActivity.this, admin_profileActivity.class);
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