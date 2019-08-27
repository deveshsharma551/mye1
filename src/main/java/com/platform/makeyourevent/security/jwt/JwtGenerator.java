package com.platform.makeyourevent.security.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Component;

@Component
public class JwtGenerator {


    public String generate(JwtUser jwtUser) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, CertificateException, KeyStoreException, UnrecoverableKeyException {


        /*Claims claims = Jwts.claims()
                .setSubject(jwtUser.getUserName());
        claims.put("userId", String.valueOf(jwtUser.getId()));
        claims.put("role", jwtUser.getRole());


        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "youtube")
                .compact();*/
    /*	SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    	long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        String filename = "D://workspace/mye1/src/main/resources/mytest.jks";
        File f = new File(filename);
        FileInputStream fis = new FileInputStream(f);
        DataInputStream dis = new DataInputStream(fis);
        byte[] keyBytes = new byte[(int)f.length()];
        dis.readFully(keyBytes);
        dis.close();
        PKCS8EncodedKeySpec spec1 = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        RSAPrivateKey  rsaPrivateKey = (RSAPrivateKey) kf.generatePrivate(spec1);*/
    	Claims claims = Jwts.claims()
                .setSubject(jwtUser.getUserName());
        claims.put("userId", String.valueOf(jwtUser.getId()));
        claims.put("role", jwtUser.getRole());
        String jksPassword = "mypass";
        KeyStore ks  = KeyStore.getInstance(KeyStore.getDefaultType());
        ks.load(new FileInputStream("D://workspace/mye1/src/main/resources/mytest.jks"), jksPassword.toCharArray());
        Key key = ks.getKey("mytest", jksPassword.toCharArray());
        String jwt =  Jwts.builder().setClaims(claims)
        .signWith(SignatureAlgorithm.RS512, key)
        .compact();
        System.out.println(jwt);
    	return jwt;
    }
}
