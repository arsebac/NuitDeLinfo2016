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
    SANITAIRE("Sanitaire", R.drawable.sanitaire),
    SANTE("Santé", R.drawable.sante),
    ASSOC("Association", R.drawable.assoc);


    static Map<String, MarkerType> map = new HashMap<>();
    private String type;
    private int icon;

    static {
        map.put("Sanitaire", SANITAIRE);
        map.put("Santé", SANTE);
        map.put("Association", ASSOC);
    }

    MarkerType(String type, int icon) {
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
        List<String> list = new ArrayList<>();
        for (String str : map.keySet()) list.add(str);
        return list;
    }
}
