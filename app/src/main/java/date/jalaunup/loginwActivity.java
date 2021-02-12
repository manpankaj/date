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

public class loginwActivity extends AppCompatActivity {

    public static final String URL_LOGIN = "http://10.135.217.19:8080/date/login_w.php";
    EditText ed_email, ed_password;
    SharedPreferences sharedPreferences;
    public static final String MY_PREFERENCES = "MyPrefs";
    public static final String EMAIL = "email";
    public static final String STATUS = "status";
    public static final String USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_worker );

        ed_email = findViewById(R.id.txtMob);
        ed_password = findViewById(R.id.txtPwd);

        sharedPreferences = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);

        boolean status = sharedPreferences.getBoolean(STATUS, false);

        if (status){
            finish();
            Intent intent = new Intent(loginwActivity.this, WelcomewActivity.class);
            startActivity(intent);
        }
    }

    public void login(View view){
        final String email = ed_email.getText().toString();
        final String password = ed_password.getText().toString();

        if(email.isEmpty()|| password.isEmpty()){
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
        }

        else {
            class Login extends AsyncTask<Void, Void, String> {
                final ProgressDialog pdLoading = new ProgressDialog(loginwActivity.this);

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();

                    //this method will be running on UI thread
                    pdLoading.setMessage("\tLoading...");
                    pdLoading.setCancelable(false);
                    pdLoading.show();
                }

                @Override
                protected String doInBackground(Void... voids) {
                    //creating request handler object
                    RequestHandler requestHandler = new RequestHandler();

                    //creating request parameters
                    HashMap<String, String> params = new HashMap<>();
                    params.put("email", email);
                    params.put("password", password);

                    //returing the response
                    return requestHandler.sendPostRequest(URL_LOGIN, params);
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    pdLoading.dismiss();

                    try {
                        //converting response to json object
                        JSONObject obj = new JSONObject(s);
                        //if no error in response
                        if (!obj.getBoolean("error")) {
                            String username = obj.getString("username");

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(USERNAME, username);
                            editor.putString(EMAIL, email);
                            editor.putBoolean(STATUS, true);
                            editor.apply();

                            finish();
                            Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(loginwActivity.this, WelcomewActivity.class);
                            startActivity(intent);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(loginwActivity.this, "Exception: " + e, Toast.LENGTH_LONG).show();
                    }
                }
            }

            Login login = new Login();
            login.execute();
        }
    }

    public void back(View view){
        finish();
        Intent intent = new Intent(loginwActivity.this, MainActivity.class);
        startActivity(intent);
    }
}