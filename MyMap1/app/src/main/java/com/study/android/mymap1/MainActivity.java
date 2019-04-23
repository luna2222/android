package com.study.android.mymap1;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

import noman.googleplaces.NRPlaces;
import noman.googleplaces.Place;
import noman.googleplaces.PlaceType;
import noman.googleplaces.PlacesException;
import noman.googleplaces.PlacesListener;

public class MainActivity extends AppCompatActivity implements PlacesListener{

    private static final String TAG = "lecture";
//    private static final Object LocationServices = ;

    Button btn1, btn2, btn3;
    EditText editText1;
    SupportMapFragment mapFragment;
    GoogleMap map;
    MarkerOptions markerOptions;
    MarkerOptions markerOptions1;
    //    MarkerOptions myLocationMarker;
    Geocoder gecoder;
    LatLng point1;


    private Marker currentMarker = null;
    boolean mMoveMapByUser = true;
    boolean mRequestingLocationUpdates = false;
    GoogleApiClient mGoogleApiClient = null;
    LatLng currentPosition;


    List<Marker> previous_marker = null;
    Spinner spinner1;
    String[] items1 = {"subway_station", "bus_station", "transit_station",
            "taxi_stand", "parking", "gas_station", "atm", "bank", "cafe",
            "convenience_store", "restaurant", "supermarket", "shoppint_mall",
            "movie_theater", "hardware_store", "post_office","museum",
            "real_estate_agency", "school", "hospital", "fire_station", "police"};
    String item1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gecoder = new Geocoder(this);
        editText1 = findViewById(R.id.editText);
        markerOptions = new MarkerOptions();
        point1 = null;

        spinner1 = findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, items1);
        spinner1.setAdapter(adapter1);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            // 아이템이 선택되었을 때 호출됨
            @Override
            public void onItemSelected(AdapterView<?> adapterView,
                                       View view, int position, long id) {
                item1 = items1[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                item1 = null;
            }
        });


        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {
                Log.d(TAG, "GoogleMap is ready.");

                map = googleMap;
                setDefaultLocation();


                // 맵 터치 이벤트 구현 //
                map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng point) {
//                        markerOptions = new MarkerOptions();


                        // 마커 타이틀
                        markerOptions.title("마커 좌표");
                        Double latitude = point.latitude;  // 위도
                        Double longitude = point.longitude;  // 경도

                        String sA = sAddress(latitude, longitude);

                        // 마커의 스니펫(간단한 텍스트) 설정
//                        markerOptions.snippet(sA + ",\n" +latitude.toString() +
//                                ",\n" + longitude.toString());

//                        markerOptions.snippet(sA + "," +latitude.toString() +
//                                "," + longitude.toString());
                        markerOptions.title(sA + "\n");
                        markerOptions.snippet(latitude.toString() +
                                "," + longitude.toString());

                        // LatLug : 위도 경도 쌍을 나타냄
                        markerOptions.position(new LatLng(latitude, longitude));

                        // 마커 (핀) 추가
                        googleMap.addMarker(markerOptions);

                        System.out.println(sA + ",\n" +latitude.toString() + ",\n" +
                                longitude.toString());
                    }
                });
            }
        });

        try {
            MapsInitializer.initialize(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }







    public void onBtn1Clicked(View v) {
        Log.d(TAG, "onBtn1Clicked");

        requestMyLocation();
    }

    private void requestMyLocation() {
        LocationManager manager =
                (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        try {
            long minTime = 10000;
            float minDistance = 0;
            manager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    minTime,
                    minDistance,
                    new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            showCurrentLocation(location);

                            Log.d(TAG, "requestMyLocation");
                            String s = location.toString();
                            Log.d("위치 : ", s);

//                            showMyLocationMarker(location);
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
            );

            Location lastLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (lastLocation != null) {
                showCurrentLocation(lastLocation);

                String s = lastLocation.toString();
                Toast.makeText(this, s, Toast.LENGTH_LONG).show();
                Log.d("위치 : ", s);
            }

            manager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    minTime,
                    minDistance,
                    new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            showCurrentLocation(location);

                            String s = location.toString();
                            Log.d("위치 : ", s);

//                            showMyLocationMarker(location);
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
            );
        } catch (SecurityException e) {
            e.printStackTrace();
        }

    }

    private void showCurrentLocation(Location location) {
        LatLng curPoint = new LatLng(location.getLatitude(), location.getLongitude());

        map.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 15));
        showMyLocationMarker(location);

    }

    private void showMyLocationMarker(Location location) {

        // 좌표(위도, 경도) 생성
        Double latitude = location.getLatitude();
        Double longitude = location.getLongitude();

        LatLng point2 = new LatLng(latitude, longitude);
        point1 = point2;


        String sA = sAddress(latitude, longitude);

        if (markerOptions1 == null) {
            markerOptions1 = new MarkerOptions();
            markerOptions1.position(point2);
            markerOptions1.title(sA + "\n");
            markerOptions1.snippet(latitude.toString() + "," +
                    longitude.toString());
//            markerOptions1.icon(BitmapDescriptorFactory.fromResource(R.drawable.mylocation));
            map.addMarker(markerOptions1);
        } else {
//            previous_marker.clear();

            markerOptions1 = new MarkerOptions();
            markerOptions1.position(point2);
            markerOptions1.title(sA + "\n");
            markerOptions1.snippet(latitude.toString() + "," +
                    longitude.toString());
//            markerOptions1.icon(BitmapDescriptorFactory.fromResource(R.drawable.mylocation));
            map.addMarker(markerOptions1);

//            markerOptions1.position(new LatLng(location.getLatitude(), location.getLongitude()));
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onPause() {
        super.onPause();
        if(map != null){
            map.setMyLocationEnabled(false);
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onResume() {
        super.onResume();
        if(map != null){
            map.setMyLocationEnabled(true);
        }
    }



    public void onBtn2Clicked(View v){

        String str = editText1.getText().toString();
        List<Address> addressList = null;
        try{
            // editText1에 입력한 텍스트 (주소, 지역, 장소 등)을
            // 지오 코딩을 이용해 변환
            addressList = gecoder.getFromLocationName(
                    str, // 주소
                    100); // 최대 검색 결과 개수
        }catch (IOException e){
            e.printStackTrace();
        }

        if(addressList != null){
            System.out.println(addressList.get(0).toString());
            // 콤마를 기준으로 split
            String[] splitStr = addressList.get(0).toString().split(",");
            String address = splitStr[0].substring(splitStr[0].indexOf("\"") +
                    1, splitStr[0].length() - 2);  // 주소
            System.out.println(address);

            String latitude = splitStr[10].substring(splitStr[10].indexOf("=") +
                    1);  //  위도

            String longitude = splitStr[12].substring(splitStr[12].indexOf("=") +
                    1);  //  경도

            System.out.println(latitude);
            System.out.println(longitude);

            // 좌펴(위도, 경도) 생성
            // LatLng point 에 data 전송
            point1 = new LatLng(Double.parseDouble(latitude),
                    Double.parseDouble(longitude));

            String sA = sAddress(Double.parseDouble(latitude),
                    Double.parseDouble(longitude));
            // 마커 생성
            MarkerOptions markerOptions2 = new MarkerOptions();
            markerOptions2.title(sA + "\n");
            markerOptions2.snippet(latitude + "," + longitude);
            markerOptions2.position(point1);
            // 마커 추가
            map.addMarker(markerOptions2);
            // 해당 좌표로 화면 줌
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(point1, 15));

        }else{
            Toast.makeText(this, "검색 내용을 입력해 주세요.", Toast.LENGTH_LONG).show();
        }



        // Add a marker in Sydeny and move the camera
//        LatLng sydeny = new LatLng(-34, 151);
//        map.addMarker(new MarkerOptions().position(sydeny).title("Marker in Sydey"));
//        map.moveCamera(CameraUpdateFactory.newLatLng(sydeny));


        System.out.println("point1 : " + point1);
    }



    public String getCurrentAddress(LatLng latlng) {

        //지오코더... GPS를 주소로 변환
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        List<Address> addresses;

        try {

            addresses = geocoder.getFromLocation(
                    latlng.latitude,
                    latlng.longitude,
                    1);
        } catch (IOException ioException) {
            //네트워크 문제
            Toast.makeText(this, "지오코더 서비스 사용불가", Toast.LENGTH_LONG).show();
            return "지오코더 서비스 사용불가";
        } catch (IllegalArgumentException illegalArgumentException) {
            Toast.makeText(this, "잘못된 GPS 좌표", Toast.LENGTH_LONG).show();
            return "잘못된 GPS 좌표";

        }


        if (addresses == null || addresses.size() == 0) {
            Toast.makeText(this, "주소 미발견", Toast.LENGTH_LONG).show();
            return "주소 미발견";

        } else {
            Address address = addresses.get(0);
            return address.getAddressLine(0).toString();
        }

    }


    public String sAddress(Double latitude, Double longitude){

        List<Address> list = null;
        String sLatitude;
        String sLongitude;

        try{
            // editText1에 입력한 텍스트 (주소, 지역, 장소 등)을
            // 지오 코딩을 이용해 변환
            list = gecoder.getFromLocation(
                    latitude, // 주소
                    longitude,
                    10); // 최대 검색 결과 개수
        }catch (IOException e){
            e.printStackTrace();
        }

        System.out.println(list.get(0).toString());
        // 콤마를 기준으로 split
        String[] splitStr = list.get(0).toString().split(",");
        String address = splitStr[0].substring(splitStr[0].indexOf("\"") +
                1, splitStr[0].length() - 2);  // 주소
        System.out.println(address);

        sLatitude = splitStr[10].substring(splitStr[10].indexOf("=") +
                1);  //  위도

        sLongitude = splitStr[12].substring(splitStr[12].indexOf("=") +
                1);  //  경도

        System.out.println(address);
        System.out.println(latitude);
        System.out.println(longitude);

        return address;

    }


    public void onBtn5Clicked(View v){
        previous_marker = new ArrayList<Marker>();
        if(point1 == null){
            Toast.makeText(this, "1 : 현재 위치정보가 없습니다.", Toast.LENGTH_LONG).show();
        }else {
            showPlaceInformation(point1);
        }
    }

    public void setDefaultLocation() {

        mMoveMapByUser = false;


        //디폴트 위치, Seoul
        LatLng DEFAULT_LOCATION = new LatLng(37.56667, 126.97806);
        String markerTitle = "";
        String markerSnippet = "서울";


        if (currentMarker != null) currentMarker.remove();

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(DEFAULT_LOCATION);
        markerOptions.title(markerTitle);
        markerOptions.snippet(markerSnippet);
        markerOptions.draggable(true);
//        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
//        currentMarker = map.addMarker(markerOptions);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(DEFAULT_LOCATION, 15);
        map.moveCamera(cameraUpdate);
    }

    @Override
    public void onPlacesFailure(PlacesException e) {

    }

    @Override
    public void onPlacesStart() {

    }

    @Override
    public void onPlacesSuccess(final List<Place> places) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for(noman.googleplaces.Place place : places){

                    LatLng latLng
                            = new LatLng(place.getLatitude()
                            , place.getLongitude());

                    String markerSnippet = getCurrentAddress(latLng);


                    MarkerOptions markerOptions = new MarkerOptions();

                    markerOptions.position(latLng);

                    markerOptions.title(place.getName());

                    markerOptions.snippet(markerSnippet);

                    Marker item = map.addMarker(markerOptions);

                    previous_marker.add(item);


                }


                //중복 마커 제거

                HashSet<Marker> hashSet = new HashSet<Marker>();

                hashSet.addAll(previous_marker);

                previous_marker.clear();

                previous_marker.addAll(hashSet);


            }

        });

    }



    public void showPlaceInformation(LatLng point)
    {
        if(point != null){
            map.clear();//지도 클리어

            if (previous_marker != null)
                previous_marker.clear();//지역정보 마커 클리어

            new NRPlaces.Builder()
                    .listener(MainActivity.this)
                    .key("AIzaSyCpHO4ZHpogqBAdk3j3kfjxboqnAJlDh24")
                    .latlng(point.latitude, point.longitude)//현재 위치
                    .radius(1000) //500 미터 내에서 검색
//                .type(PlaceType.RESTAURANT) //음식점
//                .type(PlaceType.ATM)
                    .type(item1)
                    .build()
                    .execute();
        }else{
            Toast.makeText(this, "2 : 현재 위치정보가 없습니다.", Toast.LENGTH_LONG).show();
            System.out.println("latitude : " + point.latitude);
            System.out.println("longitude : " + point.longitude);

        }
    }

    @Override
    public void onPlacesFinished() {

    }






}
