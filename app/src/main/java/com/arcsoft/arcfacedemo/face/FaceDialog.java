package com.arcsoft.arcfacedemo.face;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.arcsoft.arcfacedemo.R;

import androidx.annotation.NonNull;

public class FaceDialog extends Dialog {

    private TextView textView;
    private ImageView imageView;
    private TextView info;
    public OnClickListener onClickListener;
    private int resource;
    private String inform;

    public interface OnClickListener {
        void onIKnowClick();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }


    public FaceDialog(@NonNull Context context, int resource, String inform) {
        super(context, R.style.CustomDialog);
        this.resource = resource;
        this.inform = inform;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_face);
        textView = (TextView) findViewById(R.id.cancel_dialog);
        imageView = (ImageView) findViewById(R.id.face_enter_icon);
        info = (TextView) findViewById(R.id.face_enter_info);
        setInfo();
        initListener();

    }

    public void setInfo() {
        imageView.setImageResource(resource);
        info.setText(inform);
    }

    private void initListener() {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onIKnowClick();
            }
        });
    }

}
