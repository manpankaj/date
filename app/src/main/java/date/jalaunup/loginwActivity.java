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
import date.jalaunup.Config.url_add;

public class loginwActivity extends AppCompatActivity {
    public static final String URL_LOGIN =url_add.login_worker;
    EditText ed_email, ed_password;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_worker);
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
                final ProgressDialog pdLoading = new ProgressDialog(loginwActivity.this);
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
                            String emailid = obj.getString("email");
                            String username = obj.getString("username");
                            String category = obj.getString("category");
                            String sub_category = obj.getString("sub_category");
                            String exp_year = obj.getString("exp_year");
                            String role = obj.getString("role");
                            String address = obj.getString("address");
                            String tehsil = obj.getString("tehsil");
                            session.createLoginSession_worker(username, email, emailid, category, sub_category, exp_year, role, address, tehsil);
                            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(loginwActivity.this, WelcomewActivity.class);
                            startActivity(intent);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(loginwActivity.this, "Incorrect Mobile No. or Password", Toast.LENGTH_LONG).show();
                    }
                }
            }
            Login login = new Login();
            login.execute();
        }
    }
    public void back(View view) {
        finish();
        Intent intent = new Intent(loginwActivity.this, MainActivity.class);
        startActivity(intent);
    }
}