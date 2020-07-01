package com.example.couniversity_new.ui.qna;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.couniversity_new.MainActivity;
import com.example.couniversity_new.R;

public class QnAaskFragment extends Fragment {

    MainActivity mainActivity;

    EditText qna_title;
    EditText qna_text;

    String work;
    String title;
    String spinner_value;

    Spinner mySpinner;

    long spinnerValue;

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

    private com.example.couniversity_new.ui.qna.QnAViewModel qnaaskViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        qnaaskViewModel =
                ViewModelProviders.of(this).get(com.example.couniversity_new.ui.qna.QnAViewModel.class);
        View root = inflater.inflate(R.layout.fragment_qna_ask, container, false);
        final TextView textView = root.findViewById(R.id.text_qna);

        qnaaskViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        Button button = (Button)root.findViewById(R.id.ask_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.onChangeFragment(0);
            }
        });

        qna_text = (EditText) root.findViewById(R.id.ask_contentbox);
        work = qna_text.getText().toString();

        qna_title = (EditText) root.findViewById(R.id.ask_title_text);
        title = qna_title.getText().toString();

        mySpinner = (Spinner) root.findViewById(R.id.ask_spinner);
        spinner_value = mySpinner.getSelectedItem().toString();

        Button button2 = (Button)root.findViewById(R.id.upload_question);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                work = qna_text.getText().toString();
                title = qna_title.getText().toString();
                spinner_value = mySpinner.getSelectedItem().toString();
                spinnerValue = Long.parseLong(spinner_value);

                Log.d("db", work +" / "+ title +" / "+ String.valueOf(spinnerValue));
                mainActivity.uploadQuestion(work, title, spinnerValue);

                mainActivity.onChangeFragment(0);
                qna_text.setText("");
                qna_title.setText("");
            }
        });

        return root;
    }

}