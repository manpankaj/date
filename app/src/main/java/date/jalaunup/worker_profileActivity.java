package date.jalaunup;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    TextView username,email,tv_parent1,tv_child1;
    Button logout,back,profile;
    Spinner sp_parent,sp_child;
    ArrayList<String> arrayList_parent;
    ArrayList<String> arrayList_animals,arrayList_birds,arrayList_flowers;
    ArrayAdapter<String> arrayAdapter_parent;
    ArrayAdapter<String> arrayAdapter_child;
    String str_category,str_sub_category;
    String url = "http://10.135.217.19:8080/date/worker_profile.php";
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worker_profile);
        sharedPreferences = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        tv_parent1 = findViewById(R.id.tv_parent1);
        tv_child1 = findViewById(R.id.tv_child1);
        username.setText("Welcome " + sharedPreferences.getString(USERNAME, ""));
        email.setText("Your Mobile No. " + sharedPreferences.getString(EMAIL, ""));
        sp_parent = (Spinner) findViewById(R.id.parent);
        sp_child = (Spinner) findViewById(R.id.child);
        tv_parent1.setText("Welcome " + sharedPreferences.getString(CATEGORY, ""));
        tv_child1.setText("Welcome " + sharedPreferences.getString(SUB_CATEGORY, ""));
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
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(worker_profileActivity.this);
                requestQueue.add(request);
            }
        });
    }
}