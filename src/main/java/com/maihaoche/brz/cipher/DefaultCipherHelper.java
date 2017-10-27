package com.maihaoche.brz.cipher;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 * Created by alex on 2017/10/22.
 */
public class DefaultCipherHelper implements CipherHelper {

    private final String ALGORITHM = "RSA";
    private final String SIGN_ALGORITHMS = "SHA256WithRSA";

    private final String platformPublicKey;
    private final String corpPrivateKey;

    public DefaultCipherHelper(String platformPublicKey, String corpPrivateKey) {
        this.platformPublicKey = platformPublicKey;
        this.corpPrivateKey = corpPrivateKey;
    }

    public byte[] encrypt(byte[] plaintext) {
        try {
            // 使用默认RSA
            RSAPublicKey platformPublicKey = createPublicKey(this.platformPublicKey);
            Cipher cipher = Cipher.getInstance("RSA/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, platformPublicKey);
            return cipher.doFinal(plaintext);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("无此加密算法");
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException("padding算法不存在");
        } catch (InvalidKeyException e) {
            throw new RuntimeException("加密公钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException("明文长度非法");
        } catch (BadPaddingException e) {
            throw new RuntimeException("明文数据已损坏");
        }
    }

    public byte[] decrypt(byte[] ciphertext) {
        try {
            // 使用默认RSA
            RSAPrivateKey corpPrivateKey = createPrivateKey(this.corpPrivateKey);
            Cipher cipher = Cipher.getInstance("RSA/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, corpPrivateKey);
            return cipher.doFinal(ciphertext);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("无此加密算法");
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException("padding算法不存在");
        } catch (InvalidKeyException e) {
            throw new RuntimeException("加密公钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException("明文长度非法");
        } catch (BadPaddingException e) {
            throw new RuntimeException("明文数据已损坏");
        }
    }

    public byte[] sign(byte[] data) {
        try {
            PrivateKey corpPrivateKey = createPrivateKey(this.corpPrivateKey);
            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
            signature.initSign(corpPrivateKey);
            signature.update(data);
            return signature.sign();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("无此加密算法");
        } catch (InvalidKeyException e) {
            throw new RuntimeException("加密公钥非法,请检查");
        } catch (SignatureException e) {
            throw new RuntimeException("签名异常");
        }
    }

    public boolean verify(byte[] data, byte[] signature) {
        try {
            PublicKey platformPublicKey = createPublicKey(this.platformPublicKey);
            Signature instance = Signature.getInstance(SIGN_ALGORITHMS);
            instance.initVerify(platformPublicKey);
            instance.update(data);
            return instance.verify(data);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("无此加密算法");
        } catch (InvalidKeyException e) {
            throw new RuntimeException("加密公钥非法,请检查");
        } catch (SignatureException e) {
            throw new RuntimeException("签名异常");
        }
    }


    private RSAPublicKey createPublicKey(String puk) {
        try {
            byte[] buffer = Base64.decodeBase64(puk);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException("公钥非法");
        } catch (NullPointerException e) {
            throw new RuntimeException("公钥数据为空");
        }
    }

    private RSAPrivateKey createPrivateKey(String prk) {
        try {
            byte[] buffer = Base64.decodeBase64(prk);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException("私钥非法");
        } catch (NullPointerException e) {
            throw new RuntimeException("私钥数据为空");
        }
    }
}
