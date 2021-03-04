package date.jalaunup;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import androidx.appcompat.app.AppCompatActivity;
import date.jalaunup.Config.RequestHandler;
import date.jalaunup.Config.SessionManager;
import date.jalaunup.Config.password_encrypt;
import date.jalaunup.Config.stringPattern;

public class loginaActivity extends AppCompatActivity {
    public static final String URL_LOGIN = "http://10.135.217.19:8080/date/login_a.php";
    EditText ed_email, ed_password;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_admin);
        session = new SessionManager(getApplicationContext());
        ed_email = findViewById(R.id.txtMob);
        ed_password = findViewById(R.id.txtPwd);
        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();

    }
    public void login(View view) {
        final String email = ed_email.getText().toString();
        final String password = ed_password.getText().toString();
        String encrypt_password = password_encrypt.getSha256Hash(password);
        if (ed_email.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Mobile No.", Toast.LENGTH_SHORT).show();
        } else if (!ed_email.getText().toString().matches(stringPattern.MobilePattern)) {
            Toast.makeText(this, "Enter Correct Mobile No.", Toast.LENGTH_SHORT).show();
        } else if (ed_password.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        } else if (!ed_password.getText().toString().matches(stringPattern.passwordPattern)) {
            Toast.makeText(this, "Password must contain minimum 8 characters at least 1 Alphabet, 1 Number and 1 Special Character.", Toast.LENGTH_SHORT).show();
        } else {
            class Login extends AsyncTask<Void, Void, String> {
                final ProgressDialog pdLoading = new ProgressDialog(loginaActivity.this);
                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    pdLoading.setMessage("\tLoading...");
                    pdLoading.setCancelable(false);
                    pdLoading.show();
                }
                @Override
                protected String doInBackground(Void... voids) {
                    RequestHandler requestHandler = new RequestHandler();
                    HashMap<String, String> params = new HashMap<>();
                    params.put("email", email);
                    params.put("password", encrypt_password);
                    return requestHandler.sendPostRequest(URL_LOGIN, params);
                }
                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    pdLoading.dismiss();
                    try {
                        JSONObject obj = new JSONObject(s);
                        if (!obj.getBoolean("error")) {
                            String username = obj.getString("username");
                            String role = obj.getString("role");
                            session.createLoginSession_admin(username, email, role);
                            Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(loginaActivity.this, WelcomeaActivity.class);
                            startActivity(intent);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(loginaActivity.this, "Incorrect Mobile No. or Password", Toast.LENGTH_LONG).show();
                    }
                }
            }
            Login login = new Login();
            login.execute();
        }
    }
    public void back(View view) {
        finish();
        Intent intent = new Intent(loginaActivity.this, MainActivity.class);
        startActivity(intent);
    }
}