package com.example.couniversity_new.ui.qna;

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

public class QnAFragment extends Fragment {

    MainActivity mainActivity;

    ArrayList<String> allquestions = new ArrayList<>();
    ArrayList<String> myquestions = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity)getActivity();
        mainActivity.getInfo();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;
    }

    private com.example.couniversity_new.ui.qna.QnAViewModel qnaViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mainActivity.getInfo();
        qnaViewModel =
                ViewModelProviders.of(this).get(com.example.couniversity_new.ui.qna.QnAViewModel.class);
        View root = inflater.inflate(R.layout.fragment_qna, container, false);
        final TextView textView = root.findViewById(R.id.text_qna);

        qnaViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        Button button3 = (Button)root.findViewById(R.id.qna_askaquestion);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.onChangeFragment(1);
            }
        });

        ListView list_allquestion = (ListView)root.findViewById(R.id.list_allquestions);
        allquestions = mainActivity.getAllquestions();
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, allquestions);
        list_allquestion.setAdapter(itemsAdapter);

        ListView list_myquestion = (ListView)root.findViewById(R.id.list_myquestions);
        myquestions = mainActivity.getMyquestions();
        ArrayAdapter<String> itemsAdapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, myquestions);
        list_myquestion.setAdapter(itemsAdapter2);

        return root;
    }

}
