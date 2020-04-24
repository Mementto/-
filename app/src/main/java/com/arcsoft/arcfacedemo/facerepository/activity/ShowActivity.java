package com.arcsoft.arcfacedemo.facerepository.activity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import com.arcsoft.arcfacedemo.R;
import com.arcsoft.arcfacedemo.api.CallApi;
import com.arcsoft.arcfacedemo.databinding.ActivityShowBinding;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowActivity extends AppCompatActivity {

    private ActivityShowBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show);
        binding.setShowPhoto(this);
//        try {
////            upLoadImage();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    public void onClick(View view) {
        Intent intent = new Intent(ShowActivity.this, RecognizeAndRecognizeActive.class);
        startActivity(intent);
    }

//    private void upLoadImage() throws FileNotFoundException {
//        Bundle bundle = this.getIntent().getExtras();
//        String url = bundle.getString("url");
//        String path = getFilePathFromContentUri(Uri.parse(url), this.getContentResolver());
//        File file = new File(path);
//        RequestBody imgBody = RequestBody.create(MediaType.parse("image/jpg"), file);
//        MultipartBody.Part imgPart = MultipartBody.Part.createFormData("img", file.getName(), imgBody);
//        Retrofit retrofit = new Retrofit
//                .Builder()
//                .baseUrl("http://192.168.1.103:80/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        CallApi api = retrofit.create(CallApi.class);
//        Call<ResponseBody> call = api.uploadImage(imgPart);
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                Log.e("返回s", response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Log.e("返回", t.getMessage());
//            }
//        });
//    }

    public static String getFilePathFromContentUri(Uri selectedVideoUri, ContentResolver contentResolver) {
        String filePath;
        String[] filePathColumn = {MediaStore.MediaColumns.DATA};

        Cursor cursor = contentResolver.query(selectedVideoUri, filePathColumn, null, null, null);
//      也可用下面的方法拿到cursor
//      Cursor cursor = this.context.managedQuery(selectedVideoUri, filePathColumn, null, null, null);

        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        filePath = cursor.getString(columnIndex);
        cursor.close();
        return filePath;
    }


    public static Bitmap getBitmapFromUri(Context context, Uri uri) {
        try {
            ParcelFileDescriptor parcelFileDescriptor =
                    context.getContentResolver().openFileDescriptor(uri, "r");
            FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
            Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
            parcelFileDescriptor.close();
            return image;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
