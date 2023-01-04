package co.com.connection.security.seguridad;


import co.com.connection.security.exceptions.BlogAppException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationInMs;

    public String generarToken(Authentication authentication) {
        String username = authentication.getName();
        Date fechaActual = new Date();
        Date fechaExpiracion = new Date(fechaActual.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(fechaExpiracion)
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }

    public String obtenerUsernameDelJWT(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtSecret.getBytes(StandardCharsets.UTF_8))
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validarToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(jwtSecret.getBytes(StandardCharsets.UTF_8)).build().parseClaimsJws(token);
            return true;
        }catch (SecurityException ex) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST,"Firma JWT no valida");
        }
        catch (MalformedJwtException ex) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST,"Token JWT no valida");
        }
        catch (ExpiredJwtException ex) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST,"Token JWT caducado");
        }
        catch (UnsupportedJwtException ex) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST,"Token JWT no compatible");
        }
        catch (IllegalArgumentException ex) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST,"La cadena claims JWT esta vacia");
        }
    }
}
