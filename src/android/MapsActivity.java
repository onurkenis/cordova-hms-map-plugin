package com.huawei.hmsmapplugin;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.huawei.hms.maps.CameraUpdate;
import com.huawei.hms.maps.CameraUpdateFactory;
import com.huawei.hms.maps.HuaweiMap;
import com.huawei.hms.maps.MapView;
import com.huawei.hms.maps.OnMapReadyCallback;
import com.huawei.hms.maps.model.BitmapDescriptorFactory;
import com.huawei.hms.maps.model.CameraPosition;
import com.huawei.hms.maps.model.CircleOptions;
import com.huawei.hms.maps.model.LatLng;
import com.huawei.hms.maps.model.Marker;
import com.huawei.hms.maps.model.MarkerOptions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final String TAG = "MapsActivity";

    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";

    private static final int REQUEST_CODE = 100;

    private static final LatLng LAT_LNG = new LatLng(31.2304, 121.4737);

    private HuaweiMap hmap;

    private MapView mMapView;

    private Marker mMarker;

    private static final String[] RUNTIME_PERMISSIONS = { Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int activityMapsId = getResources().getIdentifier("activity_maps", "layout", getPackageName());
        setContentView(activityMapsId);

        if (!hasPermissions(this, RUNTIME_PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, RUNTIME_PERMISSIONS, REQUEST_CODE);
        }

        int mapViewId = getResources().getIdentifier("mapView", "id", getPackageName());
        mMapView = findViewById(mapViewId);

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }
        mMapView.onCreate(mapViewBundle);

        // get map by async method
        mMapView.getMapAsync(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mMapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mMapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onMapReady(HuaweiMap map) {
        Log.d(TAG, "onMapReady: ");

        // after call getMapAsync method ,we can get HuaweiMap instance in this call
        // back method
        hmap = map;
        hmap.setMyLocationEnabled(true);

        // move camera by CameraPosition param ,latlag and zoom params can set here
        CameraPosition build = new CameraPosition.Builder().target(new LatLng(60, 60)).zoom(5).build();

        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(build);
        hmap.animateCamera(cameraUpdate);
        hmap.setMaxZoomPreference(5);
        hmap.setMinZoomPreference(2);

        int markerId = getResources().getIdentifier("badge_ph", "drawable", getPackageName());

        // mark can be add by HuaweiMap
        mMarker = hmap.addMarker(new MarkerOptions().position(LAT_LNG)
                .icon(BitmapDescriptorFactory.fromResource(markerId)).clusterable(true));

        mMarker.showInfoWindow();

        // circle can be add by HuaweiMap
        hmap.addCircle(new CircleOptions().center(new LatLng(60, 60)).radius(5000).fillColor(Color.GREEN));
    }

    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    private static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
}
