package com.example.couniversity_new;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.couniversity_new.ui.delivery.DeliveryFragment;
import com.example.couniversity_new.ui.delivery.DeliveryRequestFragment;
import com.example.couniversity_new.ui.home.AddlistFragment;
import com.example.couniversity_new.ui.home.HomeFragment;
import com.example.couniversity_new.ui.mypage.MyPageFragment;
import com.example.couniversity_new.ui.qna.QnAFragment;
import com.example.couniversity_new.ui.qna.QnAaskFragment;
import com.example.couniversity_new.ui.setting.SettingEditFragment;
import com.example.couniversity_new.ui.setting.SettingFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    QnAFragment qnAFragment;
    QnAaskFragment qnAaskFragment;
    DeliveryFragment deliveryFragment;
    HomeFragment homeFragment;
    AddlistFragment addlistFragment;
    DeliveryRequestFragment deliveryRequestFragment;
    SettingEditFragment settingEditFragment;
    SettingFragment settingFragment;
    MyPageFragment myPageFragment;
    //String userInfo;
    ArrayList<User> data = new ArrayList<>();

    String uid;
    String upw;
    String uname;
    String uemail;
    String udept;

    String temp = "";

    long ucoupon;
    long qNum;
    long tNum;
    long rNum;

    User userinfo;

    ArrayList<String> userInfo = new ArrayList<>();

    ArrayList<String> allquestions = new ArrayList<>();
    ArrayList<String> myquestions = new ArrayList<>();
    ArrayList<String> myTodo = new ArrayList<>();
    ArrayList<String> allRequest = new ArrayList<>();
    ArrayList<String> myRequest = new ArrayList<>();

    int count_all;

    private static final String TAG = "debinf MainActivity";

    public static final String FRAGMENT_KEY = "fragment";

    private BottomNavigationView bottomNavigationView;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private NavController navController;

    private AppBarConfiguration appBarConfiguration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getInfo();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Couniversity");

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            uid = bundle.getString("id");
        }

        qnAFragment = new QnAFragment();
        qnAaskFragment = new QnAaskFragment();
        deliveryFragment= new DeliveryFragment();
        homeFragment = new HomeFragment();
        addlistFragment = new AddlistFragment();
        deliveryRequestFragment=new DeliveryRequestFragment();
        settingEditFragment = new SettingEditFragment();
        settingFragment = new SettingFragment();

        Button button = (Button) findViewById(R.id.qna_askaquestion);

        BottomNavigationView navView = (BottomNavigationView) findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_delivery, R.id.navigation_qna, R.id.navigation_home, R.id.navigation_mypage, R.id.navigation_setting)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


        Log.i(TAG, "onCreate: ");

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);
        navigationView = (NavigationView) findViewById(R.id.main_sidebar);
        drawerLayout = (DrawerLayout) findViewById(R.id.main_drawer);

        setupNavigation();
        //userinfo = getUserInfo();
        getUserInfo();
        getAllquestions();
        ucoupon = getUserCoupon();
        getMyquestions();
        getInfo();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.mygradient));
        }
    }
    // For getting value (database) in the Fragment
    public String getUserId(){
        return uid;
    }

    // For getting value (database) in the Fragment
    public String getUserName(){
        return uname;
    }

    public String getUserPW()
    {
        return upw;
    }

    public String getUserEmail()
    {
        return uemail;
    }

    public String getUserDept()
    {
        return udept;
    }

    public void setTitle(String title){
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView textView = new TextView(this);
        textView.setText(title);
        textView.setTextSize(20);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(getResources().getColor(R.color.white));
        Typeface typeface = ResourcesCompat.getFont(this, R.font.binggrae_bold);
        textView.setTypeface(typeface);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(textView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu) ;

        return true ;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.top_chat :
                Toast.makeText(this, "Chat menu button pressed",  Toast.LENGTH_SHORT).show();
                return true ;
            default :
                return super.onOptionsItemSelected(item) ;
        }
    }



    // 탭 내 fragment 변경
    public void onChangeFragment(int index){
        if(index == 0){
            int selectedItemId = bottomNavigationView.getSelectedItemId();

            if(selectedItemId == R.id.navigation_home)
            {
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,homeFragment).commit();
            }
            else if(selectedItemId == R.id.navigation_qna)
            {
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,qnAFragment).commit();
            }
            else if(selectedItemId == R.id.navigation_delivery)
            {
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,deliveryFragment).commit();
            }
        }else if(index ==1){
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,qnAaskFragment).commit();
        }
    }

    public void onChangeFragmentDelivery(int index){
        if(index == 0){
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,deliveryFragment).commit();
        }else if(index ==1){
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,deliveryRequestFragment).commit();
        }
    }


    public void onChangeFragmentHome(int index){
        if(index == 0){
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, homeFragment).commit();
        }else if(index ==1){
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, addlistFragment).commit();
        }
    }


    public void onChangeFragmentSetting(int index){
        if(index == 0){
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, settingFragment).commit();
        }else if(index ==1){
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, settingEditFragment).commit();
        }
    }

    public void onClickqna(View v) {
        Toast.makeText(this, "ask a question", Toast.LENGTH_LONG).show();
    }

    public void onClickdeliver(View v) {
        Toast.makeText(this, "request delivery", Toast.LENGTH_LONG).show();
    }


    private void setupNavigation() {
        Log.i(TAG, "setupNavigation: ");
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        appBarConfiguration =
                new AppBarConfiguration.Builder(navController.getGraph()) //Pass the ids of fragments from nav_graph which you dont want to show back button in toolbar
                        .setDrawerLayout(drawerLayout)
                        .build();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration); //Setup toolbar with back button and drawer icon according to appBarConfiguration
        NavigationUI.setupWithNavController(navigationView, navController);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        /*
         ** Listener for bottomNavigation must be called after been setupWithNavController
         ** This command will override NavigationUI.setupWithNavController(bottomNavigationView, navController)
         ** and the automatic transaction between fragments is lost
         * */
        //NavigationView.setOnNavigationItemSelectedListener(this);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        Log.i(TAG, "onBackPressed: ");
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            Log.i(TAG, "onBackPressed: DRAWER IS OPEN -  CLOSING IT");
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        Log.i(TAG, "onSupportNavigateUp: ");
        // replace navigation up button with nav drawer button when on start destination
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Log.i(TAG, "onNavigationItemSelected: SIDE BAR");
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        if (menuItem.getItemId() == R.id.drawer_question) {
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,qnAaskFragment).commit();
            Toast.makeText(this, "Ask a question",  Toast.LENGTH_SHORT).show();
        }
        if (menuItem.getItemId() == R.id.drawer_delivery) {
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,deliveryRequestFragment).commit();
            Toast.makeText(this, "Request delivery",  Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    /*
    public User getUserInfo(final String uid)
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference docRef = db.collection("User").document(uid);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null) {

                        Log.d("db", "id " + uid);
                        upw = document.getString("password");
                        Log.d("db", "password " + upw);
                        uname = document.getString("name");
                        Log.d("db", "name " + uname);
                        uemail = document.getString("email");
                        Log.d("db", "email " + uemail);
                        udept = document.getString("department");
                        Log.d("db", "udept " + udept);

                    } else {
                        Log.d("LOGGER", "No such document");
                    }
                } else {
                    Log.d("LOGGER", "get failed with ", task.getException());
                }
            }
        });
        User uInfo = new User(uid,upw,uname, uemail, udept);

        return uInfo;
    }

     */

    public void getUserInfo()
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference docRef = db.collection("User").document(uid);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null) {

                        Log.d("db", "id " + uid);
                        upw = document.getString("password");
                        Log.d("db", "password " + upw);
                        uname = document.getString("name");
                        Log.d("db", "name " + uname);
                        uemail = document.getString("email");
                        Log.d("db", "email " + uemail);
                        udept = document.getString("dept");
                        Log.d("db", "udept " + udept);

                    } else {
                        Log.d("LOGGER", "No such document");
                    }
                } else {
                    Log.d("LOGGER", "get failed with ", task.getException());
                }
            }
        });
    }

    public long getUserCoupon()
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference docRef = db.collection("User").document(uid);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null) {
                        ucoupon = document.getLong("coupon");
                        Log.d("db", "coupon : " + ucoupon);

                    } else {
                        Log.d("LOGGER", "No such document");
                    }
                } else {
                    Log.d("LOGGER", "get failed with ", task.getException());
                }
            }
        });

        return ucoupon;
    }

    public ArrayList<String> getAllquestions()
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        //getInfo();

        for(int i = 0; i < qNum; i++)
        {
            DocumentReference docRef = db.collection("qna").document(String.valueOf(i));
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document != null) {
                            if(!(allquestions.contains(document.getString("qtitle"))))
                            {
                                temp = document.getString("qtitle");
                                //Log.d("db", temp);
                                allquestions.add(temp);
                            }
                        } else {
                            Log.d("LOGGER", "No such document");
                        }
                    } else {
                        Log.d("LOGGER", "get failed with ", task.getException());
                    }
                }
            });
        }
        return allquestions;
    }


    public ArrayList<String> getMyquestions()
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        //getInfo();

        for(int i = 0; i < qNum; i++)
        {
            DocumentReference docRef = db.collection("qna").document(String.valueOf(i));
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document != null) {
                            if((uid.equals(document.getString("id")))&&!(myquestions.contains(document.getString("qtitle"))))
                            {
                                temp = document.getString("qtitle");
                                Log.d("db", uid + "=" + document.getString("id") + temp);
                                myquestions.add(temp);
                            }
                        } else {
                            Log.d("LOGGER", "No such document");
                        }
                    } else {
                        Log.d("LOGGER", "get failed with ", task.getException());
                    }
                }
            });
        }

        return myquestions;
    }


    public void uploadTodo(String work)
    {
        Todo todo = new Todo(uid, work);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        getInfo();
        db.collection("todo").document(String.valueOf(tNum)).set(todo);
        tNum++;
        Info info = new Info(qNum, tNum, rNum);
        db.collection("info").document("ID").set(info);
        Toast.makeText(getApplicationContext(),"새 할일이 업로드되었습니다!",Toast.LENGTH_SHORT).show();
    }

    public void uploadQuestion(String Qcontent, String Qtitle, long coupon)
    {
        QnA qna = new QnA(qNum, uid, Qtitle, Qcontent, coupon);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        getInfo();
        db.collection("qna").document(String.valueOf(qNum)).set(qna);
        qNum++;
        Info info = new Info(qNum, tNum, rNum);
        db.collection("info").document("ID").set(info);
        Toast.makeText(getApplicationContext(),"새 질문이 업로드되었습니다!",Toast.LENGTH_SHORT).show();
    }

    public void uploadRequest(String dcontent, String dtitle, String type, String sid, long coupon)
    {
        Delivery delivery = new Delivery(uid, rNum, dtitle, dcontent, type, sid, coupon);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        getInfo();
        db.collection("delivery").document(String.valueOf(rNum)).set(delivery);
        rNum++;
        Info info = new Info(qNum, tNum, rNum);
        db.collection("info").document("ID").set(info);
        Toast.makeText(getApplicationContext(),"새 요청이 업로드되었습니다!",Toast.LENGTH_SHORT).show();
    }

    public void getInfo()
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference docRef = db.collection("info").document("ID");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null) {
                        qNum = document.getLong("qNum");
                        tNum = document.getLong("tNum");
                        rNum = document.getLong("rNum");

                    } else {
                        Log.d("LOGGER", "No such document");
                    }
                } else {
                    Log.d("LOGGER", "get failed with ", task.getException());
                }
            }
        });
    }


    public ArrayList<String> getMyTodo()
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        //getInfo();

        for(int i = 0; i < tNum; i++)
        {
            DocumentReference docRef = db.collection("todo").document(String.valueOf(i));
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document != null) {
                            if((uid.equals(document.getString("id")))&&!(myTodo.contains(document.getString("work"))))
                            {
                                temp = document.getString("work");
                                Log.d("db", uid + "=" + document.getString("id") + temp);
                                myTodo.add(temp);
                            }
                        } else {
                            Log.d("LOGGER", "No such document");
                        }
                    } else {
                        Log.d("LOGGER", "get failed with ", task.getException());
                    }
                }
            });
        }

        return myTodo;
    }

    public ArrayList<String> getAllRequest()
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        getInfo();

        for(int i = 0; i < rNum; i++)
        {
            DocumentReference docRef = db.collection("delivery").document(String.valueOf(i));
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document != null) {
                            if(!(allRequest.contains(document.getString("dtitle"))))
                            {
                                temp = document.getString("dtitle");
                                //Log.d("db", temp);
                                allRequest.add(temp);
                            }
                        } else {
                            Log.d("LOGGER", "No such document");
                        }
                    } else {
                        Log.d("LOGGER", "get failed with ", task.getException());
                    }
                }
            });
        }
        return allRequest;
    }

    public ArrayList<String> getMyRequest()
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        getInfo();

        for(int i = 0; i < rNum; i++)
        {
            DocumentReference docRef = db.collection("delivery").document(String.valueOf(i));
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document != null) {
                            if((uid.equals(document.getString("rid")))&&!(myRequest.contains(document.getString("dtitle"))))
                            {
                                temp = document.getString("dtitle");
                                Log.d("db", uid + "=" + document.getString("id") + temp);
                                myRequest.add(temp);
                            }
                        } else {
                            Log.d("LOGGER", "No such document");
                        }
                    } else {
                        Log.d("LOGGER", "get failed with ", task.getException());
                    }
                }
            });
        }

        return myRequest;
    }

    public void updateUserInfo(String id, String pw, String name, String email, String dept)
    {
        FirebaseFirestore mDatabase = FirebaseFirestore.getInstance();
        DocumentReference documentReference = mDatabase.collection("User").document(id);
        documentReference.update("id", id);
        documentReference.update("password", pw);
        documentReference.update("name", name);
        documentReference.update("email", email);
        documentReference.update("dept", dept);

        uid = id;
    }
}
