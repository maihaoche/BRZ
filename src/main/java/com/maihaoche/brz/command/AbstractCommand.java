package com.maihaoche.brz.command;

import com.maihaoche.brz.utils.NonceUtils;

/**
 * Created by alex on 2017/10/27.
 */
public abstract class AbstractCommand {
    private final String nonce = NonceUtils.nonce();

    public String getNonce() {
        return nonce;
    }
}
