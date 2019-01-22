package com.example.sonthach.phim;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.sonthach.phim.Load.ErrorResponse;
import com.example.sonthach.phim.Load.LoginRespone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText edtEmail, edtPassword;
    Button btRegister, btLogin;
    TextView txtQuenmatkhau;
    APIService apiService;
    ProgressBar progressBar;
    String mPassword;
    Button btnxacnhan,btnhuy;
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
            Intent intent = new Intent(MainActivity.this,DanhsachphimAcitivity.class);
            startActivity(intent);
            finish();
        }
        Anhxa();


        txtQuenmatkhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setTitle("Quên mật khẩu");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.dialog_quenmatkhau);
                btnxacnhan = dialog.findViewById(R.id.btnxacnhanquenmatkhau);
                btnhuy = dialog.findViewById(R.id.btnhuyquenmatkhau);
                final EditText edtquenmatkhau = dialog.findViewById(R.id.edtquenmatkhau);

                btnxacnhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPassword = edtquenmatkhau.getText().toString().trim();

                        if (mPassword.length() == 0)
                        {
                            ThongBaoActivity.Toast(MainActivity.this,"Vui lòng nhập Email!");
                            return;
                        }
                        apiService = APIUtils.getAPIService();
                        apiService.forgotPassword(mPassword).enqueue(new Callback<ErrorResponse>() {
                            @Override
                            public void onResponse(Call<ErrorResponse> call, Response<ErrorResponse> response) {
                                ErrorResponse errorResponse = response.body();
                                if (response.isSuccessful()) {
                                    if (errorResponse.getStatus() == 200) {
                                        ThongBaoActivity.Toast(MainActivity.this, "Yêu cầu thay đổi " +
                                                "mật khẩu thành công. Vui lòng kiểm tra email ");
                                        dialog.cancel();
                                    } else {
                                        ThongBaoActivity.Toast(MainActivity.this, "Yêu cầu thay đổi " +
                                                "mật khẩu thất bại!Vui lòng thử lại. ");
                                        dialog.cancel();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<ErrorResponse> call, Throwable t) {
                                ThongBaoActivity.Toast(MainActivity.this, "Yêu cầu thay đổi " +
                                        "mật khẩu thất bại!Vui lòng thử lại. ");
                                dialog.cancel();
                            }
                        });

                    }
                });

                btnhuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });

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

    private void Anhxa() {
        progressBar = findViewById(R.id.idproressbar);
        edtEmail = findViewById(R.id.txtloginemail);
        edtPassword = findViewById(R.id.txtloginpassword);
        btRegister = findViewById(R.id.buttonreg);
        txtQuenmatkhau = findViewById(R.id.txtquenmatkhau);
        btLogin = findViewById(R.id.buttonlogin);
    }

    public void login(){
        progressBar.setVisibility(View.VISIBLE);
        btLogin.setVisibility(View.GONE);
        String mEmail = edtEmail.getText().toString();
        String mPassword = edtPassword.getText().toString();

        if(mEmail.length() == 0 || mPassword.length() == 0)
        {
            ThongBaoActivity.Toast(MainActivity.this,"Vui lòng nhập đủ thông tin!");
            progressBar.setVisibility(View.GONE);
            btLogin.setVisibility(View.VISIBLE);
        }

        apiService = APIUtils.getAPIService();
        apiService.userLogin(mEmail,mPassword).enqueue(new Callback<LoginRespone>() {
            @Override
            public void onResponse(Call<LoginRespone> call, Response<LoginRespone> response) {
                LoginRespone loginRespone = response.body();
                if(response.isSuccessful()) {
                    ThongBaoActivity.Toast(MainActivity.this,"Đăng nhập thành công!!");
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
                    ThongBaoActivity.Toast(MainActivity.this,"Sai Email hoặc Mật khẩu");
                    progressBar.setVisibility(View.GONE);
                    btLogin.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<LoginRespone> call, Throwable t) {
                ThongBaoActivity.Toast(MainActivity.this,t.getMessage());
            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();

    }

    /*private void savePfe(){
        String mEmail = edtEmail.getText().toString();
        String mPassword = edtPassword.getText().toString();

        apiService = APIUtils.getAPIService();
        apiService.userLogin(mEmail,mPassword).enqueue(new Callback<LoginRespone>() {
            @Override
            public void onResponse(Call<LoginRespone> call, Response<LoginRespone> response) {
                LoginRespone loginRespone = response.body();
                if(response.isSuccessful()) {
                    ThongBaoActivity.Toast(MainActivity.this,"Đăng nhập thành công!!");
                    SharedPreferences pre = getSharedPreferences("SaveToken",MODE_PRIVATE);
                    SharedPreferences.Editor editor = pre.edit();
                    editor.clear();
                    loginRespone.getToken();
                    String save = loginRespone.getToken();
                    editor.putString("token",save);
                    editor.commit();
                    Intent intent = new Intent(MainActivity.this,DanhsachphimAcitivity.class);
                    intent.putExtra("getToken", loginRespone.getToken());
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }else {
                    ThongBaoActivity.Toast(MainActivity.this,"Sai Email hoặc Mật khẩu");
                }
            }

            @Override
            public void onFailure(Call<LoginRespone> call, Throwable t) {
                ThongBaoActivity.Toast(MainActivity.this,t.getMessage());
            }
        });
    }*/
}
