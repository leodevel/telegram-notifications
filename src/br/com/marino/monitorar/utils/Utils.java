package br.com.marino.monitorar.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {

    public static String toMD5(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            BigInteger hash = new BigInteger(1, md.digest(password.getBytes()));
            String sen = hash.toString(16);
            return sen;
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }
    }
    
    public static boolean isNullOrEmpty(Object ...values){
        
        for(Object value : values){
            
            if (value == null){
                return true;
            }
            
            if (value instanceof String){
                if (value.toString().trim().isEmpty()){
                    return true;
                }
            }            
            
        }
        
        return false;
    }

}
