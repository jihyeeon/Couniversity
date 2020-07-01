package com.example.couniversity_new.ui.delivery;

import android.content.Context;
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

import com.example.couniversity_new.MainActivity;

import com.example.couniversity_new.R;

import java.util.ArrayList;

public class DeliveryFragment extends Fragment {

    MainActivity mainActivity;

    ArrayList<String> allRequest = new ArrayList<>();
    ArrayList<String> myRequest = new ArrayList<>();

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

    private com.example.couniversity_new.ui.delivery.DeliveryViewModel deliveryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        deliveryViewModel =
                ViewModelProviders.of(this).get(com.example.couniversity_new.ui.delivery.DeliveryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_delivery, container, false);
        final TextView textView = root.findViewById(R.id.text_delivery);

        deliveryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        Button button3 = (Button)root.findViewById(R.id.delivery_requestDelivery);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("delivery","request button clicked!");
                mainActivity.onChangeFragmentDelivery(1);
            }
        });

        ListView list_allRequest = (ListView)root.findViewById(R.id.list_allrequests);
        allRequest = mainActivity.getAllRequest();
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, allRequest);
        list_allRequest.setAdapter(itemsAdapter);

        ListView list_myRequest = (ListView)root.findViewById(R.id.list_myrequests);
        myRequest = mainActivity.getMyRequest();
        ArrayAdapter<String> itemsAdapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, myRequest);
        list_myRequest.setAdapter(itemsAdapter2);

        return root;
    }



}


