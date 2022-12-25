package com.tedu.java;

import io.jsonwebtoken.*;
import org.junit.Test;

import java.util.Date;
import java.util.UUID;

/**
 * @author： zyy
 * @date： 2022/12/16 20:01
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
public class JwtTest {
private static long TOKENEXPIRATION = 1000*60*60*24;
private static String TOKENSIGNKEY = "zyy123";
    @Test
    public void testCreatedToken(){
        JwtBuilder builder = Jwts.builder();
        //头、载荷、签名哈希
        String jwtToken = builder
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("typ", "JWT")
                //载荷：自定义信息
                .claim("nickname", "zyy")
                .claim("avatar", "1.jpg")
                .claim("role", "admin")
                //载荷：默认信息
                .setSubject("srb-user") //令牌主题
                .setIssuer("zyy") //jwt签发者
                .setAudience("zyj") //接收者
                .setIssuedAt(new Date()) //令牌的签发时间
                .setExpiration(new Date(System.currentTimeMillis() + TOKENEXPIRATION)) //过期时间
                .setNotBefore(new Date(System.currentTimeMillis() + 1000 * 20)) //在当前时间的20秒之后生效
                .setId(UUID.randomUUID().toString()) //唯一标识
                //签名哈希
                .signWith(SignatureAlgorithm.HS256, TOKENSIGNKEY)
                //组装JWT
                .compact();
        System.out.println(jwtToken);
    }
    @Test
    public void testGetUserInfo(){
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuaWNrbmFtZSI6Inp5eSIsImF2YXRhciI6IjEuanBnIiwicm9sZSI6ImFkbWluIiwic3ViIjoic3JiLXVzZXIiLCJpc3MiOiJ6eXkiLCJhdWQiOiJ6eWoiLCJpYXQiOjE2NzExOTM1MDksImV4cCI6MTY3MTI3OTkwOSwibmJmIjoxNjcxMTkzNTI5LCJqdGkiOiI2Y2MzYWI4YS0zYThmLTRhNmEtODBhYS01NzA0NTM4MDdjNDIifQ.9q6G7JentMHL9oUN3ggr4aN3DwFkHuD40_PCIhGg1ZI";
        JwtParser jwtParser = Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey(TOKENSIGNKEY).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        String nickname = (String) claims.get("nickname");
        String avatar = (String) claims.get("avatar");
        String role = (String) claims.get("role");
        String id = claims.getId();
        System.out.println(nickname);
        System.out.println(avatar);
        System.out.println(role);
        System.out.println(id);
    }
}
