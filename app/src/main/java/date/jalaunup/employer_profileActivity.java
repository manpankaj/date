package date.jalaunup;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
public class employer_profileActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences_emp;
    public static final String MY_PREFERENCES_EMP = "MyPrefsEmp";
    public static final String EMAIL_EMP = "email";
    public static final String USERNAME_EMP = "username";
    TextView username,email;
    Button logout,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employer_profile);
        sharedPreferences_emp = getSharedPreferences(MY_PREFERENCES_EMP, Context.MODE_PRIVATE);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        username.setText("Welcome " + sharedPreferences_emp.getString(USERNAME_EMP, ""));
        email.setText("Your Mobile No. " + sharedPreferences_emp.getString(EMAIL_EMP, ""));
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences_emp.edit();
                Intent intent = new Intent(employer_profileActivity.this, WelcomeeActivity.class);
                startActivity(intent);
            }
        });
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences_emp.edit();
                editor.clear();
                editor.apply();
                finish();
                Intent intent = new Intent(employer_profileActivity.this, logineActivity.class);
                startActivity(intent);
            }
        });
    }
}