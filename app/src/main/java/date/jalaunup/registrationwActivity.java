package date.jalaunup;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
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
public class registrationwActivity extends AppCompatActivity {
    EditText ed_fullname,ed_mobile,ed_email,ed_age,ed_password,ed_password1;
    RadioButton rd_male,rd_female;
    String str_fullname,str_mobile,str_email,str_age,str_sex,str_password;
    String url = "http://10.135.217.19:8080/date/registration_w.php";
    String MobilePattern = "[0-9]{10}";
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        class InputFilterMinMax implements InputFilter {
            private final int min;
            private final int max;
            public InputFilterMinMax(int min, int max) {
                this.min = min;
                this.max = max;
            }
            public InputFilterMinMax(String min, String max) {
                this.min = Integer.parseInt(min);
                this.max = Integer.parseInt(max);
            }
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                try {
                    int input = Integer.parseInt(dest.toString() + source.toString());
                    if (isInRange(min, max, input))
                        return null;
                } catch (NumberFormatException nfe) { }
                return "";
            }
            private boolean isInRange(int a, int b, int c) {
                return b > a ? c >= a && c <= b : c >= b && c <= a;
            }
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_worker);
        ed_fullname = findViewById(R.id.txtName);
        ed_mobile = findViewById(R.id.txtMob);
        ed_email = findViewById(R.id.txtEmail);
        ed_age = findViewById(R.id.txtAge);
        rd_male = findViewById(R.id.radioMale);
        rd_female = findViewById(R.id.radioFemale);
        ed_password = findViewById(R.id.txtPwd);
        ed_password1 = findViewById(R.id.txtPwd2);
        ed_age.setFilters(new InputFilter[]{new InputFilterMinMax("1", "60")});
    }
    public void Login_w(View view) {
               startActivity(new Intent(getApplicationContext(),loginwActivity.class));
        finish();    }
    public void Register_w(View view) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");

        if(ed_fullname.getText().toString().equals("")){
            Toast.makeText(this, "Enter Full Name", Toast.LENGTH_SHORT).show();
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
        else if(ed_age.getText().toString().equals("")){
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
            str_fullname = ed_fullname.getText().toString().trim();
            str_mobile = ed_mobile.getText().toString().trim();
            str_email = ed_email.getText().toString().trim();
            str_age = ed_age.getText().toString().trim();
            str_password = ed_password.getText().toString().trim();
            if (rd_male.isChecked()){
                str_sex = "Male";}
            else if (rd_female.isChecked()){
                str_sex = "Female";}
            String encrypt_password = password_encrypt.getSha256Hash(str_password);
            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    ed_fullname.setText("");
                    ed_mobile.setText("");
                    ed_email.setText("");
                    ed_age.setText("");
                    ed_password.setText("");
                    ed_password1.setText("");
                    Toast.makeText(registrationwActivity.this, response, Toast.LENGTH_SHORT).show();
                }
            },new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(registrationwActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("fullname",str_fullname);
                    params.put("mobile",str_mobile);
                    params.put("email",str_email);
                    params.put("age",str_age);
                    params.put("sex",str_sex);
                    params.put("password",encrypt_password);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(registrationwActivity.this);
            requestQueue.add(request);
        }
    }
}