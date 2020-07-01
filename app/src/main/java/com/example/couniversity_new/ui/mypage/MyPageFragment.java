package com.example.couniversity_new.ui.mypage;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.couniversity_new.MainActivity;
import com.example.couniversity_new.R;

import java.util.ArrayList;

public class MyPageFragment extends Fragment {

    MainActivity mainActivity;
    ArrayList<String> myquestions = new ArrayList<>();
    ArrayList<String> myRequest = new ArrayList<>();
    ArrayList<String> news = new ArrayList<>();


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

    private MyPageViewModel myPageViewModel;

    //String[] news ={"Delivery success!", "Your question get answers", "HI", "my name is", "jihyeon","nice", "to","meet", "you"};
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        myPageViewModel =
                ViewModelProviders.of(this).get(MyPageViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mypage, container, false);

        // -------- DB 에서 값 받아오기 -------
        String name = "User";
        name = mainActivity.getUserName();

        // -------- 알림 리스트뷰 설정 ---------
        ListView listView = (ListView) root.findViewById(R.id.listView); // listview

        myquestions = mainActivity.getMyquestions();
        myRequest = mainActivity.getMyRequest();

        news.addAll(myquestions);
        news.addAll(myRequest);

        ArrayAdapter<String> itemsAdapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,news );
        listView.setAdapter(itemsAdapter2);

        //ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, news);
        //listView.setAdapter(adapter);
        // listView 의 각 아이템이 선택 되었을 때 의 이벤트 handling제공 -> toast 로 선택된 아이템 띄워줌
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Toast.makeText(getActivity(), news[position], Toast.LENGTH_LONG).show();

        } });
        */
        // -------- 버튼 설정 ---------
        Button stBtn = (Button) root.findViewById(R.id.settingBtn);

        stBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity activity = (MainActivity) getActivity();
                Toast.makeText(getActivity(), "getActivity()", Toast.LENGTH_LONG).show();
            }
        });
        // --------- textview 설정 ---------
        TextView userName = (TextView) root.findViewById(R.id.userName);
        TextView couponNum = (TextView) root.findViewById(R.id.couponNum);

        long coupon = mainActivity.getUserCoupon();
        //String cNumber = "100";

        userName.setText(name+" 님");
        couponNum.setText("보유 쿠폰: "+ String.valueOf(coupon) +"장");


        //final TextView textView = root.findViewById(R.id.text_mypage);
        /*myPageViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        return root;
    }
}