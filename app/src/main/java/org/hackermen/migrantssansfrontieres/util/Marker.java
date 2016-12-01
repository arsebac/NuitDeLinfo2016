package org.hackermen.migrantssansfrontieres.util;

/**
 * Created by Alexandre on 01/12/2016.
 */

public class Marker {
    private String name;
    private String description;
    private long latitude;
    private long longitude;
    private String type;

    public Marker(long longitude, long latitude, String name, String description, String type) {
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public long getLatitude() {
        return latitude;
    }

    public long getLongitude() {
        return longitude;
    }
}
