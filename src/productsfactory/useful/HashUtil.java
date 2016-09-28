/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package productsfactory.useful;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Daniel
 */
public class HashUtil {
    
    
    public static String stringToMD5(String frase) throws NoSuchAlgorithmException{
        
        MessageDigest md = MessageDigest.getInstance("MD5");  
  
        BigInteger hash = new BigInteger(1, md.digest(frase.getBytes()));

        return hash.toString();
    }
    
}
