package com.project.backend;

import java.io.Serializable; 
import java.util.Date; 
import java.util.HashMap; 
import java.util.Map; 
import org.springframework.beans.factory.annotation.Value; 
import org.springframework.stereotype.Component; 
import io.jsonwebtoken.Claims; 
import io.jsonwebtoken.Jwts; 
import io.jsonwebtoken.SignatureAlgorithm; 
@Component 
public class AuthUtils implements Serializable {
   
   private static final long serialVersionUID = 7008375124389347049L; 
   public static final long TOKEN_VALIDITY = 30; @Value("{secret}") 
   private String jwtSecret; 
   public String generateJwtToken(User user) { 
      Map<String, Object> claims = new HashMap<>(); 
      return Jwts.builder().setClaims(claims).setSubject(String.valueOf(user.getId()))
         .setIssuedAt(new Date(System.currentTimeMillis())) 
         .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000)) 
         .signWith(SignatureAlgorithm.HS512, jwtSecret).compact(); 
   } 
   public Boolean validateJwtToken(String token) { 
	   String id1 = getIdFromToken(token);
	   int id=Integer.parseInt(id1);
      Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
      Boolean isTokenExpired = claims.getExpiration().before(new Date()); 
      return (!isTokenExpired); 
   } 
   public String getIdFromToken(String token) {
      final Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody(); 
      return claims.getSubject(); 
   } 
}



































//package com.project.backend;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//@Service
//public class AuthUtils {
//
//    private String SECRET_KEY = "java";
//
//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    public Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//    private Claims extractAllClaims(String token) {
//        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
//    }
//
//    private Boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    public String generateToken(User user) {
//        Map<String, Object> claims = new HashMap<>();
//        return createToken(claims, user.getUsername());
//    }
//
//    private String createToken(Map<String, Object> claims, String subject) {
//
//        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
//                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
//    }
//
//    public Boolean validateToken(String token, User user) {
//        final String username = extractUsername(token);
//        return (username.equals(user.getUsername()) && !isTokenExpired(token));
//    }
//}
