package org.hackermen.migrantssansfrontieres;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.hackermen.migrantssansfrontieres.util.GPSTracker;
import org.hackermen.migrantssansfrontieres.util.MapUtil;
import org.hackermen.migrantssansfrontieres.util.Marker;
import org.hackermen.migrantssansfrontieres.util.MarkerType;

import java.util.Arrays;
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


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        // Add a marker in Sydney and move the camera
        GPSTracker gpsTracker = new GPSTracker(this);
        LatLng currentLocation = new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude());
        mMap.addMarker(new MarkerOptions().position(currentLocation));

//        markers = mapUtil.getNearbyPointers();;

        markers = Arrays.asList(new Marker(-31, 150, "test", MarkerType.ASSOC));

        for (Marker marker : markers) {
            LatLng latLng = new LatLng(marker.getLongitude(), marker.getLatitude());
            MarkerType type = marker.getType();
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), type.getIcon(), null);
            bitmap = bitmap.createScaledBitmap(bitmap, 60, 60, false);
            mMap.addMarker(new MarkerOptions().position(latLng)
                    .title(marker.getName())
                    .icon(BitmapDescriptorFactory.fromBitmap(bitmap)));
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
        mMap.setOnMapClickListener(new ClickListener());
    }

    private class OnMarkerClick implements GoogleMap.OnMarkerClickListener{

        @Override
        public boolean onMarkerClick(final com.google.android.gms.maps.model.Marker marker) {
            Dialog dialog;
            final EditText editTextName = new EditText(getApplicationContext());
            editTextName.setTextColor(Color.BLACK);
            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
            builder.setMessage("Editer un marker.")
                    .setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            deleteMarker(marker);
                        }
                    })
                    .setNegativeButton(R.string.edit, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            editMarker(marker);
                        }
                    });
            // Create the AlertDialog object and return it
            dialog = builder.create();
            dialog.addContentView(editTextName, null);
            return true;
        }

        private void editMarker(final com.google.android.gms.maps.model.Marker marker){
            Dialog dialog;
            final EditText editTextName = new EditText(getApplicationContext());
            editTextName.setTextColor(Color.BLACK);
            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
            builder.setMessage("Initialiser un nouveau marker.")
                    .setPositiveButton(R.string.fire, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
//                            mapUtil.editMarker(marker);
//                            marker.remove();
//                            MarkerOptions options = new MarkerOptions().position(marker.getPosition())
//                                    .title(marker.getTitle()).icon(marker.get)
//
//                            mMap.addMarker(new MarkerOptions().position(latLng)
//                                    .title(marker.getName())
//                                    .icon(BitmapDescriptorFactory.fromBitmap(bitmap)));

                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            // Create the AlertDialog object and return it
            dialog = builder.create();
            dialog.addContentView(editTextName, null);
        }

        private void deleteMarker(final com.google.android.gms.maps.model.Marker marker){
            Dialog dialog;
            final EditText editTextName = new EditText(getApplicationContext());
            editTextName.setTextColor(Color.BLACK);
            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
            builder.setMessage("Voulez vous vraiment supprimer ce marker ?")
                    .setPositiveButton(R.string.fire, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            mapUtil.deleteMarker(marker);
                            marker.remove();
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            // Create the AlertDialog object and return it
            dialog = builder.create();
            dialog.addContentView(editTextName, null);
        }
    }


    private class ClickListener implements GoogleMap.OnMapClickListener {
        private String name;
        private String type;
        private boolean set = false;


        @Override
        public void onMapClick(LatLng latLng) {
            MarkerOptions markerOptions = new MarkerOptions();

            markerOptions.position(latLng);
            setMarker();
            markerOptions.title(name);

            mMap.addMarker(markerOptions);
            mapUtil.addMarker((long) latLng.longitude, (long) latLng.latitude, name, "");
        }

        public void setMarker() {
            Dialog dialog;
            final EditText editTextName = new EditText(getApplicationContext());
            editTextName.setTextColor(Color.BLACK);
            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
            builder.setMessage("Initialiser un nouveau marker.")
                    .setPositiveButton(R.string.fire, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            set = true;
                            name = String.valueOf(editTextName.getText());

                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            // Create the AlertDialog object and return it
            dialog = builder.create();
            dialog.addContentView(editTextName, null);
        }
    }


}
