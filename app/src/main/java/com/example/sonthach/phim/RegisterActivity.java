package com.example.sonthach.phim;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.sonthach.phim.Load.SignupResponse;

import retrofit2.Callback;
import retrofit2.Call;
import retrofit2.Response;

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

        Anhxa();


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
                    ThongBaoActivity.Toast(RegisterActivity.this,"Vui lòng nhập Tên");
                    progressBar.setVisibility(View.GONE);
                    Register.setVisibility(View.VISIBLE);
                }

                if(mEmail.length() == 0)
                {
                    ThongBaoActivity.Toast(RegisterActivity.this,"Vui lòng nhập Email");
                    progressBar.setVisibility(View.GONE);
                    Register.setVisibility(View.VISIBLE);
                }

                if(mPassword.length() == 0)
                {
                    ThongBaoActivity.Toast(RegisterActivity.this,"Vui lòng nhập Mật khẩu");
                    progressBar.setVisibility(View.GONE);
                    Register.setVisibility(View.VISIBLE);
                }

                if(!edtConfirm.getText().toString().equals(mPassword)){
                    ThongBaoActivity.Toast(RegisterActivity.this,"Mật khẩu xác nhận không đúng. Vui lòng nhập lại");
                    progressBar.setVisibility(View.GONE);
                    Register.setVisibility(View.VISIBLE);
                }

                Call<SignupResponse> call = RetrofitClient
                        .getInstance()
                        .getApi()
                        .signUp(mEmail,mName,mPassword);
                call.enqueue(new Callback<SignupResponse>() {
                    @Override
                    public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                        SignupResponse signupResponse = response.body();
                        if(response.isSuccessful())
                        {
                            ThongBaoActivity.Toast(RegisterActivity.this,"Đăng ký thành công!");
                            progressBar.setVisibility(View.GONE);
                            Register.setVisibility(View.VISIBLE);
                            SharedPreferences pre =getSharedPreferences("SaveToken",MODE_PRIVATE);
                            SharedPreferences.Editor editor = pre.edit();
                            editor.clear();
                            String saveToken = signupResponse.getToken();
                            String saveId = signupResponse.getUser().getId();
                            editor.putString("token",saveToken);
                            editor.putString("id",saveId);
                            editor.commit();
                            Intent intent = new Intent(RegisterActivity.this,DanhsachphimAcitivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<SignupResponse> call, Throwable t) {
                        ThongBaoActivity.Toast(RegisterActivity.this,t.getMessage());
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

    private void Anhxa() {
        edtName = findViewById(R.id.txtregname);
        edtEmail = findViewById(R.id.txtregemail);
        edtPassword = findViewById(R.id.txtregpassword);
        edtConfirm = findViewById(R.id.txtcomfirmpassword);
        progressBar = findViewById(R.id.progress_bar);

        Register = findViewById(R.id.buttonregister);
        Login = findViewById(R.id.buttonlogin);
    }

    private void checkconnection(){

           /* if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);

            }

            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);

            }
*/
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
