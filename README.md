# MP

2020.06.07 19:08 updated.

After applying map API, get error when we switch from login page to main page.
There is no error message but we must solve this forced termination.

If you want to see other functions, use below 2 files
If use those files, you can implement other functions except delivery functions
because i changed from google map API to Naver map API in gradle files and manifest files :

<fragment_deliver.xml>
--------------------------------------------------------------------------------------------------------------

<?xml version="1.0" encoding="UTF-8"?>

<androidx.constraintlayout.widget.ConstraintLayout 
tools:context=".ui.delivery.DeliveryFragment" 
android:layout_height="match_parent" 
android:layout_width="match_parent" 
xmlns:tools="http://schemas.android.com/tools" 
xmlns:app="http://schemas.android.com/apk/res-auto" 
xmlns:android="http://schemas.android.com/apk/res/android">


<com.google.android.gms.maps.MapView 
android:layout_height="match_parent" 
android:layout_width="match_parent" 
android:id="@+id/fram_map">

<fragment 
android:layout_height="match_parent" 
android:layout_width="match_parent" 
android:id="@+id/map" 
android:name="com.google.android.gms.maps.MapFragment"
/>

<Button 
android:layout_height="80dp" 
android:layout_width="80dp" 
android:id="@+id/request_list" 
android:layout_marginRight="8dp" 
android:layout_marginTop="470dp" 
android:background="@drawable/list" 
android:layout_gravity="right|top"
/>

</com.google.android.gms.maps.MapView>

<TextView 
android:layout_height="wrap_content" 
android:layout_width="match_parent" 
android:id="@+id/text_delivery" 
android:layout_marginTop="8dp" 
android:gravity="center_horizontal" 
app:layout_constraintTop_toTopOf="parent" 
app:layout_constraintStart_toStartOf="parent" 
app:layout_constraintEnd_toEndOf="parent" 
app:layout_constraintBottom_toBottomOf="parent" 
android:textSize="20sp" 
android:textAlignment<?xml version="1.0" encoding="UTF-8"?>

<androidx.constraintlayout.widget.ConstraintLayout 
tools:context=".ui.delivery.DeliveryFragment" 
android:layout_height="match_parent" 
android:layout_width="match_parent" 
xmlns:tools="http://schemas.android.com/tools" 
xmlns:app="http://schemas.android.com/apk/res-auto" 
xmlns:android="http://schemas.android.com/apk/res/android">


<com.google.android.gms.maps.MapView 
android:layout_height="match_parent" 
android:layout_width="match_parent" 
android:id="@+id/fram_map">

<fragment 
android:layout_height="match_parent" 
android:layout_width="match_parent" 
android:id="@+id/map" 
android:name="com.google.android.gms.maps.MapFragment"
/>

<Button 
android:layout_height="80dp" 
android:layout_width="80dp" 
android:id="@+id/request_list" 
android:layout_marginRight="8dp" 
android:layout_marginTop="470dp" 
android:background="@drawable/list" 
android:layout_gravity="right|top"
/>

</com.google.android.gms.maps.MapView>

<TextView 
android:layout_height="wrap_content" 
android:layout_width="match_parent" 
android:id="@+id/text_delivery" 
android:layout_marginTop="8dp" 
android:gravity="center_horizontal" 
app:layout_constraintTop_toTopOf="parent" 
app:layout_constraintStart_toStartOf="parent" 
app:layout_constraintEnd_toEndOf="parent" 
app:layout_constraintBottom_toBottomOf="parent" 
android:textSize="20sp" 
android:textAlignment="center" 
android:layout_marginEnd="8dp" 
android:layout_marginStart="8dp"
/>

</androidx.constraintlayout.widget.ConstraintLayout>="center" 
android:layout_marginEnd="8dp" 
android:layout_marginStart="8dp"
/>

</androidx.constraintlayout.widget.ConstraintLayout>

----------------------------------------------------------------------------------------------------------------


<DelivertFragment.java>
----------------------------------------------------------------------------------------------------------------

package com.example.couniversity_new.ui.delivery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.couniversity_new.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DeliveryFragment extends Fragment implements OnMapReadyCallback {

    private DeliveryViewModel deliveryViewModel;
    private MapView mapView = null;
    private GoogleMap Gmap;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        deliveryViewModel =
                ViewModelProviders.of(this).get(com.example.couniversity_new.ui.delivery.DeliveryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_delivery, container, false);

        mapView=(MapView)root.findViewById(R.id.fram_map);
        mapView.getMapAsync(this);
        /*
        final TextView textView = root.findViewById(R.id.text_delivery);
        deliveryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
        getActivity().finish();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onLowMemory();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (mapView != null) {
            mapView.onCreate(savedInstanceState);
        }

    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Gmap=googleMap;

        LatLng SEOUL = new LatLng(37.56, 126.97);

        MarkerOptions markerOptions = new MarkerOptions();

        markerOptions.position(SEOUL);

        markerOptions.title("aa");

        markerOptions.snippet("bb");

        Gmap.addMarker(markerOptions);

        Gmap.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));

        Gmap.animateCamera(CameraUpdateFactory.zoomTo(13));
    }
}

----------------------------------------------------------------------------------------------------------------



----------------------------------------------------------------------------------------------------------------
<About database>  

Also, in future, we will add the database.

If needed, we will use web database.

----------------------------------------------------------------------------------------------------------------
