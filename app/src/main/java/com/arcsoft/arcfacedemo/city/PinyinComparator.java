package com.arcsoft.arcfacedemo.city;

import java.util.Comparator;

public class PinyinComparator implements Comparator<CityData> {
    @Override
    public int compare(CityData o1, CityData o2) {
        if (o1.getFirstA().equals("@")
                || o2.getFirstA().equals("#")) {
            return -1;
        } else if (o1.getFirstA().equals("#")
                || o2.getFirstA().equals("@")) {
            return 1;
        } else {
            return o1.getFirstA().compareTo(o2.getFirstA());
        }
    }
}
