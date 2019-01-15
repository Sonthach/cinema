package com.example.sonthach.phim;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Callback;

public class TaoPhim extends AppCompatActivity {
    String IMAGE_PATH ="";
    private Uri imageToUploadUri;
    File file;
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    Spinner spin;
    private APIService apiService;
    Context context;
    String Arr[] = {"Hành động","Hài hước","Hoạt hình"};
    int spPosition = 0;
    TextView Ngayphathanh;
    Button ChupHinh,TaoPhim;
    EditText Tenphim,Mota;
    ImageView poster;
    String Format = "dd/MM/yyyy";
    SimpleDateFormat stf = new SimpleDateFormat(Format);
    final int REQUEST_TAKE_PHOTO = 123;
    final int REQUEST_CHOOSE_PHOTO = 321;
    Calendar calendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            calendar.set(Calendar.YEAR,i);
            calendar.set(Calendar.MONTH,i1);
            calendar.set(Calendar.DAY_OF_MONTH,i2);
            update(Ngayphathanh);
        }


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tao_phim);
        Ngayphathanh = findViewById(R.id.edtNgayPhatHanh2);
        Tenphim = findViewById(R.id.edtTenPhim);
        Mota = findViewById(R.id.edtMoTa);
        TaoPhim = findViewById(R.id.btnTaoPhim);
        spin = findViewById(R.id.spinnerOption);
        poster = findViewById(R.id.imgposter2);
        ChupHinh = findViewById(R.id.btnChonAnh2);
        CreateSpin();

        ChupHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectDialog();
            }
        });


        Ngayphathanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(TaoPhim.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        TaoPhim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Request();
            }
        });



    }

    private void update(TextView ngayphathanh) {
        Ngayphathanh.setText(stf.format(calendar.getTime()));
    }

    @Override

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_CAMERA_REQUEST_CODE) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();

            } else {

                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();

            }

        };
    }


    private void CreateSpin() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,Arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spin.setAdapter(adapter);
       spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               spPosition = i;
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {
                spPosition = -1;
           }
       });
    }

    private void SelectDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
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
    private void Permision(){
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
    private void takepicture(){
        Permision();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,REQUEST_TAKE_PHOTO);
    }

    private void choosePhoto(){
        Permision();
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,REQUEST_CHOOSE_PHOTO);
    }

    public String saveImage(Bitmap myBitmap, int requestCode) {
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



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK) {
            if(requestCode == REQUEST_CHOOSE_PHOTO) {

                try {
                    Uri imageUri = data.getData();
                    IMAGE_PATH = ReadPathUtils.getPath(TaoPhim.this, data.getData());
                    Uri uri = Uri.fromFile(new File(IMAGE_PATH));
                    InputStream is = getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    poster.setImageBitmap(bitmap);
                    Toast.makeText(this, IMAGE_PATH, Toast.LENGTH_LONG).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }else if(requestCode == REQUEST_TAKE_PHOTO) {
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    poster.setImageBitmap(bitmap);
                    IMAGE_PATH = saveImage(bitmap,REQUEST_TAKE_PHOTO);
            }
        }
    }

    private void Request(){
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String releaseDate ="";

        try {
            Date date = df.parse(Ngayphathanh.getText().toString());
            releaseDate = String.valueOf(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        File file = new File(IMAGE_PATH);
        RequestBody mName = RequestBody.create(MediaType.parse("text/plain"),Tenphim.getText().toString().trim());
        RequestBody mMota = RequestBody.create(MediaType.parse("text/plain"),Mota.getText().toString().trim());
        RequestBody mNgayphathanh = RequestBody.create(MediaType.parse("text/plain"),releaseDate);
        RequestBody mTheloai = RequestBody.create(MediaType.parse("text/plain"),spin.getSelectedItem().toString());
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("file", file.getName(), requestFile);


        HashMap<String, RequestBody> map = new HashMap<>();
        map.put("name",mName);
        map.put("genre",mTheloai);
        map.put("releaseDate",mNgayphathanh);
        map.put("content",mMota);

        apiService = APIUtils.getAPIService();
        apiService.PostFilm(map,body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if(response.isSuccessful())
                {
                    ThongBao.Toast(TaoPhim.this,"Thanh cong!");
                }else {
                    ThongBao.Toast(TaoPhim.this,"Có lỗi xảy ra! Vui lòng thử lại.");
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {
                ThongBao.Toast(TaoPhim.this,t.getMessage());
            }
        });


    }

}
