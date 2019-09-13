package com.example.myapplication;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailImageInsuranceActivity extends AppCompatActivity {
    @BindView(R.id.image_view_detail_image_insurance)
    ImageView mDetailImageInsuranceImageView;
    @BindView(R.id.image_button_close)
    ImageButton mCloseImageButton;
    private String mImagePath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_image_insurance);
        ButterKnife.bind(this);
        if (getIntent().getExtras() != null) {
            mImagePath = getIntent().getStringExtra("full_size_image");
            Glide.with(this).load(mImagePath).into(mDetailImageInsuranceImageView);
        }
    }

    @OnClick(R.id.image_button_close)
    public void onClickCloseButton(){
        finish();
    }
}
