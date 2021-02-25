package date.jalaunup;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import date.jalaunup.Config.RoleManager;

public class WelcomeeActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences_emp;
    public static final String MY_PREFERENCES_EMP = "MyPrefsEmp";
    public static final String EMAIL_EMP = "email";
    public static final String USERNAME_EMP = "username";
   // public static final String CATEGORY = "category";
    //public static final String SUB_CATEGORY = "sub_category";
    //public static final String EXP_YEAR = "exp_year";
    TextView username,email;
    Button logout;
    Button employer_profile,save_work,save_manpower,search_manpower,change_password;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_e);
        sharedPreferences_emp = getSharedPreferences(MY_PREFERENCES_EMP, Context.MODE_PRIVATE);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        username.setText("Welcome " + sharedPreferences_emp.getString(USERNAME_EMP, "") );
        email.setText("Your Mobile No. " + sharedPreferences_emp.getString(EMAIL_EMP, "") );
        employer_profile = findViewById(R.id.profileUpdate);

        boolean status_emp = sharedPreferences_emp.getBoolean("status_emp", false);
        if (status_emp)
        {
            finish();
            Intent intent = new Intent(WelcomeeActivity.this, logineActivity.class);
            startActivity(intent);
        }

        String roleId = sharedPreferences_emp.getString("ROLE_ID","0");
        if(roleId.equals(RoleManager.ROLE_EMPLOYER) == false)
        {
            SharedPreferences.Editor editor = sharedPreferences_emp.edit();
            editor.clear();
            editor.apply();
            finish();
            Intent intent = new Intent(WelcomeeActivity.this, logineActivity.class);
            startActivity(intent);
        }



        employer_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences_emp.edit();
                Intent intent = new Intent(WelcomeeActivity.this, employer_profileActivity.class);
                startActivity(intent);
            }
        });
        save_work = findViewById(R.id.saveWork);
        save_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences_emp.edit();
                Intent intent = new Intent(WelcomeeActivity.this, employer_saveworkActivity.class);
                startActivity(intent);
            }
        });
        save_manpower= findViewById(R.id.saveManpowerReq);
        save_manpower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences_emp.edit();
                Intent intent = new Intent(WelcomeeActivity.this, employer_profileActivity.class);
                startActivity(intent);
            }
        });
        search_manpower= findViewById(R.id.searchWorker);
        search_manpower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences_emp.edit();
                Intent intent = new Intent(WelcomeeActivity.this, employer_profileActivity.class);
                startActivity(intent);
            }
        });
        change_password = findViewById(R.id.changePassword);
        change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences_emp.edit();
                Intent intent = new Intent(WelcomeeActivity.this, employer_changePActivity.class);
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
                Intent intent = new Intent(WelcomeeActivity.this, logineActivity.class);
                startActivity(intent);
            }
        });
    }
}