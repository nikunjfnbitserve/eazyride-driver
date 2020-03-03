package com.eziride.driverRide.ui.home;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.eziride.driverRide.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class HomeFragment extends Fragment implements OnMapReadyCallback, LocationListener {




   private static final int REQUEST_CODE = 200;
    int PERMISSION_ID = 44;
   FusedLocationProviderClient fusedLocationProviderClient;

    Switch swithkey;

    MapView driverMapView;
    GoogleMap mMap;
    TextView status_tv;
    LocationManager locationManager;
    double lat,lang;

    Location currentLocation;
    LocationCallback mLocationCallback;




      public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        //add permission


       fusedLocationProviderClient =LocationServices.getFusedLocationProviderClient(getActivity());

        getLastLocation();



        swithkey=view.findViewById(R.id.avb_switch);
        status_tv=view.findViewById(R.id.statustv);



        swithkey.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    status_tv.setText("online");

                    Toast.makeText(getActivity(), "Your are Online Now", Toast.LENGTH_SHORT).show();

                }else{
                    status_tv.setText("Offline");

                    Toast.makeText(getActivity(), "You are Offline Now", Toast.LENGTH_SHORT).show();


                }

            }
        });



        driverMapView=view.findViewById(R.id.mapView);
        driverMapView.onCreate(savedInstanceState);

        driverMapView.getMapAsync(this);




        return view;



    }

    private void getLastLocation() {
          if(checkPermissions()){
              if(isLocationEnabled())
              {
                  fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                      @Override
                      public void onComplete(@NonNull Task<Location> task) {

                          currentLocation=task.getResult();
                          if(currentLocation==null){
                              requestNewLocationData();
                          }else{
                              lat=currentLocation.getLatitude();
                              lang=currentLocation.getLongitude();
                          }

                      }
                  }
                  );
              }
              else{
                  Toast.makeText(getActivity(), "Turn on location", Toast.LENGTH_LONG).show();
                  Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                  startActivity(intent);
              }

          }
          else{

              requestPermission();

          }
    }



    private void requestPermission() {
          ActivityCompat.requestPermissions(
                  getActivity(),new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},PERMISSION_ID);
    }

    private void requestNewLocationData() {

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(0);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        fusedLocationProviderClient.requestLocationUpdates(
                mLocationRequest, mLocationCallback,
                Looper.myLooper());
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }

    //CheckPermission
    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap=googleMap;
        LatLng myloc= new LatLng(lat,lang);

        //create marker


        //Clear the Prervious position
        mMap.clear();

       // map.getUiSettings().setMyLocationButtonEnabled(true);
        //map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(43.1,-88.8)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myloc));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myloc,15));
        mMap.addMarker(new MarkerOptions().position(myloc).title("my location").icon(bitmapDescriptorFromVector(getActivity(),R.drawable.bikeicon)));



    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context,int vectorResId){
        Drawable vectorDrawable= ContextCompat.getDrawable(context,vectorResId);
        vectorDrawable.setBounds(0,0,vectorDrawable.getIntrinsicWidth(),vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap =Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getMinimumHeight(),Bitmap.Config.ARGB_8888);
           Canvas canvas= new Canvas(bitmap);
           vectorDrawable.draw(canvas);

          return BitmapDescriptorFactory.fromBitmap(bitmap);
      }



    @Override
    public void onResume() {
        driverMapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        driverMapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        driverMapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        driverMapView.onLowMemory();
        super.onLowMemory();
    }

    @Override
    public void onLocationChanged(Location location) {



    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
