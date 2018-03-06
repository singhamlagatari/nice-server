package com.sl.nice.utils;

import com.google.gson.Gson;
import org.kisa.*;

import java.nio.charset.StandardCharsets;

public class Crypto {

    private static final byte[] key = {0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x10};
    private static final byte[] iv = {0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01};
    private SEEDCBC seed;

    public Crypto() {
        System.loadLibrary("KISACrypto");
        seed = new SEEDCBC();
    }

    public byte[] encrypt(String plainText) {
        byte[] input = plainText.getBytes();
        int size = seed.getOutputSize(input.length);
        byte[] output = new byte[size + 16];
        int result = seed.CBC_ENCRYPT(key, iv, input, 0, input.length, output, 0);
        if (result > 0) return output;
        return null;
    }

    public <T> T decrypt(byte[] cipherText,Class<T> type){
        int size = seed.getOutputSize(cipherText.length);
        byte[] output = new byte[size];
        int result = seed.CBC_DECRYPT(key,iv,cipherText,0,cipherText.length,output,0);
        if (result > 0){
            String s = new String(output,StandardCharsets.UTF_8);
            String trim =s.replaceAll("[^\\p{Print}]","");
            return new Gson().fromJson(trim,type);
        }
        return null;
    }



}
