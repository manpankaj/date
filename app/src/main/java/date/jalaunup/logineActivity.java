package date.jalaunup;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import androidx.appcompat.app.AppCompatActivity;
public class logineActivity extends AppCompatActivity {
    public static final String URL_LOGIN = "http://192.168.1.9:8080/date/login_e.php";
    EditText ed_email, ed_password;
    SharedPreferences sharedPreferences;
    public static final String MY_PREFERENCES = "MyPrefs";
    public static final String EMAIL = "email";
    public static final String STATUS = "status";
    public static final String USERNAME = "username";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_employer);
        ed_email = findViewById(R.id.txtMob);
        ed_password = findViewById(R.id.txtPwd);
        sharedPreferences = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        boolean status = sharedPreferences.getBoolean(STATUS, false);
        if (status){
            finish();
            Intent intent = new Intent(logineActivity.this, WelcomeeActivity.class);
            startActivity(intent);
        }
    }
    public void login(View view){
        final String email = ed_email.getText().toString();
        final String password = ed_password.getText().toString();
        String encrypt_password = password_encrypt.getSha256Hash(password);
        if(email.isEmpty()|| password.isEmpty()){
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
        }
        else {
            class Login extends AsyncTask<Void, Void, String> {
                final ProgressDialog pdLoading = new ProgressDialog(logineActivity.this);
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
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(USERNAME, username);
                            editor.putString(EMAIL, email);
                            editor.putBoolean(STATUS, true);
                            editor.apply();
                            finish();
                            Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(logineActivity.this, WelcomeeActivity.class);
                            startActivity(intent);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(logineActivity.this, "Incorrect Mobile No. or Password" , Toast.LENGTH_LONG).show();
                    }
                }
            }
            Login login = new Login();
            login.execute();
        }
    }
    public void back(View view){
        finish();
        Intent intent = new Intent(logineActivity.this, MainActivity.class);
        startActivity(intent);
    }
}