package org.hackermen.migrantssansfrontieres.util;

/**
 * Created by Alexandre on 01/12/2016.
 */

public class Marker {
    private String name;
    private long latitude;
    private long longitude;
    private MarkerType type;

    public Marker(long longitude, long latitude, String name, MarkerType type) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
    }

    public MarkerType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public long getLatitude() {
        return latitude;
    }

    public long getLongitude() {
        return longitude;
    }
}
