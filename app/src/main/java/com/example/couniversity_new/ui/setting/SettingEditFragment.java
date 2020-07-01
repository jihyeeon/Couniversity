package com.example.couniversity_new.ui.setting;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.couniversity_new.MainActivity;
import com.example.couniversity_new.R;

public class SettingEditFragment extends Fragment {

    MainActivity mainActivity;

    EditText edit_id;
    EditText edit_pw;
    EditText edit_name;
    EditText edit_email;
    EditText edit_dept;

    String id;
    String pw;
    String name;
    String email;
    String dept;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity)getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;
    }

    private com.example.couniversity_new.ui.setting.SettingViewModel settingEditViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingEditViewModel =
                ViewModelProviders.of(this).get(com.example.couniversity_new.ui.setting.SettingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_setting_edit, container, false);
        final TextView textView = root.findViewById(R.id.text_setting);

        settingEditViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        Button button = (Button)root.findViewById(R.id.edit_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.onChangeFragmentSetting(0);
            }
        });

        id = mainActivity.getUserId();
        name = mainActivity.getUserName();
        pw = mainActivity.getUserPW();
        email = mainActivity.getUserEmail();
        dept = mainActivity.getUserDept();

        edit_id = (EditText) root.findViewById(R.id.edit_id);
        edit_id.setText(id);
        edit_pw = (EditText) root.findViewById(R.id.edit_pw);
        edit_pw.setText(pw);
        edit_name = (EditText) root.findViewById(R.id.edit_name);
        edit_name.setText(name);
        edit_dept = (EditText) root.findViewById(R.id.edit_major);
        edit_dept.setText(dept);
        edit_email = (EditText) root.findViewById(R.id.edit_email);
        edit_email.setText(email);

        Button button2 = (Button)root.findViewById(R.id.update_info);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id = edit_id.getText().toString();
                name = edit_name.getText().toString();
                pw = edit_pw.getText().toString();
                dept = edit_dept.getText().toString();
                email = edit_email.getText().toString();

                mainActivity.updateUserInfo(id, pw, name, email, dept);

                Toast.makeText(getActivity(), "정보가 수정되었습니다!", Toast.LENGTH_SHORT).show();
                mainActivity.onChangeFragmentSetting(0);
            }
        });

        return root;
    }

}