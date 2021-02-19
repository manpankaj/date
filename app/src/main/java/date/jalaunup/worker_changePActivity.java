package date.jalaunup;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
public class worker_changePActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    public static final String MY_PREFERENCES = "MyPrefs";
    public static final String USERNAME = "username";
    public static final String EMAIL = "email";
    EditText ed_oldpassword,ed_password,ed_password1;
    String str_oldpassword,str_password;
    String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
    TextView username,email;
    Button logout,back,changepassword;
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ed_oldpassword = findViewById(R.id.txtOldPwd);
        ed_password = findViewById(R.id.txtPwd);
        ed_password1 = findViewById(R.id.txtPwd2);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worker_change_p);
        sharedPreferences = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        username.setText("Welcome " + sharedPreferences.getString(USERNAME, ""));
        email.setText("Your Mobile No. " + sharedPreferences.getString(EMAIL, ""));
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Intent intent = new Intent(worker_changePActivity.this, WelcomewActivity.class);
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
                Intent intent = new Intent(worker_changePActivity.this, loginwActivity.class);
                startActivity(intent);
            }
        });
    }
}