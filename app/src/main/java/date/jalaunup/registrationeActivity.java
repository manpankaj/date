package date.jalaunup;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

public class registrationeActivity extends AppCompatActivity {
    EditText ed_empname,ed_mobile,ed_email,ed_add,ed_password,ed_password1;
    String str_empname,str_mobile,str_email,str_add,str_password,str_password1;
    String url = "http://192.168.1.9:8080/date/registration_e.php";
    String MobilePattern = "[0-9]{10}";
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_employer);
        ed_empname = findViewById(R.id.txtName);
        ed_mobile = findViewById(R.id.txtMob);
        ed_email = findViewById(R.id.txtEmail);
        ed_add = findViewById(R.id.txtAdd);
        ed_password = findViewById(R.id.txtPwd);
        ed_password1 = findViewById(R.id.txtPwd2);
    }
    public void Login_e(View view) {
        startActivity(new Intent(getApplicationContext(),logineActivity.class));
        finish();
    }
    public void Register_e(View view) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");
        if(ed_empname.getText().toString().equals("")){
            Toast.makeText(this, "Enter Name of Employer" , Toast.LENGTH_SHORT).show();
        }
        else if(ed_mobile.getText().toString().equals("")){
            Toast.makeText(this, "Enter Mobile No.", Toast.LENGTH_SHORT).show();
        }
        else if(!ed_mobile.getText().toString().matches(MobilePattern)){
            Toast.makeText(this, "Enter Correct Mobile No.", Toast.LENGTH_SHORT).show();
        }
        else if(ed_email.getText().toString().equals("")){
            Toast.makeText(this, "Enter Mail ID.", Toast.LENGTH_SHORT).show();
        }
        else if(!ed_email.getText().toString().matches(emailPattern)){
            Toast.makeText(this, "Enter Correct Email ID.", Toast.LENGTH_SHORT).show();
        }
        else if(ed_add.getText().toString().equals("")){
            Toast.makeText(this, "Enter age in year.", Toast.LENGTH_SHORT).show();
        }
        else if(ed_password.getText().toString().equals("")){
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        }
        else if(!ed_password.getText().toString().matches(passwordPattern)){
            Toast.makeText(this, "Password must contain minimum 8 characters at least 1 Alphabet, 1 Number and 1 Special Character.", Toast.LENGTH_SHORT).show();
        }
        else if(ed_password1.getText().toString().equals("")){
            Toast.makeText(this, "Enter Confirm Password", Toast.LENGTH_SHORT).show();
        }
        else if(!ed_password1.getText().toString().equals (ed_password.getText().toString())){
            Toast.makeText(this, "Password and Confirm Password Does not match", Toast.LENGTH_SHORT).show();
        }
        else{
            progressDialog.show();
            str_empname = ed_empname.getText().toString().trim();
            str_mobile = ed_mobile.getText().toString().trim();
            str_email = ed_email.getText().toString().trim();
            str_add = ed_add.getText().toString().trim();
            str_password = ed_password.getText().toString().trim();
            String encrypt_password = password_encrypt.getSha256Hash(str_password);
            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    ed_empname.setText("");
                    ed_mobile.setText("");
                    ed_email.setText("");
                    ed_add.setText("");
                    ed_password.setText("");
                    ed_password1.setText("");
                    Toast.makeText(registrationeActivity.this, response, Toast.LENGTH_SHORT).show();
                }
            },new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(registrationeActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("empname",str_empname);
                    params.put("mobile",str_mobile);
                    params.put("email",str_email);
                    params.put("add",str_add);
                    params.put("password",encrypt_password);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(registrationeActivity.this);
            requestQueue.add(request);
        }
    }
}