package org.hackermen.migrantssansfrontieres.util;

import android.graphics.drawable.Icon;

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
    SANITAIRE("sanitaire",R.drawable.sanitaire),
    SANTE("sante", R.drawable.sante),
    ASSOC("assoc",R.drawable.assoc);


    static Map<String, MarkerType> map = new HashMap<>();
    private String type;
    private int icon;

    static {
        map.put("sanitaire", SANITAIRE);
        map.put("sante", SANTE);
        map.put("assoc", ASSOC);
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

    public static MarkerType getMarker(String type){
        return map.get(type);
    }

    public static List<String> getStringList(){
        List<String> list = new ArrayList<>();
        for (String str : map.keySet()){
            list.add(str);
        }
        return list;
    }
}
