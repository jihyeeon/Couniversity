package com.example.couniversity_new;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Splash extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_screen);

        load();
    }

    public void load(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final ArrayList<User> data = new ArrayList<>();
        db.collection("user")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("totalData",data);
                            Intent intent = new Intent(Splash.this, Login.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            finish();
                        } else {

                        }
                    }
                });
    }
}