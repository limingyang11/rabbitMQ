package com.lmy.receive.utils;

import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lmy
 * @data 2021-08-06 17:22
 */
public class OrderMap {

    private OrderMap() {
    }

    private static OrderMap orderMap;

    private static Map<String, Integer> map = new ConcurrentHashMap<>();

    public static OrderMap getInstance() {
        if (orderMap == null) {
            synchronized (OrderMap.class) {
                if (orderMap == null) {
                    orderMap = new OrderMap();
                }
            }
        }
        return orderMap;
    }

    public void put(String name, Integer order) {
        try {
            map.put(name, order);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int incr(String name) {
        if (map.get(name) == null) {
            map.put(name, 0);
            return 0;
        } else {
            map.put(name, map.get(name) + 1);
        }

        return map.get(name);
    }

    public void remove(String name) {
        map.remove(name);
    }

}
