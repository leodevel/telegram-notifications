package br.com.marino.monitorar.utils;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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
    
    public static void createFileCofig(String filename, String text) {
        File fileConfig = Paths.get(filename).toFile();

        if (!fileConfig.getParentFile().exists()) {
            fileConfig.getParentFile().mkdirs();
        }

        if (!fileConfig.exists()) {
            try {
                fileConfig.createNewFile();
                Files.write(fileConfig.toPath(), text.getBytes(),
                        StandardOpenOption.WRITE);
            } catch (IOException ex1) {
            }
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
