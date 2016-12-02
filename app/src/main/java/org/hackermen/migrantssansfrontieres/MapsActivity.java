package org.hackermen.migrantssansfrontieres;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.vision.text.Text;

import org.hackermen.migrantssansfrontieres.util.GPSTracker;
import org.hackermen.migrantssansfrontieres.util.MapUtil;
import org.hackermen.migrantssansfrontieres.util.Marker;
import org.hackermen.migrantssansfrontieres.util.MarkerType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
            mMap.setOnMarkerClickListener(new OnMarkerClick());
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
        mMap.setOnMapClickListener(new ClickListener());
    }

    private class OnMarkerClick implements GoogleMap.OnMarkerClickListener{

        @Override
        public boolean onMarkerClick(final com.google.android.gms.maps.model.Marker marker) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
            builder.setMessage(marker.getTitle())
                    .setCancelable(true)
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
            builder.create().show();
            return true;
        }

        private void editMarker(final com.google.android.gms.maps.model.Marker marker){
            AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
            LinearLayout layout = new LinearLayout(MapsActivity.this);
            layout.setOrientation(LinearLayout.VERTICAL);
            final EditText editText = new EditText(MapsActivity.this);
            editText.setTextColor(Color.BLACK);
            Spinner spinner = new Spinner(MapsActivity.this);
            spinner.setAdapter(new ArrayAdapter<>(MapsActivity.this, R.layout.text_view, MarkerType.getStringList()));

            layout.addView(editText);
            layout.addView(spinner);

            builder.setMessage("Initialiser un nouveau marker.")
                    .setCancelable(true)
                    .setView(layout)
                    .setPositiveButton(R.string.fire, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            mapUtil.editMarker(marker);
                            marker.remove();
                            Marker marker1 = null;
                            for (int i = 0; i < markers.size(); i++){
                                if (markers.get(i).getName().equals(marker.getTitle())){
                                    marker1 = markers.get(i);
                                    break;
                                }
                            }
                            editText.setText(marker1.getName());
                            LatLng latLng = new LatLng(marker1.getLongitude(), marker1.getLatitude());
                            MarkerType type = marker1.getType();
                            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), type.getIcon(), null);
                            bitmap = bitmap.createScaledBitmap(bitmap, 60, 60, false);

                            mMap.addMarker(new MarkerOptions().position(latLng)
                                    .title(marker1.getName())
                                    .icon(BitmapDescriptorFactory.fromBitmap(bitmap)));

                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            // Create the AlertDialog object and return it
            builder.create().show();
        }

        private void deleteMarker(final com.google.android.gms.maps.model.Marker marker){
            AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
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
            builder.create().show();
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
            final EditText editTextName = new EditText(MapsActivity.this);
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
