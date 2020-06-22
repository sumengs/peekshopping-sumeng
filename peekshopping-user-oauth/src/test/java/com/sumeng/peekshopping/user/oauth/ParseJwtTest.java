package com.sumeng.peekshopping.user.oauth;

import org.junit.Test;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;

/**
 * @date: 2020/6/20 18:59
 * @author: sumeng
 */
public class ParseJwtTest {


    /**
     * 基于公钥解析jwt
     */
    @Test
    public void ParseJwt() {

        String jwt = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJhcHAiXSwibmFtZSI6bnVsbCwiaWQiOm51bGwsImV4cCI6MTU5MjgzODYxMywiYXV0aG9yaXRpZXMiOlsic2Vja2lsbF9saXN0IiwiZ29vZHNfbGlzdCJdLCJqdGkiOiJkODUzOGIwYS1kOTE2LTQ2ZmUtYjE5Yy1mMGQ2YTgxYTg1NzQiLCJjbGllbnRfaWQiOiJjaGFuZ2dvdSIsInVzZXJuYW1lIjoiaGVpbWEifQ.KqH1FoHCNDUBzsRQnUEuP82duTW8rVrUxEKtFa-yhiPs3InplE2rnhgoFicCXJ5zM_-ltNK6aKwqarukm_8ZqWCAcL3aBqCwtit2z8RvW9IqmOb1QjlVYX9VAzVr0BsjUY1yJdF4jSbmQh5p-mDaDIH5R8ckzmqMXaJKv6Tp_1Lu4738UZ4QJTqhxHdLmLXYS0Oih4TzzvWmpjXvIkUrLX94EjrpH9W0wWDVFOs1tMI4lMgKpCtGpV0Vpwqc_WFOXgb33O5JBQqPaf0KE_De6H0xXusYfallVnAIr2uH0s8nw35Fdm8ocrPKlui8n4Q6_RFoGGzQF28lzFNC0pcdJA";

        String publicKey = "-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvFsEiaLvij9C1Mz+oyAmt47whAaRkRu/8kePM+X8760UGU0RMwGti6Z9y3LQ0RvK6I0brXmbGB/RsN38PVnhcP8ZfxGUH26kX0RK+tlrxcrG+HkPYOH4XPAL8Q1lu1n9x3tLcIPxq8ZZtuIyKYEmoLKyMsvTviG5flTpDprT25unWgE4md1kthRWXOnfWHATVY7Y/r4obiOL1mS5bEa/iNKotQNnvIAKtjBM4RlIDWMa6dmz+lHtLtqDD2LF1qwoiSIHI75LQZ/CNYaHCfZSxtOydpNKq8eb1/PGiLNolD4La2zf0/1dlcr5mkesV570NxRmU1tFm8Zd3MZlZmyv9QIDAQAB-----END PUBLIC KEY-----";

        Jwt token = JwtHelper.decodeAndVerify(jwt, new RsaVerifier(publicKey));
        String claims = token.getClaims();

        System.out.println(claims);
    }


}
