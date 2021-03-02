package date.jalaunup;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import date.jalaunup.Config.password_encrypt;

public class employer_changePActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences_emp;
    public static final String MY_PREFERENCES_EMP = "MyPrefsEmp";
    public static final String EMAIL_EMP = "email";
    public static final String USERNAME_EMP = "username";
    EditText ed_oldpassword,ed_password,ed_password1;
    String str_oldpassword,str_password,str_mobile;
    String url_changeP = "http://10.135.217.19:8080/date/employer_changeP.php";
    String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
    TextView username,email;
    Button logout,back;
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employer_change_p);
        sharedPreferences_emp = getSharedPreferences(MY_PREFERENCES_EMP, Context.MODE_PRIVATE);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        username.setText("Welcome " + sharedPreferences_emp.getString(USERNAME_EMP, ""));
        email.setText("Your Mobile No. " + sharedPreferences_emp.getString(EMAIL_EMP, ""));
        ed_oldpassword = findViewById(R.id.txtOPwd);
        ed_password = findViewById(R.id.txtNPwd);
        ed_password1 = findViewById(R.id.txtNPwd2);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences_emp.edit();
                Intent intent = new Intent(employer_changePActivity.this, WelcomeeActivity.class);
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
                Intent intent = new Intent(employer_changePActivity.this, logineActivity.class);
                startActivity(intent);
            }
        });
    }
    public void changeP(View v) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");
        if (ed_oldpassword.getText().toString().equals("")){
            Toast.makeText(this, "Enter Old Password", Toast.LENGTH_SHORT).show();
        }
        else if(!ed_oldpassword.getText().toString().matches(passwordPattern)){
            Toast.makeText(this, "Old Password must contain minimum 8 characters at least 1 Alphabet, 1 Number and 1 Special Character.", Toast.LENGTH_SHORT).show();
        }
        else if(ed_password.getText().toString().equals("")){
            Toast.makeText(this, "Enter New Password", Toast.LENGTH_SHORT).show();
        }
        else if(!ed_password.getText().toString().matches(passwordPattern)){
            Toast.makeText(this, "New Password must contain minimum 8 characters at least 1 Alphabet, 1 Number and 1 Special Character.", Toast.LENGTH_SHORT).show();
        }
        else if(ed_password1.getText().toString().equals("")){
            Toast.makeText(this, "Enter Confirm Password", Toast.LENGTH_SHORT).show();
        }
        else if(!ed_password1.getText().toString().equals (ed_password.getText().toString())){
            Toast.makeText(this, "Password and Confirm Password Does not match", Toast.LENGTH_SHORT).show();
        }
        else{
            progressDialog.show();
            str_mobile = sharedPreferences_emp.getString(EMAIL_EMP, "");
            str_oldpassword = ed_oldpassword.getText().toString().trim();
            str_password = ed_password.getText().toString().trim();
            String encrypt_oldpassword = password_encrypt.getSha256Hash(str_oldpassword);
            String encrypt_password = password_encrypt.getSha256Hash(str_password);
            StringRequest request = new StringRequest(Request.Method.POST, url_changeP, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    SharedPreferences.Editor editor = sharedPreferences_emp.edit();
                    Toast.makeText(employer_changePActivity.this, response, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(employer_changePActivity.this, WelcomeeActivity.class);
                    startActivity(intent);
                }
            },new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(employer_changePActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("mobile",str_mobile);
                    params.put("oldpassword",encrypt_oldpassword);
                    params.put("password",encrypt_password);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(employer_changePActivity.this);
            requestQueue.add(request);
        }
    }
}