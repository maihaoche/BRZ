package com.maihaoche.brz.utils;

import java.util.UUID;

/**
 * Created by alex on 2017/10/27.
 */
public class NonceUtils {
    public static String nonce() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
}
