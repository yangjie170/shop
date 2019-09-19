package com;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class Config {

    public static Map<String, String> account = new HashMap<>();
    static {
        account.put("caopeng", "123");

    }

}
