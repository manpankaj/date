package date.jalaunup;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
public class MainActivity<val> extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button login_w = findViewById(R.id.login_worker);
        Button login_e = findViewById(R.id.login_employer);
        Button regis_w = findViewById(R.id.registration_worker);
        Button regis_e = findViewById(R.id.registration_employer);
        login_w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, loginwActivity.class);
                startActivity(intent);
            }
        });
        login_e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, logineActivity.class);
                startActivity(intent);
            }
        });
        regis_w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, registrationwActivity.class);
                startActivity(intent);
            }
        });
        regis_e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, registrationeActivity.class);
                startActivity(intent);
            }
        });
        TextView register = (TextView)findViewById(R.id.disclaimer);
        register.setMovementMethod(LinkMovementMethod.getInstance());
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, disclaimerActivity.class);
                startActivity(intent);
            }
        });
    }
}