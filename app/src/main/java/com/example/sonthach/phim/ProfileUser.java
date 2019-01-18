package com.example.sonthach.phim;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.sonthach.phim.Load.LoginRespone;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileUser extends AppCompatActivity {
    Button btndangxuat,btnxacnhan,btnhuy;
    TextView txtdoimatkhau,txtnameuser,txtemailuser,txtpassworduser;
    Toolbar toolbar;
    APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);

        btndangxuat = findViewById(R.id.btndangxuat);
        toolbar = findViewById(R.id.tbuser);
        txtnameuser = findViewById(R.id.txtnameuser);
        txtemailuser = findViewById(R.id.txtemailuser);
        txtpassworduser = findViewById(R.id.txtmatkhauuser);

        txtdoimatkhau = findViewById(R.id.txtdoimatkhau);
        actionBar();
        //getUser();
        btndangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileUser.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
                builder.setTitle("Bạn có chắn chắn muốn đăng xuất ?");
                builder.setMessage("Hãy lựa chọn bên dưới để xác nhận !");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferences sharedPreferences = getSharedPreferences("SaveToken",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.clear();
                        editor.commit();
                        finish();
                        startActivity(new Intent(ProfileUser.this,MainActivity.class));
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });

        txtdoimatkhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(ProfileUser.this);
                dialog.setTitle("Đổi nật khẩu");
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dialog_change_password);
                btnxacnhan = dialog.findViewById(R.id.btnxacnhandoimatkhau);
                btnhuy = dialog.findViewById(R.id.btnhuy);
                btnxacnhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
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
    }

    private void actionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void getUser(){
        final String getToken = getIntent().getExtras().getString("getToken");

        apiService = APIUtils.getAPIService();
        apiService.getUser(getToken).enqueue(new Callback<LoginRespone>() {
            @Override
            public void onResponse(Call<LoginRespone> call, Response<LoginRespone> response) {
                LoginRespone loginRespone = response.body();

                if(response.isSuccessful())
                {
                    txtnameuser.setText(loginRespone.getUser().getName());
                    txtemailuser.setText(loginRespone.getUser().getEmail());
                    txtemailuser.setText(loginRespone.getUser().getPassword());
                }
            }

            @Override
            public void onFailure(Call<LoginRespone> call, Throwable t) {

            }
        });
    }
}
