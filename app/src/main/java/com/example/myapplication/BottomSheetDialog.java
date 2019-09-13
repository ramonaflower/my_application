package com.example.myapplication;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BottomSheetDialog extends BottomSheetDialogFragment {

    @BindView(R.id.button_choose_image_from_gallery)
    Button mChooseImageFromGalleryButton;
    @BindView(R.id.button_take_picture_from_camera)
    Button mTakePictureFromCameraButton;
    @BindView(R.id.button_cancel)
    Button mCancelButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.bottom_sheet_dialog, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick(R.id.button_take_picture_from_camera)
    public void onClickTakePictureFromCamera() {
        ProfilePatient1Fragment parentFragment = ((ProfilePatient1Fragment) BottomSheetDialog.this.getParentFragment());
        if (parentFragment != null) {
            parentFragment.takePictureFromCamera();
            dismiss();
        }
    }

    @OnClick(R.id.button_choose_image_from_gallery)
    public void onClickTakePictureFromGallery() {
        ProfilePatient1Fragment parentFragment = ((ProfilePatient1Fragment) BottomSheetDialog.this.getParentFragment());
        if (parentFragment != null) {
            parentFragment.takePictureFromGallery();
            dismiss();
        }
    }

}
