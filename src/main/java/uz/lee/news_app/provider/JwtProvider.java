package uz.lee.news_app.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import uz.lee.news_app.roles.Roles;
import uz.lee.news_app.user.Users;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider {

    @Value("${jwt.token.expiration.mills}")
    private Long expiration;

    @Value("${jwt.token.secret.key}")
    private String secretKey;

    private SecretKey key;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String generateToken(Users users) {
        Roles roles = users.getRoles();
        String roleName = roles != null ? roles.getName() : "";

        return Jwts.builder()
                .setSubject(users.getUsername())
                .setIssuedAt(new Date())
                .claim("id", users.getId())
                .claim("email", users.getEmail())
                .claim("username", users.getUsername())
                .claim("role", roleName)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            final Claims claims = parseClaims(token);
            return !claims.getExpiration().before(new Date());
        }catch (Exception e) {
            return false;
        }
    }

    public Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }
}
