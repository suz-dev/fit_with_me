package com.ssafit;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;

public class JWTTest {

    public static void main(String[] args) throws UnsupportedEncodingException, InterruptedException {

        // 토큰 발급
        String token = Jwts.builder().setHeaderParam("alg", "HS256").setHeaderParam("type", "JWT")
                .claim("userId", "SSAFY").setExpiration(new Date(System.currentTimeMillis() + 3000))
                .signWith(SignatureAlgorithm.HS256, "SSAFIT".getBytes("UTF-8")).compact();

        System.out.println(token);

        // 내용 보기
        System.out.println(new String(Base64.getDecoder().decode(token.split("\\.")[1])));

        // 시스템 죽이기 (테스트용)
        Thread.sleep(4000);

        // 유효성 검사
        Jws<Claims> claims = Jwts.parser().setSigningKey("SSAFIT".getBytes("UTF-8")).parseClaimsJws(token);
        System.out.println(claims);

    }

}
