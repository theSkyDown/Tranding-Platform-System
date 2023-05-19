package com.eccentric.tranding;

import com.eccentric.tranding.utils.Md5Util;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TrandingPlatformApplicationTests {

    @Test
    void contextLoads() throws Exception {

        System.out.println(Md5Util.getMD5("123"));
    }

}
