package com.example.couniversity_new;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SingUp extends AppCompatActivity {


    private static final int SEARCH_ADDRESS_ACTIVITY = 10000;
    private static final int SEARCH_DESTINATION_ACTIVITY = 20000;

    private EditText ID;
    private EditText PW;
    private EditText Name;
    private EditText dept;
    private EditText email;

    private Button SignUP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        ID = findViewById(R.id.signUpId);
        PW = findViewById(R.id.signUpPw);
        Name = findViewById(R.id.signUpName);
        dept = findViewById(R.id.signUpdepartment);
        email = findViewById(R.id.signUpemail);

        SignUP = findViewById(R.id.register);

        SignUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser(view);
            }
        });

    }

    public void registerUser(View v){
        String idValue = ID.getText().toString();
        String pwValue = PW.getText().toString();
        String nameValue = Name.getText().toString();
        String deptValue = dept.getText().toString();
        String emailValue = email.getText().toString();

        Map<String,Object> user = new HashMap();

        user.put("id",idValue);
        user.put("password",pwValue);
        user.put("name",nameValue);
        user.put("email",emailValue);
        user.put("department", deptValue);

        User uInfo = new User(idValue, pwValue, nameValue, emailValue, deptValue);

        if(pwValue.equals("") || idValue.equals("") ){
            Toast.makeText(this, "정보가 누락되었습니다.", Toast.LENGTH_SHORT).show();
        }
        else{
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("User").document(idValue).set(uInfo);
            Toast.makeText(getApplicationContext(),"회원가입 성공!",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
        }
    }
}
