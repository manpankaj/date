package date.jalaunup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputFilter;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import date.jalaunup.Config.RequestHandler;
import date.jalaunup.Config.SessionManager;
import date.jalaunup.Config.integerMinMax;
import date.jalaunup.Config.url_add;

public class worker_profileActivity extends AppCompatActivity {
    SessionManager session;
    TextView username,email,tv_parent,tv_child,txtadd,txtteh;
    EditText tv_expyear,ed_add;
    Spinner sp_tehsil;
    String str_add,str_tehsil;
    ArrayList<String> arrayList_tehsil;
    ArrayAdapter<String> arrayAdapter_tehsil;
    Button logout,back,profile;
    Spinner sp_parent,sp_child;
    ArrayList<String> arrayList_parent;
    ArrayList<String> arrayList_Civil,arrayList_Electrical,arrayList_Jal_Sansaadan,arrayList_Computer,arrayList_Education,arrayList_Official_Work;
    ArrayAdapter<String> arrayAdapter_parent;
    ArrayAdapter<String> arrayAdapter_child;
    String str_category1,str_subcategory1,str_expyear1,str_username,str_email,str_role,str_category,str_subcategory,str_expyear,stradd,strtehsil;
    String url =url_add.worker_update;
    public static final String UPLOAD_KEY = "image";
    public static final String TAG = "MY MESSAGE";
    private int PICK_IMAGE_REQUEST = 1;
    private Button buttonChoose;
    private Button buttonUpload;
    private Button buttonView;
    private ImageView imageView;
    private Bitmap bitmap;
    private Uri filePath;
    public static final String UPLOAD_URL = url_add.worker_upload_id;

    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worker_profile);
        session = new SessionManager(getApplicationContext());
        session.checkLogin();
        String roleNew =  session.checkWorkerNew(session);
        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
        HashMap<String, String> user = session.getUserDetails();
        str_username = user.get(SessionManager.KEY_NAME);
        str_email = user.get(SessionManager.KEY_EMAIL);
        str_role = user.get(SessionManager.KEY_ROLE);
        str_category = user.get(SessionManager.KEY_CATEGORY);
        str_subcategory = user.get(SessionManager.KEY_SUBCATEGORY);
        str_expyear = user.get(SessionManager.KEY_EXPYEAR);
        stradd = user.get(SessionManager.KEY_ADDRESS);
        strtehsil = user.get(SessionManager.KEY_TEHSIL);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        txtadd = findViewById(R.id.txtAdd);
        txtteh = findViewById(R.id.txtTeh);
        tv_parent = findViewById(R.id.tv_parent1);
        tv_child = findViewById(R.id.tv_child1);
        tv_expyear = findViewById(R.id.tv_expyear1);
        tv_expyear.setFilters(new InputFilter[]{new integerMinMax.InputFilterMinMax("1", "30")});
        username.setText("Welcome " + str_username );
        email.setText("Your Mobile No. " + str_email);
        sp_parent = (Spinner) findViewById(R.id.parent);
        sp_child = (Spinner) findViewById(R.id.child);
        sp_tehsil = (Spinner) findViewById(R.id.tehsil);
        ed_add = findViewById(R.id.Address);
        buttonChoose = (Button) findViewById(R.id.ChooseId);
        buttonUpload = (Button) findViewById(R.id.UploadId);
        buttonView = (Button) findViewById(R.id.ViewId);

        imageView = (ImageView) findViewById(R.id.imageId);

        DisplayWorkerDetail(str_email);
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
        arrayList_parent = new ArrayList<>();
        arrayList_parent.add("Civil");
        arrayList_parent.add("Electrical");
        arrayList_parent.add("Jal Sansaadan");
        arrayList_parent.add("Computer");
        arrayList_parent.add("Education");
        arrayList_parent.add("Official Work");
        arrayAdapter_parent = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList_parent);
        sp_parent.setAdapter(arrayAdapter_parent);
        arrayList_Civil = new ArrayList<>();
        arrayList_Civil.add("Civil Labour");
        arrayList_Civil.add("Construction Mistree");
        arrayList_Civil.add("Civil Supplier");
        arrayList_Civil.add("Civil JCB");
        arrayList_Civil.add("Painter");
        arrayList_Civil.add("Plumber");
        arrayList_Civil.add("Electrician");
        arrayList_Civil.add("Carpenter");
        arrayList_Civil.add("Tiles Mistree");
        arrayList_Civil.add("Jaal Mistree");
        arrayList_Electrical = new ArrayList<>();
        arrayList_Electrical.add("Electrical Labour");
        arrayList_Electrical.add("Electrician");
        arrayList_Electrical.add("Heavy Electrical Supplier");
        arrayList_Jal_Sansaadan = new ArrayList<>();
        arrayList_Jal_Sansaadan.add("Irrigation Labour");
        arrayList_Jal_Sansaadan.add("Irrigation Mistree");
        arrayList_Jal_Sansaadan.add("Irrigation JCB");
        arrayList_Jal_Sansaadan.add("Irrigation Supplier");
        arrayList_Computer = new ArrayList<>();
        arrayList_Computer.add("Data Entry Operator");
        arrayList_Computer.add("Programmer");
        arrayList_Computer.add("Network Engineer");
        arrayList_Computer.add("Hardware Engineer");
        arrayList_Computer.add("Computer Equipment Supplier");
        arrayList_Computer.add("Computer Equipment Maintenance");
        arrayList_Education = new ArrayList<>();
        arrayList_Education.add("Primary Teaching");
        arrayList_Education.add("Junior Teaching");
        arrayList_Education.add("Higher Secondary Teaching");
        arrayList_Education.add("Secondary Teaching");
        arrayList_Education.add("Graduation Teaching");
        arrayList_Education.add("Post-Graduation Teaching");
        arrayList_Official_Work = new ArrayList<>();
        arrayList_Official_Work.add("Clerk");
        arrayList_Official_Work.add("Manager");
        arrayList_Official_Work.add("Peon");
        arrayList_Official_Work.add("Receptionist");
        sp_parent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_category1 =  parent.getItemAtPosition(position).toString();
                if (position == 0) {
                    arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList_Civil);
                    sp_child.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> child, View view, int position, long id) {
                            str_subcategory1 = child.getItemAtPosition(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> child) {
                        }
                    });
                }
                if (position == 1) {
                    arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList_Electrical);
                    sp_child.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> child, View view, int position, long id) {
                            str_subcategory1 = child.getItemAtPosition(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> child) {
                        }
                    });
                }
                if (position == 2) {
                    arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList_Jal_Sansaadan);
                    sp_child.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> child, View view, int position, long id) {
                            str_subcategory1 = child.getItemAtPosition(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> child) {
                        }
                    });
                }
                if (position == 3) {
                    arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList_Computer);
                    sp_child.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> child, View view, int position, long id) {
                            str_subcategory1 = child.getItemAtPosition(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> child) {
                        }
                    });
                }
                if (position == 4) {
                    arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList_Education);
                    sp_child.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> child, View view, int position, long id) {
                            str_subcategory1 = child.getItemAtPosition(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> child) {
                        }
                    });
                }
                if (position == 5) {
                    arrayAdapter_child = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList_Official_Work);
                    sp_child.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> child, View view, int position, long id) {
                            str_subcategory1 = child.getItemAtPosition(position).toString();
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
                Intent intent = new Intent(worker_profileActivity.this, WelcomewActivity.class);
                startActivity(intent);
            }
        });
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    session.logoutUser();
            }
        });
        buttonChoose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    showFileChooser();
                }
        });
        buttonUpload.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                uploadImage();
            }
        });
        buttonView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                viewImage();
            }
        });
         profile = findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                str_expyear1 = tv_expyear.getText().toString().trim();
                str_add = ed_add.getText().toString().trim();
                session.updateWorkerProfile(str_category1,str_subcategory1,str_expyear1,str_add,str_tehsil);
                ProgressDialog progressDialog = new ProgressDialog(worker_profileActivity.this);
                progressDialog.setMessage("Please Wait..");
                progressDialog.show();
                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Toast.makeText(worker_profileActivity.this, response, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(worker_profileActivity.this, WelcomewActivity.class);
                        startActivity(intent);
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
                        params.put("fullname", str_username);
                        params.put("mobile", str_email);
                        params.put("category", str_category1);
                        params.put("sub_category", str_subcategory1);
                        params.put("expyear", str_expyear1);
                        params.put("address", str_add);
                        params.put("tehsil", str_tehsil);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(worker_profileActivity.this);
                requestQueue.add(request);
            }
        });
    }
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void uploadImage(){
        class UploadImage extends AsyncTask<Bitmap,Void,String>{
            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(worker_profileActivity.this, "Uploading...", null,true,true);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }
            @Override
            protected String doInBackground(Bitmap... params) {
                Bitmap bitmap = params[0];
                String uploadImage = getStringImage(bitmap);
                HashMap<String,String> data = new HashMap<>();
                data.put(UPLOAD_KEY, uploadImage);
                data.put("mobile", str_email);
                String result = rh.sendPostRequest(UPLOAD_URL,data);
                return result;
            }
        }

        UploadImage ui = new UploadImage();
        ui.execute(bitmap);
    }

    private void viewImage() {
        startActivity(new Intent(this, ImageListView.class));
    }

    /**************************************************************************************************************/
    public void DisplayWorkerDetail(String currentWorkerId) {
        class BindWorkerMaster extends AsyncTask<Void, Void, String> {
            ProgressDialog pdLoading = new ProgressDialog(worker_profileActivity.this);

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
                params.put("id", currentWorkerId);
                return requestHandler.sendPostRequest(url_add.worker_by_id, params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                pdLoading.dismiss();
                try {
                    JSONObject obj = new JSONObject(s);
                    txtadd.setText(obj.getString("WorkerAdd"));
                    txtteh.setText(obj.getString("WorkerTeh"));
                    tv_parent.setText(obj.getString("WorkerField"));
                    tv_child.setText(obj.getString("WorkerWork"));
                    tv_expyear.setText(obj.getString("WorkerExp"));

                } catch (Exception e) {
                }
            }
        }
        BindWorkerMaster obj = new BindWorkerMaster();
        obj.execute();
    }
    /************************************************************************************************************/
}