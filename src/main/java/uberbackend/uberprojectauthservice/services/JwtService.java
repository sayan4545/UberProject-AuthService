package uberbackend.uberprojectauthservice.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    @Value("${jwt.expiry}")
    private int expiry;

    @Value("${jwt.secret}")
    private  String SECRET;

    /**
     *
     * A methoid that creates a jwt token for us
     * @return
     */
    private String createToken(Map<String, Object> payload,String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiry*1000l);
        return Jwts.builder()
                .setClaims(payload)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expiryDate)
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS256,SECRET)
                .compact();

    }
}
