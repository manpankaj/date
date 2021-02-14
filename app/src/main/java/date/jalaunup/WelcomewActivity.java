package date.jalaunup;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
public class WelcomewActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    public static final String MY_PREFERENCES = "MyPrefs";
    public static final String USERNAME = "username";
    public static final String EMAIL = "email";
    TextView username,email;
    Button logout;
    Button worker_profile,search_employment,change_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_w);
        sharedPreferences = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        username.setText("Welcome " + sharedPreferences.getString(USERNAME, ""));
        email.setText("Your Mobile No. " + sharedPreferences.getString(EMAIL, ""));
        worker_profile = findViewById(R.id.profileupdate);
        worker_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Intent intent = new Intent(WelcomewActivity.this, worker_profileActivity.class);
                startActivity(intent);
            }
        });
        search_employment = findViewById(R.id.searchemployment);
        search_employment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Intent intent = new Intent(WelcomewActivity.this, worker_employmentActivity.class);
                startActivity(intent);
            }
        });
        change_password = findViewById(R.id.changepassword);
        change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Intent intent = new Intent(WelcomewActivity.this, worker_changePActivity.class);
                startActivity(intent);
            }
        });

        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                finish();
                Intent intent = new Intent(WelcomewActivity.this, loginwActivity.class);
                startActivity(intent);
            }
        });
    }
}