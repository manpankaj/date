package date.jalaunup;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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
public class worker_profileActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    public static final String MY_PREFERENCES = "MyPrefs";
    public static final String USERNAME = "username";
    public static final String EMAIL = "email";
    public static final String CATEGORY = "category";
    public static final String SUB_CATEGORY = "sub_category";
    public static final String EXP_YEAR = "exp_year";
    TextView username,email,tv_parent,tv_child;
    EditText expyear;
    Button logout,back,profile;
    Spinner sp_parent,sp_child;
    ArrayList<String> arrayList_parent;
    ArrayList<String> arrayList_animals,arrayList_birds,arrayList_flowers;
    ArrayAdapter<String> arrayAdapter_parent;
    ArrayAdapter<String> arrayAdapter_child;
    String str_category,str_sub_category,str_expyear;
    String url = "http://10.135.217.19:8080/date/worker_profile.php";
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
        setContentView(R.layout.worker_profile);
        sharedPreferences = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        tv_parent = findViewById(R.id.tv_parent1);
        tv_child = findViewById(R.id.tv_child1);
        expyear = findViewById(R.id.expyear);
        expyear.setFilters(new InputFilter[]{new InputFilterMinMax("1", "30")});
        username.setText("Welcome " + sharedPreferences.getString(USERNAME, ""));
        email.setText("Your Mobile No. " + sharedPreferences.getString(EMAIL, ""));
        sp_parent = (Spinner) findViewById(R.id.parent);
        sp_child = (Spinner) findViewById(R.id.child);
        tv_parent.setText(sharedPreferences.getString(CATEGORY, ""));
        tv_child.setText(sharedPreferences.getString(SUB_CATEGORY, ""));
        expyear.setText(sharedPreferences.getString(EXP_YEAR, ""));
        arrayList_parent = new ArrayList<>();
        arrayList_parent.add("Animals");
        arrayList_parent.add("Birds");
        arrayList_parent.add("Flowers");
        arrayAdapter_parent = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList_parent);
        sp_parent.setAdapter(arrayAdapter_parent);
        arrayList_animals = new ArrayList<>();
        arrayList_animals.add("Tiger");
        arrayList_animals.add("Lion");
        arrayList_animals.add("Elephant");
        arrayList_animals.add("Monkey");
        arrayList_animals.add("Cow");
        arrayList_birds = new ArrayList<>();
        arrayList_birds.add("Sparrow");
        arrayList_birds.add("Peacock");
        arrayList_birds.add("Eagle");
        arrayList_flowers = new ArrayList<>();
        arrayList_flowers.add("Rose");
        arrayList_flowers.add("Lotus");
        sp_parent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_category =  parent.getItemAtPosition(position).toString();
                if (position == 0) {
                    arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList_animals);
                    sp_child.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> child, View view, int position, long id) {
                            str_sub_category = child.getItemAtPosition(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> child) {
                        }
                    });
                }
                if (position == 1) {
                    arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList_birds);
                    sp_child.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> child, View view, int position, long id) {
                            str_sub_category = child.getItemAtPosition(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> child) {
                        }
                    });
                }
                if (position == 2) {
                    arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList_flowers);
                    sp_child.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> child, View view, int position, long id) {
                            str_sub_category = child.getItemAtPosition(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> child) {
                        }
                    });
                }
                sp_child.setAdapter(arrayAdapter_child);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Intent intent = new Intent(worker_profileActivity.this, WelcomewActivity.class);
                startActivity(intent);
            }
        });
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                finish();
                Intent intent = new Intent(worker_profileActivity.this, loginwActivity.class);
                startActivity(intent);
            }
        });
        profile = findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                str_expyear = expyear.getText().toString().trim();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(CATEGORY, str_category);
                editor.putString(SUB_CATEGORY, str_sub_category);
                editor.putString(EXP_YEAR, str_expyear);
                editor.apply();
                ProgressDialog progressDialog = new ProgressDialog(worker_profileActivity.this);
                progressDialog.setMessage("Please Wait..");
                progressDialog.show();
                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Toast.makeText(worker_profileActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(worker_profileActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                ) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("fullname", sharedPreferences.getString(USERNAME, ""));
                        params.put("mobile", sharedPreferences.getString(EMAIL, ""));
                        params.put("category", str_category);
                        params.put("sub_category", str_sub_category);
                        params.put("expyear", str_expyear);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(worker_profileActivity.this);
                requestQueue.add(request);
            }
        });
    }
}