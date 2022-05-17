package com.viola.backend.voilabackend.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

import javax.xml.bind.DatatypeConverter;

import org.springframework.security.crypto.password.PasswordEncoder;

public class VoilaPasswordEncoder implements PasswordEncoder {
 
    @Override
    public String encode(CharSequence rawPassword) {
        //'Voila-Cardvisit>' . substr(md5(sha1(crc32($password))), 3, 8);
        String crc32String = crc32(rawPassword.toString());
        String sha1String = sha1(crc32String);
        String md5String = md5(sha1String);
        String encodedPassword = "Voila-Cardvisit>" + md5String.toLowerCase().substring(3, 11);
        return encodedPassword;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }

    public String crc32(String string) {
        Checksum crc32 = new CRC32();
        crc32.update(string.toString().getBytes(), 0, string.length());
        return crc32.getValue() + "";
    }

    public String sha1(String string) {
        MessageDigest crypt;
        String sha1String = "";
        try {
            crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string.getBytes("UTF-8"));
            sha1String = DatatypeConverter.printHexBinary(crypt.digest());
        } catch(Exception e) {
            System.out.println(e.toString());
        }
        return sha1String;
    }

    public String md5(String string) {
        String md5String = "";
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(string.toLowerCase().getBytes());
            byte[] digest = md.digest();
            md5String = DatatypeConverter.printHexBinary(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5String;
    }
}