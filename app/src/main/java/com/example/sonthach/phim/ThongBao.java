package com.example.sonthach.phim;

import android.content.Context;
import android.widget.Toast;

public class ThongBao {
    public static void Toast(Context context, String thongbao) {
        Toast.makeText(context,thongbao,Toast.LENGTH_LONG).show();
    }
}
