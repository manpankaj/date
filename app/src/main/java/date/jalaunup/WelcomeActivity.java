package date.jalaunup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
public class WelcomeActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    public static final String MY_PREFERENCES = "MyPrefs";
    public static final String USERNAME = "username";
    TextView username;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        sharedPreferences = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        username = findViewById(R.id.username);
        username.setText("Welcome, " + sharedPreferences.getString(USERNAME,""));

        logout = findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                finish();
                Intent intent = new Intent(WelcomeActivity.this, loginwActivity.class);
                startActivity(intent);
            }
        });
    }
}