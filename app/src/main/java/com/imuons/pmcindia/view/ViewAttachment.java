package com.imuons.pmcindia.view;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.imuons.pmcindia.R;

import static com.imuons.pmcindia.application.PMCApplication.imageLoader;

public class ViewAttachment extends AppCompatActivity {

    private ImageView iv_attacment;
    private Bundle bundle;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attachment);
        bundle=getIntent().getExtras();
        url=bundle.getString("url");
        intUI();

    }

    private void intUI() {
        iv_attacment=(ImageView)findViewById(R.id.iv_attacment);
        imageLoader.displayImage(url, iv_attacment);
    }
}
