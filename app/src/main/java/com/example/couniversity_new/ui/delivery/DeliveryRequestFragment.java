package com.example.couniversity_new.ui.delivery;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.couniversity_new.MainActivity;
import com.example.couniversity_new.R;

public class DeliveryRequestFragment extends Fragment {

    MainActivity mainActivity;

    EditText request_text;
    EditText request_title;

    String text;
    String title;

    Spinner item_spin;
    Spinner store_spin;
    Spinner coupon_spin;

    String item_value;
    String store_value;
    String coupon_value;

    long couponValue;

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

    ArrayAdapter<CharSequence> adapterItem;
    ArrayAdapter<CharSequence> adapterStore;

        private com.example.couniversity_new.ui.delivery.DeliveryViewModel deliveryRequestViewModel;

        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            deliveryRequestViewModel =
                    ViewModelProviders.of(this).get(com.example.couniversity_new.ui.delivery.DeliveryViewModel.class);
            final View root = inflater.inflate(R.layout.fragment_delivery_request, container, false);
            final TextView textView = root.findViewById(R.id.text_delivery);

            //final ViewGroup rootview = (ViewGroup)inflater.inflate(R.layout.fragment_delivery_request,container,false);


            item_spin=(Spinner)root.findViewById(R.id.item_spinner);
            store_spin=(Spinner)root.findViewById(R.id.store_spinner);
            coupon_spin=(Spinner)root.findViewById(R.id.request_spinner);

            adapterItem = ArrayAdapter.createFromResource(getContext(), R.array.item, android.R.layout.simple_spinner_dropdown_item);

            adapterItem.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            item_spin.setAdapter(adapterItem);

            item_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(adapterItem.getItem(position).equals("커피")){
                        adapterStore =ArrayAdapter.createFromResource(root.getContext(),R.array.coffee,android.R.layout.simple_spinner_dropdown_item);
                        store_spin.setAdapter(adapterStore);

                        store_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Toast.makeText(root.getContext(),adapterStore.getItem(position).toString()+"가 선택되었습니다!",
                                        Toast.LENGTH_SHORT).show() ;
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                    else if(adapterItem.getItem(position).equals("음식")){
                        adapterStore =ArrayAdapter.createFromResource(root.getContext(),R.array.food,android.R.layout.simple_spinner_dropdown_item);
                        store_spin.setAdapter(adapterStore);

                        store_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Toast.makeText(root.getContext(),adapterStore.getItem(position).toString()+"가 선택되었습니다!",
                                        Toast.LENGTH_SHORT).show() ;
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                    else if(adapterItem.getItem(position).equals("복사")){
                        adapterStore =ArrayAdapter.createFromResource(root.getContext(),R.array.copy,android.R.layout.simple_spinner_dropdown_item);
                        store_spin.setAdapter(adapterStore);

                        store_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Toast.makeText(root.getContext(),adapterStore.getItem(position).toString()+"가 선택되었습니다!",
                                        Toast.LENGTH_SHORT).show() ;
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            deliveryRequestViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(@Nullable String s) {
                    textView.setText(s);
                }
            });

            Button button = (Button)root.findViewById(R.id.delivery_back);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mainActivity.onChangeFragment(0);
                }
            });

            request_text = (EditText) root.findViewById(R.id.request_content_box);
            text = request_text.getText().toString();

            request_title = (EditText) root.findViewById(R.id.request_title_text);
            title = request_title.getText().toString();

            Button button2 = (Button)root.findViewById(R.id.upload_request);
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    text = request_text.getText().toString();
                    title = request_title.getText().toString();
                    item_value = item_spin.getSelectedItem().toString();
                    store_value = store_spin.getSelectedItem().toString();
                    coupon_value = coupon_spin.getSelectedItem().toString();
                    couponValue = Long.parseLong(coupon_value);

                    mainActivity.uploadRequest(text, title, item_value, store_value, couponValue);

                    mainActivity.onChangeFragment(0);
                    request_title.setText("");
                    request_text.setText("");
                    item_spin.setSelection(0);
                    store_spin.setSelection(0);
                    coupon_spin.setSelection(0);

                }
            });

            return root;
        }

    }

