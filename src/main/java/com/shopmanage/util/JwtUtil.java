package com.shopmanage.util;

import com.mysql.cj.util.StringUtils;
import com.shopmanage.entity.UserBean;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {

    public static  final String SUBJECT="shopmanage";
    public static  final long EXPIRE=1000*60*60*24*7;
    public static  final String APPSECRET="X666";
    public static  String geneJsonWebToken(UserBean userBean){
        String token=Jwts.builder().setSubject(SUBJECT)
                .claim("uid",userBean.getUid())
                .claim("name",userBean.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRE))
                .signWith(SignatureAlgorithm.HS256,APPSECRET)
                .compact();
        return  token;

    }
    public static Claims checkJWT(String token){
        try{
            final Claims claims= Jwts.parser().setSigningKey(APPSECRET).parseClaimsJws(token).getBody();
            return claims;
        }catch(Exception e){

        }
           return null;
    }
}
