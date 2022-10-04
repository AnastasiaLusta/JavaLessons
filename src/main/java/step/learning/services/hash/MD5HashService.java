package step.learning.services.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5HashService implements HashService {

    @Override
    public String hash(String data){
        try{
            var md = MessageDigest.getInstance("MD5");
            md.update(data.getBytes());
            var hash = md.digest();
            var sb = new StringBuilder();
            for (byte b : hash){
                sb.append(Integer.toHexString(b & 0xFF));
            }
            return sb.toString();
        }catch(NoSuchAlgorithmException ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
