package com.example.sonthach.phim;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sonthach.phim.Load.ErrorResponse;
import com.example.sonthach.phim.Load.LoginRespone;
import com.example.sonthach.phim.Load.User;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileUser extends AppCompatActivity {
    Button btndangxuat,btnxacnhan,btnhuy,btnxacnnhandoiten,btnxacnhanhuyten;
    TextView txtdoimatkhau,txtnameuser,txtemailuser,txtdanhsachphim;
    Toolbar toolbar;
    APIService apiService;
    String moldPassword,mnewPassword,mCofirmpassword,imgviewChangeName;
    Button imageViewchangeName;
    ImageView imgchangeavatar;
    String IMAGE_PATH ="";
    File file;
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    final int REQUEST_TAKE_PHOTO = 123;
    final int REQUEST_CHOOSE_PHOTO = 321;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);

        btndangxuat = findViewById(R.id.btndangxuat);
        toolbar = findViewById(R.id.tbuser);
        txtnameuser = findViewById(R.id.txtnameuser);
        txtemailuser = findViewById(R.id.txtemailuser);
        imageViewchangeName = findViewById(R.id.imgchangename);
        txtdoimatkhau = findViewById(R.id.txtdoimatkhau);
        imgchangeavatar = findViewById(R.id.imgposter);
        txtdanhsachphim = findViewById(R.id.txxdanhsachphim);

        txtdanhsachphim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileUser.this,MyListMovie.class);
                startActivity(intent);
            }
        });


        actionBar();
        getUser();

        imgchangeavatar.setClickable(true);
        imgchangeavatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectDialog();


            }
        });
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
                dialog.setTitle("Đổi mật khẩu");
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dialog_change_password);
                btnxacnhan = dialog.findViewById(R.id.btnxacnhandoimatkhau);
                btnhuy = dialog.findViewById(R.id.btnhuy);
                final EditText edtoldpassword = dialog.findViewById(R.id.edtmatkhaucu);
                final EditText edtnewpassword = dialog.findViewById(R.id.edtmatkhaumoi);
                final EditText edtcofirmpassword = dialog.findViewById(R.id.edtconfirmmatkhau);
                btnxacnhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        moldPassword = edtoldpassword.getText().toString();
                        mnewPassword = edtnewpassword.getText().toString();
                        mCofirmpassword = edtcofirmpassword.getText().toString();

                        if(moldPassword.length() == 0)
                        {
                            ThongBao.Toast(ProfileUser.this,"Vui lòng nhập mật khẩu cũ");
                            return;
                        }

                        if (!mnewPassword.equals(mCofirmpassword)){
                            ThongBao.Toast(ProfileUser.this,"Xác nhận mật khẩu mới không đúng");
                            return;
                        }

                        SharedPreferences pre = getSharedPreferences("SaveToken",MODE_PRIVATE);
                        SharedPreferences.Editor editor = pre.edit();
                        String getToken = pre.getString("token","");
                        apiService = APIUtils.getAPIService();
                        apiService.changePassword(getToken,moldPassword,mnewPassword).enqueue(new Callback<ErrorResponse>() {
                            @Override
                            public void onResponse(Call<ErrorResponse> call, Response<ErrorResponse> response) {
                                ErrorResponse errorResponse = response.body();
                                if(response.isSuccessful()){
                                    if(errorResponse.getStatus() == 200){
                                        ThongBao.Toast(ProfileUser.this,"Đổi mật khẩu thành công!!");
                                    }else if(errorResponse.getErrorMessage().getStatus() == 400){
                                        ThongBao.Toast(ProfileUser.this,"Mật khẩu cũ không đúng!");
                                    }
                                }
                                dialog.cancel();
                            }

                            @Override
                            public void onFailure(Call<ErrorResponse> call, Throwable t) {
                                ThongBao.Toast(ProfileUser.this,"Đổi mật khẩu thất bại !");
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


        txtnameuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(ProfileUser.this);
                dialog.setTitle("Đổi tên");
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dialog_change_name);
                btnxacnnhandoiten = dialog.findViewById(R.id.btnxacnhandoiten);
                btnxacnhanhuyten = dialog.findViewById(R.id.btnhuyten);
                final EditText edtTenmoi = dialog.findViewById(R.id.edtnewname);

                btnxacnnhandoiten.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        imgviewChangeName = edtTenmoi.getText().toString();

                        if(imgviewChangeName.length() == 0){
                            ThongBao.Toast(ProfileUser.this,"Vui lòng nhập Tên muốn đổi!");
                        }
                        SharedPreferences pre = getSharedPreferences("SaveToken",MODE_PRIVATE);
                        SharedPreferences.Editor editor = pre.edit();
                        String getToken = pre.getString("token","");

                        apiService = APIUtils.getAPIService();
                        apiService.changeName(getToken,imgviewChangeName).enqueue(new Callback<ErrorResponse>() {
                            @Override
                            public void onResponse(Call<ErrorResponse> call, Response<ErrorResponse> response) {
                                ErrorResponse errorResponse = response.body();
                                if(response.isSuccessful()){
                                    if(errorResponse.getStatus() == 200){
                                        ThongBao.Toast(ProfileUser.this,"Đổi tên thành công!!");
                                        txtnameuser.setText(imgviewChangeName);
                                    }
                                }
                                dialog.cancel();
                            }

                            @Override
                            public void onFailure(Call<ErrorResponse> call, Throwable t) {

                            }
                        });
                    }
                });

                btnxacnhanhuyten.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });

    }

    private void Permision() {
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ) {
            requestPermissions(new String[]{Manifest.permission.CAMERA},
                    MY_CAMERA_REQUEST_CODE);

        }
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_CAMERA_REQUEST_CODE);

        }

        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_CAMERA_REQUEST_CODE);

        }

        if (checkSelfPermission(Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.INTERNET},
                    MY_CAMERA_REQUEST_CODE);

        }
    }
    private void selectDialog() {
        android.app.AlertDialog.Builder pictureDialog = new android.app.AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {"Select photo from gallery" , "Capture photo from camera"};
        pictureDialog.setItems(pictureDialogItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i)
                {
                    case 0:
                        choosePhoto();
                        break;
                    case 1:
                        takepicture();
                        break;
                }
            }
        });
        pictureDialog.show();
    }

    private void takepicture() {
        Permision();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,REQUEST_TAKE_PHOTO);
    }

    private void choosePhoto() {
        Permision();
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,REQUEST_CHOOSE_PHOTO);
    }
    public String saveImage(Bitmap myBitmap, int requestCode) {
        Permision();
        if (requestCode == REQUEST_TAKE_PHOTO) {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
            File wallpaperDirectory = new File(
                    Environment.getExternalStorageDirectory() + "/image");
            // have the object build the directory structure, if needed.
            if (!wallpaperDirectory.exists()) {
                wallpaperDirectory.mkdirs();
            }

            try {
                File f = new File(wallpaperDirectory, Calendar.getInstance()
                        .getTimeInMillis() + ".jpg");
                f.createNewFile();
                FileOutputStream fo = new FileOutputStream(f);
                fo.write(bytes.toByteArray());
                MediaScannerConnection.scanFile(this,
                        new String[]{f.getPath()},
                        new String[]{"image/jpeg"}, null);
                fo.close();
                Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());
                //mImageFile = f;
                return f.getAbsolutePath();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return "";
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
        SharedPreferences pre = getSharedPreferences("SaveToken",MODE_PRIVATE);
        SharedPreferences.Editor editor = pre.edit();
        String getToken = pre.getString("token","");

        apiService = APIUtils.getAPIService();
        apiService.getUser(getToken).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User loginRespone = response.body();
                String url = "https://cinema-hatin.herokuapp.com";
                if(response.isSuccessful())
                {

                    txtnameuser.setText(loginRespone.getName());
                    txtemailuser.setText(loginRespone.getEmail());
                    Picasso.with(getApplicationContext()).load(url+loginRespone.getAvatarURL()).placeholder(R.drawable.avatardefault).
                            error(R.drawable.avatardefault).into(imgchangeavatar);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK) {
            if(requestCode == REQUEST_CHOOSE_PHOTO) {

                try {
                    Uri imageUri = data.getData();
                    IMAGE_PATH = ReadPathUtils.getPath(ProfileUser.this, data.getData());
                    Uri uri = Uri.fromFile(new File(IMAGE_PATH));
                    InputStream is = getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    imgchangeavatar.setImageBitmap(bitmap);
                    Toast.makeText(this, IMAGE_PATH, Toast.LENGTH_LONG).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }else if(requestCode == REQUEST_TAKE_PHOTO) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imgchangeavatar.setImageBitmap(bitmap);
                IMAGE_PATH = saveImage(bitmap,REQUEST_TAKE_PHOTO);
            }
        }
        changeAvatarUser();
    }

    private void changeAvatarUser(){
        SharedPreferences pre = getSharedPreferences("SaveToken",MODE_PRIVATE);
        SharedPreferences.Editor editor = pre.edit();
        String getToken = pre.getString("token","");
        File file = new File(IMAGE_PATH);
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        apiService = APIUtils.getAPIService();
        apiService.changeAvaterUser(getToken,body).enqueue(new Callback<ErrorResponse>() {
            @Override
            public void onResponse(Call<ErrorResponse> call, Response<ErrorResponse> response) {
                ErrorResponse errorResponse = response.body();
                if(response.isSuccessful()){
                    if(errorResponse.getStatus() == 200){
                        ThongBao.Toast(ProfileUser.this,"Đổi Avatar thành công!!");
                    }
                }
            }

            @Override
            public void onFailure(Call<ErrorResponse> call, Throwable t) {

            }
        });
    }
}
