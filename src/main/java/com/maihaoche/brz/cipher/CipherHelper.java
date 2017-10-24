package com.maihaoche.brz.cipher;

/**
 * Created by alex on 2017/10/22.
 */
public interface CipherHelper {

    byte[] encrypt(byte[] plaintext);

    byte[] decrypt(byte[] ciphertext);

    byte[] sign(byte[] data);

    boolean verify(byte[] data, byte[] signature);
}
