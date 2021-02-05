package date.jalaunup;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by tutlane on 08-01-2018.
 */

public class disclaimerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.disclaimer);
        Button backdis = findViewById(R.id.backDis);

        backdis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(disclaimerActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}





