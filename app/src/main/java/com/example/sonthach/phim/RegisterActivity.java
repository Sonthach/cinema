package com.example.sonthach.phim;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sonthach.phim.Load.LoginRespone;
import com.example.sonthach.phim.Load.Movie;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Call;
import retrofit2.Response;

import static android.support.v4.app.ActivityCompat.requestPermissions;
import static android.support.v4.content.PermissionChecker.checkSelfPermission;

public class RegisterActivity extends AppCompatActivity {
    public static String Preference = "SaveToken";
    public EditText edtName, edtEmail, edtPassword,edtConfirm;
    Button Register, Login;
    ProgressBar progressBar;
    APIService apiService = APIUtils.getAPIService();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        checkconnection();

        SharedPreferences pre = getSharedPreferences("SaveToken",MODE_PRIVATE);
        SharedPreferences.Editor editor = pre.edit();

        String s = pre.getString("token","");
        //String i = pre.getString("id","");
        if(!s.equals(""))
        {
            finish();
            Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        edtName = findViewById(R.id.txtregname);
        edtEmail = findViewById(R.id.txtregemail);
        edtPassword = findViewById(R.id.txtregpassword);
        edtConfirm = findViewById(R.id.txtcomfirmpassword);
        progressBar = findViewById(R.id.progress_bar);

        Register = findViewById(R.id.buttonregister);
        Login = findViewById(R.id.buttonlogin);


        edtEmail.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                Register.setVisibility(View.GONE);
                final String mName = edtName.getText().toString().trim();
                String mEmail = edtEmail.getText().toString().trim();
                String mPassword = edtPassword.getText().toString().trim();



                if(mName.length() == 0)
                {
                    ThongBao.Toast(RegisterActivity.this,"Vui lòng nhập Tên");
                }

                if(mEmail.length() == 0)
                {
                    ThongBao.Toast(RegisterActivity.this,"Vui lòng nhập Email");
                }

                if(mPassword.length() == 0)
                {
                    ThongBao.Toast(RegisterActivity.this,"Vui lòng nhập Mật khẩu");
                }

                if(!edtConfirm.getText().toString().equals(mPassword)){
                    ThongBao.Toast(RegisterActivity.this,"Mật khẩu xác nhận không đúng. Vui lòng nhập lại");
                }

                Call<ResponseBody> call = RetrofitClient
                        .getInstance()
                        .getApi()
                        .signUp(mEmail,mName,mPassword);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        ResponseBody responseBody = response.body();
                        if(response.isSuccessful())
                        {
                            ThongBao.Toast(RegisterActivity.this,"Đăng ký thành công!");
                            progressBar.setVisibility(View.GONE);
                            Register.setVisibility(View.VISIBLE);

                            SharedPreferences pre =getSharedPreferences("SaveToken",MODE_PRIVATE);
                            String token = pre.getString("token","");
                            String id = pre.getString("id","");
                            SharedPreferences.Editor editor = pre.edit();
                            editor.clear();
                            Intent intent = new Intent(RegisterActivity.this,DanhsachphimAcitivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        ThongBao.Toast(RegisterActivity.this,t.getMessage());
                        progressBar.setVisibility(View.GONE);
                        Register.setVisibility(View.VISIBLE);
                    }
                });
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,MainActivity.class));
            }
        });
    }
    private void checkconnection(){

            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);

            }

            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);

            }

            if (checkSelfPermission(Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.INTERNET},
                        1);

            }

       /* if (checkSelfPermission(Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.GET_ACCOUNTS},
                    1);

        }*/

    }
}
