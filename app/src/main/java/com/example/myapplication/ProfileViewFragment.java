package com.example.myapplication;

import android.os.Bundle;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileViewFragment extends Fragment {
    @BindView(R.id.text_view_profileview_name)
    TextView mProfileViewNameTextView;
    @BindView(R.id.text_view_profileview_date_of_birth)
    TextView mProfileViewDateOfBirthTextView;
    @BindView(R.id.radio_group_profileview_gender)
    RadioGroup mProfileViewGenderRadioGroup;
    @BindView(R.id.radio_button_profileview_male)
    RadioButton mProfileViewMaleRadioButton;
    @BindView(R.id.radio_button_profileview_female)
    RadioButton mProfileViewFemaleRadioButton;
    @BindView(R.id.text_view_profileview_blood_type)
    TextView mProfileViewBloodTypeTextView;
    @BindView(R.id.text_view_profileview_height)
    TextView mProfileViewHeightTextView;
    @BindView(R.id.text_view_profileview_weight)
    TextView mProfileViewWeightTextView;
    @BindView(R.id.checkbox_profileview_allergy_medicine)
    CheckBox mProfileViewMedicineAllergyCheckBox;
    @BindView(R.id.checkbox_profileview_allergy_food)
    CheckBox mProfileViewFoodAllergyCheckBox;
    @BindView(R.id.checkbox_profileview_other_allergy)
    CheckBox mProfileViewOtherAllergyCheckBox;
    @BindView(R.id.edit_text_profileview_other_allergy)
    TextView mProfileViewOtherAllergyTextView;
    @BindView(R.id.text_view_profileview_cancer_type)
    TextView mProfileViewCancerTypeTextView;
    @BindView(R.id.text_view_profileview_cancer_stage)
    TextView mProfileViewCancerStageSTextView;
    @BindView(R.id.linearLayout_profileview_image_insurance)
    LinearLayout mProfileViewPickImageInsuranceLinearLayout;
    @BindView(R.id.image_view_profileview_first_image_insurance)
    ImageView mProfileViewFirstImageInsuranceImageView;
    @BindView(R.id.image_view_profileview_second_image_insurance)
    ImageView mProfileViewSecondImageInsuranceImageView;
    @BindView(R.id.button_profileview_edit)
    Button mProfileViewSEditButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_view, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.button_profileview_edit)
    public void showProfileEdit() {
        ((MainActivity) requireActivity()).showProfileEdit();
    }
}
