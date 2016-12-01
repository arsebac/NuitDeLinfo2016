package org.hackermen.migrantssansfrontieres.util;

/**
 * Created by Alexandre on 01/12/2016.
 */

public class Pointer {
    private String name;
    private String description;
    private long latitude;
    private long longitude;

    public Pointer(long longitude, long latitude, String name, String description) {
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
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
