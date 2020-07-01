package com.example.couniversity_new.ui.setting;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.couniversity_new.MainActivity;
import com.example.couniversity_new.R;

import java.util.ArrayList;

public class SettingFragment extends Fragment {

    String username, password, major, email, userId;
    Button btn_modify, btn_purchase;

    ArrayList<String> list = new ArrayList<>();

    MainActivity mainActivity = new MainActivity();

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

    private com.example.couniversity_new.ui.setting.SettingViewModel settingViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingViewModel =
                ViewModelProviders.of(this).get(com.example.couniversity_new.ui.setting.SettingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_setting, container, false);
        final TextView textView = root.findViewById(R.id.text_setting);

        settingViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        ListView listView = (ListView) root.findViewById(R.id.listview_modify);

        mainActivity.getUserInfo();

        userId = mainActivity.getUserId();
        username = mainActivity.getUserName();
        password = mainActivity.getUserPW();
        major = mainActivity.getUserDept();
        email = mainActivity.getUserEmail();

        list.add("ID : "+userId);
        list.add("PW : "+password);
        list.add("User Name : "+username);
        list.add("Email : "+email);
        list.add("Dept : "+major);

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);

        listView.setAdapter(itemsAdapter);

        btn_modify = (Button)root.findViewById(R.id.btn_modify);
        btn_purchase = (Button)root.findViewById(R.id.btn_purchase);

        btn_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.onChangeFragmentSetting(1);

            }
        });

        btn_purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(SettingFragment.this.getActivity(), Purchase.class);
                //startActivity(intent);
            }
        });

        return root;
    }
}
