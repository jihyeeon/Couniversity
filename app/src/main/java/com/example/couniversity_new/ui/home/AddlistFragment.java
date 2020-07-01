package com.example.couniversity_new.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.couniversity_new.MainActivity;
import com.example.couniversity_new.R;

public class AddlistFragment extends Fragment {

    MainActivity mainActivity;

    EditText work_text;
    String work;

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

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_addlist, container, false);
        final TextView textView = root.findViewById(R.id.text_home);

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        Button button = (Button)root.findViewById(R.id.add_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mainActivity.onChangeFragmentHome(0);;
            }
        });

        work_text = (EditText) root.findViewById(R.id.add_contentbox);

        Button button2 = (Button)root.findViewById(R.id.upload_list);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                work = work_text.getText().toString();
                mainActivity.uploadTodo(work);
                mainActivity.onChangeFragmentHome(0);
                work_text.setText("");
            }
        });

        return root;
    }

}
