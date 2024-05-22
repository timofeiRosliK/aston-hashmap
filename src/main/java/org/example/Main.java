package org.example;

import dz_1.GenHashMap;

public class Main {
    public static void main(String[] args) {
        GenHashMap<String, Integer>  genHashMap = new GenHashMap<>();
        genHashMap.put("medical_physicist", 500);
        genHashMap.put("engineer", 400);
        genHashMap.put("driver", 600);
        genHashMap.put("driver", 6500);



        int size = genHashMap.size();
        System.out.println(size);

    }
}