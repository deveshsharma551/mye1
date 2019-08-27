package com.platform.makeyourevent.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.spec.X509EncodedKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {


    private String secret = "youtube";

    public JwtUser validate(String token) {

        JwtUser jwtUser = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(loadPublicKey("D://workspace/mye1/src/main/resources/public.cer"))
                    .parseClaimsJws(token)
                    .getBody();

            jwtUser = new JwtUser();

            jwtUser.setUserName(body.getSubject());
            jwtUser.setId(Long.parseLong((String) body.get("userId")));
            jwtUser.setRole((String) body.get("role"));
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return jwtUser;
    }
    
	public  PublicKey loadPublicKey(String filename) throws Exception {
		/*CertificateFactory cf = CertificateFactory.getInstance("X.509");
		Certificate cert = cf
				.generateCertificate(new FileInputStream(filename));
		PublicKey retVal = cert.getPublicKey();
		return retVal;*/
		//byte[] keyBytes = Files.readAllBytes(Paths.get(filename));
		byte[] keyBytes = Base64.decodeBase64("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkdE/ZWEDww0KREFAglww3A2GlaWtkxmQAGzwu7Y54TVUBgjO1s0LE09Xz5E4X/sRp6KmSmfnCMdkVCUhBjQw970b0xQzOABbEdflkCRfeWyOKuupd/Dy5ZODi0QTWMup0a9CUPnwRiRF/HV1+peU0/EDb/YCzQsh9Q3tef7IH2zqdhi7LWHStuoT5clakzxirBu9nOeYKT1+2I0B5eR479B/b1lM7te4fz+iiKs3X1d0VEVw9SN4H/Ww8SuDRSOsIWMQd9BABZDlNAss9XxG+xmKjQb22ATGLxT9TW71DOdLZ98xZ/fHCuURQ3WEG0+3KV4BJbLvrFSL1kJFjCBHYQIDAQAB");
	    X509EncodedKeySpec spec =
	      new X509EncodedKeySpec(keyBytes);
	    KeyFactory kf = KeyFactory.getInstance("RSA");
	    return kf.generatePublic(spec);
	}
}
