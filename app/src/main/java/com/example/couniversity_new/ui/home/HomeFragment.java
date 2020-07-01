package com.example.couniversity_new.ui.home;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
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

import com.example.couniversity_new.Login;
import com.example.couniversity_new.MainActivity;
import com.example.couniversity_new.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    MainActivity mainActivity;
    ListView listView;
    String[] number;
    SQLiteDatabase db;
    String sql;
    Cursor cursor;
    int idValue;
    Login loginActivity;

    ArrayList<String> myTodo = new ArrayList<>();

    final static String dbname = "todo.db";
    final static int dbVersion = 1;

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

    private com.example.couniversity_new.ui.home.HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mainActivity.getInfo();
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        Button button = (Button)root.findViewById(R.id.add_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mainActivity.onChangeFragmentHome(1);;
            }
        });

        Button button2 = (Button)root.findViewById(R.id.home_askquestion);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.onChangeFragment(1);
            }
        });

        Button button3 = (Button)root.findViewById(R.id.home_requestdelivery);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Home", "Button Clicked!!!!!!!!!!!!!!!!!!");

                mainActivity.onChangeFragmentDelivery(1);
            }
        });

        listView = (ListView) root.findViewById(R.id.todolist);
        listView.setAdapter(null);
        myTodo = mainActivity.getMyTodo();
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, myTodo);
        listView.setAdapter(itemsAdapter);

        return root;
    }

    /*
    public void FillList() {
        int[] id = {R.id.todolist_row};
        String[] CompanyName = new String[]{"CompanyName"};
        if (databaseHelper == null)
            databaseHelper = new DatabaseHelper(getActivity());
        SQLiteDatabase sqlDb = databaseHelper.getReadableDatabase();
        Cursor c = databaseHelper.getTodo();

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getActivity(), R.layout.todolist_row, c, CompanyName, id, 0);
        listView.setAdapter(adapter);
    }

     */


}
