package com.sumeng.peekshopping.user.oauth;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;
import java.util.Map;

/**
 * @date: 2020/6/20 18:34
 * @author: sumeng
 */


public class CreateJWTTest {


    /**
     * 创建令牌测试
     */
    @Test
    public void testCreateToken(){



        //证书文件路径
        String key_location = "changgou.jks";

        //秘钥库密码
        String key_password = "changgou";

        //秘钥密码
        String keyword = "changgou";

        //秘钥别名
        String alias = "changgou";

        //访问证书路径
        ClassPathResource resource = new ClassPathResource(key_location);

        //创建秘钥工厂
        KeyStoreKeyFactory keyFactory = new KeyStoreKeyFactory(resource, key_password.toCharArray());

        //读取秘钥队（公钥、私钥）
        KeyPair keyPair = keyFactory.getKeyPair(alias, keyword.toCharArray());

        //获取私钥
        RSAPrivateKey rsaPrivate = (RSAPrivateKey) keyPair.getPrivate();

        //生成jwt令牌
        Map<String, String> map = new HashMap<>();
        map.put("company", "sumeng");
        map.put("address", "cq");

        Jwt jwt = JwtHelper.encode(JSON.toJSONString(map), new RsaSigner(rsaPrivate));

        //取出令牌
        String jwtEncoded = jwt.getEncoded();
        System.out.println(jwtEncoded);


    }


}
