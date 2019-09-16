package com.example.myapplication;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfilePatient1Fragment extends Fragment {

    @BindView(R.id.edittext_input_patient_name)
    EditText mPatientNameEdittext;
    @BindView(R.id.text_view_patient_date_of_birth)
    TextView mPatientDateOfBirthTextView;
    @BindView(R.id.radio_group_gender)
    RadioGroup mGenderRadioGroup;
    @BindView(R.id.radio_button_male)
    RadioButton mMaleRadioButton;
    @BindView(R.id.radio_button_female)
    RadioButton mFemaleRadioButton;
    @BindView(R.id.spinner_choose_blood_type)
    Spinner mChooseBloodTypeSpinner;
    @BindView(R.id.edit_text_input_patient_height)
    EditText mPatientHeightEdittext;
    @BindView(R.id.edit_text_input_patient_weight)
    EditText mPatientWeightEdittext;
    @BindView(R.id.checkbox_allergy_medicine)
    CheckBox mMedicineAllergyCheckBox;
    @BindView(R.id.checkbox_allergy_food)
    CheckBox mFoodAllergyCheckBox;
    @BindView(R.id.checkbox_other_allergy)
    CheckBox mOtherAllergyCheckBox;
    @BindView(R.id.edit_text_input_detail_other_allergy)
    EditText mOtherAllergyEdittext;
    @BindView(R.id.spinner_choose_cancer_type)
    Spinner mChooseCancerTypeSpinner;
    @BindView(R.id.spinner_choose_cancer_stage)
    Spinner mChooseCancerStageSpinner;
    @BindView(R.id.edit_text_input_detail_other_disease)
    EditText mDetailOtherDiseaseEdittext;
    @BindView(R.id.linearLayout_pick_image_insurance)
    LinearLayout mPickImageInsuranceLinearLayout;
    @BindView(R.id.image_view_first_image_insurance)
    ImageView mFirstImageInsuranceImageView;
    @BindView(R.id.image_view_second_image_insurance)
    ImageView mSecondImageInsuranceImageView;
    @BindView(R.id.button_save)
    Button mSaveButton;
    private DatePickerDialog mDatePicker;
    private int mImageInsuranceIndex = -1;
    private File mCapturePhotoFile;
    private String mFirstImageInsurancePath = "";
    private String mSecondImageInsurancePath = "";
    private boolean mIsFirstImageInsurancePicked = false;
    private boolean mIsSecondImageInsurancePicked = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_tab1, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupView();
    }

    private void setupView() {
        // setup data for spinner choose blood type.
        String[] stringArrayBloodType = requireActivity().getResources().getStringArray(R.array.blood_type);
        HintAdapter bloodTypeAdapter = new HintAdapter(requireActivity(), android.R.layout.simple_spinner_item);
        // Add data to adapter
        for (String string : stringArrayBloodType){
            bloodTypeAdapter.add(string);
        }
        bloodTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mChooseBloodTypeSpinner.setAdapter(bloodTypeAdapter);
        mChooseBloodTypeSpinner.setSelection(bloodTypeAdapter.getCount());

        // setup data for spinner choose cancer stage.
        String[] stringArrayCancerStage = requireActivity().getResources().getStringArray(R.array.cancer_stage);
        HintAdapter cancerStageAdapter = new HintAdapter(requireActivity(), android.R.layout.simple_spinner_item);
        // Add data to adapter
        for (String string : stringArrayCancerStage){
            cancerStageAdapter.add(string);
        }
        cancerStageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mChooseCancerStageSpinner.setAdapter(cancerStageAdapter);
        mChooseCancerStageSpinner.setSelection(cancerStageAdapter.getCount());

        // setup data for spinner choose cancer type.
        String[] stringArrayCancerType = requireActivity().getResources().getStringArray(R.array.fake_cancer_type);
        HintAdapter cancerTypeAdapter = new HintAdapter(requireActivity(), android.R.layout.simple_spinner_item);
        // Add data to adapter
        for (String string : stringArrayCancerType){
            cancerTypeAdapter.add(string);
        }
        cancerTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mChooseCancerTypeSpinner.setAdapter(cancerTypeAdapter);
        mChooseCancerTypeSpinner.setSelection(cancerTypeAdapter.getCount());
    }

    @OnClick(R.id.text_view_patient_date_of_birth)
    public void onClickPickDateOfBirth() {
        final Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        // date picker dialog
        mDatePicker = new DatePickerDialog(requireActivity(),
                (view1, year1, monthOfYear, dayOfMonth) -> mPatientDateOfBirthTextView.setText(requireActivity()
                        .getResources()
                        .getString(R.string.date_of_birth_text_format, year1, monthOfYear + 1, dayOfMonth)), year, month, day);
        mDatePicker.show();
    }

    @OnClick(R.id.image_view_first_image_insurance)
    public void onClickFirstImageInsurance() {
        if (!mIsFirstImageInsurancePicked) {
            showBottomSheetDialogPickImage(1);
        } else {
            showFullSizeOfImage(mFirstImageInsurancePath);
        }
    }

    @OnClick(R.id.image_view_second_image_insurance)
    public void onClickSecondImageInsurance() {
        if (!mIsSecondImageInsurancePicked) {
            showBottomSheetDialogPickImage(2);
        } else {
            showFullSizeOfImage(mSecondImageInsurancePath);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == Constants.REQUEST_CODE_CAPTURE_FIRST_IMAGE_INSURANCE && resultCode == Activity.RESULT_OK) {
            mIsFirstImageInsurancePicked = true;
            mFirstImageInsurancePath = mCapturePhotoFile.getAbsolutePath();
            reduceSizeOfImage(mCapturePhotoFile.getAbsolutePath());
            Glide.with(requireActivity()).load(mFirstImageInsurancePath).into(mFirstImageInsuranceImageView);
        }
        if (requestCode == Constants.REQUEST_CODE_CAPTURE_SECOND_IMAGE_INSURANCE && resultCode == Activity.RESULT_OK) {
            mSecondImageInsurancePath = mCapturePhotoFile.getAbsolutePath();
            reduceSizeOfImage(mFirstImageInsurancePath);
            Glide.with(requireActivity()).load(mSecondImageInsurancePath).into(mSecondImageInsuranceImageView);
        }
        if (requestCode == Constants.REQUEST_CODE_TAKE_FIRST_IMAGE_INSURANCE_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            if (data != null && data.getData() != null) {
                Uri imageUri = data.getData();
                try {
                    Bitmap bitmapImage = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), imageUri);
                    if (checkImageSize(bitmapImage)) {
                        mIsFirstImageInsurancePicked = true;
                        mFirstImageInsurancePath = imageUri.getPath();
                        Glide.with(requireActivity()).load(bitmapImage).into(mFirstImageInsuranceImageView);
                    } else {
                        mIsFirstImageInsurancePicked = false;
                        showDialogCantRegisterImageInsurance();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (requestCode == Constants.REQUEST_CODE_TAKE_SECOND_IMAGE_INSURANCE_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            if (data != null && data.getData() != null) {
                Uri imageUri = data.getData();
                try {
                    Bitmap bitmapImage = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), imageUri);
                    if (checkImageSize(bitmapImage)) {
                        mIsSecondImageInsurancePicked = true;
                        mSecondImageInsurancePath = imageUri.getPath();
                        Glide.with(requireActivity()).load(bitmapImage).into(mSecondImageInsuranceImageView);
                    } else {
                        mIsSecondImageInsurancePicked = false;
                        showDialogCantRegisterImageInsurance();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void showDialogCantRegisterImageInsurance() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setMessage("File vuot qua 3m!");
        builder.setCancelable(true);
        builder.setPositiveButton("Ok", (dialog, id) -> dialog.cancel());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private boolean checkImageSize(Bitmap bitmapImage) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] imageInByte = stream.toByteArray();
        long sizeOfImageInKb = imageInByte.length / 1024; //Image size
        final long maximumSize = 3072;
        if (sizeOfImageInKb > maximumSize) {
            return false;
        }
        return true;
    }


    private void showFullSizeOfImage(String path) {
        Intent intent = new Intent(requireActivity(), DetailImageInsuranceActivity.class);
        intent.putExtra(Constants.EXTRA_FULL_SIZE_IMAGE, path);
        startActivity(intent);
    }

    private void showBottomSheetDialogPickImage(int imageIndex) {
        mImageInsuranceIndex = imageIndex;
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog();
        bottomSheetDialog.show(getChildFragmentManager(), null);
    }

    private void reduceSizeOfImage(String imagePath) {
        Bitmap imageBitmap = BitmapFactory.decodeFile(imagePath);
        int imageHeight = imageBitmap.getHeight();
        int imageWidth = imageBitmap.getWidth();
        final int requireSize = 2560;
        if (imageHeight > imageWidth) {
            if (imageHeight > requireSize) {
                Bitmap resizedBitmap = scaleToFitHeight(imageBitmap, requireSize);
                saveResizedFile(resizedBitmap);
            }
        } else {
            if (imageWidth > requireSize) {
                Bitmap resizedBitmap = scaleToFitWidth(imageBitmap, requireSize);
                saveResizedFile(resizedBitmap);
            }
        }
    }

    private void saveResizedFile(Bitmap resizedBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        resizedBitmap.compress(Bitmap.CompressFormat.JPEG, 70, bytes);
        try {
            File resizedFile = createImageFile();
            FileOutputStream fos = new FileOutputStream(resizedFile);
            fos.write(bytes.toByteArray());
            fos.close();
            switch (mImageInsuranceIndex) {
                case 1:
                    mFirstImageInsurancePath = resizedFile.getAbsolutePath();
                    break;
                case 2:
                    mSecondImageInsurancePath = resizedFile.getAbsolutePath();
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Bitmap scaleToFitWidth(Bitmap b, int width) {
        float factor = width / (float) b.getWidth();
        return Bitmap.createScaledBitmap(b, width, (int) (b.getHeight() * factor), true);
    }

    private static Bitmap scaleToFitHeight(Bitmap b, int height) {
        float factor = height / (float) b.getHeight();
        return Bitmap.createScaledBitmap(b, (int) (b.getWidth() * factor), height, true);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == Constants.REQUEST_PERMISSIONS_CODE_CAMERA_AND_WRITE_EXTERNAL_STORAGE) {
            boolean cameraPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
            boolean readExternalFile = grantResults[1] == PackageManager.PERMISSION_GRANTED;
            if (cameraPermission && readExternalFile) {
                requestCamera();
            }
        } else if (requestCode == Constants.REQUEST_PERMISSIONS_CODE_READ_EXTERNAL_STORAGE) {
            if (permissions[0].equals(Manifest.permission.READ_EXTERNAL_STORAGE)
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                requestGallery();
            }
        }
    }

    public void takePictureFromCamera() {
        if (isCameraPermissionGranted()) {
            requestCamera();
        }
    }

    public void takePictureFromGallery() {
        if (isReadExternalStoragePermissionGranted()) {
            requestGallery();
        }
    }

    private void requestCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(requireActivity(), getString(R.string.file_provider), photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                switch (mImageInsuranceIndex) {
                    case 1:
                        startActivityForResult(takePictureIntent, Constants.REQUEST_CODE_CAPTURE_FIRST_IMAGE_INSURANCE);
                        break;
                    case 2:
                        startActivityForResult(takePictureIntent, Constants.REQUEST_CODE_CAPTURE_SECOND_IMAGE_INSURANCE);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String imageFileName = generateImageFileName();
        File storageDir = requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        mCapturePhotoFile = File.createTempFile(imageFileName, ".jpeg", storageDir);
        return mCapturePhotoFile;
    }

    private String generateImageFileName() {
        return new SimpleDateFormat(getString(R.string.image_name_format), Locale.getDefault()).format(new Date());
    }

    private void requestGallery() {
        // Create intent for picking a photo from the gallery
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
        // So as long as the result is not null, it's safe to use the intent.
        if (pickPhoto.resolveActivity(requireActivity().getPackageManager()) != null) {
            // Bring up gallery to select a photo
            switch (mImageInsuranceIndex) {
                case 1:
                    startActivityForResult(pickPhoto, Constants.REQUEST_CODE_TAKE_FIRST_IMAGE_INSURANCE_FROM_GALLERY);
                    break;
                case 2:
                    startActivityForResult(pickPhoto, Constants.REQUEST_CODE_TAKE_SECOND_IMAGE_INSURANCE_FROM_GALLERY);
                    break;
                default:
                    break;
            }

        }
    }

    private boolean isCameraPermissionGranted() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA + Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constants.REQUEST_PERMISSIONS_CODE_CAMERA_AND_WRITE_EXTERNAL_STORAGE);
            return false;
        } else {
            return true;
        }
    }

    private boolean isReadExternalStoragePermissionGranted() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Constants.REQUEST_PERMISSIONS_CODE_READ_EXTERNAL_STORAGE);
            return false;
        } else {
            return true;
        }
    }
}
