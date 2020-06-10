package com.sumeng.peekshopping.system.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

/**
 * JWT工具类
 *
 * @author: sumeng
 */
public class JwtUtil {

    /**
     * 有效期为 60 * 60 *1000*100  100个小时
     */
    public static final Long JWT_TTL = 360000000L;
    /**
     * 设置秘钥明文
     */
    public static final String JWT_KEY = "sumeng";

    /**
     * 创建token
     *
     * @param id        id
     * @param subject   subject
     * @param ttlMillis ttlMillis
     * @return JWT
     */
    public static String createJWT(String id, String subject, Long ttlMillis) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if (ttlMillis == null) {
            ttlMillis = JwtUtil.JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        SecretKey secretKey = generalKey();

        JwtBuilder builder = Jwts.builder()
                //唯一的ID
                .setId(id)
                // 主题  可以是JSON数据
                .setSubject(subject)
                // 签发者
                .setIssuer("admin")
                // 签发时间
                .setIssuedAt(now)
                //使用HS256对称加密算法签名, 第二个参数为秘钥
                .signWith(signatureAlgorithm, secretKey)
                // 设置过期时间
                .setExpiration(expDate);
        return builder.compact();
    }

    /**
     * 生成加密后的秘钥 secretKey
     *
     * @return SecretKey
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    /**
     * 解析
     *
     * @param jwt jwt
     * @return Claims
     * @throws Exception e
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }
}