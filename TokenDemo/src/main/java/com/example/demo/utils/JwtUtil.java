package com.example.demo.utils;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtUtil {
    private static final long EXPIER_TIME = 15 * 60 * 1000;
    private static final String TOKEN_SECRET = "123456";
 
    public static String sign(String userName, String userId,String roleName,String[] listCode) {
        Date data = new Date(System.currentTimeMillis() + EXPIER_TIME);
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            Map<String, Object> header = new HashMap<String, Object>(2);
            header.put("typ","JWT");
            header.put("alg","HS256");
            return JWT.create()
                    .withHeader(header)
                    .withClaim("userId",userId)
                    .withClaim("userName",userName)
                    .withClaim("roleName",roleName)
                    .withAudience(listCode)
                    .withExpiresAt(data).sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
 
 
 
 
 
    public static String verify(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return "认证通过";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "认证失败";
        } catch (TokenExpiredException e) {
            e.printStackTrace();
            return "token已失效，请重新登录";
        } catch (Exception e) {
            e.printStackTrace();
            return "认证失败";
        }
    }
 
 
 
 
    public static String getUserName(String token){
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("userName").asString();
    }
 
 
 
    public static String getUserId(String token){
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("userId").asString();
    }
 
 
 
    public static String getroleName(String token){
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("roleName").asString();
    }
 
 
    public static List<String> getlistCode(String token){
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getAudience();
    }
}