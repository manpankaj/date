package date.jalaunup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import date.jalaunup.Config.SessionManager;
import date.jalaunup.Config.password_encrypt;
import date.jalaunup.Config.stringPattern;
import date.jalaunup.Config.url_add;

public class admin_resetEPActivity extends AppCompatActivity {
    SessionManager session;
    EditText employer_mobile;
    String str_employer_mobile,str_username,str_email,str_role;
    String url_changeP = url_add.reset_EP;
    TextView username,email;
    String NewPassword="Test@123";
    Button back;
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_employer_reset);
        session = new SessionManager(getApplicationContext());
        session.checkLogin();
        String roleNew =  session.checkAdminNew(session);
        HashMap<String, String> user = session.getUserDetails();
        str_username = user.get(SessionManager.KEY_NAME);
        str_email = user.get(SessionManager.KEY_EMAIL);
        str_role = user.get(SessionManager.KEY_ROLE);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        username.setText("Welcome " + str_username + str_role);
        email.setText("Your Mobile No. " + str_email);
        employer_mobile = findViewById(R.id.txt_employer_mobile);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin_resetEPActivity.this, WelcomeaActivity.class);
                startActivity(intent);
            }
        });
    }
    public void reset(View v) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");
        if (employer_mobile.getText().toString().equals("")){
            Toast.makeText(this, "Enter employer Mobile No.", Toast.LENGTH_SHORT).show();
        }
        else if (!employer_mobile.getText().toString().matches(stringPattern.MobilePattern)) {
            Toast.makeText(this, "Enter Correct Mobile No.", Toast.LENGTH_SHORT).show();
        }
        else{
            progressDialog.show();
            str_employer_mobile = employer_mobile.getText().toString().trim();
            String encrypt_password = password_encrypt.getSha256Hash(NewPassword);
            StringRequest request = new StringRequest(Request.Method.POST, url_changeP, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(admin_resetEPActivity.this, response, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(admin_resetEPActivity.this, WelcomeaActivity.class);
                    startActivity(intent);
                }
            },new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(admin_resetEPActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("mobile",str_employer_mobile);
                    params.put("password",encrypt_password);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(admin_resetEPActivity.this);
            requestQueue.add(request);
        }
    }
}