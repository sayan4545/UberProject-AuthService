package uberbackend.uberprojectauthservice.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

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
    private String createToken(Map<String, Object> payload,String email) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiry*1000l);
        SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setClaims(payload)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expiryDate)
                .setSubject(email)
                .signWith(getSignInKey())
                .compact();

    }
    private Claims extractAllPayloads(String token){
//        SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
//        final  claims = Jwts.parser().decryptWith(key).build().parseSignedClaims(token).getPayload();
        return Jwts
                .parser()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();


    }
    private Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }

    public <T> T extractClaim(String token , Function<Claims,T> resolverFunction){
        final Claims claims = extractAllPayloads(token);
        return resolverFunction.apply(claims);
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());

    }
    private Key getSignInKey(){
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }
    private String extractEmail(String token){
        return extractClaim(token,Claims::getSubject);
    }
    private String extractPhoneNumber(String token){
        Claims claims = extractAllPayloads(token);
        String number = claims.get("phoneNumber",String.class);
        return number;
    }
    private Boolean validateToken(String token,String email){
        String emailFetchedFromToken = extractEmail(token);
        return (emailFetchedFromToken.equals(email)&& !isTokenExpired(token));
    }

    @Override
    public void run(String... args) throws Exception {
        Map<String, Object> mp = new HashMap<>();
        mp.put("username","sayan");
        mp.put("email","sayan@gmail.com");
        mp.put("password","1234");
        mp.put("phoneNumber","9748346904");
        String result = createToken(mp,"SYC");
        System.out.println("created token is : "+ result);
        System.out.println("Phone number is : "+ extractPhoneNumber(result));
    }
}
