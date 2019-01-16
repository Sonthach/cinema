package com.example.sonthach.phim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sonthach.phim.Load.LoginRespone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText edtEmail, edtPassword;
    Button btRegister, btLogin;
    APIService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    private void login(){
        String mEmail = edtEmail.getText().toString();
        String mPassword = edtPassword.getText().toString();



        apiService = APIUtils.getAPIService();
        apiService.userLogin(mEmail,mPassword).enqueue(new Callback<LoginRespone>() {
            @Override
            public void onResponse(Call<LoginRespone> call, Response<LoginRespone> response) {
                LoginRespone loginRespone = response.body();
                if(response.isSuccessful()) {
                    ThongBao.Toast(MainActivity.this,"Đăng nhập thành công!!");
                    //SharePrefManager.getmInstance(MainActivity.this).saveUsers(loginRespone.getToken());
                    Intent intent = new Intent(MainActivity.this,DanhsachphimAcitivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
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
