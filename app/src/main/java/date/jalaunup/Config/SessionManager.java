package date.jalaunup.Config;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

import date.jalaunup.MainActivity;

public class SessionManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "DatePref";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_ROLE = "role";
    public static String KEY_CATEGORY = "category";
    public static String KEY_SUBCATEGORY = "subcategory";
    public static String KEY_EXPYEAR = "expyear";


    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(String username, String email, String category, String sub_category, String exp_year, String role){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_NAME, username);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_CATEGORY, category);
        editor.putString(KEY_SUBCATEGORY, sub_category);
        editor.putString(KEY_EXPYEAR, exp_year);
        editor.putString(KEY_ROLE, role);
        editor.commit();
    }
    public void updateWorkerProfile( String category, String sub_category, String exp_year){
        editor.putString(KEY_CATEGORY, category);
        editor.putString(KEY_SUBCATEGORY, sub_category);
        editor.putString(KEY_EXPYEAR, exp_year);
        editor.commit();
    }

    public void checkLogin(){
        if(!this.isLoggedIn() ){
            Intent i = new Intent(_context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }
    }

    public void checkWorker(){
        if(!this.isLoggedIn() || pref.getString(KEY_ROLE,null)!="w"){
            Intent i = new Intent(_context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }
        if(pref.getString(KEY_ROLE,null)=="3"){
            Intent i = new Intent(_context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }
    }
    public void checkEmployer(){
        if(pref.getString(KEY_ROLE,null)=="1"){
            Intent i = new Intent(_context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }
        if(pref.getString(KEY_ROLE,null)=="3"){
            Intent i = new Intent(_context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }
    }
    public void checkAdmin(){
        if(pref.getString(KEY_ROLE,null)=="1"){
            Intent i = new Intent(_context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }
        if(pref.getString(KEY_ROLE,null)=="2"){
            Intent i = new Intent(_context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }
    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        user.put(KEY_ROLE, pref.getString(KEY_ROLE, null));
        user.put(KEY_CATEGORY, pref.getString(KEY_CATEGORY, null));
        user.put(KEY_SUBCATEGORY, pref.getString(KEY_SUBCATEGORY, null));
        user.put(KEY_EXPYEAR, pref.getString(KEY_EXPYEAR, null));
        return user;
    }

    public void logoutUser(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(_context, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}