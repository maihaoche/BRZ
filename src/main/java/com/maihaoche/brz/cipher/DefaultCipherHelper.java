package com.maihaoche.brz.cipher;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.ByteBuffer;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by alex on 2017/10/22.
 */
public class DefaultCipherHelper implements CipherHelper {

    private final String ALGORITHM = "RSA";
    private final String CIPHER_ALGORITHM = "RSA/ECB/PKCS1Padding";
    private final String SIGN_ALGORITHMS = "SHA1WithRSA";

    private final String publicKey;
    private final String privateKey;

    public DefaultCipherHelper(String publicKey, String privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public byte[] encrypt(byte[] plaintext) {
        try {
            // 使用默认RSA
            RSAPublicKey publicKey = createPublicKey(this.publicKey);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            int bitLengthOfPuk = publicKey.getModulus().bitLength() / 8;
            int blockSize = bitLengthOfPuk - 11;
            if (plaintext.length > blockSize) {
                List<byte[]> blocks = block(plaintext, blockSize);
                ByteBuffer buffer = ByteBuffer.allocate(blocks.size() * bitLengthOfPuk);
                for (byte[] block : blocks) {
                    buffer.put(cipher.doFinal(block));
                }
                return buffer.array();
            } else {
                return cipher.doFinal(plaintext);
            }

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
            RSAPrivateKey privateKey = createPrivateKey(this.privateKey);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            int blockSize = privateKey.getModulus().bitLength() / 8;
            if (ciphertext.length > blockSize) {
                List<byte[]> blocks = block(ciphertext, blockSize);
                ByteBuffer buffer = ByteBuffer.allocate(blocks.size() * blockSize);
                for (byte[] block : blocks) {
                    buffer.put(cipher.doFinal(block));
                }

                buffer.flip();
                byte[] result = new byte[buffer.limit()];
                buffer.get(result);
                return result;
            } else {
                return cipher.doFinal(ciphertext);
            }
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


    public byte[] sign(byte[] data, byte[] nonce) {
        try {
            PrivateKey privateKey = createPrivateKey(this.privateKey);
            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
            signature.initSign(privateKey);

            byte[] withSalt = concat(data, nonce);

            signature.update(withSalt);
            return signature.sign();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("无此加密算法");
        } catch (InvalidKeyException e) {
            throw new RuntimeException("加密公钥非法,请检查");
        } catch (SignatureException e) {
            throw new RuntimeException("签名异常");
        }
    }

    public boolean verify(byte[] data, byte[] nonce, byte[] signature) {
        try {
            PublicKey publicKey = createPublicKey(this.publicKey);
            Signature instance = Signature.getInstance(SIGN_ALGORITHMS);
            instance.initVerify(publicKey);
            byte[] withSalt = concat(data, nonce);
            instance.update(withSalt);
            return instance.verify(signature);
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
            KeySpec keySpec = new X509EncodedKeySpec(buffer);
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
            KeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
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

    private byte[] concat(byte[] b1, byte[] b2) {
        byte[] withSalt = new byte[b1.length + b2.length];
        System.arraycopy(b1, 0, withSalt, 0, b1.length);
        System.arraycopy(b2, 0, withSalt, b1.length, b2.length);
        return withSalt;
    }

    private List<byte[]> block(byte[] src, int blockSize) {
        int group;
        if (src.length % blockSize == 0) {
            group = src.length / blockSize;
        } else {
            group = src.length / blockSize + 1;
        }

        List<byte[]> blocks = new ArrayList<byte[]>();
        for (int i = 0; i < group; i++) {
            int from = i * blockSize;
            int to = Math.min(src.length, (i + 1) * blockSize);

            byte[] block = Arrays.copyOfRange(src, from, to);

            blocks.add(block);
        }
        return blocks;
    }

}
