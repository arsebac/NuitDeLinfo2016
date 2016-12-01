package org.hackermen.migrantssansfrontieres;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.hackermen.migrantssansfrontieres.util.GPSTracker;
import org.hackermen.migrantssansfrontieres.util.MapUtil;
import org.hackermen.migrantssansfrontieres.util.Marker;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private List<Marker> markers;
    private MapUtil mapUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapUtil = new MapUtil(this);

        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        // Add a marker in Sydney and move the camera
        GPSTracker gpsTracker = new GPSTracker(this);
        LatLng currentLocation = new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude());
        mMap.addMarker(new MarkerOptions().position(currentLocation));

        markers = mapUtil.getNearbyPointers();;

        for (Marker marker : markers) {
            LatLng latLng = new LatLng(marker.getLongitude(), marker.getLatitude());
            mMap.addMarker(new MarkerOptions().position(latLng)
                    .title(marker.getName()));
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
        mMap.setOnMapClickListener(new ClickListener());
    }

    private class ClickListener implements GoogleMap.OnMapClickListener

    {

        @Override
        public void onMapClick(LatLng latLng) {
            MarkerOptions markerOptions = new MarkerOptions();

            markerOptions.position(latLng);
            String title = latLng.latitude + " : " + latLng.longitude;
            markerOptions.title(title);

            mMap.addMarker(markerOptions);
            mapUtil.addMarker((long) latLng.longitude, (long) latLng.latitude, title, "", "");
        }
    }


}
