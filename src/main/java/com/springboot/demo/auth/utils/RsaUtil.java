package com.springboot.demo.auth.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * 非对称加密算法 RSA
 * 使用RSA明文长度不得超过秘钥长度否则会抛诸如
 * javax.crypto.IllegalBlockSizeException: Data must not be longer than xxxx bytes 的异常)。
 *
 * @author from internet
 */
@Slf4j
public class RsaUtil {

    private static final String RSA_ALGORITHM = "RSA";
    private static final int KEY_SIZE = 1024;
    private static KeyPairGenerator keyPairGenerator;

    public static Map<String, String> createKeys() {
        try {
            keyPairGenerator = KeyPairGenerator.getInstance(RSA_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            log.warn(e.getMessage());
        }
        keyPairGenerator.initialize(KEY_SIZE, new SecureRandom());
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        Key publicKey = keyPair.getPublic();
        Key privateKey = keyPair.getPrivate();

        System.out.println(Base64.encodeBase64String(publicKey.getEncoded()));
        String publicKeyStr = Base64.encodeBase64String(publicKey.getEncoded());
        String privateKeyStr = Base64.encodeBase64String(privateKey.getEncoded());

        Map<String, String> map = new HashMap<>(8);
        map.put("publicKey", publicKeyStr);
        map.put("privateKey", privateKeyStr);
        return map;

    }

    public static String rsaDecode(String data, String privateKey) {

        try {
            //解析字符串
            KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8EncodedKeySpec);

            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);
            return new String(cipher.doFinal(Base64.decodeBase64(data)), StandardCharsets.UTF_8);

        } catch (Exception e) {
            log.warn(e.getMessage());
            return null;
        }
    }

    public static String rsaEncode(String data, String publicKey) {
        try {
            //将字符串形式解析成类
            KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);

            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);

            return Base64.encodeBase64URLSafeString(cipher.doFinal(data.getBytes(StandardCharsets.UTF_8)));

        } catch (Exception e) {
            log.warn(e.getMessage());
            return null;
        }
    }

}
