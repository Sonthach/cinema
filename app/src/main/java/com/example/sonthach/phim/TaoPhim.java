package com.example.sonthach.phim;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import okhttp3.Call;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit.GsonConverterFactory;
import retrofit2.Retrofit;

public class TaoPhim extends AppCompatActivity {
    Spinner spin;
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
        APIService apiService = APIUtils.getAPIService();


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

            }
        });

    }

    private void update(TextView ngayphathanh) {
        Ngayphathanh.setText(stf.format(calendar.getTime()));
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

    private void takepicture(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,REQUEST_TAKE_PHOTO);
    }

    private void choosePhoto(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,REQUEST_CHOOSE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK) {
            if(requestCode == REQUEST_CHOOSE_PHOTO) {

                try {
                    Uri imageUri = data.getData();
                    InputStream is = getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    poster.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }else if(requestCode ==REQUEST_TAKE_PHOTO){
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    poster.setImageBitmap(bitmap);
            }
        }
    }

    private void Request(){
        String mName = Tenphim.getText().toString().trim();
        String mMota = Mota.getText().toString().trim();
        String mNgayphathanh = Ngayphathanh.getText().toString().trim();
        String Theloai = spin.getSelectedItem().toString();

    }

}
