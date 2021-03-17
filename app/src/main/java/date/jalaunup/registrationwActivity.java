package date.jalaunup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import androidx.appcompat.app.AppCompatActivity;
import date.jalaunup.Config.integerMinMax;
import date.jalaunup.Config.password_encrypt;
import date.jalaunup.Config.stringPattern;
import date.jalaunup.Config.url_add;

public class registrationwActivity extends AppCompatActivity {
    EditText ed_fullname,ed_mobile,ed_email,ed_age,ed_password,ed_password1,ed_add;
    RadioButton rd_male,rd_female;
    String str_fullname,str_mobile,str_email,str_age,str_add,str_sex,str_password;
    String url = url_add.regis_worker;
    Spinner sp_tehsil;
    ArrayList<String> arrayList_tehsil;
    ArrayAdapter<String> arrayAdapter_tehsil;
    String str_tehsil;
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        sp_tehsil = (Spinner) findViewById(R.id.tehsil);
        ed_add = findViewById(R.id.Address);
        ed_age.setFilters(new InputFilter[]{new integerMinMax.InputFilterMinMax("1", "60")});
        arrayList_tehsil = new ArrayList<>();
        arrayList_tehsil.add("Jalaun");
        arrayList_tehsil.add("Orai");
        arrayList_tehsil.add("Kalpi");
        arrayList_tehsil.add("Konch");
        arrayList_tehsil.add("Madhogarh");
        arrayAdapter_tehsil = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList_tehsil);
        sp_tehsil.setAdapter(arrayAdapter_tehsil);
        sp_tehsil.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> tehsil, View view, int position, long id) {
                str_tehsil =  tehsil.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> tehsil) {
            }
        });
    }
    public void back(View view) {
               startActivity(new Intent(getApplicationContext(),MainActivity.class));
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
        else if(!ed_mobile.getText().toString().matches(stringPattern.MobilePattern)){
            Toast.makeText(this, "Enter Correct Mobile No.", Toast.LENGTH_SHORT).show();
        }
        else if(ed_email.getText().toString().equals("")){
            Toast.makeText(this, "Enter Mail ID.", Toast.LENGTH_SHORT).show();
        }
        else if(!ed_email.getText().toString().matches(stringPattern.emailPattern)){
            Toast.makeText(this, "Enter Correct Email ID.", Toast.LENGTH_SHORT).show();
        }
        else if(ed_age.getText().toString().equals("")){
            Toast.makeText(this, "Enter age in year.", Toast.LENGTH_SHORT).show();
        }
        else if(ed_add.getText().toString().equals("")){
            Toast.makeText(this, "Enter Address.", Toast.LENGTH_SHORT).show();
        }
        else if(ed_password.getText().toString().equals("")){
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        }
        else if(!ed_password.getText().toString().matches(stringPattern.passwordPattern)){
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
            str_add = ed_add.getText().toString().trim();
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
                    ed_add.setText("");
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
                    params.put("add",str_add);
                    params.put("tehsil",str_tehsil);
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