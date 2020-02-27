package com;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class Config {

    public static Map<String, String> account = new HashMap<>();
    static {
        account.put("caopeng", "123");

    }

}
