package com.example.sonthach.phim;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.sonthach.phim.Load.LoginRespone;
import com.example.sonthach.phim.Load.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText edtEmail, edtPassword;
    Button btRegister, btLogin;
    APIService apiService;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences pre = getSharedPreferences("SaveToken",MODE_PRIVATE);
        SharedPreferences.Editor editor = pre.edit();

        String s = pre.getString("token","");
        String i = pre.getString("id","");
        if(!s.equals("") && !i.equals(""))
        {
            finish();
            Intent intent = new Intent(MainActivity.this,DanhsachphimAcitivity.class);
            startActivity(intent);
        }
        progressBar = findViewById(R.id.idproressbar);
        edtEmail = findViewById(R.id.txtloginemail);
        edtPassword = findViewById(R.id.txtloginpassword);
        btRegister = findViewById(R.id.buttonreg);
        btLogin = findViewById(R.id.buttonlogin);




        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));
            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    public void login(){
        progressBar.setVisibility(View.VISIBLE);
        btLogin.setVisibility(View.GONE);
        String mEmail = edtEmail.getText().toString();
        String mPassword = edtPassword.getText().toString();

        if(mEmail.length() == 0 || mPassword.length() == 0)
        {
            ThongBao.Toast(MainActivity.this,"Vui lòng nhập đủ thông tin!");
        }

        apiService = APIUtils.getAPIService();
        apiService.userLogin(mEmail,mPassword).enqueue(new Callback<LoginRespone>() {
            @Override
            public void onResponse(Call<LoginRespone> call, Response<LoginRespone> response) {
                LoginRespone loginRespone = response.body();
                if(response.isSuccessful()) {
                    ThongBao.Toast(MainActivity.this,"Đăng nhập thành công!!");
                    SharedPreferences pre = getSharedPreferences("SaveToken",MODE_PRIVATE);
                    SharedPreferences.Editor editor = pre.edit();
                    editor.clear();
                    loginRespone.getToken();
                    loginRespone.getUser().getId();
                    String saveToken = loginRespone.getToken();
                    String saveId = loginRespone.getUser().getId();
                    editor.putString("token",saveToken);
                    editor.putString("id",saveId);
                    editor.commit();
                    Intent intent = new Intent(MainActivity.this,DanhsachphimAcitivity.class);
                    progressBar.setVisibility(View.GONE);
                    btLogin.setVisibility(View.VISIBLE);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }else {
                    ThongBao.Toast(MainActivity.this,"Sai Email hoặc Mật khẩu");
                    progressBar.setVisibility(View.GONE);
                    btLogin.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<LoginRespone> call, Throwable t) {
                ThongBao.Toast(MainActivity.this,t.getMessage());
            }
        });

    }



    @Override
    protected void onResume() {
        super.onResume();

    }

    private void savePfe(){
        String mEmail = edtEmail.getText().toString();
        String mPassword = edtPassword.getText().toString();

        apiService = APIUtils.getAPIService();
        apiService.userLogin(mEmail,mPassword).enqueue(new Callback<LoginRespone>() {
            @Override
            public void onResponse(Call<LoginRespone> call, Response<LoginRespone> response) {
                LoginRespone loginRespone = response.body();
                if(response.isSuccessful()) {
                    ThongBao.Toast(MainActivity.this,"Đăng nhập thành công!!");
                    SharedPreferences pre = getSharedPreferences("SaveToken",MODE_PRIVATE);
                    SharedPreferences.Editor editor = pre.edit();
                    editor.clear();
                    loginRespone.getToken();
                    String save = loginRespone.getToken();
                    editor.putString("token",save);
                    editor.commit();
                    Intent intent = new Intent(MainActivity.this,DanhsachphimAcitivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }else {
                    ThongBao.Toast(MainActivity.this,"Sai Email hoặc Mật khẩu");
                }
            }

            @Override
            public void onFailure(Call<LoginRespone> call, Throwable t) {
                ThongBao.Toast(MainActivity.this,t.getMessage());
            }
        });
    }
}
