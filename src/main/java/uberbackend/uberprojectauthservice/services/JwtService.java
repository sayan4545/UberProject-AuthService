package uberbackend.uberprojectauthservice.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService implements CommandLineRunner {

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

    @Override
    public void run(String... args) throws Exception {
        Map<String, Object> mp = new HashMap<>();
        mp.put("username","sayan");
        mp.put("email","sayan@gmail.com");
        mp.put("password","1234");
        String result = createToken(mp,"SYC");
        System.out.println("created token is : "+ result);
    }
}
