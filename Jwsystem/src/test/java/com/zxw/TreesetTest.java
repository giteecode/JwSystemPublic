package com.zxw;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;

public class TreesetTest {
    public static void main(String[] args) {
        TreeSet<Integer>  a = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        TreeSet<Integer>  b = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        Collection<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        a.addAll(list);
        for (Integer integer : list) {
            b.add(integer);
        }
        System.out.println(a);
        System.out.println(b);
    }
}
