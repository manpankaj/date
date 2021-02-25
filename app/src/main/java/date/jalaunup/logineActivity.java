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
import date.jalaunup.Config.RoleManager;
public class logineActivity extends AppCompatActivity {
    public static final String URL_LOGIN = "http://10.135.217.19:8080/date/login_e.php";
    EditText ed_email, ed_password;
    SharedPreferences sharedPreferences_emp;
    public static final String MY_PREFERENCES_EMP = "MyPrefsEmp";
    public static final String EMAIL_EMP = "email";
    public static final String STATUS_EMP = "status_emp";
    public static final String USERNAME_EMP = "username";

    //public static final String CATEGORY = "category";
    //public static final String SUB_CATEGORY = "sub_category";
    //public static final String EXP_YEAR = "exp_year";
    String MobilePattern = "[0-9]{10}";
    String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_employer );
        ed_email = findViewById(R.id.txtMob);
        ed_password = findViewById(R.id.txtPwd);
        sharedPreferences_emp = getSharedPreferences(MY_PREFERENCES_EMP, Context.MODE_PRIVATE);
        boolean status_emp = sharedPreferences_emp.getBoolean(STATUS_EMP, false);
        if (status_emp){
            finish();
            Intent intent = new Intent(logineActivity.this, WelcomeeActivity.class);
            startActivity(intent);
        }
    }
    public void login(View view){
        final String email = ed_email.getText().toString();
        final String password = ed_password.getText().toString();
        String encrypt_password = password_encrypt.getSha256Hash(password);
        if(ed_email.getText().toString().equals("")){
            Toast.makeText(this, "Enter Mobile No.", Toast.LENGTH_SHORT).show();
        }
        else if(!ed_email.getText().toString().matches(MobilePattern)){
            Toast.makeText(this, "Enter Correct Mobile No.", Toast.LENGTH_SHORT).show();
        }
        else if(ed_password.getText().toString().equals("")){
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        }
        else if(!ed_password.getText().toString().matches(passwordPattern)){
            Toast.makeText(this, "Password must contain minimum 8 characters at least 1 Alphabet, 1 Number and 1 Special Character.", Toast.LENGTH_SHORT).show();
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
                    params.put("password", encrypt_password );
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
                            //String category = obj.getString("category");
                            //String sub_category = obj.getString("sub_category");
                            //String exp_year = obj.getString("exp_year");
                            SharedPreferences.Editor editor = sharedPreferences_emp.edit();
                            editor.putString(USERNAME_EMP, username);
                            editor.putString(EMAIL_EMP, email);
                            editor.putBoolean(STATUS_EMP, true);
                            editor.putBoolean(STATUS_EMP, true);
                            editor.putString("ROLE_ID",RoleManager.ROLE_EMPLOYER);
                            //editor.putString(CATEGORY, category);
                            //editor.putString(SUB_CATEGORY, sub_category);
                           // editor.putString(EXP_YEAR, exp_year);
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