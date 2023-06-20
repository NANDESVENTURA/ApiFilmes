package Filmex.api.utils;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtils {

    public String crypt(String value) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(value.getBytes());
        byte[] bytes = md.digest();
        return DatatypeConverter.printHexBinary(bytes).toLowerCase();
    }
}
