package date.jalaunup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;
import date.jalaunup.Config.SessionManager;

public class employer_profileActivity extends AppCompatActivity {
    SessionManager session;
    TextView username,email;
    Button logout,back;
    String str_username,str_email,str_role;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employer_profile);
        session = new SessionManager(getApplicationContext());
        session.checkLogin();
        //session.checkEmployer();
        //Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
        HashMap<String, String> user = session.getUserDetails();
        str_username = user.get(SessionManager.KEY_NAME);
        str_email = user.get(SessionManager.KEY_EMAIL);
        str_role = user.get(SessionManager.KEY_ROLE);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        username.setText("Welcome " + str_username + str_role);
        email.setText("Your Mobile No. " + str_email);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(employer_profileActivity.this, WelcomeeActivity.class);
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