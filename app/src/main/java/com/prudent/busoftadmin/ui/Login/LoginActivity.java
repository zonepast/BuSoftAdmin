package com.prudent.busoftadmin.ui.Login;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.prudent.busoftadmin.Helper.Constant;
import com.prudent.busoftadmin.R;
import com.prudent.busoftadmin.data.api.model.Login.Response.LoginResponse;
import com.prudent.busoftadmin.data.api.model.Login.Response.Table1;
import com.prudent.busoftadmin.data.db.Local.Login.LoginRealm;
import com.prudent.busoftadmin.data.pref.SessionManager;
import com.prudent.busoftadmin.ui.Dashboard.DashboardActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;

public class LoginActivity extends AppCompatActivity implements LoginView{

    @BindView(R.id.textView_logo)
    TextView textViewLogo;
    @BindView(R.id.edt_email_login)
    EditText edtEmailLogin;
    @BindView(R.id.txt_lyt_email_login)
    TextInputLayout txtLytEmailLogin;
    @BindView(R.id.edt_password_login)
    EditText edtPasswordLogin;
    @BindView(R.id.txt_lyt_password_login)
    TextInputLayout txtLytPasswordLogin;
    @BindView(R.id.btn_login)
    Button btnLogin;

    private LoginPresenter loginPresenter;
    private ProgressDialog progressDialog;
    private Realm realm;
    private SessionManager sessionManager;
    Boolean doubleBackToExitPressedOnce = false;
    int PERMISSION_ALL = 1;
    String[] PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sessionManager = new SessionManager(this);
        realm = Realm.getDefaultInstance();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#1397DA"));
        }

        if (sessionManager.isLoggedIn()){
            startActivity(new Intent(getApplicationContext(),DashboardActivity.class));
        }

        if(!hasPermissions(this, PERMISSIONS)){
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        initPresenter();
        onAttach();

        SetProgress();
    }

    private void SetProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
    }

    @Override
    public void Error(String Title, String Message) {
        ErrorDialog(Title, Message);
    }

    @Override
    public void ShowLoginResponse(LoginResponse response) {
            if (response.getTable().get(0).getSuccess()==1){
                realm.beginTransaction();
                RealmResults<LoginRealm> results1 = realm.where(LoginRealm.class).findAll();
                results1.deleteAllFromRealm();
                for (int i=0;i<response.getTable1().size();i++){
                    LoginRealm loginRealm = realm.createObject(LoginRealm.class);
                    loginRealm.setXname(response.getTable1().get(i).getXname());
                    loginRealm.setXcode(response.getTable1().get(i).getXcode());
                    loginRealm.setXmaster(response.getTable1().get(i).getXmaster());
                }
                RealmResults<LoginRealm> corp = realm.where(LoginRealm.class)
                        .equalTo("xmaster", "CORPCENTRE")
                        .findAll();
                RealmResults<LoginRealm> user = realm.where(LoginRealm.class)
                        .equalTo("xmaster", "USER")
                        .findAll();
                String CorpCode = corp.get(0).getXcode();
                String CorpName = corp.get(0).getXname();
                String UserCode = user.get(0).getXcode();
                String UserName = user.get(0).getXname();
                sessionManager.createLoginSession(CorpName,CorpCode,UserName,UserCode);
                realm.commitTransaction();
                Toast.makeText(getApplicationContext(),response.getTable().get(0).getMessage(),Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
            }else {
                Toast.makeText(getApplicationContext(),response.getTable().get(0).getMessage(),Toast.LENGTH_SHORT).show();
            }
            //SetUpEmployeeSpinner(response.getTable());
    }

    @Override
    public void ShowProgress() {
        progressDialog.show();
    }

    @Override
    public void HideProgress() {
        progressDialog.dismiss();
    }

    private void ErrorDialog(String Title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(Title);
        builder.setCancelable(false);
        builder.setMessage(Message);
        builder.setPositiveButton("RETRY",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        validation();
                    }
                });
        builder.show();
    }

    private void initPresenter() {
        loginPresenter = new LoginPresenter();
    }

    @Override
    public void onAttach() {
        loginPresenter.onAttach(this);
    }

    @Override
    public void onDetach() {
        loginPresenter.onDetach();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        onDetach();
        super.onDestroy();
        realm.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
            startActivity(intent);
            finish();
            System.exit(0);
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    @OnClick(R.id.btn_login)
    public void onClick() {
        validation();
    }

    private void validation() {
        boolean failFlag = false;
        if (edtEmailLogin.getText().toString().trim().length() == 0) {
            failFlag = true;
            txtLytEmailLogin.setError("Username Cannot be Empty");
        }
        /*if (!isValidEmail(edtEmailLogin.getText().toString())) {
            failFlag = true;
            txtLytEmailLogin.setError("Email is invalid");
        }*/
        if (edtPasswordLogin.getText().toString().trim().length() == 0) {
            failFlag = true;
            txtLytPasswordLogin.setError("Password Cannot be Empty");
        }
        if (!failFlag) {
            String email = edtEmailLogin.getText().toString();
            String password = edtPasswordLogin.getText().toString();
            loginPresenter.Login("Login","btngetcorporate", Constant.CORPCENTRE,"","",email,password);
            //signinPresenter.SubmitLogin(email, password, Config.CORPCENTRE);
        }
    }

    public boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
}
