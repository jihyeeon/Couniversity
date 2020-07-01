package com.example.couniversity_new;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    EditText ID;
    EditText Pw;
    String pwtemper;
    User User;
    ArrayList<User> data = new ArrayList<>();

    private FirebaseAuth firebaseAuth;
    MainActivity mainActivity;


    boolean findState = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ID = findViewById(R.id.idText);
        Pw = findViewById(R.id.pwText);

        // 아이디 치기 귀찮아서 예시값 넣어둔 것
        ID.setText("yujin");
        Pw.setText("105102");

    }
    public void signInMethod(View view) {
        if(ID.getText().toString().equals("") || Pw.getText().toString().equals("")){
            Toast.makeText(this, "ID 혹은 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            searchUser();
        }
    }

    public void signUpMethod(View view) {
        Intent intent = new Intent(this, SingUp.class);
        startActivity(intent);
    }

    /*
    public void searchUser() {
        Bundle bundle = getIntent().getExtras();
        Log.d("db", bundle.toString());
        assert bundle != null;
        data = (ArrayList<User>) bundle.getSerializable("totalData");
        for (User temp : data) {
            if ((temp.getId().equals(ID.getText().toString()) && temp.getPassword().equals(Pw.getText().toString()))) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                Bundle shot = new Bundle();
                Log.d("db","uid : "+temp.getId());
                shot.putString("uid",temp.getId());
                shot.putSerializable("totalData", data);
                intent.putExtras(shot);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();
            }
        }
        finish();
    }

     */

    public void searchUser(){
        String idValue = ID.getText().toString();
        String passwordValue = Pw.getText().toString();

        if(isLoginValid(idValue, passwordValue)) {
            Intent intent = new Intent(Login.this, MainActivity.class);

            Bundle user = new Bundle();
            user.putString("id",idValue);

            intent.putExtras(user);
            startActivity(intent);

            Toast.makeText(Login.this, "환영합니다!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(Login.this, "잘못된 입력입니다!", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isLoginValid(String idValue, String password) {
        boolean state = true;
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;

        data = (ArrayList<User>) bundle.getSerializable("totalData");
        for(int i = 0; i < data.size(); i++)
        {
            User temp = data.get(i);
            if((temp.getId() == idValue)&&(temp.getPassword()==password))
            {
                state = true;
                break;
            }
            else
            {
                state = false;
            }
        }

        return state;
    }

}