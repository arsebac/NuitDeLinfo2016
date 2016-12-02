package org.hackermen.migrantssansfrontieres.util;

import org.hackermen.migrantssansfrontieres.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alexandre on 02/12/2016.
 */

public enum MarkerType {
    SANITAIRE("Sanitaire", "sanitaire", R.drawable.sanitaire),
    SANTE("Santé", "sante", R.drawable.sante),
    ASSOC("Association", "assoc", R.drawable.assoc);


    static Map<String, MarkerType> map = new HashMap<>();
    static List<String> names;
    private String description;
    private String type;
    private int icon;

    static {
        map.put("sanitaire", SANITAIRE);
        map.put("sante", SANTE);
        map.put("assoc", ASSOC);
        names = Arrays.asList("Sanitaire", "Santé", "Association");
    }

    MarkerType(String description, String type, int icon) {
        this.description = description;
        this.type = type;
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public int getIcon() {
        return icon;
    }

    public static MarkerType getMarker(String type) {
        return map.get(type);
    }

    public static List<String> getStringList() {
        return names;
    }
}
