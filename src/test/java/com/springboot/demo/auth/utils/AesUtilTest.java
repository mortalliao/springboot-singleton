package com.springboot.demo.auth.utils;

/**
 * @author Jim
 */
class AesUtilTest {

    public static void main(String[] args) {
        String[] keys = {
                "", "123456", "word"
        };
        System.out.println("key | AESEncode | AESDecode");
        for (String key : keys) {
            System.out.print(key + " | ");
            String encryptString = AesUtil.aesEncode(key);
            System.out.print(encryptString + " | ");
            String decryptString = AesUtil.aesDecode(encryptString);
            System.out.println(decryptString);
        }
    }
}