package com.heima;

import com.heima.utils.common.JwtUtils;
import com.heima.utils.common.RsaUtils;
import org.junit.Test;

/**
 * @author zhangqin
 * @Date: 2023/3/26 - 03 - 26 - 12:30
 * @Description: com.heima
 * @version: 1.0
 */
public class JWTTEST {
    String publicKeyPath = "C:\\Users\\admin\\Desktop\\heima-Headlines\\heima-leadnews\\heima-leadnews-service\\heima-leadnews-user\\src\\main\\resources\\rsa-key.pub";
    String privateKeyPath = "C:\\Users\\admin\\Desktop\\heima-Headlines\\heima-leadnews\\heima-leadnews-service\\heima-leadnews-user\\src\\main\\resources\\rsa-key";

    @Test
    public void test()throws Exception{
        RsaUtils.generateKey(publicKeyPath,privateKeyPath,"heima",2048);
    }
}
